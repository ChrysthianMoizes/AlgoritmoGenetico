/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author 2015122760084
 */
public class AlgoritmoGenetico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner arquivoJogadores = abrirArquivoJogadores();
        Scanner arquivoPosicoes = abrirArquivoPosicoes();

        List<Time> times = new ArrayList<>();
        List<Jogador> jogadores = preencherJogadores(arquivoJogadores);
        List<Posicao> posicoes = preencherPosicoes(arquivoPosicoes);

        menu(jogadores, posicoes, times);

    }

    public static Scanner abrirArquivoJogadores() {

        try {

            // abrindo arquivo de jogadores
            FileInputStream jogadores = new FileInputStream("jogadores.txt");
            Scanner arquivoJogadores = new Scanner(jogadores);
            System.out.println("Arquivo Jogadores aberto com Sucesso!");

            return arquivoJogadores;

        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo Jogadores não encontrado");
        }
        return null;
    }

    public static Scanner abrirArquivoPosicoes() {

        try {

            // abrindo arquivo de jogadores
            FileInputStream posicoes = new FileInputStream("posicoes.txt");
            Scanner arquivoPosicoes = new Scanner(posicoes);
            System.out.println("Arquivo Posicoes aberto com Sucesso!");

            return arquivoPosicoes;

        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo Posicoes não encontrado");
        }
        return null;
    }

    public static List<Jogador> preencherJogadores(Scanner arquivoJogadores) {

        List<Jogador> jogadores = new ArrayList<>();

        while (arquivoJogadores.hasNext()) {

            String nome = arquivoJogadores.next();
            int reflexo = arquivoJogadores.nextInt();
            int forca = arquivoJogadores.nextInt();
            int velocidade = arquivoJogadores.nextInt();
            int habilidade = arquivoJogadores.nextInt();

            Jogador jogador = new Jogador(nome, reflexo, forca, velocidade, habilidade);
            jogadores.add(jogador);
        }
        arquivoJogadores.close();
        return jogadores;
    }

    public static List<Posicao> preencherPosicoes(Scanner arquivoPosicoes) {

        List<Posicao> posicoes = new ArrayList<>();

        while (arquivoPosicoes.hasNext()) {

            String nomePosicao = arquivoPosicoes.next();
            double pesoReflexo = arquivoPosicoes.nextDouble();
            double pesoForca = arquivoPosicoes.nextDouble();
            double pesoVelocidade = arquivoPosicoes.nextDouble();
            double pesoHabilidade = arquivoPosicoes.nextDouble();

            Posicao posicao = new Posicao(nomePosicao, pesoReflexo, pesoForca, pesoVelocidade, pesoHabilidade);
            posicoes.add(posicao);
        }
        arquivoPosicoes.close();
        return posicoes;
    }

    public static void menu(List<Jogador> jogadores, List<Posicao> posicoes, List<Time> times) {

        int op;
        int tamanhoTime = 0;
        int qtJogadores = 0;
        int populacao = 0;

        do {

            Scanner opcao = new Scanner(System.in);
            System.out.println("=================================================== MENU =============================================================");
            System.out.println("");
            System.out.println("Selecione uma das opções abaixo: ");
            System.out.println("");
            System.out.println("1 - Criar População.");
            System.out.println("2 - Exibir População.");
            System.out.println("3 - Iniciar Processo Evolutivo.");
            System.out.println("4 - Exibir melhor Time.");
            System.out.println("5 - Exibir pior Time.");
            System.out.println("6 - Sair.");
            System.out.println("=======================================================================================================================");
            op = opcao.nextInt();

            switch (op) {

                case 1:
                    System.out.println("Digite o tamanho da população: ");
                    populacao = opcao.nextInt();
                    System.out.println("Digite a quantidade de jogadores por time: ");
                    tamanhoTime = opcao.nextInt();
                    System.out.println("Digite a quantidade de jogadores a serem avaliados: ");
                    qtJogadores = opcao.nextInt();
                    criarPopulacaoInicial(jogadores, posicoes, times, populacao, tamanhoTime, qtJogadores);
                    System.out.println("População criada com Sucesso!");
                    break;

                case 2:
                    System.out.println("Digite a quantidade de times que deseja ver: ");
                    int inicial = opcao.nextInt();
                    exibirPopulacao(times, inicial);
                    break;

                case 3:
                    System.out.println("Digite a quantidade de gerações desejadas: ");
                    int qtEvolucao = opcao.nextInt();
                    evoluirPopulacao(posicoes, times, qtEvolucao, tamanhoTime, populacao);
                    System.out.println("Processo Evolutivo Concluído!");
                    break;

                case 4:
                    exibirTime(times, 0);
                    break;

                case 5:
                    exibirTime(times, times.size() - 1);
                    break;

                case 6:
                    System.out.println("Volte Sempre!");
                    break;

                default:
                    System.out.println("Opção Inválida!");

            }
            limparTela();
        } while (op != 6);

    }

    public static void criarPopulacaoInicial(List<Jogador> jogadores, List<Posicao> posicoes, List times, int populacao, int tamanhoTime, int qtJogadores) {

        Jogador jogador = null;
        Random gerador = new Random();
        double totalTime = 0;
        int posicao = 0;

        for (int i = 0; i < populacao; i++) {
            List<Integer> sorteados = new ArrayList<>();
            List<Jogador> time = new ArrayList<>();
            totalTime = 0;

            for (int j = 0; j < tamanhoTime; j++) {

                int resultado = gerador.nextInt(qtJogadores);

                if (verificarSorteados(sorteados, resultado) == 0) {
                    jogador = jogadores.get(resultado);
                    time.add(jogador);
                } else {
                    j--;
                }
            }
            totalTime = avaliarTime(time, posicoes);
            Time t = new Time(time, totalTime);
            times.add(t);
        }
        ordenarTimes(times);
    }

    public static void evoluirPopulacao(List<Posicao> posicoes, List<Time> times, int qtEvolucao, int tamanhoTime, int populacao) {

        //CRIA – MUTA – AVALIA – ORDENA - CORTA
        Random gerador = new Random();
        int total = 0;
        List intervalos = null;

        for (int geracao = 0; geracao < qtEvolucao; geracao++) {
            //System.out.println("GERACAO: " + geracao);
            total = calcularTotalTimes(times);
            intervalos = gerarIntervalo(times, total);
            
            for (int i = 0; i < (int) (populacao / 2); i++) {

                //System.out.println("POPULACAO: " + i);
                Time filho1 = null;
                Time filho2 = null;
                Time pai1 = null;
                Time pai2 = null;

                int s1 = identificarIntervalo(intervalos, gerador.nextInt((int) total));

                int s2 = identificarIntervalo(intervalos, gerador.nextInt((int) total));
       
                while (s1 == s2) {
                    s2 = identificarIntervalo(intervalos, gerador.nextInt((int) total));
                }

                pai1 = (Time) (times.get(s1));
                pai2 = (Time) (times.get(s2));

                filho1 = criarNovoTime(pai1, pai2, posicoes);
                mutarTime(filho1);
                times.add(filho1);

                filho2 = criarNovoTime(pai2, pai1, posicoes);
                mutarTime(filho2);
                times.add(filho2);
                
            }

            ordenarTimes(times);
            cortarTimes(times, populacao);
            //System.out.println(times.size());
        }
    }

    public static void ordenarTimes(List times) {

        Collections.sort(times, new OrdenarTime());
    }

    public static double avaliarTime(List<Jogador> time, List<Posicao> posicoes) {

        int posicao = 0;
        double totalJogador = 0;
        double totalTime = 0;
        Jogador jogador = null;

        totalJogador = 0;
        totalTime = 0;
            
        for (int j = 0; j < time.size(); j++) {
            jogador = (Jogador) time.get(j);
            posicao = identificarPosicao(j);
            totalJogador = multiplicarAtributos(jogador, posicoes, posicao);
            totalTime = totalTime + totalJogador;
        } 
        return totalTime;
    }

    public static int verificarSorteados(List<Integer> sorteados, int resultado) {

        for (int i = 0; i < sorteados.size(); i++) {
            if (sorteados.get(i) == resultado) {
                return 1;
            }
        }
        sorteados.add(resultado);
        return 0;
    }

    private static int posJogadorSegTime(Jogador jogador, List<Jogador> segmento, int pos_ini, int pos_fim){
        for(int i=pos_ini; i<pos_fim; i++){
            if (jogador.getNome().equals(segmento.get(i).getNome()))
                return i;
        }
        return -1;
    }
    
    private static int buscarJogadorNaoInseridoNovoSegmento(List<Jogador> segmentoFonte, List<Jogador> segmentoVerificacao, int ini_fonte, int fim_fonte, int ini_ver, int fim_ver){
        int pos;
        for(int i=ini_fonte; i<fim_fonte; i++){
            Jogador jogador_fonte = segmentoFonte.get(i);
            pos = posJogadorSegTime(jogador_fonte, segmentoVerificacao, ini_ver, fim_ver);
            if(pos == -1)
                return i;
        }
        // Se acontecer, temos ERRO!!
        return -1;
    }
    
    public static Time criarNovoTime(Time time1, Time time2, List<Posicao> posicoes) {

        List<Jogador> segmentoTime = new ArrayList<>();
        Jogador jogadorPai1 = null;
        Jogador jogadorPai2 = null;
        //Jogador jogadorRepetido = null;
        double totalTime = 0;
        
        List<Jogador> pai1 = time1.getTime();// Crio uma lista de jogadores com nome de pai1
        List<Jogador> pai2 = time2.getTime();// Crio uma lista de jogadores com nome de pai2

        int parte1 = (int) (pai1.size() / 2) + 1;
        
        for (int i = 0; i < parte1; i++) {
            segmentoTime.add(pai1.get(i));
        }

       int cont = parte1;
       int pos;
            
        while(cont < pai2.size()){
            jogadorPai2 = pai2.get(cont);
            pos = posJogadorSegTime(jogadorPai2, segmentoTime, 0, cont);
            // Se não está no segmento de Time então inserir
            if (pos == -1)
                segmentoTime.add(jogadorPai2);
            else{
                jogadorPai1 = pai1.get(cont);
                pos = posJogadorSegTime(jogadorPai1, segmentoTime, parte1, cont);
                if (pos == -1)
                    segmentoTime.add(jogadorPai1);
                else {
                    pos = buscarJogadorNaoInseridoNovoSegmento(pai1, segmentoTime, parte1, cont, parte1, cont);
                    if (pos !=  -1)
                        segmentoTime.add(pai1.get(pos));
                    else
                        System.out.println("FALHA GERAL");
                }
            }
            cont++;
        }
        
        totalTime = avaliarTime(segmentoTime, posicoes);
        Time time = new Time(segmentoTime, totalTime);
    
        return time;
    }

    public static void mutarTime(Time time) {

        Random gerador = new Random();
        Jogador jogador1 = null;
        Jogador jogador2 = null;

        List<Jogador> jogadores = time.getTime();
        
        int m1 = 0;
        int m2 = 0;
        int mutacao = gerador.nextInt(100);

        if (mutacao <= 20) {

            m1 = gerador.nextInt(jogadores.size());
            m2 = gerador.nextInt(jogadores.size());

            while (m1 == m2) {
                m2 = gerador.nextInt(jogadores.size());
            }
            
            jogador1 = (Jogador) jogadores.get(m1);
            jogador2 = (Jogador) jogadores.get(m2);

            jogadores.set(m1, jogador2);
            jogadores.set(m2, jogador1); 
            
            time.setTime(jogadores);
        }
    }

    public static void cortarTimes(List times, int corte) {

        while (times.size() > corte) {
            times.remove(times.size() - 1);
        }
    }

    public static void exibirPopulacao(List<Time> times, int qt) {

        try {
            Time time = null;

            System.out.println("");
            for (int i = 0; i < qt; i++) {
                time = (Time) times.get(i);
                System.out.println("Time " + i + ":");
                System.out.println(time.toString());
                System.out.println("");
                System.out.println("");
            }

        } catch (IndexOutOfBoundsException erro) {
            System.out.println("Quantidade Inválida!");
        }
    }

    public static void exibirTime(List<Time> times, int inicio) {

        try {

            Jogador jogador = null;
            Time time = null;
            
            System.out.println("");

            time = (Time) times.get(inicio);
            System.out.println("Time " + inicio + ":");
            System.out.println("");
            System.out.println(time.toString());
            System.out.println("");
            System.out.println("");

        } catch (IndexOutOfBoundsException erro) {
            System.out.println("População não encontrada!");
        }
    }

    public static double multiplicarAtributos(Jogador jogador, List<Posicao> posicoes, int p) {

        double forca = 0;
        double reflexo = 0;
        double velocidade = 0;
        double habilidade = 0;
        double total = 0;

        forca = (jogador.getForca() * posicoes.get(p).getPesoForca());
        reflexo = (jogador.getReflexo() * posicoes.get(p).getPesoReflexo());
        velocidade = (jogador.getVelocidade() * posicoes.get(p).getPesoVelocidade());
        habilidade = (jogador.getHabilidade() * posicoes.get(p).getPesoHabilidade());
        total = (forca + reflexo + velocidade + habilidade);

        return total;
    }

    public static int identificarPosicao(int j) {
 
        int resultado = (int)(j+1)/2;
        return resultado;
    }

    public static int calcularTotalTimes(List<Time> times) {

        double total = 0;
        Time time = null;

        for (int i = 0; i < times.size(); i++) {
            time = (Time) times.get(i);
            total = (time.getTotal() + total);
        }
        return (int) total;
    }

    public static List gerarIntervalo(List<Time> times, double total) {

        List intervalos = new ArrayList<>();
        Time time = null;
        int totalTime = (int) total;

        intervalos.add(totalTime);

        for (int i = 0; i < times.size(); i++) {
            time = (Time) times.get(i);
            totalTime = totalTime - (int) time.getTotal();
            intervalos.add(totalTime);
        }
        return intervalos;
    }

    public static int identificarIntervalo(List<Integer> intervalos, int sorteado) {

        for (int i = 1; i < intervalos.size(); i++) {
            
            if ( sorteado > intervalos.get(i)) {
                return i-1;
            }
        }
        return 0;
    }

    private static void limparTela() {
        for (int i = 0; i < 5; i++) {
            System.out.println("");
        }
    }
}
