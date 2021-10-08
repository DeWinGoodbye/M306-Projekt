package view;

import control.Logic;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Container;
import java.sql.Array;
import java.sql.Time;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class NameLearning {

    public static int rightAnswer;
    public static String givenAnswer;
    static int r;
    static int rand;

    Play play = new Play();

    public NameLearning() throws IOException {
    }

    private static JLabel titel;
    private static JTextField textField = new JTextField();
    private static JTextArea textArea = new JTextArea();
    public static JButton buttonA = new JButton();
    public static JButton buttonB = new JButton();
    public static JButton buttonC = new JButton();
    public static JButton buttonD = new JButton();
    private static JButton buttonE = new JButton();
    private static JLabel show = new JLabel();
    private static JLabel picLabel = new JLabel();
    private static JPanel buttons;
    private static JPanel buttonPanel;
    private static BufferedImage studentImg;
    static Image dimg;
    static ImageIcon imageIcon;
    static String[] alleBilder;
    static String[] alleB;
    public static String[] allStudents;
    public static int[] zahlen;
    static File[] listOfFiles;
    static File file;

    public static String[] klassen;
    public static String[] bildPfad;
    public static String[] namen;
    static String[] words;

    public static int count = 0; //Zähler
    static int runden = 0;
    public static int correctGuesses = 0;
    public static int length = 0; //Anzahl Schüler
    public static float percentage;
    public static String auswahlKlasse;

    static long Time0;
    static long Time1;
    static long runtime = 0;


    static class Play extends JFrame
    {


        public Play() throws IOException {


            setTitle("view.NameLearning");


            klassen = Logic.klassen;
            Logic.getDirectory(klassen);

            File file = new File("img/" + klassen[ChooseClass.dieKlasse]);
            File[] listOfFiles = file.listFiles();
            bildPfad = new String[listOfFiles.length];
            Logic.getPicture(bildPfad);
            namen = new String[listOfFiles.length];

            getContentPane().setBackground(new Color(40, 45, 88));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1000, 1000);
            getContentPane().setBackground(new Color(50, 50, 50));
            setLayout(null);
            setResizable(true);

            Font buttonFont = new Font("Arial", Font.BOLD, 30);

            buttonA.setFont(buttonFont);
            buttonA.setFocusable(false);

            buttonB.setFont(buttonFont);
            buttonB.setFocusable(false);

            buttonC.setFont(buttonFont);
            buttonC.setFocusable(false);

            buttonD.setFont(buttonFont);
            buttonD.setFocusable(false);

            buttonE.setFont(buttonFont);
            buttonE.setFocusable(false);
            //buttonE.addActionListener(this);
            buttonE.setText("Abbrechen");

            if (ChooseClass.choice) {
                NameLearning.initAll();
            } else {
                NameLearning.initClass();
            }


            titel = new JLabel("Namen- Lernen", SwingConstants.CENTER);
            titel.setFont(new Font("Arial", Font.BOLD, 50));
            titel.setForeground(Color.white);

            show.setFont(buttonFont);
            show.setForeground(Color.white);

            picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonA.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonB.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonC.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonD.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonE.setAlignmentX(Component.LEFT_ALIGNMENT);

            //Füllen des Buttonpanels mit den Buttons
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
            buttonPanel.add(picLabel);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
            buttonPanel.add(buttonA);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
            buttonPanel.add(buttonB);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
            buttonPanel.add(buttonC);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
            buttonPanel.add(buttonD);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));//TODO LABEL
            buttonPanel.add(show);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 65)));
            buttonPanel.add(buttonE);
            buttons = new JPanel();
            buttons.add(buttonPanel);

            buttonPanel.setBackground(new Color(25, 25, 75));
            buttons.setBackground(new Color(25, 25, 75));

            //Setzen des Layouts und hinzufügen der Komponenten
            getContentPane().setLayout(new BorderLayout(1, 1));
            getContentPane().add(buttons, BorderLayout.CENTER);
            getContentPane().add(titel, BorderLayout.NORTH);


            //ActionListener für das klassenspiegel Button
            buttonA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    givenAnswer = buttonA.getText();
                    Logic.checkAnswer();

                    count++;
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                    Logic.message();
                    warten();
                    nextQuestion();
                }
            });

            buttonB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    givenAnswer = buttonB.getText();
                    Logic.checkAnswer();

                    count++;
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                    Logic.message();
                    warten();
                    nextQuestion();

                }
            });

            buttonC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    givenAnswer = buttonC.getText();
                    Logic.checkAnswer();

                    count++;
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                    Logic.message();
                    warten();
                    nextQuestion();
                }
            });

            buttonD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    givenAnswer = buttonD.getText();
                    Logic.checkAnswer();

                    count++;
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                    Logic.message();
                    warten();
                    nextQuestion();
                }
            });

            buttonE.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    count = 0;
                    App app = new App();
                }
            });

            setVisible(true);
        }
    }

    public static void setButtons() throws IOException {
        int answer = Logic.setRightAnswer();
        System.out.println(answer);
        System.out.println();
        buttonA.setText(allStudents[random()]);
        buttonB.setText(allStudents[random()]);
        buttonC.setText(allStudents[random()]);
        buttonD.setText(allStudents[random()]);
        switch (answer) {
            case 1:
                buttonA.setText(allStudents[zahlen[count]]);
                break;
            case 2:
                buttonB.setText(allStudents[zahlen[count]]);
                break;
            case 3:
                buttonC.setText(allStudents[zahlen[count]]);
                break;
            case 4:
                buttonD.setText(allStudents[zahlen[count]]);
                break;
        }
    }

    public static void countStudents() {
        for (int i = 0; i < klassen.length; i++) {
            File file = new File("img/" + klassen[i]);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles == null) {
                throw new AssertionError();
            }
            String[] namen1 = new String[listOfFiles.length];
            length = length + namen1.length;
        }
    }

    public static void nextQuestion() {
        if (runden < length) {
            try {
                setButtons();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                studentImg = ImageIO.read(new File(alleBilder[zahlen[count]]));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            picLabel.setBounds(300, 75, 350, 350);
            dimg = studentImg.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(dimg);
            picLabel.setIcon(imageIcon);
            runden++;
            show.setText(runden + "/" + length);
            System.out.println(runden + "/" + length);//TODO letsGO
        }
        else {
            Logic.result();
        }
    }

    public static void initClass(){
        file = new File("img/" + klassen[ChooseClass.dieKlasse]);
        listOfFiles = file.listFiles();
        if (listOfFiles == null) {
            throw new AssertionError();
        }
        length = listOfFiles.length;
        setLength();

        bildPfad = new String[listOfFiles.length];
        namen = new String[listOfFiles.length];
        int o =0;
        for (int i = 0; i< listOfFiles.length; i++){
            if (listOfFiles[i].isFile()){
                bildPfad[i] = listOfFiles[i].getName();
                String noLowerCase = bildPfad[i];
                noLowerCase = noLowerCase.replaceAll("_", " ").toLowerCase();
                words = noLowerCase.split(" ", 3);
                String n = words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + words[1].substring(0, 1).toUpperCase() + words[1].substring(1) + Arrays.toString(words);
                String name = n.substring(0, n.indexOf("."));
                alleBilder[o] = "img/" + klassen[ChooseClass.dieKlasse] + "/" + bildPfad[i];
                namen[i] = name + " " + klassen[ChooseClass.dieKlasse];
                allStudents[o] = namen[i];
                o++;
            }
        }
        zahlen = new int[allStudents.length];
        for (int i = 0; i<zahlen.length; i++){
            zahlen[i] = i;
        }
        Logic.shuffleArrayInt(zahlen);
        nextQuestion();
    }

    public static void killMe() {
        int o = 0;
        for (String s : klassen) {
            file = new File("img/" + s);
            listOfFiles = file.listFiles();
            if (listOfFiles == null) throw new AssertionError();
            bildPfad = new String[listOfFiles.length];
            namen = new String[listOfFiles.length];
            for (int e = 0; e < listOfFiles.length; e++) {
                if (listOfFiles[e].isFile()) {
                    bildPfad[e] = listOfFiles[e].getName();
                    String noLowerCase = bildPfad[e];
                    noLowerCase = noLowerCase.replaceAll("_", " ").toLowerCase();
                    words = noLowerCase.split(" ", 3);
                    String n = words[0].substring(0, 1).toUpperCase() + words[0].substring(1) + " " + words[1].substring(0, 1).toUpperCase() + words[1].substring(1) + Arrays.toString(words);
                    String name = n.substring(0, n.indexOf("."));
                    alleBilder[o] = "img/" + s + "/" + bildPfad[e];
                    namen[e] = name + " " + s;
                    allStudents[o] = namen[e];
                    System.out.println(allStudents[o]);
                    System.out.println(alleBilder[o]);
                    o++;
                }
            }
        }
        zahlen = new int[allStudents.length];
        for (int i = 0; i<zahlen.length; i++){
            zahlen[i] = i;
        }
        Logic.shuffleArrayInt(zahlen);

        for (int e = 0; e < allStudents.length; e++) {
            System.out.println("-------------");
            System.out.println(allStudents[e]);
            System.out.println(alleBilder[e]);
            System.out.println(allStudents[zahlen[e]]);
            System.out.println(alleBilder[zahlen[e]]);
            System.out.println("-------------");
        }
    }

    public static int random() {
        int no = length;
        rand = (int) (Math.random() * ((no - 4) + 1));
        return rand;
    }

    public static void initAll(){
        countStudents();
        setLength();
        killMe();
        nextQuestion();
    }

    public static void setLength(){
        alleBilder = new String[length];
        alleB = new String[length];
        allStudents = new String[length];
    }

    public static void warten() {
        Time0 = System.currentTimeMillis();
        runtime = 0;
        while (runtime < 500) {
            Time1 = System.currentTimeMillis();
            runtime = Time1 - Time0;
        }
        buttonA.setBackground(UIManager.getColor("Button.background"));
        buttonB.setBackground(UIManager.getColor("Button.background"));
        buttonC.setBackground(UIManager.getColor("Button.background"));
        buttonD.setBackground(UIManager.getColor("Button.background"));
    }
}


