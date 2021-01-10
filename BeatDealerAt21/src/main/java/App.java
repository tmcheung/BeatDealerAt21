package BeatDealerAt21.src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import BeatDealerAt21.src.main.java.Card.Card;
import BeatDealerAt21.src.main.java.Card.Rank;
import BeatDealerAt21.src.main.java.Card.Suit;

public class App 
{
    public static void main( String[] args )
    {
        Deck deck = null;
        Player sam = new Player("Sam");
        Dealer dealer = new Dealer("Dealer");

        File file = tryGetFile(args);
        if(file != null){
            Deck deckFromFile = readDeckFromFile(file);
            if(deckFromFile.isValid() && deckFromFile.isComplete()){
                deck = deckFromFile;
            }
            else{
                System.out.println("Incomplete or invalid deck found, disposing...");
            }
        }

        if(deck == null){
            deck = new Deck();
            deck.shuffleCards();
        }

        TwentyOne game = new TwentyOne(deck, dealer, sam);
        play(game);
        presentResults(game);
    }

    private static void play(TwentyOne game) {
        game.dealInitialHands();
        if(game.hasInitialWinner()) return;

        game.playersTurn();
        game.dealersTurn();
    }

    private static void presentResults(TwentyOne game){
        Player winner = game.getWinner();
        System.out.println(winner.getName());
        game.getPlayers()
            .forEach(p -> {
                System.out.printf("%s: ", p.getName());
                ArrayList<Card> hand = p.getHand();
                int handSize = hand.size();
                for(int i = 0; i < handSize; i++){
                    String s = String.format("%s%s", hand.get(i).getSuit().getShorthand(), hand.get(i).getRank().getShorthand());
                    s += (i == handSize-1) ? "\n" : ", ";
                    System.out.printf(s);
                }
            });
    }

    private static Deck readDeckFromFile(File file) {
        Scanner reader = null;
        try{
            reader = new Scanner(file);
        }catch(FileNotFoundException e){
            System.out.println("File does not exist");
            return null;
        }

        try{
            Stack<Card> cards = new Stack<Card>();
            String deckString = reader.nextLine();
            HashMap<String, Suit> suitMap = Suit.getShorthandMap();
            HashMap<String, Rank> rankMap = Rank.getShorthandMap();

            //Just having fun with StreamAPI (O(2n) instead of O(n))
            Arrays.stream(deckString.split(", "))
                .map(s -> new Card(suitMap.get(s.substring(0,1)), rankMap.get(s.substring(1))))
                .forEach(c -> cards.add(c));
            //end fun

            return new Deck(cards);
        }catch(Exception e){
            System.out.printf("Error occured while reading file: %s\n", e.getMessage());
            return null;
        }finally{
            reader.close();
        }
    }

    private static File tryGetFile(String[] args) {
        if(args.length < 1){
            return null;
        }

        String filename = args[0];
        try{
            ClassLoader l = App.class.getClassLoader();
            URL u = l.getResource(filename);
            URI r = u.toURI();
            return new File(r);
        }catch(URISyntaxException e){
            System.out.println("Invalid URI for file");
            return null;
        }

    }
}
