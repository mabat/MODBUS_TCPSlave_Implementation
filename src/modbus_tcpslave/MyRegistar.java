package modbus_tcpslave;

import net.wimpi.modbus.procimg.Register;

public class MyRegistar implements Register {

    private final int addres;  //adresa registra
    private final int[] unit; //unit(special range)
    private final int range1; //range koji odredjuje vrijednost registra, od-do
    private final int range2;
    private byte[] value = new byte[2]; //ako je int onda modbus test krivo cita vrijednosti

    MyRegistar(int adr, int[] u, int r1, int r2) {

        addres = adr;
        unit = u;
        range1 = r1;
        range2 = r2;
    }

    public int getAdress() {
        return addres;
    }

    public int[] getUnit() {
        return unit;
    }

    public int getUnitIndexAt(int n) {
        return unit[n];
    }

    public int getR1() {
        return range1;
    }

    public int getR2() {
        return range2;
    }

    @Override
    public void setValue(int i) {
        setValue((short) i);
    }

    @Override
    public void setValue(short s) {
        if (value == null) {
            value = new byte[2];
        }
        value[0] = (byte) (0xff & (s >> 8));
        value[1] = (byte) (0xff & s);
    }

    @Override
    public void setValue(byte[] bytes) {
        if (bytes.length < 2) {
            throw new IllegalArgumentException();
        } else {
            value[0] = bytes[0];
            value[1] = bytes[1];
        }
    }

    @Override
    public int getValue() {
        return ((value[0] & 0xff) << 8 | (value[1] & 0xff));
    }

    @Override
    public int toUnsignedShort() {
        return ((value[0] & 0xff) << 8 | (value[1] & 0xff));
    }

    @Override
    public short toShort() {
        return (short) ((value[0] << 8) | (value[1] & 0xff));
    }

    @Override
    public byte[] toBytes() {
        return value;
    }
}
