package playerpaczka;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * klasa w ktorej rysowany jest panel z informacjami po prawej stronie ekranu
 */
public class InfoSquare extends JPanel implements ActionListener {
        private int WIDTH;
        private int HEIGHT;
        Player player;
        int lvl;
        JButton przycisk;
    public InfoSquare(Player p, int width, int height,int level){
        this.player=p;
        WIDTH=width;
        HEIGHT=height;
        lvl=level;
        przycisk= new JButton("Przerwij gre");
    }
    public void draw(Graphics2D g2d){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("zasoby/pixheart.gif"));
        } catch (IOException e) {
        }
        img.getHeight();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(WIDTH-256*WIDTH/1000,0,500*WIDTH/1000, HEIGHT);
        for (int i=0; i<player.life1;i++) {
            g2d.drawImage(img, WIDTH - 100*WIDTH/1000, (123+i*50)*HEIGHT/1000, 30*WIDTH/1000, 33*HEIGHT/1000, null);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawString("Liczba punktow: " + Integer.toString(player.points), player.wid - 200 * WIDTH / 1000, 100 * HEIGHT / 1000);
        g2d.drawString("Poziom: "+lvl,player.wid-200*WIDTH/1000,750*HEIGHT/1000);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
