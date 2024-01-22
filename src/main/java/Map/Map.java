/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map;
import Graphs.Network;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import Lists.ArrayLists.ArrayList;
import Lists.UnorderedLists.ArrayUnorderedList;
import exceptions.EmptyCollectionException;
import exceptions.UnknownPathException;
import java.util.Scanner;




public class Map {
    private Network<String> map = new Network<>();
   // public CoordenadasMapa coordenadasMapa;
    private ArrayUnorderedList<String> verticesList = new ArrayUnorderedList<>();
    private boolean[][] arestasExistentes;
    private double[][] pesosArestas;

    public Map() {
        this.map = new Network<>();
       // this.coordenadasMapa = new CoordenadasMapa(100);
    }
    public int getCoordinateX(String location) {
     // Lógica para calcular a coordenada X com base na posição da localização
     int index = 0;
     String lastPart = location.substring(location.lastIndexOf(" ") + 1);

     if (!lastPart.isEmpty()) {
         index = Integer.parseInt(lastPart) - 1;
     }

     // Posiciona as localizações em um círculo
     int circleRadius = 100; // raio do círculo
     double angle = (2 * Math.PI * index) / verticesList.size();
     return (int) (circleRadius * Math.cos(angle)) + 300; // ajuste conforme necessário
    }
    public double getPesoAresta(String vertex1, String vertex2) {
        int index1 = Integer.parseInt(vertex1.substring(vertex1.lastIndexOf(" ") + 1)) - 1;
        int index2 = Integer.parseInt(vertex2.substring(vertex2.lastIndexOf(" ") + 1)) - 1;

        return pesosArestas[index1][index2];
    }

    public int getCoordinateY(String location) {
        // Lógica para calcular a coordenada Y com base na posição da localização
        int index = 0;
        String lastPart = location.substring(location.lastIndexOf(" ") + 1);

        if (!lastPart.isEmpty()) {
            index = Integer.parseInt(lastPart) - 1;
        }

        // Posiciona as localizações em um círculo
        int circleRadius = 100; // raio do círculo
        double angle = (2 * Math.PI * index) / verticesList.size();
        return (int) (circleRadius * Math.sin(angle)) + 300; // ajuste conforme necessário
    }

    private boolean arestaExiste(int i, int j) {
        return arestasExistentes[i][j] || arestasExistentes[j][i];
    }

    private void adicionarArestaExistente(int i, int j) {
        arestasExistentes[i][j] = true;
        arestasExistentes[j][i] = true;
    }
    private void adicionarPesoAresta(int i, int j, double peso) {
        // Certifique-se de que os índices estão dentro dos limites
        if (i >= 0 && i < pesosArestas.length && j >= 0 && j < pesosArestas[i].length) {
            pesosArestas[i][j] = peso;
            pesosArestas[j][i] = peso;
        } else {
            System.err.println("Índices fora dos limites: " + i + ", " + j);
        }
    }
    //add localizaçao
    public void addVertex(String vertex) {
    // Verifica se o vértice já está presente antes de adicioná-lo novamente
    if (!verticesList.contains(vertex)) {
        verticesList.addToRear(vertex); // Adiciona o vértice à lista
        map.addVertex(vertex); // Adiciona o vértice ao mapa
    } else {
        System.out.println("Localização já existe: " + vertex);
    }
}

