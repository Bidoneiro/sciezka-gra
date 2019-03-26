package playerpaczka;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * klasa odpowiedzalna zap obierania dancyh z pliku config
 */
public class Config {
    static int ROZMIAR = 100;
    static int WYSOKOSC = 40;
    static int SZEROKOSC = 25;
    static int velMap = 4;
    static int MAXlifes = 6;
    static int numberLifes;
    static int numberBpoints;
    public static int points;
    static int lifesBonus;
    static int RozmiarOknaX;
    static int RozmiarOknaY;

    Properties config = new Properties();


    public Config(){

        Pobieranie();
        ustawienie();
    }

    public void Pobieranie() {
        FileInputStream in = null;

        try {
            in = new FileInputStream("zasoby/config1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("1wyjatek");
        }
        try {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("2222wyjatek");
        }

    }

    private void ustawienie(){
        ROZMIAR = Integer.parseInt(config.getProperty("ROZMIAR"));
        WYSOKOSC = Integer.parseInt(config.getProperty("WYSOKOSC"));
        SZEROKOSC = Integer.parseInt(config.getProperty("SZEROKOSC"));
        velMap = Integer.parseInt(config.getProperty("velMap"));
        MAXlifes = Integer.parseInt(config.getProperty("MAXlifes"));
        numberLifes = Integer.parseInt(config.getProperty("NumberOfLifes"));
        numberBpoints = Integer.parseInt(config.getProperty("NumberOfbpoints"));
        points = Integer.parseInt(config.getProperty("AmountBonuspoints"));
        lifesBonus = Integer.parseInt(config.getProperty("LifeBonuspoints"));
        RozmiarOknaX = Integer.parseInt(config.getProperty("ROZMIAROKNAX"));
        RozmiarOknaY = Integer.parseInt(config.getProperty("ROZMIAROKNAY"));
    }

}