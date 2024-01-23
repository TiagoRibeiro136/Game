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
import java.text.SimpleDateFormat;
import java.util.Date;
//nao esta a ser usada!!
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
        JTextField textField1 = new JTextField(5);
JTextField textField2 = new JTextField(5);
JButton generateMapButton = new JButton("Gerar Novo Mapa");

// Adicione os campos de texto e o botão à sua interface gráfica
// Substitua 'seuContainer' pelo contêiner onde você deseja adicionar esses componentes


generateMapButton.addActionListener(e -> {
    try {
        int valor1 = Integer.parseInt(textField1.getText());
        int valor2 = Integer.parseInt(textField2.getText());

        // Lógica para gerar um novo mapa
        gameMap.gerarMapaAleatorio(valor1, valor2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dataAtual = dateFormat.format(new Date());

        // Atualiza o nome do arquivo com a data atual
        String nomeArquivo = "mapa_" + dataAtual + ".txt";

        // Atualiza o método exportarMapaParaArquivo para receber o nome do arquivo
        gameMap.exportarMapaParaArquivo(nomeArquivo);

        // Atualiza a interface gráfica
        mapPanel.repaint();
    } catch (NumberFormatException ex) {
        // Trate a exceção se os valores inseridos não forem números inteiros
        JOptionPane.showMessageDialog(mapPanel, "Por favor, insira valores numéricos válidos.");
    }
});

        // Adiciona os botões ao JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(importMapButton);
        buttonPanel.add(generateMapButton);
        buttonPanel.add(textField1);
        buttonPanel.add(textField2);

        add(buttonPanel, BorderLayout.NORTH);

        // Exibe o JFrame
        setVisible(true);
    }

    // JPanel personalizado para representar o mapa
    public class MapPanel extends JPanel {
    private Map gameMap;
     private JTextArea consoleTextArea;

    public MapPanel(Map gameMap) {
        this.gameMap = gameMap;
        this.consoleTextArea = new JTextArea(10, 30);

        // Adiciona um JScrollPane à JTextArea para permitir a rolagem
        add(new JScrollPane(consoleTextArea), BorderLayout.CENTER);
    }

    // Método para adicionar texto à console
    public void appendToConsole(String text) {
        consoleTextArea.append(text + "\n");
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}