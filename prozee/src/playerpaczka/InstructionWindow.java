package playerpaczka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * klasa pobierajaca intrukcje z pliku i tworzaca okienko w ktorej instrukcja jest wyswietlana
 */
public class InstructionWindow extends JFrame implements ActionListener {

    JLabel instrukcja= new JLabel();
    FileReader fileReader;
    BufferedReader bufferedReader;
    String tekst= new String();
    public InstructionWindow() {

        try {
            fileReader = new FileReader("zasoby/instrukcja.txt");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bufferedReader = new BufferedReader(fileReader);
        System.out.println(tekst);
        tekst="\n";
        String tekst2;
        try {
            while(true) {
                if ((tekst2=bufferedReader.readLine())==null) {

                    break;
                }
                tekst = tekst + tekst2 + ("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tekst);
        JOptionPane.showMessageDialog(this, tekst);

        //instrukcja = new JLabel(tekst);
        //add(instrukcja);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}