/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Map;

import Interfaces.NetworkADT;
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
        //mapGenerator.gerarMapaAleatorio(numLocations, edgeDensity);
        //mapGenerator.exportarMapaParaArquivo("map.csv");
        mapGenerator.importarMapaDeArquivo("map.csv");
       // mapGenerator.chooseLocationForFlag();
        mapGenerator.caminhoCurto("Localização 14", "Localização 130");
        mapGenerator.caminhomedio("Localização 14", "Localização 130");
        
        
       
        
        // Agora você pode usar o mapa conforme necessário
    }
}
