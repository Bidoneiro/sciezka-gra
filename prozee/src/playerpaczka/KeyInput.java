package playerpaczka;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import playerpaczka.Player;

public class KeyInput extends KeyAdapter {
    Player p;

    public KeyInput(Player p) {
        this.p=p;
    }

    public void keyPressed(KeyEvent e)
    {

        p.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {

        p.keyReleased(e);
    }

};