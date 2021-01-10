package BeatDealerAt21.src.main.java.Card;

import java.util.HashMap;

public enum Suit {
    Clubs("C"),
    Diamonds("D"),
    Hearts("H"),
    Spades("S");

    private final String shorthand;
    Suit(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getShorthand(){
        return shorthand;
    }

    private static HashMap<String, Suit> shorthandMap = null;
    public static HashMap<String, Suit> getShorthandMap(){
        if(shorthandMap != null) return shorthandMap;
        
        HashMap<String, Suit> m = new HashMap<String, Suit>();
        for (Suit suit : Suit.values()) {
            m.put(suit.shorthand, suit);
        }

        shorthandMap = m;
        return shorthandMap;
    }
}
