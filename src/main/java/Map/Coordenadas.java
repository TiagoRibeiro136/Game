/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map;

/**
 *
 * @author tiago
 */
class CoordenadasMapa {
    private String[] localizacoes;
    private int[] coordenadasX;
    private int[] coordenadasY;
    private int tamanho;

    public CoordenadasMapa(int capacidade) {
        localizacoes = new String[capacidade];
        coordenadasX = new int[capacidade];
        coordenadasY = new int[capacidade];
        tamanho = 0;
    }

    public void adicionarCoordenadas(String localizacao, int x, int y) {
        if (tamanho < localizacoes.length) {
            localizacoes[tamanho] = localizacao;
            coordenadasX[tamanho] = x;
            coordenadasY[tamanho] = y;
            tamanho++;
        } else {
            System.err.println("Capacidade excedida ao adicionar coordenadas.");
        }
    }

    public int getCoordinateX(String localizacao) {
        for (int i = 0; i < tamanho; i++) {
            if (localizacoes[i].equals(localizacao)) {
                return coordenadasX[i];
            }
        }
        System.err.println("Localização não encontrada: " + localizacao);
        return -1; // Ou outra estratégia apropriada para indicar erro
    }

    public int getCoordinateY(String localizacao) {
        for (int i = 0; i < tamanho; i++) {
            if (localizacoes[i].equals(localizacao)) {
                return coordenadasY[i];
            }
        }
        System.err.println("Localização não encontrada: " + localizacao);
        return -1; // Ou outra estratégia apropriada para indicar erro
    }
}
