/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bots;

import Lists.LinkedList.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ricar
 */
public class Bots {

    private LinkedList<Bots> blueTeamBots = new LinkedList<Bots>();
    private LinkedList<Bots> redTeamBots = new LinkedList<Bots>() ;
    private int botNumber;
    private String location;
    private int strategy;

    public Bots(int botNumber, String location, int strategy) {
        this.botNumber = botNumber;
        this.location = location;
        this.strategy = strategy;
    }

    public Bots() {
    }

    public void addBots() {
        Scanner scanner = new Scanner(System.in);
        int nBots;
        int option;
        do {
            System.out.println("Digite o numero de bots que pretende jogar (Numero Par): ");
            nBots = scanner.nextInt();
        } while (nBots % 2 == 1);
        System.out.println("Blue Team:");
        for (int j = 0; j < nBots / 2; j++) {
            System.out.println("[Bot " + (j+1) + "]");
            System.out.println("Qual a estrategia que pretende usar:");
            do {
                System.out.println("[1-Caminho Mais Curto | 2-Caminho Mais Longo | 3-Caminho Medio]");
                option = scanner.nextInt();
            } while (option < 1 || option > 3);
            addBlueBot(j+1, option);
        }

        System.out.println("Red Team:");
        for (int j = 0; j < nBots / 2; j++) {
            System.out.println("[Bot " + (j+1) + "]");
            System.out.println("Qual a estrategia que pretende usar:");
            do {
                System.out.println("[1-Caminho Mais Curto | 2-Caminho Mais Longo | 3-Caminho Medio]");
                option = scanner.nextInt();
            } while (option < 1 || option > 3);
            addRedBot(j+1, option);
        }
    }

    private void addBlueBot(int nBot, int strategy) {
        Bots bot = new Bots(nBot, "boas", strategy);
        blueTeamBots.add(bot);
    }

    private void addRedBot(int nBot, int strategy) {
        Bots bot = new Bots(nBot, "boas", strategy);
        redTeamBots.add(bot);
    }

}
