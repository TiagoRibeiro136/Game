/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author tiago
 */
import Map.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        MapPanel mapPanel = new MapPanel();
        add(mapPanel);

        // Adiciona um botão para selecionar um campo
        JButton selectFieldButton = new JButton("Selecionar Campo");
        selectFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para selecionar um campo no mapa
                // Aqui você pode implementar a lógica de seleção do campo
                // por exemplo, destacando-o de alguma maneira
                System.out.println("Campo selecionado!");
            }
        });

        // Adiciona o botão ao JFrame
        add(selectFieldButton, BorderLayout.SOUTH);

        // Adiciona um botão para importar um mapa
        JButton importMapButton = new JButton("Importar Mapa");
        importMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para importar um mapa
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(GameUI.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();

                    // Chama o método importarMapa do objeto Map
                    gameMap.importarMapaDeArquivo(filePath);

                    // Atualiza a interface gráfica ou realiza outras operações necessárias
                    // Aqui, por simplicidade, apenas exibo uma mensagem
                    System.out.println("Mapa importado de: " + filePath);
                }
            }
        });

        // Adiciona um botão para gerar um novo mapa
        JButton generateMapButton = new JButton("Gerar Novo Mapa");
        generateMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para gerar um novo mapa
                // Aqui você pode implementar a lógica para criar um novo mapa
                System.out.println("Novo mapa gerado!");
            }
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
    private class MapPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Desenha o mapa no JPanel
            // Você precisa implementar a lógica para desenhar o mapa de acordo com o seu formato
            // Pode envolver o uso de gráficos 2D para desenhar linhas, círculos, etc.
            // Aqui, por simplicidade, apenas exibirei uma mensagem
            g.drawString("Mapa Aqui", 100, 100);
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crie a interface gráfica do jogo
                new GameUI();
            }
        });
    }
}
