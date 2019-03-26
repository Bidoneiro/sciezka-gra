package playerpaczka;
import java.util.Comparator;
/**
 *przeciazenie interfejsu comparator w celu sortowania specjalnej klasy BestPlayers
 */
public class PlayersCompare implements Comparator<BestPlayers>
{
    @Override
    public int compare(BestPlayers p1, BestPlayers p2) {
        if(p2 == null) return -1;
        if(p1.getScore() > p2.getScore())
            return -1;
        else if(p1.getScore() < p2.getScore())
            return 1;
        else return 0;
    }
}
