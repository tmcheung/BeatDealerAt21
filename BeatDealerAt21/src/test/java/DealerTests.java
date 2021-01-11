import CardClasses.Card;
import CardClasses.Rank;
import CardClasses.Suit;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class DealerTests {
    Player player;
    Dealer dealer;
    @Before
    public void InitPlayers(){
        player = new Player("sam");
        dealer = new Dealer("dealer");
    }

    @DataPoints
    public static Deck[] deckData(){
        Stack<Card> cards1 = new Stack<Card>();
        cards1.add(new Card(Suit.Clubs, Rank.Ten));
        cards1.add(new Card(Suit.Clubs, Rank.Ten));

        Stack<Card> cards2 = new Stack<Card>();
        cards2.add(new Card(Suit.Clubs, Rank.Nine));
        cards2.add(new Card(Suit.Clubs, Rank.Ten));

        return new Deck[]{new Deck(cards1), new Deck(cards2)};
    }

    @Theory
    public void should_draw_until_higher_not_equal(Deck playerDeck){
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Ten));
        cards.add(new Card(Suit.Clubs, Rank.Ten));
        cards.add(new Card(Suit.Clubs, Rank.Ten));
        Deck deck = new Deck(cards);

        while(player.draw(playerDeck));
        dealer.setScoreToBeat(player.getHandScore());
        while(dealer.draw(deck));

        assertTrue(player.getHandScore() < dealer.getHandScore());
    }

    @Test
    public void should_draw_not_draw_if_player_busts(){
        Stack<Card> playerCards = new Stack<Card>();
        playerCards.add(new Card(Suit.Clubs, Rank.Ace));
        playerCards.add(new Card(Suit.Clubs, Rank.Ace));
        Deck playerDeck = new Deck(playerCards);


        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Ten));
        Deck deck = new Deck(cards);

        while(player.draw(playerDeck));
        dealer.setScoreToBeat(player.getHandScore());
        while(dealer.draw(deck));

        assertTrue(dealer.getHandScore() == 0);
    }
}
