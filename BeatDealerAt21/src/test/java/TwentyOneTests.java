import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import CardClasses.Card;
import CardClasses.Rank;
import CardClasses.Suit;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import java.util.Stack;

@RunWith(Theories.class)
public class TwentyOneTests
{
    Player player;
    Dealer dealer;
    @Before
    public void InitPlayers(){
        player = new Player("sam");
        dealer = new Dealer("dealer");
    }

    @Test
    public void players_should_initally_have_two_cards()
    {
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Two));
        cards.add(new Card(Suit.Clubs, Rank.Three));
        cards.add(new Card(Suit.Clubs, Rank.Four));
        cards.add(new Card(Suit.Clubs, Rank.Five));
        cards.add(new Card(Suit.Clubs, Rank.Six));
        Deck deck = new Deck(cards);
        TwentyOne game = new TwentyOne(deck, dealer, player);

        game.dealInitialHands();

        assertTrue(player.getHand().size() == 2);
        assertTrue(dealer.getHand().size() == 2);
    }

    @Test
    public void dealer_should_be_initial_winner_when_both_bust()
    {
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        cards.add(new Card(Suit.Spades, Rank.Ace));
        cards.add(new Card(Suit.Diamonds, Rank.Ace));
        cards.add(new Card(Suit.Hearts, Rank.Ace));
        Deck deck = new Deck(cards);
        TwentyOne game = new TwentyOne(deck, dealer, player);

        game.dealInitialHands();

        assertTrue(game.hasInitialWinner());
        assertTrue(game.getWinner() == dealer);
    }

    @Test
    public void player_should_be_initial_winner_when_both_blackjack()
    {
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.Ace));
        cards.add(new Card(Suit.Spades, Rank.Ace));
        cards.add(new Card(Suit.Diamonds, Rank.King));
        cards.add(new Card(Suit.Hearts, Rank.King));
        Deck deck = new Deck(cards);
        TwentyOne game = new TwentyOne(deck, dealer, player);

        game.dealInitialHands();

        assertTrue(game.hasInitialWinner());
        assertTrue(game.getWinner() == player);
    }

    @Test
    public void should_not_have_initial_winner_when_both_less_than_blackjack()
    {
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Clubs, Rank.King));
        cards.add(new Card(Suit.Spades, Rank.King));
        cards.add(new Card(Suit.Diamonds, Rank.King));
        cards.add(new Card(Suit.Hearts, Rank.King));
        Deck deck = new Deck(cards);
        TwentyOne game = new TwentyOne(deck, dealer, player);

        game.dealInitialHands();

        assertFalse(game.hasInitialWinner());
    }

//    @Theory
//    public void player_should_draw_until_17_or_bust()
//    {
//    }

//    @Theory
//    public void dealer_should_draw_until_higher_or_bust()
//    {
//    }

    @Test
    public void dealer_should_win_if_player_bust()
    {
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Hearts, Rank.Ten));
        cards.add(new Card(Suit.Spades, Rank.Ten));
        cards.add(new Card(Suit.Diamonds, Rank.Ten));
        cards.add(new Card(Suit.Hearts, Rank.Ace));
        cards.add(new Card(Suit.Clubs, Rank.Two));

        Deck deck = new Deck(cards);
        TwentyOne game = new TwentyOne(deck, dealer, player);

        game.dealInitialHands();
        game.playersTurn();
        game.dealersTurn();

        assertTrue(game.getWinner() == dealer);
    }

    @Test
    public void player_should_win_if_dealer_bust()
    {
        Stack<Card> cards = new Stack<Card>();
        cards.add(new Card(Suit.Hearts, Rank.Eight));
        cards.add(new Card(Suit.Spades, Rank.Six));
        cards.add(new Card(Suit.Diamonds, Rank.Ten));
        cards.add(new Card(Suit.Hearts, Rank.Nine));
        cards.add(new Card(Suit.Clubs, Rank.Seven));

        Deck deck = new Deck(cards);
        TwentyOne game = new TwentyOne(deck, dealer, player);

        game.dealInitialHands();
        game.playersTurn();
        game.dealersTurn();

        assertTrue(game.getWinner() == player);
    }
}
