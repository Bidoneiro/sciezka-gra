package playerpaczka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *klasa odpowiedzialna za wyswietlanie komunikatow po wygranej lub przegrnaej
 */
public class InfoWindow extends JFrame implements ActionListener {
    private JLabel nameLabe;
    private JButton bcancel;

    public InfoWindow(int komunikat, Player p, JButton b1){
        setFocusable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(300, 200, 300, 200);
        JPanel przyciski = new JPanel();
        bcancel = b1;
        if (komunikat==1){
            nameLabe = new JLabel("Przegrana! Twoj wynik to: " + p.points);
        }else if (komunikat==2) {
            nameLabe = new JLabel("Wygrana! Twoj wynik to: " + p.points);
        }
        bcancel.setBounds(50,70, 100, 40);
        nameLabe.setBounds(50, 30, 200, 40);
        przyciski.add(bcancel);
        przyciski.add(nameLabe);

        przyciski.setLayout(null);
        add(przyciski);
        bcancel.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == bcancel) {
            dispose();


        }
    }
}