import java.io.*;
import java.util.*;

public class Card implements Comparable<Card>{
    
    private long ID;
    private String name;
    private Rank rank;
    private long price;
    
    public Card(long ID, String name, Rank rank) {
        this.ID = ID;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }
    
    public long getID () {return ID;}
    
    public String getName() {return name;}
    
    public Rank getRank () {return rank;}
    
    public long getPrice () {return price;}
    
    public void setPrice (long price) {this.price = price;}
    
    public String toString() {
        return  "ID: [" + ID + "]\nName: [" + name + "]\nRank: [" + rank + "]"; // returns string representation, with a new line for each property. Use of [] to reveal any white space
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true; // if comparing object to itself, return true
        }
        if (!(o instanceof Card card)) {
            return false; // if o is not a card object, return false
        }
        
        return ID == card.ID && name.equals(card.name) && rank == card.rank; // returns a boolean value if they have the same properties
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(ID, name, rank); // returns a hash code for the object
    }
    
    @Override
    public int compareTo(Card otherCard) {
        if (rank != otherCard.rank) {
            return rank.compareTo(otherCard.rank); // rank takes priority
        }
        if (!name.equals(otherCard.name)) {
            return name.compareTo(otherCard.name);
        }
        if (ID != otherCard.ID) {
            return Long.compare(ID, otherCard.ID);
        }
        return 0;
    }
}
