package playerpaczka;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

/**
 * klasa odpowiedzialna za pobieranie wspolrzednych mapy, rysowanie jej oraz pozostałych elementow mapy
 */
public class Map {
    int bonusVel=0;
    int[] x1;
    int[] x2;
    int[] obst;
    int [] lifes;
    int [] bpoints;
    public int check;
    public int Width;
    public int Height;
    public int y=750;
    public int level=1;
    private int p=0;
    private Config config;
    private String leveltxt;
    private BufferedImage img = null;
    private BufferedImage imgCoin=null;
    private BufferedImage imgBomb=null;
    private BufferedImage imgTree=null;

    public Map() {
        /**
         *pobieranie danych z pliku config
         */
        config=new Config();
        x1 = new int[Config.ROZMIAR];
        x2 = new int[Config.ROZMIAR];
        obst = new int[Config.ROZMIAR];
        lifes = new int[Config.ROZMIAR];
        bpoints = new int[Config.ROZMIAR];
        for (int k = 0; k < Config.ROZMIAR; k++) {
            lifes[k]=0;
            bpoints[k]=0;
        }

        try {
            /**
             *ladowanie obrazkow
             */
            img = ImageIO.read(new File("zasoby/pixheart.gif"));
            imgTree = ImageIO.read(new File("zasoby/tree.png"));
            imgCoin = ImageIO.read(new File("zasoby/gold coin.png"));
            imgBomb = ImageIO.read(new File("zasoby/bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void ustawTablice(){
        /**
         *tworzenie zyc i dodatkowych punktow
         */
        int losowa;
        Random rand= new Random();
        for (int k = 0; k < Config.numberLifes; k++) {
            int z = (k + 1) * Config.ROZMIAR / (3 + Config.numberLifes);
            losowa=x1[z]+20+rand.nextInt((x2[z]-20)-(x1[z]+20));

            lifes[z]=(losowa);
        }
        for (int k = 0; k < Config.numberBpoints; k++) {
            int z = (k + 1) * Config.ROZMIAR / (1 + Config.numberBpoints);
            losowa=x1[z]+20+rand.nextInt((x2[z]-20)-(x1[z]+20));

            bpoints[z]=(losowa);
        }

    }

    /**
     * @param l oznacza poziom na ktorym sie znajdujemy
     */
    void pobieranie(int l) {
        /**
         * metoda odpowiada za pobieranie wspolrzednych mapy z odpowiedniego pliku
         */
        level=l;
        if (level==1) {
            leveltxt = "zasoby/map1.txt";
        } else if (level==2) {
            bonusVel=1;
            leveltxt = "zasoby/map2.txt";
        }   else if (level==3) {
            bonusVel=2;
            leveltxt = "zasoby/map3.txt";
        }

        FileReader fr = null;
        char[] buff = new char[3];
        char[] buff2 = new char[3];
        // OTWIERANIE PLIKU:
        try {
            fr = new FileReader(leveltxt);
        } catch (FileNotFoundException e) {
            System.out.println("BLAD PRZY OTWIERANIU PLIKU!");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        // ODCZYT KOLEJNYCH LINII Z PLIKU:
        try {
            for (int p = 0; (p < Config.ROZMIAR) && (check != -1); p++) {
                int arr3 = 1;
                String obstacle;
                check=bfr.read(buff, 0, 3); //scanner
                String arr = new String(buff);
                int number = Integer.parseInt(arr);
                bfr.read();
                bfr.read(buff2, 0, 3);
                String arr2 = new String(buff2);
                int number2 = Integer.parseInt(arr2);
                bfr.read();
                obstacle=bfr.readLine();
                if(obstacle.length()>1) {
                    arr3 = Integer.parseInt(obstacle);
                }

                obst[p] = arr3;
                x1[p] = number;
                x2[p] = number2;

            }
        } catch (IOException e) {
            System.out.println("BLAD ODCZYTU Z PLIKU!");
            System.exit(2);
        }

        System.out.println();
        // ZAMYKANIE PLIKU
        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("BLAD PRZY ZAMYKANIU PLIKU!");
            System.exit(3);
        }
        ustawTablice();
    }

    public void draw(Graphics2D g2d){

        for (p = 0; p < Config.ROZMIAR; p++) {

            g2d.setColor(Color.black);
            g2d.drawImage(imgTree, x1[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900, null);
            g2d.drawImage(imgTree, x2[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900, null);
            g2d.setColor(Color.RED);
            /**
             * rysowanie przeszkod
             */
            if(obst[p]!=1) {
                g2d.drawImage(imgBomb, obst[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900, null);
                g2d.drawRect(obst[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900);
            }
            g2d.setColor(Color.BLUE);
            /**
             *rysowanie dodatkowych punktow
             */
            if (bpoints[p]!=0) {
                g2d.drawImage(imgCoin, bpoints[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900, null);
            }
            /**
             *rysowanie zyc
             */
            g2d.setColor(Color.GREEN);
            if (lifes[p]!=0) {
                g2d.drawImage(img, lifes[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900, null);
                g2d.drawRect(lifes[p] * Width / 1000, (y - Config.WYSOKOSC * p) * Height / 1000, Config.SZEROKOSC * Width / 1000, Config.WYSOKOSC * Height / 900);
            }
            /**
             *rysowanie mapy
             */
            g2d.setColor(Color.RED);
            g2d.fillRect(Width * (x1[Config.ROZMIAR - 1]) / 1000, (y - Config.WYSOKOSC * Config.ROZMIAR) * Height / 1000, (x2[Config.ROZMIAR - 1] - x1[Config.ROZMIAR - 1] + Config.SZEROKOSC) * Width / 1000, 31);
        }
    }

    /**
     * @param speed wartosc przyspieszania i zwalniania przy pomocy strzalek gora dol
     */
    public void update(int speed) {
        y = y + Config.velMap + speed + bonusVel;
    }

    public void reset(){
        y=750;
        p=0;
    }
}