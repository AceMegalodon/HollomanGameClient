import java.io.*;
import java.util.*;
import java.net.*;

/*///////////////////////////////////

    Login: service
    Password: everyfoodplant
    
/*///////////////////////////////////


public class HollomonClient {

    private String server;
    private int port;
    Socket socket = null;
    InputStream is = null;
    OutputStream os = null;
    BufferedReader br = null;
    BufferedWriter bw = null;
                
    CardInputStream cis = null;

    public HollomonClient(String server, int port) {
        this.server = server;
        this.port = port; // this key word sets the object calling this function's port to the argument port
    }
    
    public List<Card> login(String username, String password) throws UnknownHostException, IOException {
        try {
            socket = new Socket(server, port);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            br = new BufferedReader (new InputStreamReader(is));
            bw = new BufferedWriter (new OutputStreamWriter(os));
            cis = new CardInputStream(is);
            bw.write(username.toLowerCase() + "\n");
            bw.flush(); // must flush first then send password
            bw.write(password + "\n");
            bw.flush();
            if (br == null) {return null;}
            if (!(br.readLine().equals("User " + username.toLowerCase() + " logged in successfully."))) { return null; } // if not succesful, return null
            
            List<Card> list = new ArrayList<Card>();
            
            for (Card card = cis.readCard(); card != null; card = cis.readCard()) {
                list.add(card);
            }
            Collections.sort(list);
            return list;
        }
        catch (UnknownHostException e) {
            System.err.println("Error in login function: " + e);
            return null;
        }
    }
    
    public void close() {
    // closes all streams
        try {
            socket.close();
            is.close();
            os.close();
            br.close(); // no method calls this, so im assuming the tests call it?
            bw.close();
            cis.close();
        }
        
        catch (UnknownHostException exception) {
            System.err.println(exception);
        }
        
        catch (IOException exception) {
            System.err.println(exception);
        }
    }
    public long getCredits() throws IOException{
        try {
            bw.write("CREDITS\n");
            bw.flush();
            String line = br.readLine();
            //System.out.println(line);
            if (cis.readResponse().equals("OK")) {
                return Long.parseLong(line);
            }
            return 0;
        }
        catch(IOException e) {
            System.err.println("There has been an error in the getCredits method: " + e);
            return 0;
        }
    }
    
    public List<Card> getCards() throws IOException {
        try {
            bw.write("CARDS\n");
            bw.flush();
            
            List<Card> list = new ArrayList<Card>();
            
            for (Card card = cis.readCard(); card != null; card = cis.readCard()) {
                list.add(card);
            }
            Collections.sort(list);
            return list;
        }
        catch(IOException e) {
            System.err.println(e);
            return null;
        }
    }
    
    public List<Card> getOffers() throws IOException {
        try {
            bw.write("OFFERS\n");
            bw.flush();
            
            List<Card> list = new ArrayList<Card>();
            
            for (Card card = cis.readCard(); card != null; card = cis.readCard()) {
                list.add(card);
            }
            Collections.sort(list);
            return list;
        }
        catch(IOException e) {
            System.err.println("There has been an error in the getOffers() method: " + e);
            return null;
        }
    }
    public boolean buyCard(Card card) throws IOException {
        try {
            if (this.getCredits() >= card.getPrice()) {
                bw.write("BUY " + card.getID() + "\n");
                bw.flush();
                if (br.readLine().equals("OK")) {
                    return true;
                }
                return false;
            }
            return false;
        }
        catch(IOException e) {
            System.err.println("There has been an error in the buyCard() method: " + e);
            return false;
        }
    }
    public boolean sellCard(Card card, long price) {
        try {
            if (getCards().contains(card)) {
                bw.write("SELL " + card.getID() + " " + price + "\n");
                bw.flush();
                if (br.readLine().equals("OK")) {
                    return true;
                }
                return false;
            }
            return false;
        }
        catch(IOException e) {
            System.err.println("There has been an error in the sellCard() method: " + e);
            return false;
        }
    }
}

class HollomonClientTest {

    public static void main(String args[]) {
        try {
            HollomonClient hc = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
            assert null == hc.login("random", "random"); // unsuccessful login with wrong details
            List<Card> cardList = hc.login("service", "everyfoodplant"); // successful with correct details
            assert null != cardList;
            Card card = new Card(33572, "Williamson", Rank.COMMON);
            System.out.println(hc.getCredits());
            System.out.println(hc.getCards());
            assert hc.sellCard(card, 23);
            /*long creditsBeforeBuy = hc.getCredits();
            hc.buyCard(card);
            long creditsAfterBuy = hc.getCredits();
            System.out.println(creditsBeforeBuy);
            System.out.println(creditsAfterBuy);
            assert creditsBeforeBuy - card.getPrice() == creditsAfterBuy;*/
            
            // cannot run this code as can only buy a specific card once.
            hc.close();
        }
        
        catch (UnknownHostException exception) {
            System.err.println(exception);
        }
        
        catch (IOException exception) {
            System.err.println(exception);
        }
    
    }

}
