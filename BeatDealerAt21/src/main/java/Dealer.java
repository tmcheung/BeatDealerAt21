
public class Dealer extends Player {

    private int scoreToBeat;

    public Dealer(String name) {
        super(name);
    }
    
    public void setScoreToBeat(int score){
        this.scoreToBeat = score;
    }

    @Override
    protected boolean shouldDraw(){
        return scoreToBeat < TwentyOneConstants.BLACKJACK && getHandScore() <= scoreToBeat;
    }
}
