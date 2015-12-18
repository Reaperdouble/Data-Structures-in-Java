
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
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
 * AuctionSystem is the main method of this assignment, this method will provide
 * the user with the interface to enter the details. we have implemented the
 * serializable to save the data of the auction when the program is terminated.
 * When the program is stated again, the data will be read from the saved file,
 * so the user doesnt have to enter the details over and over.
 *
 * @author radra_000
 */
public class AuctionSystem implements Serializable {

    /**
     * main method that will provide user with the interface to enter the
     * details. file is created at beginning if it does not already exists, and
     * the information about the auction is stored to the file when the program
     * is terminated.
     *
     * @param args arguments that will be passed in when the program is run from
     * cmd line.
     */
    public static void main(String[] args) {
        System.out.println("Starting");
        AuctionTable auctionTable;
        FileInputStream file;
        try {
            file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            auctionTable = (AuctionTable) inStream.readObject();
            System.out.println("Importing Table....");
        } catch (Exception ex) {    //all exception are caught, and we will create a new file. 
            System.out.println("No previous auction table detected");
            System.out.println("Creating new table...");
            auctionTable = new AuctionTable();
        }
//        } catch (IOException ex) {
//            System.out.println("Cannot read the data from the file");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("The class cannot be deserialized");
        String username;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter username: ");
        username = input.nextLine();
        System.out.println("Menu: "
                + "\n\t (D) - Import Data from URL"
                + "\n\t (A) - Create a New Auction"
                + "\n\t (B) - Bid on an Item"
                + "\n\t (I) - Get Info on Auction"
                + "\n\t (P) - Print All Auctions"
                + "\n\t (R) - Remove Expired Auctions"
                + "\n\t (T) - Let Time Pass"
                + "\n\t (Q) - Quit");
        boolean run = true;
        while (run) {
            System.out.println("Please select an option: ");
            switch (input.nextLine().toUpperCase()) {
                case ("D"):
                    System.out.println("Please enter a URL");
                    String url = input.nextLine();
                    System.out.println("loading");
                    auctionTable = AuctionTable.buildFromUrl(url);
                    break;
                case ("A"):
                    System.out.println("Creating new Auction as " + username);
                    System.out.println("Please enter an Auction ID: ");
                    String auctionId = input.nextLine();
                    System.out.println("Please enter an Auction Time(Hours) :");
                    int hours = input.nextInt();
                    System.out.println("Please enter some Item Info: ");
                    input.nextLine();
                    //System.out.println("");
                    String itemInfo = input.nextLine();
                    Auction auction = new Auction(auctionId, username, hours, itemInfo);
                    auctionTable.put(auctionId, auction);
                    System.out.println("Auction " + auctionId + " inserted into the table");
                    break;
                case ("B"):
                    try {
                        System.out.println("Please enter an Auction ID: ");
                        String id = input.nextLine();
                        Auction temp = auctionTable.getAuction(id);
                        if (temp.getTimeRemaining() == 0) {
                            System.out.println("Auction " + id + " is closed");
                            System.out.println("\t Current Bid :" + temp.getCurrentBid());
                            System.out.println("You can no longer bid on the item");
                        } else {
                            System.out.println("Auction " + id + " is open:");
                            System.out.println("\tCurrent Bid: " + temp.getCurrentBid());
                            System.out.println("\nWhat would you like to bid?:");
                            int bid = input.nextInt();
                            try {
                                temp.newBid(username, bid);
                                System.out.println("Bid Accepted");
                            } catch (ClosedAuctionException ex) {
                                System.out.println("The Auction is closed");
                                System.out.println("\t Current Bid :" + temp.getCurrentBid());
                                System.out.println("You can no longer bid on the item");
                                break;
                            } catch (IllegalArgumentException ex) {
                                System.out.println("The New bid has to be higher "
                                        + "than the current bid of " + temp.getCurrentBid());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("The Auction id you entered is not valid");
                    }
                    break;
                case ("I"):
                    try{
                    System.out.println("Enter the Auction Id: ");
                    auctionTable.auctionInfo(input.nextLine());
                    }
                    catch(NullPointerException e){
                        System.out.println("The entered auction id is invalid");
                    }
                    break;
                case ("P"):
                    auctionTable.printTable();
                    break;
                case ("R"):
                    auctionTable.removeExpiredAuctions();
                    System.out.println("removed expired auctions");
                    break;
                case ("T"):
                    try {
                        System.out.println("How many hours should pass: ");
                        auctionTable.letTimePass(input.nextInt());
                        System.out.println("Time passing....");
                        System.out.println("Auction Times Updated.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("The time cannot be negative");
                    }
                    break;
                case ("Q"):
                    run = false;
                    System.out.println("Writing Auction Table to file...");
                    FileOutputStream outFile;
                    try {
                        outFile = new FileOutputStream("auction.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(outFile);
                        outStream.writeObject(auctionTable);
                    } catch (Exception ex) {
                        System.out.println("Error Writing Data back to file");
                    }
                    System.out.println("Done.");
                    System.out.println("Quitting...");
                    break;
            }
        }
    }

}
