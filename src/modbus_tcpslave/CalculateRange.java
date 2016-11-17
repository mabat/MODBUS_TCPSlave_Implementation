package modbus_tcpslave;

import java.util.Random;

final class CalculateRange {

    private Random rand = null;

    CalculateRange() {

        rand = new Random();
    }

    public int range(Registar reg) {
        
        //if registar has unit value than calculete random unit number
        if (reg.getUnit().size() > 1){
           int index = rand.nextInt(((reg.getUnit().size()-1) - 0) + 1) + 0; 
           return reg.getUnitIndexAt(index);
            
        }else if (reg.getR1() < 0) {

            return rand.nextInt(reg.getR2() + Math.abs(reg.getR1())) - Math.abs(reg.getR1()); //za range od negativne do pozitivne vrijednosti
        } else {

            return rand.nextInt((reg.getR2() - reg.getR1()) + 1) + reg.getR1();
        }
    }
}
