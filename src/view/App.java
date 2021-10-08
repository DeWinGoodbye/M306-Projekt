package view;

import control.Logic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Eine Applikation, bei dem der User (Optimal Lehrer) Namen einer Klasse
 * oder aller Schüler lernen kann.
 *
 * @author Devin Kutbay
 * @version 1.0
 * @since 05.10.2021
 */

public class App extends JFrame {

    private JLabel titel;
    private JButton start;
    private JButton klassenspiegel;
    private JButton stats;
    private JButton credits;
    private JButton leave;
    private JPanel buttons;
    private JPanel buttonPanel;
    NameLearning nameLearning;

    /**
     * Startet dei Applikation
     */
    public App() {
        setTitle("view.NameLearning");
        getContentPane().setBackground(new Color(40, 45, 88));
        setSize(800, 800);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);

        titel = new JLabel("Namen- Lernsystem", SwingConstants.CENTER);
        titel.setForeground(new Color(25, 25, 25));

        //Buttons
        start = new JButton("Lernen");
        klassenspiegel = new JButton("Klassenspiegel");
        stats = new JButton("Vergangene Spiele");
        credits = new JButton("Credits");
        leave = new JButton("Beenden");

        //Border
        EmptyBorder emptyBorder = new EmptyBorder(75, 0, 75, 0);
        titel.setBorder(emptyBorder);

        //Setzen der Schriftart und Grösse der Buttons und des Titels
        Font buttonFont = new Font("Arial", Font.PLAIN, 40);
        titel.setFont(new Font("Arial", Font.BOLD, 70));
        start.setFont(buttonFont);
        klassenspiegel.setFont(buttonFont);
        stats.setFont(buttonFont);
        credits.setFont(buttonFont);
        leave.setFont(buttonFont);

        //Setzten der Farbe der Buttons

        Color buttonFontColor = new Color(0, 0, 0);

        titel.setBackground(new Color(23, 27, 64));
        titel.setForeground(Color.white);
        start.setForeground(buttonFontColor);
        klassenspiegel.setForeground(buttonFontColor);
        stats.setForeground(buttonFontColor);
        credits.setForeground(buttonFontColor);
        leave.setForeground(buttonFontColor);

        //Aligment für die Buttons
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        klassenspiegel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stats.setAlignmentX(Component.CENTER_ALIGNMENT);
        credits.setAlignmentX(Component.CENTER_ALIGNMENT);
        leave.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Füllen des Buttonpanels mit den Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.add(start);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        buttonPanel.add(klassenspiegel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        buttonPanel.add(stats);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        buttonPanel.add(credits);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 45)));
        buttonPanel.add(leave);
        buttons = new JPanel();
        buttons.add(buttonPanel);

        buttonPanel.setBackground(new Color(25, 25, 75));
        buttons.setBackground(new Color(25, 25, 75));

        //Setzen des Layouts und hinzufügen der Komponenten
        getContentPane().setLayout(new BorderLayout(1, 1));
        getContentPane().add(buttons, BorderLayout.CENTER);
        getContentPane().add(titel, BorderLayout.NORTH);

        ///////////////////////////////////////////////////////////////////////////
        // ActionListener
        ///////////////////////////////////////////////////////////////////////////

        /**
         * ActionListener für den "start" Button
         * Für das Starten des Spiels (Auswahl der Klasse die man lernen will)
         */
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    ChooseClass chooseClass = new ChooseClass();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        /**
         * ActionListener für den "klassenspiegel" Button
         * Für das Exportieren einer Klasse zu HTML
         */
        klassenspiegel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JavaToHTML javaToHTML = new JavaToHTML();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
            }
        });

        /**
         * ActionListener für den "credits" Button
         */
        credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.credit();
            }
        });

        /**
         * ActionListener für den "stats" Button
         * Für die Ausgabe der Statistiken der vergangenen Spiele
         */
        stats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Logic.fileReader();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        /**
         * ActionListener für den "leave" Button
         * Beendet die Applikation
         */
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        App app = new App();
    }
}
