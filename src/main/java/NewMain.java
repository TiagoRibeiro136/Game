/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import Bots.Bots;
import Interfaces.NetworkADT;
import Map.Map;
import exceptions.EmptyCollectionException;
import exceptions.UnknownPathException;

/**
 *
 * @author tiago
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmptyCollectionException, UnknownPathException {
        int numLocations = 170;
        double edgeDensity = 10;
        System.out.println("Diretório Atual: " + System.getProperty("user.dir"));

        Map mapGenerator = new Map();
        Bots bots = new Bots();
        //Game game = new Game();
        mapGenerator.gerarMapaAleatorio(numLocations, edgeDensity);
        mapGenerator.exportarMapaParaArquivo("map.csv");
        mapGenerator.importarMapaDeArquivo("map.csv");
        mapGenerator.chooseLocationForFlag();
        bots.addBots();
       
      
        mapGenerator.caminhoCurto("Localização 14", "Localização 130");
        mapGenerator.caminhoMedio("Localização 14", "Localização 130");
        mapGenerator.caminholongo("Localização 14", "Localização 130");
        
        
       
        
    }
}
