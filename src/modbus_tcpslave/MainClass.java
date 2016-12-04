package modbus_tcpslave;

import java.net.*;
import org.json.simple.parser.ParseException;

public class MainClass {

    public static void main(String[] args) throws UnknownHostException, ParseException {

        TCPSlave CR = new TCPSlave("modbus.json"); //naziv JSON datoteke sa registrima i inf. o serveru
    }
}
