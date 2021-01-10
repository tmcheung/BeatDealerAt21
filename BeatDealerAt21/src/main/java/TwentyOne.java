package BeatDealerAt21.src.main.java;
import java.util.stream.Stream;

public class TwentyOne {
    private final Deck deck;
    private final Dealer dealer;
    private final Player player;

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
    }

    public void dealersTurn(){
        dealer.setScoreToBeat(player.getHandScore());
        while(dealer.draw(deck));
    }

    public Player getWinner() {
        boolean playerWon = player.getHandScore() <= TwentyOneConstants.BLACKJACK && player.getHandScore() >= dealer.getHandScore();
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
