
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.List;

/**
 *
 * @author Chrysthian
 */
public class TimeException extends Exception{
    
    private List<Jogador> pai1;
    private List<Jogador> pai2;

    public TimeException(List<Jogador> pai1, List<Jogador> pai2) {
        this.pai1 = pai1;
        this.pai2 = pai2;
    }

    public List<Jogador> getPai1() {
        return pai1;
    }

    public List<Jogador> getPai2() {
        return pai2;
    }

    
}
