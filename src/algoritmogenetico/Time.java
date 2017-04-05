/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Multimidia
 */
public class Time {
    
    private List<Jogador> time;
    private double total;
    DecimalFormat deci = new DecimalFormat("0.00");

    public Time(List time, double total) {
        this.time = time;
        this.total = total;
    }

    public List getTime() {
        return time;
    }

    public double getTotal() {
        return total;
    }

    public void setTime(List time) {
        this.time = time;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return time + "\nTotal = " + deci.format(total);
    }  
}
