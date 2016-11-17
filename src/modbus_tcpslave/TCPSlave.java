package modbus_tcpslave;

import java.net.*;
import net.wimpi.modbus.net.ModbusTCPListener;

public class TCPSlave {

    public static void main(String[] args) throws UnknownHostException {

        CreateRegistar CR = new CreateRegistar();
        ModbusTCPListener listener = new ModbusTCPListener(3);
        //int port = Modbus.DEFAULT_PORT;
        listener.setPort(1024);
        listener.setAddress(InetAddress.getByName("192.168.1.23"));
        listener.start();
    }
}
