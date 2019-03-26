package playerpaczka;

/**
 * klasa reprezentujaca obiekty ktore odzwierciedlaja instancje graczy sluzace do zapisywania wysokkich wynikow
 */
public class BestPlayers {
    public String name;
    public int score;

    public BestPlayers(String nazwaGracza, int wynik) {

        name = nazwaGracza;
        score = wynik;
    }

    public int getScore(){return score;}

    public String getScoreString() {
        return Integer.toString(score);
    }

    public void show() {
        System.out.println(name);
        System.out.println(score);
    }
}
