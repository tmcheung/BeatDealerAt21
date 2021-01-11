import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import CardClasses.Card;
import CardClasses.Rank;
import CardClasses.Suit;
import org.junit.Test;

import java.util.Stack;

public class DeckTests {
    @Test
    public void should_be_invalid_when_duplicates_found(){
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        cards.add(new Card(Suit.Spades, Rank.Ace));
        cards.add(new Card(Suit.Hearts, Rank.Ace));
        cards.add(new Card(Suit.Hearts, Rank.Ace));
        Deck deck = new Deck(cards);

        boolean validDeck = deck.isValid();

        assertFalse(validDeck);
    }

    @Test
    public void should_be_valid_when_all_unique(){
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        cards.add(new Card(Suit.Spades, Rank.Ace));
        cards.add(new Card(Suit.Diamonds, Rank.Ace));
        cards.add(new Card(Suit.Hearts, Rank.Ace));
        Deck deck = new Deck(cards);

        boolean validDeck = deck.isValid();

        assertTrue(validDeck);
    }

    @Test
    public void should_not_be_complete_when_less_than_fiftytwo_cards(){
        Deck deck = new Deck();
        deck.draw();

        boolean isComplete = deck.isComplete();

        assertFalse(isComplete);
    }

    @Test
    public void should_generate_complete_valid_deck(){
        Deck deck = new Deck();

        boolean isComplete = deck.isComplete();
        boolean isValid = deck.isComplete();

        assertTrue(isComplete);
        assertTrue(isValid);
    }
}
