package modbus_tcpslave;

import java.net.*;
import java.util.Timer;
import net.wimpi.modbus.net.ModbusTCPListener;

public class TCPSlave {

    public static void main(String[] args) throws UnknownHostException {

        Timer timer = new Timer();
        timer.schedule(new Schedule(), 0, 1000);
        ModbusTCPListener listener = new ModbusTCPListener(3);
        listener.setPort(1024);
        listener.setAddress(InetAddress.getByName("192.168.1.23"));
        listener.start();
    }
}
