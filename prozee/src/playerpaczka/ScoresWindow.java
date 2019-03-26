package playerpaczka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *klasa tworzaca okienko w ktorym wyswietlane sa wysokie wyniki
 */
public class ScoresWindow extends JFrame implements ActionListener {
    private JLabel []Player;
    private JButton bcancel;

    public ScoresWindow(HighScores2 wyniki) {
        Player = new JLabel[wyniki.iloscWynikow];
        setFocusable(true);
        setTitle("High Scores");
        setResizable(false);
        setSize(250, 250);
        Container contain =getContentPane();
        SpringLayout layout= new SpringLayout();

        for (int k = 0; k < wyniki.iloscWynikow; k++) {
            Player[k] = new JLabel((k + 1) + ".   " + wyniki.PlayersArr[k].name + ":    " + wyniki.PlayersArr[k].getScoreString());
            contain.add(Player[k]);

        }

        layout.putConstraint(SpringLayout.WEST, Player[0],20, SpringLayout.WEST, contain);
        layout.putConstraint(SpringLayout.NORTH, Player[0],12, SpringLayout.NORTH, contain);
        for (int k = 1; k < wyniki.iloscWynikow; k++) {
            layout.putConstraint(SpringLayout.WEST, Player[k], 20, SpringLayout.WEST,contain);
            layout.putConstraint(SpringLayout.NORTH, Player[k], 20, SpringLayout.SOUTH, Player[k-1]);
        }
        bcancel = new JButton ("Wyjście");
        contain.setLayout(layout);
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