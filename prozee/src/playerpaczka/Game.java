package playerpaczka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/**
 * klasa w ktorej zawiera sie glowna "petla" gry
 */
public class Game extends JPanel implements ActionListener {


    private Timer Gamelooptime;
    private Player p;
    private Map map;
    private InfoSquare infoSquare;
    private HighScores2 wyniki;
    private int level = 1;
    private JButton button1;
    private int WIDTH;
    private int HEIGHT;
    private Config config = new Config();
    public Game(Player p, JButton b1){
        WIDTH = Config.RozmiarOknaX;
        HEIGHT = Config.RozmiarOknaY;
        setFocusable(true);
        wyniki=new HighScores2();
        this.p=p;
        map= new Map();
        map.pobieranie(level);
        Gamelooptime= new Timer (10,this);
        Gamelooptime.setInitialDelay(2000);
        Gamelooptime.start();
        p.playerUpdatePosition(map);
        setBackground(Color.ORANGE);
        button1 =b1;
        button1.addActionListener(this);
        button1.setBounds(10,10 ,100,50);
        setLayout(null);
        add(button1);
    }
    public void paint (Graphics g){
        super.paint(g);
        int WIDTH = getWidth();
        int HEIGHT = getHeight();
        Graphics2D g2d=(Graphics2D) g;
        infoSquare = new InfoSquare(p, WIDTH, HEIGHT, level);
        map.Width = WIDTH;
        map.Height = HEIGHT;
        p.wid = WIDTH;
        p.heig = HEIGHT;
        p.draw(g2d);
        map.draw(g2d);
        infoSquare.draw(g2d);

    }

    @Override
    public void actionPerformed (ActionEvent e) {
        /**
         *aktualizowanie i sprawdzanie informacji o stanie gry
         */
        Object source = e.getSource();
        if (source == button1) {
            Gamelooptime.stop();
        }else {
            int kolizja = p.update(map);
            int wygrana = p.end(map);
            if (wygrana == 1) {
                if (level == 3) {
                    /**
                     *sprawdzamy czy osiagnelismy mete w poziomie 3, jesli tak wygrywamy. Program zapisuej wynik i zatrzymuje timer
                     */
                    wyniki.DodajWynik(p);
                    InfoWindow infoWindow = new InfoWindow(2, p,button1);
                    Gamelooptime.stop();
                } else {
                    /**
                     *jesli nie jestesmy na 3 lvl osiagnicie mety powoduje aktualizacje mapy i zmiane poziomu
                     */
                    level = level + 1;
                    Gamelooptime.restart();
                    map.pobieranie(level);
                    p.playerUpdatePosition(map);
                    map.reset();

                }
            } else {

                if (kolizja == 1 || kolizja == 2) {
                    Gamelooptime.restart();
                    repaint();
                    /**
                     * Sprawdzamy czy nie została zwrocona informacja o kolizji
                     */

                    if (2 == kolizja) {
                        /**
                         * sprawdzamy ilsoc zyc
                         *
                         */
                        wyniki.DodajWynik(p);
                        Gamelooptime.stop();
                        InfoWindow infoWindow = new InfoWindow(1, p, button1);

                    }
                }
            }
            repaint();
        }

    }
}
