import java.io.*;
import java.util.*;

public class CardTest {

    // $ java -ea CardTest

    public static void main(String[] args) {
        // ID, name, rank
        
        // Same: ID, name, rank
        Card c0 = new Card(1, "card1", Rank.UNIQUE);
        Card c1 = new Card(1, "card1", Rank.UNIQUE);
        assert 0 == c0.compareTo(c1);
        
        // Same: ID, name
        // Different: rank
        Card c2 = new Card(1, "card1", Rank.UNIQUE);
        Card c3 = new Card(1, "card1", Rank.RARE);
        assert -1 == c2.compareTo(c3); // RARE is 1 less than UNIQUE
        
        // Same: ID, name
        // Different: rank
        Card c4 = new Card(1, "card1", Rank.UNIQUE);
        Card c5 = new Card(1, "card2", Rank.UNCOMMON);
        assert -2 == c4.compareTo(c5); // UNCOMMON is 2 less than UNIQUE
        
        // Same: ID name
        // Different: rank
        Card c6 = new Card(1, "card1", Rank.UNIQUE);
        Card c7 = new Card(1, "card1", Rank.COMMON);
        assert -3 == c6.compareTo(c7); //COMMON is 3 less than UNIQUE
        
        Card c8 = new Card(1, "card1", Rank.RARE);
        Card c9 = new Card(1, "card1", Rank.UNIQUE);
        assert 1 == c8.compareTo(c9); // RARE is 1 more than UNIQUE
        
        Card c10 = new Card(1, "card1", Rank.UNCOMMON);
        Card c11 = new Card(1, "card1", Rank.UNIQUE);
        assert 2 == c10.compareTo(c11); //UNCOMMON is 2 more than UNIQUE
        
        Card c12 = new Card(1, "card1", Rank.COMMON);
        Card c13 = new Card(1, "card1", Rank.UNIQUE);
        assert 3 == c12.compareTo(c13); // COMMON is 3 more than UNIQUE
        
        // Testing to test how name interaction works
        Card c14 = new Card(1, "card1", Rank.COMMON);
        Card c15 = new Card(1, "card2", Rank.COMMON);
        assert -1 == c14.compareTo(c15);
        
        // Testing that despite there being 3 differences, rank is compared first
        Card c16 = new Card(1, "card1", Rank.COMMON);
        Card c17 = new Card(2, "card2", Rank.UNCOMMON);
        assert 1 == c16.compareTo(c17);
        
        // Testing that name is compared after rank and before ID
        Card c18 = new Card(2, "card1", Rank.COMMON);
        Card c19 = new Card(1, "card2", Rank.COMMON);
        assert -1 == c18.compareTo(c19); // returns -1 instead of 1
        
        // Testing the string representation works as intended
        Card c20 = new Card(1, "card1", Rank.UNIQUE);
        assert c20.toString().equals("ID: [1]\nName: [card1]\nRank: [UNIQUE]");
        
        Card c00 = new Card(1, "card1", Rank.UNIQUE);
        Card c01 = new Card(1, "card1", Rank.UNIQUE);
        c01.setPrice(1); // c00 price auto set to 0
        HashSet<Card> cards = new HashSet<Card>();
        cards.add(c00);
        cards.add(c01);
        assert cards.size() == 1; // size would be 1, not 2, if they are duplicated as hashSets are sets.
        
        Card c000 = new Card(1, "card1", Rank.UNIQUE);
        Card c001 = new Card(1, "card1", Rank.RARE);   
        Card c002 = new Card(2, "card1", Rank.UNIQUE); 
        Card c003 = new Card(1, "card2", Rank.UNIQUE);    
        Card c004 = new Card(2, "card2", Rank.UNIQUE);  
        TreeSet<Card> cardsTreeSet = new TreeSet<Card>();
        cardsTreeSet.add(c000);
        cardsTreeSet.add(c001);
        cardsTreeSet.add(c002);
        cardsTreeSet.add(c003);
        cardsTreeSet.add(c004);
        String string = "";
        for (Card i : cardsTreeSet) {
            string += i + "\n";
        }
        assert string.equals("ID: [1]\nName: [card1]\nRank: [UNIQUE]\nID: [2]\nName: [card1]\nRank: [UNIQUE]\nID: [1]\nName: [card2]\nRank: [UNIQUE]\nID: [2]\nName: [card2]\nRank: [UNIQUE]\nID: [1]\nName: [card1]\nRank: [RARE]\n");
        // prints in order of importance, rank --> name --> id
    }

}
