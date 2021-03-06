package control;

import view.ChooseClass;
import view.NameLearning;
import view.Result;

import java.io.*;
import java.awt.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Falls möglich wird die Logik der Applikation in diesem File ausgeführt
 *
 * @author Devin Kutbay
 * @date 05.10.2021
 */

public class Logic {

    static int rightAnswer = 0;
    public static String nachricht;
    public static String stats = "";

    public static File directory = new File("img");
    public static File[] listOfDirectory = directory.listFiles();
    public static String[] klassen = new String[listOfDirectory.length];
    public static String correction;

    File file = new File("img/" + klassen[0]);
    File[] listOfFiles = file.listFiles();
    String[] namen = new String[listOfFiles.length];
    String[] bildPfad = new String[listOfFiles.length]; //Pfad des Bildes

    public Logic() {

    }

    /**
     * Getter für das Directory (Klassen Ordner)
     *
     * @param klassen
     */
    public static void getDirectory(String[] klassen) {
        for (int i = 0; i < listOfDirectory.length; i++) {
            if (listOfDirectory[i].isDirectory()) {
                klassen[i] = listOfDirectory[i].getName();
            }
        }
    }

    /**
     * Getter für den Pfad des Bildes
     *
     * @param bildPfad
     */
    public static void getPicture(String[] bildPfad) {
        File file = new File("img/" + klassen[ChooseClass.dieKlasse]);
        File[] listOfFiles = file.listFiles();
        for (int e = 0; e < listOfFiles.length; e++) {
            if (listOfFiles[e].isFile()) {
                bildPfad[e] = listOfFiles[e].getName();
            }
        }
    }

    /**
     * Vermischt das eingegeben String Array
     *
     * @param ar das Array gemischelt
     */
    public static void shuffleArray(String[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Einzeltausch
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    /**
     * Vermischt das eingegeben int Array
     *
     * @param ar das Array gemischelt
     */
    public static void shuffleArrayInt(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Einzeltausch
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    /**
     * Überprüft die Antwort die im NameLearning ausgewählt wurde
     */
    public static void checkAnswer() {
        String givenAnswer = NameLearning.givenAnswer;
        nachricht = "";
        if (givenAnswer == NameLearning.allStudents[NameLearning.zahlen[NameLearning.count]]) {
            correction = "Richtig";
            NameLearning.correctGuesses++;
        } else {
            correction = "Falsch";
            nachricht = "Richtige Antwort: " + NameLearning.allStudents[NameLearning.zahlen[NameLearning.count]];
        }
        String a = NameLearning.buttonA.getText();
        String b = NameLearning.buttonB.getText();
        String c = NameLearning.buttonC.getText();
        String d = NameLearning.buttonD.getText();
        String correct = NameLearning.allStudents[NameLearning.zahlen[NameLearning.count]];

        NameLearning.buttonA.setBackground(Color.red);
        NameLearning.buttonB.setBackground(Color.red);
        NameLearning.buttonC.setBackground(Color.red);
        NameLearning.buttonD.setBackground(Color.red);
        if (a == correct) {
            NameLearning.buttonA.setBackground(Color.green);
        }
        if (b == correct) {
            NameLearning.buttonB.setBackground(Color.green);
        }
        if (c == correct) {
            NameLearning.buttonC.setBackground(Color.green);
        }
        if (d == correct) {
            NameLearning.buttonD.setBackground(Color.green);
        }
        System.out.println(correction);
        System.out.println(nachricht);
    }

    /**
     * Zufallsauswahl welche der vier Buttons (A,B,C,D) ddie korrekte ist
     *
     * @return
     */
    public static int setRightAnswer() {
        rightAnswer = 1 + (int) (Math.random() * ((4 - 1) + 1));
        return rightAnswer;
    }

    /**
     * Anzeige einer Meldung nach auswahl einer Antwort
     * Gibt an ob die Antwort korrekt oder Falsch ist
     */
    public static void message() {
        showMessageDialog(null, Logic.correction + "\n" + "" + Logic.nachricht);
    }

    /**
     * Anzeige einer Meldung mit den Entwickeler der Applikation
     */
    public static void credit() {
        showMessageDialog(null, "Made by:\n" +
                "Yannis Lee \n" +
                "Nils Iseli \n" +
                "Ivan Gotovcevic\n" +
                "Devin Kutbay \n");
    }

    /**
     * Ausgabe einer Meldung mit dem Resultat
     * Mit wieviele Fragen man korrekt hat von der Anzahl Fragen
     * Anzeige wieviel Prozent der Antworten korrekt ist
     */
    public static void result() {
        System.out.println(NameLearning.length);
        System.out.println(NameLearning.correctGuesses);
        float lol = (float) NameLearning.length / (float) NameLearning.correctGuesses;
        NameLearning.percentage = 100 / lol;
        NameLearning.auswahlKlasse = "";
        if (ChooseClass.choice) {

        } else {
            NameLearning.auswahlKlasse = "von der Klasse " + NameLearning.klassen[ChooseClass.dieKlasse];
        }
        showMessageDialog(null, "Von " + NameLearning.length + " Schüler " +
                "haben sie " + NameLearning.correctGuesses + " richtig " + NameLearning.auswahlKlasse +
                "\n Das ist eine Erfolgsquote von " + NameLearning.percentage + "%");
        try {
            fileWriter();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * Speichert Das Ergebnis mit Datum und Zeit
     * Von "result"
     * In ein html-File mit dem namen "stats.txt"
     *
     * @throws FileNotFoundException
     */
    public static void fileWriter() throws FileNotFoundException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        FileOutputStream fos = new FileOutputStream("stats.txt", true);
        PrintWriter pw = new PrintWriter(fos);
        pw.println(dtf.format(now));
        pw.println(NameLearning.correctGuesses + "/" + NameLearning.length + " richtig " + NameLearning.auswahlKlasse +
                "\n" + NameLearning.percentage + "% korrekt" +
                "\n----------------------------------------------------");
        pw.close();
    }

    /**
     * Liest das File "stats.html" und gibt den Inhalt als JPanel an
     * Für anzeige der Historik
     *
     * @throws FileNotFoundException
     */
    public static void fileReader() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("stats.txt");
        Scanner in = new Scanner(fis);
        while (in.hasNext()) {
            stats = stats + "\n" + in.nextLine() + "\n";
        }
        //showMessageDialog(null, stats);
        Result result = new Result();
        result.main();

    }

    public static void main(String[] args) {
        Logic logic = new Logic();
    }
}
