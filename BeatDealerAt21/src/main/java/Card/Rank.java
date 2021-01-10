package BeatDealerAt21.src.main.java.Card;

import java.util.HashMap;

public enum Rank {
    Two("2", 2),
    Three("3",3),
    Four("4", 4),
    Five("5", 5),
    Six("6", 6),
    Seven("7", 7),
    Eight("8", 8),
    Nine("9", 9),
    Ten("10", 10),
    Jack("J", 10),
    Queen("Q", 10),
    King("K", 10),
    Ace("A", 11);

    private final int value;
    private final String shorthand;
    Rank(String shorthand, int value){
        this.value = value;
        this.shorthand = shorthand;
    }

    public String getShorthand() {
		return shorthand;
	}

	public int getValue(){
        return value;
    }

    private static HashMap<String, Rank> shorthandMap = null;
    public static HashMap<String, Rank> getShorthandMap(){
        if(shorthandMap != null) return shorthandMap;
        
        HashMap<String, Rank> m = new HashMap<String, Rank>();
        for (Rank rank : Rank.values()) {
            m.put(rank.shorthand, rank);
        }

        shorthandMap = m;
        return shorthandMap;
    }
}