package modbus_tcpslave;

import java.util.Random;

final class CalculateRange {

    private Random rand = null;

    CalculateRange() {

        rand = new Random();
    }

    public int range(MyRegistar reg) {

        if (reg.getUnit() == null) { //znaci da je fake registar
            return 0;
        } else if (reg.getUnit().length > 0) { //znaci ima unit niz
            int index = rand.nextInt(((reg.getUnit().length) - 1) + 1) + 0;
            return reg.getUnitIndexAt(index);

        } else if (reg.getR1() < 0) { //znaci da nema unit niza

            return rand.nextInt(reg.getR2() + Math.abs(reg.getR1())) - Math.abs(reg.getR1()); //za range od negativne do pozitivne vrijednosti
        } else {

            return rand.nextInt((reg.getR2() - reg.getR1()) + 1) + reg.getR1(); //range od nula do neke pozitivne vrijednosti
        }
    }
}
