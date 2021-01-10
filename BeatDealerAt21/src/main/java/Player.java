import java.util.ArrayList;
import CardClasses.Card;

public class Player {

    private final String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        super();
        this.name = name;
        this.hand = new ArrayList<Card>();
    }

    public void receiveCard(Card card){
        hand.add(card);
    }

    protected boolean shouldDraw() {
        return getHandScore() < TwentyOneConstants.PLAYER_SCORE_LIMIT;
    }

    public boolean draw(Deck deck){
        if(shouldDraw()){
            hand.add(deck.draw());
            return true;
        }
        return false;
    }

    public int getHandScore(){
        return hand
            .stream()
            .map(c -> c.getRankValue())
            .reduce(0, (a, b) -> a + b);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
