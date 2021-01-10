import CardClasses.Card;
import CardClasses.Rank;
import CardClasses.Suit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;


public class Deck {
    private static final byte MAX_SIZE = 52;

    private final Stack<Card> cards;

    public Deck() {
        super();
        System.out.println("__Generating new deck__");
        Stack<Card> cards = new Stack<Card>();
        for (Suit suit : Suit.values()) 
            for (Rank rank : Rank.values()) 
                cards.add(new Card(suit, rank));                

        this.cards = cards;
    }

    public Deck(Stack<Card> cards) {
        super();
        this.cards = cards;
    }

    public Card draw(){
        return cards.pop();
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    public boolean isValid(){
        int amount = cards.size();
        
        if(amount > MAX_SIZE)
            return false;
        
        HashSet<Card> set = new HashSet<Card>(cards);
        if(set.size() < amount)
            return false;

        return true;
    }

    public boolean isComplete(){
        return cards.size() == MAX_SIZE;
    }
}
