package CardClasses;
public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        super();
        this.suit = suit;
        this.rank = rank;
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int hashCode(){
        int prime = 17;
        int hash = 1;
        hash *= suit.hashCode() * prime;
        hash *= rank.hashCode() * prime;
        return hash;
    }

    @Override
    public boolean equals(Object o){
        return hashCode() == o.hashCode();
    }
}
