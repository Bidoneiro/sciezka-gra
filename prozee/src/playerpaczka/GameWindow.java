package playerpaczka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * klasa tworzaca okno z gra
 */
public class GameWindow extends JFrame implements ActionListener{
    JButton cancel;
    Config config = new Config();

    GameWindow(String playernick){

        pack();
        setFocusable(true);
        setSize(Config.RozmiarOknaX, Config.RozmiarOknaY);
        toFront();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Player p= new Player(getWidth()/2,700, playernick);
        cancel = new JButton ("Zamknij");
        cancel.addActionListener(this);
        Game game =new Game(p, cancel);
        add(game);
        toFront();


        addKeyListener(new KeyInput(p));
        setFocusable(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cancel) {
            dispose();
        }
    }

}
