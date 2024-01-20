/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author tiago
 */
import Lists.UnorderedLists.ArrayUnorderedList;
import Map.Map;
import exceptions.EmptyCollectionException;
import exceptions.NonComparableElementException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;

public class GameUI extends JFrame {
    private Map gameMap;

    public GameUI() {
        gameMap = new Map();
        // Configurações básicas do JFrame
        setTitle("Capture The Flag Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona um JPanel personalizado que representa o mapa
        MapPanel mapPanel = new MapPanel(gameMap);
        add(mapPanel);

        // Adiciona um botão para importar um mapa
        JButton importMapButton = new JButton("Importar Mapa");
        importMapButton.addActionListener(e -> {
            // Lógica para importar um mapa
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(GameUI.this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();

                // Chama o método importarMapa do objeto Map
                gameMap.importarMapaDeArquivo(filePath);

                // Atualiza a interface gráfica
                mapPanel.repaint();
            }
        });

        // Adiciona um botão para gerar um novo mapa
        JButton generateMapButton = new JButton("Gerar Novo Mapa");
        generateMapButton.addActionListener(e -> {
            // Lógica para gerar um novo mapa
            gameMap.gerarMapaAleatorio(5, 50);
            // Atualiza a interface gráfica
            mapPanel.repaint();
        });

        // Adiciona os botões ao JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(importMapButton);
        buttonPanel.add(generateMapButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Exibe o JFrame
        setVisible(true);
    }

    // JPanel personalizado para representar o mapa
    public class MapPanel extends JPanel {
        private Map gameMap;

        public MapPanel(Map gameMap) {
            this.gameMap = gameMap;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // Desenha o mapa no JPanel
            
            ArrayUnorderedList<String> vertices = gameMap.getVertices();
            for (String location : vertices) {
                int x = gameMap.getCoordinateX(location);
                int y = gameMap.getCoordinateY(location);
                // Desenha a localização como uma elipse
                
                Ellipse2D ellipse = new Ellipse2D.Double(x, y, 20, 20);
                g2d.setColor(Color.BLUE);
                g2d.fill(ellipse);
                g2d.setColor(Color.BLACK);
                g2d.drawString(location.substring(location.lastIndexOf(" ") + 1), x + 10, y + 10);

                // Desenha as arestas
                try {
                    for (String neighbor : gameMap.getNetwork().getNeighbours(location)) {
                        // Desenha a aresta como uma linha
                        int xNeighbor = gameMap.getCoordinateX(neighbor);
                        int yNeighbor = gameMap.getCoordinateY(neighbor);
                        g2d.setColor(Color.BLACK);
                        g2d.drawLine(x + 10, y + 10, xNeighbor + 10, yNeighbor + 10);
                        double peso = gameMap.getPesoAresta(location, neighbor);
                        String pesoStr = String.format("%.2f", peso);
                        g2d.drawString(pesoStr, (x + xNeighbor) / 2, (y + yNeighbor) / 2);
                    }
                } catch (EmptyCollectionException | NonComparableElementException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}