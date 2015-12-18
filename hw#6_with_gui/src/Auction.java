
import java.io.Serializable;

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
 * Auction class represents an active auction currently in the database. This
 * class lists the parameters required for an auction to be made. The serializable
 * is implemented to save the auction when quitting the program. 
 */

public class Auction implements Serializable {

    private int timeRemaining;
    private double currentBid;
    private String auctionID;
    private String sellerName;
    private String buyerName;
    private String itemInfo;

    /**
     * Constructor method that will set the auction id, name, time, and information
     * about the auction. 
     * @param id
     *      String Auction Id that will be obtained from the user and set to 
     *      auctionID of this class. 
     * @param name
     *      String Name parameter is the name of the seller that is obtained 
     *      when the user creates a new auction
     * @param time
     *      The int time parameter contains the information about the duration for 
     *      which the auction has to be live. 
     * @param itemInfo
     *      The String itemInfo contains the details or description of the item. 
     *      and the intemInfo of this class will be set to the itemInfo passed in. 
     */
    public Auction(String id, String name, int time, String itemInfo) {
        timeRemaining = time;
        auctionID = id;
        sellerName = name;
        this.itemInfo = itemInfo;
    }

    /**
     * Overloaded constructor to be used when the details of the auction are 
     * taken from the url. Since the url can contain time in the format of days
     * and hours, we have convert the days into hours and set the timeRemaining
     * variable of this class. The rest of the field will be directly assigned
     * no modification is required. 
     * @param seller
     *      seller is the name of the seller of the item, obtained from the URL
     * @param bid
     *      int bid is the bid amount placed currently, obtained from the URL.
     * @param time
     *     String time will be modified to obtained the total hours. and will be
     *      parsed to int and assigned to the timeRemaining. 
     * @param id
     *      String id contains the information regarding the Auction id, which
     *      will be assigned to the auctionID variable of this class. 
     * @param bidder
     *      String bidder is the name of the highest bidder of this current item. 
     *      this will be assigned to buyerName variable of this class. 
     * @param itemInfo
     *      itemInfo is the details or description of the item taken from the
     *      URL and assigned to the itemInfo Variable of this class
     */
    public Auction(String seller, String bid, String time, String id, String bidder, String itemInfo) {
        this.sellerName = seller;
        //System.out.println(bid.substring(bid.indexOf("$")+1, bid.length()-1));
        this.currentBid = Double.parseDouble(bid.substring(bid.indexOf("$") + 1, bid.length() - 1).replaceAll(",", ""));
        if (time.contains("day")) {
            int days = Integer.parseInt(time.substring(0, time.indexOf("day") - 1));
            int hours = Integer.parseInt(time.substring(time.indexOf(",") + 2, time.indexOf("hour") - 1));
            //System.out.println(days +" "+hours);
            this.timeRemaining = days * 24 + hours;
        } else {
            int hours = Integer.parseInt(time.substring(0, time.indexOf("hour") - 1));
            //System.out.println(hours);
            this.timeRemaining = hours;
        }
        this.auctionID = id;
        this.buyerName = bidder;
        this.itemInfo = itemInfo;
    }

    /**
     *decrementTimeRemaining method is used to decrement the timeRemaining variable
     * of this class, to simulate the time passing. If the time parameter makes
     * the time remaining reduce below zero and override will be made to make 
     * timeRemaining zero, in order to prevent negative time. 
     * @param time
     *      int time parameter contains the information about the amount of time 
     *      to be reduced from the timeRemaining. 
     */
    public void decrementTimeRemaining(int time) {
        if (time < timeRemaining) {
            timeRemaining -= time;
        } else {
            timeRemaining = 0;
        }
    }

    /**
     * when a newbid is  placed by the user, this method will be invoked. The 
     * details of the bidderName and the bid amount is obtained, and if the 
     * bid amount is larger than the current bid, then the bid is accepted. else
     * and illegal argument exception is thrown. If the timeRemaing is zero ie,
     * the bid is closed. the closedAuction Exception is thrown. 
     * @param bidderName
     *     String bidderName is the name of the bidder. 
     * @param bidAmt
     *      double bidAmt is the amount for which the bid is placed. 
     * @throws ClosedAuctionException
     *      this exception is thrown if the auction has already ended/closed.
     * @throws IllegalArgumentException
     *      this exception is thrown if the enter bidAmt is less than the current bid
     */
    public void newBid(String bidderName, double bidAmt) throws
            ClosedAuctionException, IllegalArgumentException {
        if (timeRemaining < 0) {
            throw new ClosedAuctionException("Time is over and the auction is "
                    + "closed");
        }
        if (bidAmt <= currentBid) {
            throw new IllegalArgumentException("The new bid value should be greater");
        } else {
            currentBid = bidAmt;
            buyerName = bidderName;
        }
    }

    /**
     *getter method to return the timeRemaining in this auction
     * @return
     *      int variable containing information about the time remaining in the 
     *      current auction. 
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * getter method to return the current bid that the auction has. 
     * @return
     *      double currentBid containing the information about the bid that the 
     *      auction currently has. 
     */
    public double getCurrentBid() {
        return currentBid;
    }

    /**
     *getter method to return the auction id of this auction. 
     * @return
     *      String auctionID containing information about the Auction ID of this. 
     */
    public String getAuctionID() {
        return auctionID;
    }

    /**
     * getter method to return the information about the seller of this auction. 
     * @return
     *      String sellerName, contains infomation about the seller of this auction. 
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     *getter method to return the infomation about the highest bidder of this 
     * auction ie, the buyer of this product. 
     * @return
     *      String buyerName containing the information about the highest bidder
     *      of this auction. 
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     *getter method to return information about the item that is being sold in the 
     * auction. 
     * @return
     *      String itemInfo containing the information/description of the item 
     *      being sold. 
     */
    public String getItemInfo() {
        return itemInfo;
    }
    /**
     * overrided string method to return a string representing a neatly
     * tabulated form of the infomation of this auction, like id, currend bid, 
     * seller, buyer, time remaining, description of the item. 
     * @return 
     *      String representation of details of this auction. 
     */
    @Override
    public String toString() {
        return (String.format("%10s", auctionID) + " | $"
                + String.format("%10s", currentBid) + " | "
                + String.format("%25s", sellerName) + " | "
                + String.format("%25s", (buyerName==null)?"":buyerName) + " | "
                + String.format("%10s", timeRemaining + " hours") + " | "
                + String.format("%25s", itemInfo));

    }
}
