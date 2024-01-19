/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Map;

import Interfaces.NetworkADT;
import exceptions.EmptyCollectionException;

/**
 *
 * @author tiago
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmptyCollectionException {
        int numLocations = 20;
        double edgeDensity = 10;
        System.out.println("Diretório Atual: " + System.getProperty("user.dir"));

        Map mapGenerator = new Map();
        mapGenerator.gerarMapaAleatorio(numLocations, edgeDensity);
        mapGenerator.exportarMapaParaArquivo("map.csv");
        //mapGenerator.importarMapaDeArquivo("map.csv");
        

        
        // Agora você pode usar o mapa conforme necessário
    }
}
