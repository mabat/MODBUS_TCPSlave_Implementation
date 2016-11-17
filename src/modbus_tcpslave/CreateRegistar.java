package modbus_tcpslave;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.procimg.SimpleProcessImage;

public class CreateRegistar {

    private SimpleProcessImage spi = null;
    private final CalculateRange CR; //za racun random vrijednosti u nekom range-u
    private final List<Registar> registar = new ArrayList<>();
    private final Timer timer;

    CreateRegistar() throws UnknownHostException {

        CR = new CalculateRange(); //pomocna klasa za zacunanje range-a
        spi = new SimpleProcessImage();
        ModbusCoupler.getReference().setProcessImage(spi);
        ModbusCoupler.getReference().setMaster(false);
        ModbusCoupler.getReference().setUnitID(15); //id za spajanje na simulator
        
        //######### dodavanje registara u listu ##########
        
        //fake registers in list
        for (int i = 0; i<771; ++i){
            registar.add(new Registar(i,new int[]{0},0, 0));
        }
        //real registers
        registar.add(new Registar(771, new int[]{0}, 0, 65336));
        registar.add(new Registar(772, new int[]{0}, -32668, 32668));
        registar.add(new Registar(773, new int[]{0}, -32668, 32668));
        registar.add(new Registar(774, new int[]{0, 1, 2, 3}, 0, 65336));
        registar.add(new Registar(775, new int[]{0, 2, 3, 4, 5, 6, 7, 11, 252}, 0, 65336));
        registar.add(new Registar(776, new int[]{0}, 0, 65336));
        registar.add(new Registar(777, new int[]{0}, -32668, 32668));
        registar.add(new Registar(778, new int[]{0, 1, 2, 3}, 0, 65336));
        registar.add(new Registar(779, new int[]{0}, 0, 65336));
        registar.add(new Registar(780, new int[]{0, 1}, 0, 65336));
        registar.add(new Registar(781, new int[]{0, 2}, 0, 65336));
        registar.add(new Registar(782, new int[]{0, 2}, 0, 65336));
        registar.add(new Registar(783, new int[]{0, 2}, 0, 65336));
        registar.add(new Registar(784, new int[]{0}, 0, 65336));
        registar.add(new Registar(785, new int[]{0}, 0, 65336));
        registar.add(new Registar(786, new int[]{0}, 0, 65336));
        registar.add(new Registar(787, new int[]{0}, 0, 65336));
        registar.add(new Registar(788, new int[]{0}, 0, 65336));
        registar.add(new Registar(789, new int[]{0}, 0, 65336));
        registar.add(new Registar(790, new int[]{0, 1}, 0, 65336));

        //######### dodavanje vrijednosti registara ##########

        //fake first 770
        for (int i = 0; i < 771; ++i) {
            spi.addInputRegister(new SimpleInputRegister(0));
        }
        //real values
        for (int j = 771; j < 791; ++j) {
            spi.addInputRegister(new SimpleInputRegister(CR.range(registar.get(j))));
        }
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                changeRegistar();   //call method every second
            }
        },0, 1000);
        //timer.cancel();
    }
    public void changeRegistar() {

        for (int j = 771; j < 791; ++j) {
            spi.setInputRegister(j,new SimpleInputRegister(CR.range(registar.get(j))));
        }
//        System.out.println("test ");
//        System.out.println(spi.getInputRegister(775).getValue());
//        System.out.println("size "+registar.size());
    }
}

