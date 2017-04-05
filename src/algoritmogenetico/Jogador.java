/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 *
 * @author 2015122760084
 */
public class Jogador{
    
    private String nome;
    private int reflexo;
    private int forca;
    private int velocidade;
    private int habilidade;
    DecimalFormat deci = new DecimalFormat("0.00");

    public Jogador(String nome, int reflexo, int forca, int velocidade, int habilidade) {
        this.nome = nome;
        this.reflexo = reflexo;
        this.forca = forca;
        this.velocidade = velocidade;
        this.habilidade = habilidade;
    }

    @Override
    public boolean equals(Object obj) {
        final Jogador other = (Jogador) obj;
        if (Objects.equals(this.nome, other.nome)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    public String getNome() {
        return nome;
    }

    public int getReflexo() {
        return reflexo;
    }

    public int getForca() {
        return forca;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setReflexo(int reflexo) {
        this.reflexo = reflexo;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    @Override
    public String toString() {
        return "\nJogador{" + "nome = " + nome + ", reflexo = " + reflexo + ", forca = " + forca + ", velocidade = " + velocidade + ", habilidade = " + habilidade + "}";
    } 
}
