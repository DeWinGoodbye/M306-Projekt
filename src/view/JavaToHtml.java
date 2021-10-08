package view;


import com.fasterxml.jackson.databind.ObjectMapper;
import control.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

class JavaToHTML extends JFrame {
    private JLabel titel;
    private JButton start;
    private JPanel buttons;
    private JPanel buttonPanel;
    public static int dieKlasse;
    private TreeMap<String, String> nameUme;


    public JavaToHTML() throws IOException{
        getContentPane().setBackground(new Color(40,45,88));
        setSize(800,800);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        titel = new JLabel("Auswahl der Klassen", SwingConstants.CENTER);
        titel.setForeground(new Color(25,25,25));

        //Buttons
        start = new JButton("Lernen");

        //Border
        EmptyBorder emptyBorder = new EmptyBorder(75,0,75,0);
        titel.setBorder(emptyBorder);

        //Setzen der Schriftart und Grösse der Buttons und des Titels
        Font buttonFont = new Font("Arial", Font.PLAIN, 40);
        titel.setFont(new Font("Arial", Font.BOLD, 70));
        start.setFont(buttonFont);

        //Setzten der Farbe der Buttons

        Color buttonFontColor = new Color(255,255,255);

        titel.setBackground(new Color(23, 27 ,64));
        titel.setForeground(buttonFontColor);

        //Aligment für die Buttons
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Füllen des Buttonpanels mit den Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        buttons = new JPanel();
        buttons.add(buttonPanel);

        File[] directories = Logic.listOfDirectory;

        for(int i = 0;i< directories.length;i++) {
            int temp = i;
            JButton button = new JButton(directories[i].getName());
            button.addActionListener(e -> {
                try {
                    chooseClass(temp);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            button.setBackground(new Color(50,75,50));
            button.setForeground(buttonFontColor);
            button.setFont(buttonFont);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
            buttonPanel.add(button);
        }

        buttonPanel.setBackground(new Color(25, 25, 75));
        buttons.setBackground(new Color(25, 25, 75));

        //Setzen des Layouts und hinzufügen der Komponenten
        getContentPane().setLayout(new BorderLayout(1, 1));
        getContentPane().add(buttons, BorderLayout.CENTER);
        getContentPane().add(titel, BorderLayout.NORTH);
        setVisible(true);
    }

    public void chooseClass(int index) throws IOException {
        System.out.println(index);
        dieKlasse = index;
        getNames();
        App app = new App();
        setVisible(false);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\test.html");

        Vector<String> names = new Vector<>();

        File[] directories = Logic.listOfDirectory;
        File[] files = directories[1].listFiles();

        for(File f: files) {
            String name = f.getPath();
            names.add(name);
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }



    }

    public static void saveJSON(Vector<Schueler> map) {
        try {

            URL url = new URL("https://api.npoint.io/0032fa0bc1702e0b27e5");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);


            String jsonInputString = new ObjectMapper().writeValueAsString(map.toArray());



            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Daten wurden exportiert");
    }

    public static void getNames() {

        File[] directories = Logic.listOfDirectory;
        File[] files = directories[dieKlasse].listFiles();

        Vector<Schueler> schuelerVector = new Vector<>();


        for(File f: files) {
            Schueler s = new Schueler();
            String path = f.getPath();
            String[] nameSplit = path.split("_|\\.|\\\\");


            s.setFirstName(nameSplit[2]);
            s.setName(nameSplit[3]);
            s.setKlasse(nameSplit[1]);
            s.setImage(f.getPath());
            schuelerVector.add(s);



        }
        System.out.println("auswahl");
        saveJSON(schuelerVector);
    }
}
