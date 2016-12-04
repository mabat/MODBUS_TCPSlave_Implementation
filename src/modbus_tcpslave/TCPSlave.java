package modbus_tcpslave;

import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.net.ModbusTCPListener;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TCPSlave {

    private SimpleProcessImage spi = null;
    private final CalculateRange CR; //za racun random vrijednosti u nekom range-u
    private final Timer timer;
    private int countRegisters; //ukupan broj registara, potrebno pamtiti poslije za izmjenu svih

    TCPSlave(String jsonFile) throws UnknownHostException, ParseException {

        CR = new CalculateRange(); //pomocna klasa za za racunanje range-a
        spi = new SimpleProcessImage();

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(jsonFile));
            JSONObject root = (JSONObject) obj; //cijeli dokument u JSONobjektu root
            JSONObject serverData = (JSONObject) root.get("serverData"); //prvi objekt u cijelom dokumentu

            String ip = (String) serverData.get("ip");
            long port = (long) serverData.get("port");
            long unitID = (long) serverData.get("unitID");

            ModbusTCPListener listener = new ModbusTCPListener(3);
            listener.setPort((int) port);
            listener.setAddress(InetAddress.getByName(ip));
            listener.start();
            ModbusCoupler.getReference().setProcessImage(spi);
            ModbusCoupler.getReference().setMaster(false);
            ModbusCoupler.getReference().setUnitID((int) unitID); //id za spajanje na simulator

            long tempAddres = 0; // pomocna varijabla za dodavanje fake registara

            JSONArray arr = (JSONArray) root.get("regs"); //drugi objekt u JSON dokumentu koji je niz objekata, tj. registara
            for (int i = 0; i < arr.size(); ++i) {
                JSONObject reg = (JSONObject) arr.get(i); //svaki element niza je objekt, tj. registar
                long addres = (long) reg.get("addres");
                long rangeA = (long) reg.get("rangeA");
                long rangeB = (long) reg.get("rangeB");

                JSONArray units = (JSONArray) reg.get("unit"); //element objekta koji je niz sa vrijednostima unit
                int[] unit = new int[units.size()];
                for (int j = 0; j < unit.length; ++j) {
                    long temp = (long) units.get(j);
                    unit[j] = (int) temp;
                }

                //fake registers ==> pamti adresu zadnjeg dodanog pa ako je 
                //izmedju fake da ga doda na tu adresu koje nema u JSON-u            
                for (long k = tempAddres; k < addres; ++k) {
                    spi.addRegister(new MyRegistar((int) k, null, 0, 0));
                    countRegisters++;  //ukupan broj registara
                }
                //real registers            
                spi.addRegister(new MyRegistar((int) addres, unit, (int) rangeA, (int) rangeB));
                countRegisters++;
                tempAddres = addres + 1;
            }

            //dodjeljivanje vrijednosti registara
            for (int i = 0; i < countRegisters; ++i) {
                spi.getRegister(i).setValue(CR.range((MyRegistar) spi.getRegister(i)));
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPSlave.class.getName()).log(Level.SEVERE, null, ex);
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeRegistar();   //svako 5 sekundi poziva metodu za izmjenu registra
            }
        }, 0, 5000);
        //timer.cancel();
    }

    public void changeRegistar() {
        //promjena vrijednosti
        for (int i = 0; i < countRegisters; ++i) {
            spi.getRegister(i).setValue(CR.range((MyRegistar) spi.getRegister(i)));
        }
    }
}
