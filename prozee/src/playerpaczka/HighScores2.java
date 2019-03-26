package playerpaczka;


import java.io.*;
import java.util.Arrays;

/**
 * klasa odpowiedzialna za pobieranie wysokich wynikow z pliku i zapisywanie ich tam
 */
public class HighScores2 {
    boolean emptyFile;
    int iloscWynikow=5;

    public BestPlayers[]PlayersArr= new BestPlayers[iloscWynikow];
    public String nick;
    public int wynik;
    FileWriter file;
    BufferedWriter writer;
    String TextName;

    public HighScores2() {

        for(int j = 0; j<iloscWynikow; j++){
            PlayersArr[j]= new BestPlayers("bidon",15);
        }
        TextName = "zasoby/wyniki.txt";

        PlayersCompare comp= new PlayersCompare();
        PobranieWynikow();
        /**
         *sortowanie wynikow w tablicy
         */
        Arrays.sort(PlayersArr,comp);


    }

    /**
     *dodanie nowego wyniku do tablicy jesli jest on wystarczajaco wysoki
     */
    public void DodajWynik(Player player){

        PlayersCompare comp= new PlayersCompare();
        BestPlayers newPlayer= new BestPlayers(player.nick, player.points);
        if(1!=(comp.compare(newPlayer,PlayersArr[4]))){
            PlayersArr[4]=newPlayer;
        }
        Arrays.sort(PlayersArr,comp);
        ZapisWyniku();

    }

    /**
     *zapis tablicy z wynikami do pliku
     */
    public void ZapisWyniku(){
        FileWriter file= null;
        try {
            file = new FileWriter(TextName);
            BufferedWriter writer = new BufferedWriter(file);
            for(int k = 0; k<iloscWynikow; k++){
                writer.write(PlayersArr[k].name);
                writer.newLine();
                writer.write(Integer.toString(PlayersArr[k].score));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * pobieranie wysokich wynikow z pliku do tablicy
     */
    public void PobranieWynikow(){

        try {
            FileReader file = new FileReader(TextName);
            BufferedReader reader = new BufferedReader(file);
            for(int k = 0; k<iloscWynikow; k++){
                PlayersArr[k].name=reader.readLine();
                if(PlayersArr[k].name==null){
                    emptyFile=true;
                    break;
                }else {
                    PlayersArr[k].score= Integer.parseInt(reader.readLine());
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
