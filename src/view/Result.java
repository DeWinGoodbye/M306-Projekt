package view;

import control.Logic;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Ausgabe des Resultates mittels JPanel
 * Mit Datum und Zeit
 * Wieviel man von weivielen Fragen der User korrekt hatte
 * Wieviel Prozent der Fragen vom User korrekt war
 *
 * @author Devin Kutbay
 * @date 08.10.2021
 */
public class Result {

    public static void main() {
        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "Resultate"));


        JTextArea display = new JTextArea(Logic.stats, 16, 58);
        display.setEditable(false); //Read only
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        middlePanel.add(scroll);    //Fügt eine Scroll funktion hinzu

        JFrame frame = new JFrame();
        frame.add(middlePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


