package modbus_tcpslave;

public class TestTCPSlave {
   /*testiranje veze sa programom Modbus POll
    
	public static void main(String[] args) {

		ModbusTCPListener listener = null;
		SimpleProcessImage spi = null;
		//int port = Modbus.DEFAULT_PORT;
		int port = 1024;
               
                
		try {
			if (args != null && args.length == 1) {
				port = Integer.parseInt(args[0]);
			}
			// 1. prepare a process image
			spi = new SimpleProcessImage();

			spi.addDigitalOut(new SimpleDigitalOut(true));
			spi.addDigitalOut(new SimpleDigitalOut(true));

			spi.addDigitalIn(new SimpleDigitalIn(false));
			spi.addDigitalIn(new SimpleDigitalIn(true));
			spi.addDigitalIn(new SimpleDigitalIn(false));
			spi.addDigitalIn(new SimpleDigitalIn(true));
			// allow checking LSB/MSB order
			spi.addDigitalIn(new SimpleDigitalIn(true));
			spi.addDigitalIn(new SimpleDigitalIn(true));
			spi.addDigitalIn(new SimpleDigitalIn(true));
			spi.addDigitalIn(new SimpleDigitalIn(true));

			spi.addRegister(new SimpleRegister(251));
			spi.addInputRegister(new SimpleInputRegister(45));

			// 2. create the coupler holding the image
			ModbusCoupler.getReference().setProcessImage(spi);
			ModbusCoupler.getReference().setMaster(false);
			ModbusCoupler.getReference().setUnitID(15); //id za spajanje na simulator

			// 3. create a listener with 3 threads in pool
	
			listener = new ModbusTCPListener(3);
			listener.setPort(port);
                        listener.setAddress(InetAddress.getByName("192.168.1.23"));
			
			listener.start();
			
			//listener.stop();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
     */
    
}
