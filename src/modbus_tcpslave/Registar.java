package modbus_tcpslave;

import java.util.ArrayList;
import java.util.List;

public class Registar {
    
    private final int addres;  //adress of register
    private final List <Integer> unit = new ArrayList<>(); //unit(special range)
    private final int range1; //values to calculate range of register values
    private final int range2;
    
    Registar (int adr, int []u, int r1, int r2){
        
        addres = adr;
        for (int i= 0; i<u.length;++i){
            unit.add(u[i]);
        }
        range1=r1;
        range2=r2;
    }
    public int getAdress (){
        return addres;
    }
    public List<Integer> getUnit(){ 
        return unit;
    }
    public Integer getUnitIndexAt(int n){ 
        return unit.get(n);
    }
    public int getR1(){
        return range1;
    }
    public int getR2(){
        return range2;
    }
}
