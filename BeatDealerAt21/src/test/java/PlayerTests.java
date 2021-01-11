import CardClasses.Card;
import CardClasses.Rank;
import CardClasses.Suit;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class PlayerTests {
    Player player;
    @Before
    public void InitPlayers(){
        player = new Player("sam");
    }

    @DataPoints
    public static Deck[] deckData(){
        Stack<Card> cards1 = new Stack<Card>();
        cards1.add(new Card(Suit.Clubs, Rank.Two));
        cards1.add(new Card(Suit.Clubs, Rank.Two));
        cards1.add(new Card(Suit.Clubs, Rank.Six));
        cards1.add(new Card(Suit.Clubs, Rank.Ten));

        Stack<Card> cards2 = new Stack<Card>();
        cards2.add(new Card(Suit.Clubs, Rank.Two));
        cards2.add(new Card(Suit.Clubs, Rank.Two));
        cards2.add(new Card(Suit.Clubs, Rank.Five));
        cards2.add(new Card(Suit.Clubs, Rank.Ten));

        return new Deck[]{new Deck(cards1), new Deck(cards2)};
    }

    @Test
    public void should_stop_drawing_when_bust(){
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Two));
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        Deck deck = new Deck(cards);

        player.draw(deck);
        player.draw(deck);
        player.draw(deck);

        assertTrue(player.getHandScore() == 22);
    }

    @Test
    public void should_stop_drawing_when_blackjack(){
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Two));
        cards.add(new Card(Suit.Clubs, Rank.Ten));
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        Deck deck = new Deck(cards);

        player.draw(deck);
        player.draw(deck);
        player.draw(deck);

        assertTrue(player.getHandScore() == 21);
    }

    @Theory
    public void should_draw_until_seventeen(Deck deck){
//        Stack<Card> cards = new Stack<Card>();
//        cards.add(new Card(Suit.Clubs, Rank.Two));
//        cards.add(new Card(Suit.Clubs, Rank.Two));
//        cards.add(new Card(Suit.Clubs, Rank.Five));
//        cards.add(new Card(Suit.Clubs, Rank.Ten));
//        Deck deck = new Deck(cards);

        while(player.draw(deck));

        assertTrue(player.getHandScore() >= 17);
    }
}
