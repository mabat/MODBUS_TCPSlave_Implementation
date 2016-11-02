package modbus_tcpslave;

import java.net.UnknownHostException;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;

public class CreateRegistar {

    private SimpleProcessImage spi = null;
    private final CalculateRange CR; //za racun random vrijednosti u nekom range-u

    CreateRegistar() throws UnknownHostException {
        
        CR = new CalculateRange(); //pomocna klasa za zacunanje range-a
        spi = new SimpleProcessImage();
        ModbusCoupler.getReference().setProcessImage(spi);
        ModbusCoupler.getReference().setMaster(false);
        ModbusCoupler.getReference().setUnitID(15); //id za spajanje na simulator
        
        
                            //######### dodavanje registara ##########
        //0-770 Non-writable
        for (int i = 0; i < 771; ++i) {
            spi.addInputRegister(new SimpleInputRegister(0));
        }
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 771
        spi.addInputRegister(new SimpleInputRegister(CR.range(-32668, 32668))); //Non writ 772
        spi.addInputRegister(new SimpleInputRegister(CR.range(-32668, 32668))); //Non writ 773
        spi.addInputRegister(new SimpleInputRegister(0)); //non writ 774 fake
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 775
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 776
        spi.addInputRegister(new SimpleInputRegister(CR.range(-32668, 32668))); //Non writ 777
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 778
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 779
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 780
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 781
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 782
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 783
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 784
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 785
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 786
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 787
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 788
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 789
        spi.addInputRegister(new SimpleInputRegister(CR.range(0, 65336))); //Non writ 790
        
        //0-773 writable
        for (int i = 0; i < 774; ++i) {
            spi.addRegister(new SimpleRegister(0));
        }
        spi.addRegister(new SimpleRegister(CR.range(0, 65336))); //writable 774  !!!
    }

}
