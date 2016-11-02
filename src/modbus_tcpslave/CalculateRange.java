package modbus_tcpslave;

import java.util.Random;

final class CalculateRange {

    private Random rand = null;

    CalculateRange() {

        rand = new Random();
    }

    public int range(int a, int b) {

        if (a < 0) {

            return rand.nextInt(b + Math.abs(a)) - Math.abs(a); //za range od negativne do pozitivne vrijednosti
        } else {

            return rand.nextInt((b - a) + 1) + a;
        }
    }
}