    public ArrayUnorderedList<String> getVertices() {
        // Retorna a lista de vértices do mapa
        return verticesList;
    }
    
    
        //gerar mapa aleatorio
    public void gerarMapaAleatorio(int quantidadeLocalizacoes, double densidadeArestas) {
    // Inicialize o array de arestas existentes
    arestasExistentes = new boolean[quantidadeLocalizacoes][quantidadeLocalizacoes];
    pesosArestas = new double[quantidadeLocalizacoes][quantidadeLocalizacoes];  // Inicialize o array de pesos


    // Adicione vértices
    for (int i = 0; i < quantidadeLocalizacoes; i++) {
        map.addVertex("Localizacao " + (i + 1));
    }

    // Calcule o número máximo de arestas em um grafo completo
    int arestasMaximas = (quantidadeLocalizacoes * (quantidadeLocalizacoes - 1));

    // Calcule o número de arestas com base na densidade
    int arestasDesejadas = (int) (arestasMaximas * (densidadeArestas * 0.01));

    // Adicione arestas com base no número desejado
    int arestasAdicionadas = 0;
    while (arestasAdicionadas < arestasDesejadas) {
        int i = (int) (Math.random() * quantidadeLocalizacoes);
        int j = (int) (Math.random() * quantidadeLocalizacoes);

        if (i != j && i >= 0 && i < quantidadeLocalizacoes && j >= 0 && j < quantidadeLocalizacoes && !arestaExiste(i, j)) {
            // Adicione uma aresta com peso aleatório (1 a 15 quilômetros) e uma casa decimal
            double distancia = Math.round((Math.random() * 14.9 + 1) * 10.0) / 10.0;

            map.addEdge("Localizacao " + (i + 1), "Localizacao " + (j + 1), distancia);
            adicionarArestaExistente(i, j);
            adicionarPesoAresta(i, j, distancia);
            arestasAdicionadas++;
        }
    }

    System.out.println(map.toString());
}       //exportar mapa gerado
        public void exportarMapaParaArquivo(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            // Exportar vértices
            writer.write("Vertices:");
            writer.newLine();
            for (int i = 1; i <= map.size(); i++) {
                writer.write("Localização " + i);
                writer.newLine();
            }

            // Exportar arestas e pesos
            writer.newLine();
            writer.write("Arestas:");
            writer.newLine();
            for (int i = 0; i < arestasExistentes.length; i++) {
                for (int j = i + 1; j < arestasExistentes[i].length; j++) {
                    if (arestasExistentes[i][j]) {
                        writer.write("Localização " + (i + 1) + ", Localização " + (j + 1) + ", " + pesosArestas[i][j]);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        // importar mapa 
    public void importarMapaDeArquivo(String nomeArquivo) {
    try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
        String line;
        boolean lendoVertices = false;
        boolean lendoArestas = false;

        while ((line = reader.readLine()) != null) {
            if (line.equals("Vertices:")) {
                lendoVertices = true;
                lendoArestas = false;
            } else if (line.equals("Arestas:")) {
                lendoVertices = false;
                lendoArestas = true;
                arestasExistentes = new boolean[map.size()][map.size()];
                pesosArestas = new double[map.size()][map.size()];
            } else if (lendoVertices) {
                // Adicionar vértice ao mapa e à lista
                addVertex(line);
            } else if (lendoArestas) {
                lendoVertices = false;
                lendoArestas = true;
                if (map.size() > 0) { // Certifique-se de que o mapa tenha pelo menos um vértice
                    arestasExistentes = new boolean[map.size()][map.size()];
                    pesosArestas = new double[map.size()][map.size()];
                } else {
                    System.err.println("Mapa vazio. Não é possível criar arestas.");
                    return; // Saia do método ou trate conforme necessário
                }
                // Ler informações da aresta
                String[] partes = line.split(", ");
                if (partes.length == 3) {
                    int index1 = Integer.parseInt(partes[0].substring(partes[0].lastIndexOf(" ") + 1)) - 1;
                    int index2 = Integer.parseInt(partes[1].substring(partes[1].lastIndexOf(" ") + 1)) - 1;
                    double peso = Double.parseDouble(partes[2]);

                    // Adicionar aresta ao mapa
                    map.addEdge("Localização " + (index1 + 1), "Localização " + (index2 + 1), peso);
                    adicionarArestaExistente(index1, index2);
                    adicionarPesoAresta(index1, index2, peso);
                } else {
                    System.err.println("Formato inválido para a aresta: " + line);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
      

    public Network<String> getNetwork() {
        return map;
    }
    public void chooseLocationForFlag() {
        // Obtenha a lista de localizações do mapa
        
        ArrayUnorderedList<String> locations = getVertices();
        // Exiba as opções de localizações
        System.out.println("Escolha uma localização para colocar sua bandeira:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i));
        }

        // Obtenha a escolha do jogador
        int chosenIndex = -1;
        do {
            System.out.print("Digite o número da localização desejada: ");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                chosenIndex = scanner.nextInt();
            } else {
                scanner.nextLine(); // Consumir entrada inválida
            }
        } while (chosenIndex < 1 || chosenIndex > locations.size());

        // A localização escolhida pelo jogador
        String chosenLocation = locations.get(chosenIndex - 1);

        System.out.println("Você escolheu colocar sua bandeira em: " + chosenLocation);

        // Agora, você pode fazer o que for necessário com a localização escolhida
        // Por exemplo, colocar a bandeira nessa localização no seu jogo.
    }
    public ArrayUnorderedList caminhoCurto(String vertice1, String vertice2) throws EmptyCollectionException, UnknownPathException {
        System.out.println(map.shortestPathWeight(vertice1, vertice2));
    return map.shortestPathWeight(vertice1, vertice2);
}
     public ArrayUnorderedList caminhomedio(String vertice1, String vertice2) throws EmptyCollectionException, UnknownPathException {
        System.out.println(map.averagePath(vertice1, vertice2));
    return map.averagePath(vertice1, vertice2);
}

    // ... outras partes da sua classe ...
}



    

 

