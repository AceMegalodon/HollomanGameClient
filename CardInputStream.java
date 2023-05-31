import java.io.*;
import java.util.*;
import java.net.*;

public class CardInputStream extends InputStream {

    BufferedReader br = null;

    public CardInputStream(InputStream input) {
        br = new BufferedReader(new InputStreamReader(input));
    }
    
    @Override
    public void close(){
        try {
            br.close();
        }
        catch(IOException e) {
            System.err.println("There has been an error in the close() method: " + e);
        }
    }

    @Override
    public int read() {
        return 1; // here to allow compilation, no plans to use this.
    }
    
    public Card readCard() throws IOException {
        try {
            if (br.readLine().equals("CARD")) {
                long ID = Long.parseLong(br.readLine());
                String name = br.readLine();
                Rank rank = Rank.valueOf(br.readLine());
                long price = Long.parseLong(br.readLine());
                Card card = new Card(ID, name, rank);
                card.setPrice(price);
                return card;
            }
            return null;
        }
        catch(IOException e) {
            System.err.println("There has been an error in the readCard() method: " + e);
            return null;
        }
    }
    
    public String readResponse() {
        try {
            return br.readLine();
        }
        catch(IOException e) {
            System.out.println("There has been an error in the readResponse() method: " + e);
            return null;
        }
    }
}
