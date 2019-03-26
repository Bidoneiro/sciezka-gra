package playerpaczka;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

/**
 * klasa reprezentujaca gracza
 */
public class Player extends GlobalPosition {
    int points=0;
    String nick;
    private int velX=0;
    int life1=3;
    int wid;
    int heig;
    private boolean pause=false;
    private int k=0;
    private int lifeMAX;
    private Config config;
    private int speed=0;
    private BufferedImage imgCar;

    public Player(int x, int y, String playernick) {
        super(x, y);
        try {
            imgCar = ImageIO.read(new File("zasoby/car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lifeMAX = Config.MAXlifes;
        config=new Config();
        nick=playernick;
    }

    /**
     * uaktualniamy pozycje gracza na nowej mapie
     */
    public void playerUpdatePosition(Map map){

        xx=(map.x1[5]+map.x2[5])/2;
    }

    /**
     *aktualizacja pozycji gracza, ilosci punktow
     */

    public int update(Map map){
        if (!pause){
            points = points + (1 + Config.velMap) * (1 + speed) / 4;
            k = collision(map);
            xx=(xx+velX);
            map.update(speed);

        }
        return k;
    }

    /**
     * metoda rysujaca gracza
     */
    public void draw(Graphics2D g2d) {

        g2d.drawImage(imgCar,xx*wid/1000 , yy*heig/1000, 30*wid/1000, 40*heig/1000,null);
        g2d.drawRect(xx*wid/1000 , yy*heig/1000, 30*wid/1000, 40*heig/1000);

    }

    /**
     * @param map przekazujemy obecna mape aby sprawdzic czy przekroczylismy juz mete
     * metoda sprawdzajaca zderzenie z meta
     */
    public int end(Map map){
        int r=0;
        Rectangle pl = new Rectangle(xx*wid/1000, yy*heig/1000, 20*wid/1000, 30*heig/1000);
        Rectangle meta = new Rectangle(map.Width * (map.x1[Config.ROZMIAR - 1]) / 1000, (map.y - Config.WYSOKOSC * Config.ROZMIAR) * heig / 1000, (map.x2[Config.ROZMIAR - 1] - map.x1[Config.ROZMIAR - 1]) * map.Width / 1000, 31);

        if (pl.intersects(meta)) {
            System.out.println("wygrałes!!!!!!!");
            r=1;
            xx=((map.x1[3]+map.x2[2])/2);
        }
        return r;
    }

    /**
     * @param map przekazujemy obecna mape aby moc okreslic wspolrzedne potrzebne do sprawdzania kolizji
     *metoda sprawdzajaca zderzenia z krawedziami mapy, przeszkodami, dodatkowymi punkatmi oraz zyciami
     */
    public int collision(Map map) {
        int f = 0;
        for (int i = 0; i < Config.ROZMIAR; i++) {
            //sprawdzanie zderzen z krawedziami drogi
            Rectangle obst = new Rectangle(map.obst[i] * map.Width / 1000, map.y - Config.WYSOKOSC * i, Config.SZEROKOSC * map.Width / 1000, Config.WYSOKOSC * map.Height / 900);
            Rectangle lifes = new Rectangle(map.lifes[i] * map.Width / 1000, map.y - Config.WYSOKOSC * i, Config.SZEROKOSC * map.Width / 1000, Config.WYSOKOSC * map.Height / 900);
            Rectangle bpoints = new Rectangle(map.bpoints[i] * map.Width / 1000, map.y - Config.WYSOKOSC * i, Config.SZEROKOSC * map.Width / 1000, Config.WYSOKOSC * map.Height / 900);

            Rectangle rec = new Rectangle(map.x1[i] * map.Width / 1000, map.y - Config.WYSOKOSC * i, Config.SZEROKOSC * map.Width / 1000, Config.WYSOKOSC * map.Height / 900);
            Rectangle rec2 = new Rectangle(map.x2[i] * map.Width / 1000, map.y - Config.WYSOKOSC * i, Config.SZEROKOSC * map.Width / 1000, Config.WYSOKOSC * map.Height / 900);
            Rectangle pl = new Rectangle(xx*map.Width/1000, yy, 20*map.Width/1000, 30*map.Height/900);

            if (pl.intersects(lifes)){
                if(life1<lifeMAX) {
                    life1++;
                    map.lifes[i] = 0;
                }else {
                    points = points + Config.lifesBonus;
                    map.lifes[i] = 0;
                }
            }
            if (pl.intersects(bpoints)){
                points = points + Config.points;
                map.bpoints[i]=0;

            }
            if (pl.intersects(rec)||pl.intersects(rec2)||pl.intersects(obst)) {
                map.obst[i]=1;
                life1=life1-1;
                points = points - 1000;
                yy=700;
                xx=((map.x1[i]+map.x2[i])/2);
                f =1;
                for(int z = -1; z<2; z++) {
                    if (i < Config.ROZMIAR - 3) {
                        map.obst[i + z] = 1;
                    }
                }
            }else if (life1==0) {
                f =2;
            }
        }
        return f;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT){
            velX=2+speed/2;
        }else if(key==KeyEvent.VK_LEFT) {
            velX = -2-speed/2;
        }else if(key==KeyEvent.VK_UP) {
            speed=1;
        }else if(key==KeyEvent.VK_DOWN) {
            speed=-3;
        }else if (key==KeyEvent.VK_ENTER) {
            pause = !pause;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key==KeyEvent.VK_RIGHT){
            velX=0;
        }else if(key==KeyEvent.VK_LEFT){
            velX=0;
        }else if(key==KeyEvent.VK_UP){
            speed=0;
        }else if(key==KeyEvent.VK_DOWN){
            speed=0;
        }
    }
}
