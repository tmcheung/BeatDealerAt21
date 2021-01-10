import java.util.stream.Stream;

public class TwentyOne {
    private final Deck deck;
    private final Dealer dealer;
    private final Player player;
    private boolean playerHasPlayed = false;
    private boolean dealerHasPlayed = false;

    public TwentyOne(Deck deck, Dealer dealer, Player player) {
        super();
        this.deck = deck;
        this.dealer = dealer;
        this.player = player;
    }

    public void dealInitialHands() {
        player.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        player.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
    }

    public void playersTurn(){
        while(player.draw(deck));
        playerHasPlayed = true;
    }

    public void dealersTurn(){
        if(!playerHasPlayed){
            System.out.println("Player must play first");
            return;
        }

        dealer.setScoreToBeat(player.getHandScore());
        while(dealer.draw(deck));
        dealerHasPlayed = true;
    }

    public Player getWinner() {
        int playerScore = player.getHandScore();
        int dealerScore = dealer.getHandScore();
        boolean playerWon = playerScore <= TwentyOneConstants.BLACKJACK && (playerScore >= dealerScore || dealerScore > TwentyOneConstants.BLACKJACK);
        if(playerWon)
            return player;
        return dealer;
    }

	public boolean hasInitialWinner() {
		return player.getHandScore() >= TwentyOneConstants.BLACKJACK || dealer.getHandScore() >= TwentyOneConstants.BLACKJACK;
    }

    public Stream<Player> getPlayers(){
        Stream.Builder<Player> stream = Stream.builder();
        return stream.add(player)
            .add(dealer)
            .build();
    }
}
