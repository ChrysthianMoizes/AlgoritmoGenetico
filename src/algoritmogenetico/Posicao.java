/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

/**
 *
 * @author 2015122760084
 */
public class Posicao {
    
    private String nomePosicao;
    private double pesoReflexo;
    private double pesoForca;
    private double pesoVelocidade;
    private double pesoHabilidade;

    public Posicao(String nomePosicao, double pesoReflexo, double pesoForca, double pesoVelocidade, double pesoHabilidade) {
        this.nomePosicao = nomePosicao;
        this.pesoReflexo = pesoReflexo;
        this.pesoForca = pesoForca;
        this.pesoVelocidade = pesoVelocidade;
        this.pesoHabilidade = pesoHabilidade;
    }

    public String getNomePosicao() {
        return nomePosicao;
    }

    public double getPesoReflexo() {
        return pesoReflexo;
    }

    public double getPesoForca() {
        return pesoForca;
    }

    public double getPesoVelocidade() {
        return pesoVelocidade;
    }

    public double getPesoHabilidade() {
        return pesoHabilidade;
    }

    public void setNomePosicao(String nomePosicao) {
        this.nomePosicao = nomePosicao;
    }

    public void setPesoReflexo(double pesoReflexo) {
        this.pesoReflexo = pesoReflexo;
    }

    public void setPesoForca(double pesoForca) {
        this.pesoForca = pesoForca;
    }

    public void setPesoVelocidade(double pesoVelocidade) {
        this.pesoVelocidade = pesoVelocidade;
    }

    public void setPesoHabilidade(double pesoHabilidade) {
        this.pesoHabilidade = pesoHabilidade;
    } 

    @Override
    public String toString() {
        return "Posicao{" + "nomePosicao = " + nomePosicao + ", pesoReflexo = " + pesoReflexo + ", pesoForca = " + pesoForca + ", pesoVelocidade = " + pesoVelocidade + ", pesoHabilidade = " + pesoHabilidade + '}';
    }
     
}
