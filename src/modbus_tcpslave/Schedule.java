package modbus_tcpslave;

import java.net.UnknownHostException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Schedule extends TimerTask{

    Schedule() {
        run();
    }

    @Override
    public final void run() {
        
        try {
            CreateRegistar createRegistar = new CreateRegistar();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
