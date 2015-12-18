
import big.data.*;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#6 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */
/**
 * AuctionTable class will be used to store the information or acts as a 
 * database to store all the auction using hashtable. We will be using 
 * the hash table implementation provided by the java api. 
 * @author radra_000
 */
public class AuctionTable implements Serializable {// extends Hashtable<Auction, String>{

    Hashtable<String, Auction> table = new Hashtable<String, Auction>();
    //AuctionTable auctionTable;

    /**
     *buildFromUrl is a static method that will be used to obtain the necessary
     * details from the url entered by the user. We will use the DataSource 
     * object to obtain this information. We will used arrays to obtain the
     * details since the xml file, might contain multiple listings. We will 
     * then take these variables create a auction object assign to the hash table
     * with the auction id being the unique key. 
     * @param URL
     *      The url of the xml file from which the details have to be mined. 
     * @return
     *      AuctionTable which is the table containing all the auction that was
     *      obtained from the url. Since this is a static method, return auction
     *      table is necessary to be used from anywhere. 
     * @throws IllegalArgumentException
     *      illegal argument exception is thrown whenever the enter url gives a 
     *      problem, ie when the url does not represent a valid datasource.  
     */
    public static AuctionTable buildFromUrl(String URL) throws IllegalArgumentException {
        try {
            DataSource source = DataSource.connectXML(URL).load();

            //System.out.println(source.hasData());
            AuctionTable auctionTable = new AuctionTable();
            String seller[] = source.fetchStringArray("listing/seller_info/seller_name");
            String bid[] = source.fetchStringArray("listing/auction_info/current_bid");
            String[] time = source.fetchStringArray("listing/auction_info/time_left");
            String[] id = source.fetchStringArray("listing/auction_info/id_num");
            String[] bidder = source.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
            String[] itemMemory = source.fetchStringArray("listing/item_info/memory");
            String[] itemHardDrive = source.fetchStringArray("listing/item_info/hard_drive");
            String[] itemCpu = source.fetchStringArray("listing/item_info/cpu");
            for (int i = 0; i < id.length; i++) {
                String itemInfo = itemCpu[i] + " - " + itemMemory[i] + " - " + itemHardDrive[i];
                Auction auction = new Auction(seller[i], bid[i], time[i], id[i], bidder[i], itemInfo);
                auctionTable.put(id[i], auction);
            }
            System.out.println("Auction Data loaded succesfully!!!!");
            return auctionTable;
        } catch (Exception e) {
            System.out.println(URL + "is not a valid URL");
            return null;
        }
    }

    /**
     *put method will be used the enter the auction to the hash table. 
     * @param auctionID
     *      auctionID parameter is the string containing the id of the auction. 
     *      this will be used as out unique key for the hash table. 
     * @param auction
     *      the auction to insert in the table with the corresponding auction id. 
     * @throws IllegalArgumentException
     *      if the passed in auction id already exists the exception is thrown 
     */
    public void put(String auctionID, Auction auction) throws IllegalArgumentException {
        if (table.contains(auctionID)) {
            throw new IllegalArgumentException("This auction ID already exists");
        }
        table.put(auctionID, auction);
    }

    /**
     * getAuction method is used to return the auction with the unique auctionId
     * parameter. 
     * @param auctionID
     *      String auctionID is the unique key with which a auction was stored in
     *      a hash table. 
     * @return
     *      return the Auction object that was return from the hash table. 
     */
    public Auction getAuction(String auctionID) {
        return (Auction) table.get(auctionID);
    }

    /**
     *method to simulate the passing of time. Decreases the timeRemaining of all
     * auction objects by the amount specified. 
     * @param numHours
     *      numHours is the amount of time to be reduced from each auction in the table. 
     * @throws IllegalArgumentException
     *      illegal argument exception is thrown when the passed in parameter
     *      is a value less than 0. 
     */
    public void letTimePass(int numHours) throws IllegalArgumentException {
        if (numHours < 0) {
            throw new IllegalArgumentException("The hours cannot be negative");
        }
        Enumeration keys = table.keys();
        while (keys.hasMoreElements()) {
            Auction temp = (Auction) table.get((String) keys.nextElement());
            temp.decrementTimeRemaining(numHours);
        }
    }

    /**
     *method to iterate over all the auction objects in the table and remove the 
     * ones that have timeRemaining equal to zero. Keys is a enumeration variable
     * so we will have to cast it to string when using the get method to obtain
     * the data. 
     */
    public void removeExpiredAuctions() {
        Enumeration keys = table.keys();
        while (keys.hasMoreElements()) {
            Auction temp =  getAuction((String) keys.nextElement());
            if (temp.getTimeRemaining() == 0) {
                table.remove(temp.getAuctionID());
            }
        }
    }

    /**
     *auctionInfo method was added personally to be used when the user requests
     * information about one particular auction, so the output doesnt not 
     * have to be in the form of a table. 
     * @param id
     *      String id the auctionID of the auction, ie the unique key to be used
     *      to obtain the auction object from the hash table. 
     */
    public void auctionInfo(String id) {
        if (table.containsKey(id)) {
            Auction temp = this.getAuction(id);
            System.out.println("Auction : " + temp.getAuctionID());
            System.out.println("\tSeller : " + temp.getSellerName());
            System.out.println("\tBuyer : " + temp.getBuyerName());
            System.out.println("\tTime Left : " + temp.getTimeRemaining());
            System.out.println("\tInfo : " + temp.getItemInfo());
        }
    }

    /**
     *print table method will be used to output the information about all the 
     * auctions that is inside the hash table. 
     */
    public void printTable() {
        String output = String.format("%-10s", "Auction ID") + " | "
                + String.format("%-10s", "Bid") + "  | "
                + String.format("%-25s", "Seller") + " | "
                + String.format("%-25s", "Buyer") + " | "
                + String.format("%-10s", "Time") + " | "
                + String.format("%-25s", "Item Info");
        System.out.println(output);
        System.out.println("============================================"
                + "=========================================================="
                + "======================================================");
        Enumeration keys = table.keys();
        //System.out.println(keys);
        while (keys.hasMoreElements()) {
            Auction temp = (Auction) this.getAuction((String) keys.nextElement());
            System.out.println(temp.toString());
        }
    }
}
