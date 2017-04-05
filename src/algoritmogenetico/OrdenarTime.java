/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Chrysthian
 */
public class OrdenarTime implements Comparator<Time>{

     @Override
    public int compare(Time time1, Time time2) {

        if (time1.getTotal() > time2.getTotal()) {
            return -1;
        } else if(time1.getTotal() < time2.getTotal()){
            return 1;
        }else{
           return 0;     
        }
    } 
}
