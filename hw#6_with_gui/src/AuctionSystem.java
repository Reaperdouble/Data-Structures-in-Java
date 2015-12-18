import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/*
 * **********************GUI Version**************Gui Version******************
 * ****************************************************************************
 * *****For regular version use the other auctionSystem.java file**********
 * AuctionSystem is the main method of this assignment, this method will 
 * provide the user with the interface to enter the details. we have implemented
 * the serializable to save the data of the auction when the program is terminated.
 * When the program is stated again, the data will be read from the saved file,
 * so the user doesnt have to enter the details over and over. 
 * @author radra_000
 */
public class AuctionSystem extends Application implements Serializable{
    Button D, A, B, I , P, R, T, Q, enter;
    Stage startScreen, infoScreen;
    TextField userName;
    Label userNameLabel;
    public static AuctionTable auctionTable = new AuctionTable();
    public boolean run = true;
    /**
     * main method that will set up the files for saving purpose. and call 
     * the start method. 
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Starting");
        AuctionSystem auctionSystem = new AuctionSystem(); 
        FileInputStream file;
        try {
            file = new FileInputStream("auction.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            auctionSystem.auctionTable = (AuctionTable) inStream.readObject();
            System.out.println("Importing Table....");
        } catch (Exception ex) {
            System.out.println("No previous auction table detected");
            System.out.println("Creating new table...");
            auctionSystem.auctionTable = new AuctionTable();
        }
        launch(args);
    }
    /**
     * set up the window and create button for various user inputs. 
     * and textfield, for the user name. 
     * @param window 
     *      Stage window is the stage containing all the gui features. 
     */
    public void start(Stage window){
        startScreen = window;
        window.setTitle("Menu");
        D = new Button("Import Data from URL");
        A = new Button("New Auction");
        B = new Button("Bid");
        I = new Button("Get Info");
        P = new Button("Print Auction");
        R = new Button("Expired Auction");
        T = new Button("Let Time Pass");
        Q = new Button("Quit");
        if (userName == null) {
            userName = new TextField();
            userNameLabel = new Label("Enter your UserName: ");
        }
        else{
            userNameLabel.setVisible(false);
            userName.setVisible(false);
        }
        D.setOnAction(e -> dPress(window));
        A.setOnAction(e -> aPress(userName.getText(), window));
        B.setOnAction(e -> bPress(userName.getText(),window));
        I.setOnAction(e -> iPress(window));
        P.setOnAction(e -> auctionTable.printTable());
        R.setOnAction(e -> {
            auctionTable.removeExpiredAuctions();
            System.out.println("Expired Auctions Removed...");
                });
        T.setOnAction(e -> tPress(window));
        Q.setOnAction(e -> qPress(window));
        VBox vPane = new VBox(10);
        vPane.getChildren().addAll(userNameLabel,userName,D,A,B,I,P,R,T,Q);
        Scene scene = new Scene(vPane, 500,500);
        startScreen.setScene(scene);
        startScreen.show();
        
    }
    /**
     * this method is used to setup the start screen again. so when the user
     * wants to press another method, they could use the original start window. 
     * @param window 
     *      Stage window that will be passed in to start method to setup the button
     *      and other fields. 
     */
    public void mainScreen(Stage window){
        start(window);
    }
    /**
     * method is called when the button d is pressed. "import data from url"
     * @param window 
     *      The start window will be modified to get the details of the url 
     * from the user. 
     */
    public void dPress(Stage window){
        startScreen.close();
        infoScreen = window;
        window.setTitle("Import Data from URL");
        TextField URL = new TextField("Enter the URL for the .xml file");
        Label urlLabel = new Label("Enter the URL for the .xml file");
        enter = new Button("Enter");
        enter.setOnAction(e -> {
           // System.out.println(URL.getText());
            infoScreen.close();
            auctionTable = AuctionTable.buildFromUrl(URL.getText());
            mainScreen(window);
        });
        VBox vPane1 = new VBox(30);
        VBox vPane2 = new VBox(30);
        HBox hPane = new HBox(60);
        vPane1.getChildren().addAll(URL, enter);
        Scene scene = new Scene(vPane1, 500, 500);
        infoScreen.setScene(scene);
        infoScreen.show();
        //System.out.println("Auction Data loaded succesfully!!!!");

    }
    /**
     * method is invoked when the "a" button is pressed. "create new Auction"
     * @param userName
     *      name of the current user that was passed in when button D was pressed. 
     * @param window
     *      window is stage that will be modified to include the fields and button
     *      necessary to get details reagarding the new auction from the user. 
     */
    public void aPress(String userName, Stage window){
        startScreen.close();
        infoScreen = window;
        window.setTitle("Create a New Auction");
        Label userNameLabel = new Label("Creating new Auction as");
        Label user = new Label(userName);
        Label auctionIdLabel = new Label("Enter Auction ID: ");
        TextField auctionId = new TextField();
        Label timeLabel = new Label ("Enter Auction Time: ");
        TextField time = new TextField();
        Label itemInfoLabel = new Label("Enter Item Info: ");
        TextField itemInfo = new TextField();
        enter = new Button("Enter");
        enter.setOnAction(e -> {
            Auction auction = new Auction(auctionId.getText(),
                    userName, Integer.parseInt(time.getText()), itemInfo.getText());
            auctionTable.put(auctionId.getText(), auction);
            mainScreen(window);
        });
        VBox vPane1 = new VBox(30);
        VBox vPane2 = new VBox(30);
        HBox hPane = new HBox(60);
        vPane1.getChildren().addAll(userNameLabel,auctionIdLabel,timeLabel,itemInfoLabel);
        vPane2.getChildren().addAll(user, auctionId,time,itemInfo,enter);
        hPane.getChildren().addAll(vPane1,vPane2);
        Scene scene = new Scene(hPane, 500, 500);
        infoScreen.setScene(scene);
        infoScreen.show();
        System.out.println("Auction "+auctionId.getText()+" inserted into the table");
    }
    /**
     * this method will be invoked when the button B is pressed in the start window.
     * "bid on the an item. 
     * @param userName
     *      userName of the current user that was passed in when the program first
     *      started. 
     * @param window 
     *      Windows is a stage that will be modified to obtain the information 
     *      regarding the new bid that will be placed by the user. 
     */
    public void bPress(String userName, Stage window){
        startScreen.close();
        infoScreen = window;
        window.setTitle("Place Bid");
        Label auctionIdLabel = new Label("Enter the Auction ID : ");
        TextField auctionId = new  TextField();
        HBox hPane = new HBox(60);
        VBox vPane = new VBox(30);
        enter = new Button("Enter");
        enter.setOnAction(e -> {
            infoScreen.close();
            String id = auctionId.getText();
            Auction temp = auctionTable.getAuction(id);
            if (temp.getTimeRemaining() == 0) {
                System.out.println("Auction " + id + " is closed");
                System.out.println("\t Current Bid :" + temp.getCurrentBid());
                System.out.println("You can no longer bid on the item");
                mainScreen(window);
            } else {
                System.out.println("Auction " + id + " is open:");
                System.out.println("\tCurrent Bid: " + temp.getCurrentBid());
                Label status  = new Label("Auction " + id + " is open:");
                Label currentBid = new Label("Current Bid: " +Double.toString(temp.getCurrentBid()));
                infoScreen = window;
                window.setTitle("Place Bid");
                infoScreen.show();
                Label bidLabel = new Label("Enter the Bid Amount : ");
                TextField bid = new TextField();
                HBox hPane1 = new HBox(60);
                VBox vPane1 = new VBox(30);
                enter = new Button("Enter");
                enter.setOnAction(x -> {
                    infoScreen.close();
                    try {
                    temp.newBid(userName, Integer.parseInt(bid.getText()));
                    System.out.println("Bid Accepted");
                } catch (ClosedAuctionException ex) {
                    System.out.println("The Auction is closed");
                    System.out.println("\t Current Bid :" + temp.getCurrentBid());
                    System.out.println("You can no longer bid on the item");
                } catch (IllegalArgumentException ex) {
                    System.out.println("The New bid has to be higher "
                            + "than the current bid of " + temp.getCurrentBid());
                }
                    mainScreen(window);
                });
                hPane1.getChildren().addAll(bidLabel, bid);
                vPane1.getChildren().addAll(status,currentBid,hPane1, enter);
                Scene scene = new Scene(vPane1, 500, 500);
                infoScreen.setScene(scene);
                infoScreen.show();
            }
        //mainScreen(window);
        });
        hPane.getChildren().addAll(auctionIdLabel, auctionId);
        vPane.getChildren().addAll(hPane, enter);
        Scene scene = new Scene(vPane,500, 500);
        infoScreen.setScene(scene);
        infoScreen.show();
    }
    /**
     * this method will be invoked when the Button i is pressed. 
     * "get info on the auction"
     * @param window 
     *      window is a stage that will be modified to get the details about the
     *      auction id from the user. 
     */
    public void iPress(Stage window){
        startScreen.close();
        infoScreen = window;
        window.setTitle("Get Auction Info");
        Label auctionIdLabel = new Label("Enter the Auction ID : ");
        TextField auctionId = new  TextField();
        HBox hPane = new HBox(60);
        VBox vPane = new VBox(30);
        enter = new Button("Enter");
        enter.setOnAction(e -> {
            infoScreen.close();
            auctionTable.auctionInfo(auctionId.getText());
            mainScreen(window);
        });
        hPane.getChildren().addAll(auctionIdLabel, auctionId);
        vPane.getChildren().addAll(hPane, enter);
        Scene scene = new Scene(vPane,500, 500);
        infoScreen.setScene(scene);
        infoScreen.show();
    }
    /**
     * this method will be invoked when the button T is pressed on the start screen
     * "Let time Pass"
     * @param window 
     *      window is a stage that will be modified to get the information regarding
     *      how much time to pass from each auction. 
     */
    public void tPress(Stage window){
        //startScreen.close();
        startScreen.close();
        infoScreen = window;
        window.setTitle("Let Time Pass");
        Label timeLabel = new Label("How many Hours should pass : ");
        TextField time = new  TextField();
        HBox hPane = new HBox(60);
        VBox vPane = new VBox(30);
        enter = new Button("Enter");
        enter.setOnAction(e -> {
            infoScreen.close();
            auctionTable.letTimePass(Integer.parseInt(time.getText()));
            System.out.println("Time passing....");
            System.out.println("Auction Times Updated.");
            mainScreen(window);
        });
        
        hPane.getChildren().addAll(timeLabel, time);
        vPane.getChildren().addAll(hPane, enter);
        Scene scene = new Scene(vPane,500, 500);
        infoScreen.setScene(scene);
        infoScreen.show();

    }
    /**
     * This method is invoked when the button Q is pressed. 
     *"quit the program". 
     * @param window 
     *      window is stage that will be modified to display the quit message. 
     */
    public void qPress(Stage window){
        startScreen.close();
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
    }
}









//        System.out.println("Starting");
//        AuctionTable auctionTable;
//        FileInputStream file;
//        try {
//            file = new FileInputStream("auction.obj");
//            ObjectInputStream inStream = new ObjectInputStream(file);
//            auctionTable = (AuctionTable) inStream.readObject();
//            System.out.println("Importing Table....");
//        } catch (Exception ex) {
//            System.out.println("No previous auction table detected");
//            System.out.println("Creating new table...");
//            auctionTable = new AuctionTable();
//        }
////        } catch (IOException ex) {
////            System.out.println("Cannot read the data from the file");
////        } catch (ClassNotFoundException ex) {
////            System.out.println("The class cannot be deserialized");
//        String username;
//        Scanner input = new Scanner(System.in); 
//        System.out.println("Please enter username: ");
//        username = input.next();
//        System.out.println("Menu: "
//                + "\n\t (D) - Import Data from URL"
//                + "\n\t (A) - Create a New Auction"
//                + "\n\t (B) - Bid on an Item"
//                + "\n\t (I) - Get Info on Auction"
//                + "\n\t (P) - Print All Auctions"
//                + "\n\t (R) - Remove Expired Auctions"
//                + "\n\t (T) - Let Time Pass"
//                + "\n\t (Q) - Quit");
//        boolean run = true;
//        while (run) {
//            System.out.println("Please select an option: ");
//            switch (input.next().toUpperCase()) {
//                case ("D"):
//                    System.out.println("Please enter a URL");
//                    String url = input.next();
//                     System.out.println("loading");
//                    auctionTable.buildFromUrl(url);
//                    System.out.println("Auction Data loaded succesfully!!!!");
//                    break;
//                case ("A"):
//                    System.out.println("Creating new Auction as "+username);
//                    System.out.println("Please enter an Auction ID: ");
//                    String auctionId = input.next();
//                    System.out.println("Please enter an Auction Time(Hours) :");
//                    int hours = input.nextInt();
//                    System.out.println("Please enter some Item Info: ");
//                    input.next();
//                    String itemInfo = input.nextLine();
//                    Auction auction = new Auction(auctionId, username, hours, itemInfo);
//                    auctionTable.put(auctionId, auction);
//                    System.out.println("Auction "+auctionId+" inserted into the table");
//                    break;
//                case ("B"):
//                    System.out.println("Please enter an Auction ID: ");
//                    String id = input.next();
//                    Auction temp = auctionTable.get(id);
//                    if (temp.getTimeRemaining() == 0) {
//                        System.out.println("Auction " + id + " is closed");
//                        System.out.println("\t Current Bid :" + temp.getCurrentBid());
//                        System.out.println("You can no longer bid on the item");
//                    } else {
//                        System.out.println("Auction " + id + " is open:");
//                        System.out.println("\tCurrent Bid: " + temp.getCurrentBid());
//                        System.out.println("\nWhat would you like to bid?:");
//                        int bid = input.nextInt();
//                        try {
//                            temp.newBid(username, bid);
//                            System.out.println("Bid Accepted");
//                        } catch (ClosedAuctionException ex) {
//                            System.out.println("The Auction is closed");
//                            System.out.println("\t Current Bid :"+temp.getCurrentBid());
//                            System.out.println("You can no longer bid on the item");
//                            break;
//                        } catch (IllegalArgumentException ex) {
//                            System.out.println("The New bid has to be higher "
//                                    + "than the current bid of " + temp.getCurrentBid());
//                        }
//                    }
//                    break;
//                case ("I"):
//                    System.out.println("Enter the Auction Id: ");
//                    auctionTable.auctionInfo(input.next());
//                    break;
//                case ("P"):
//                    auctionTable.printTable();
//                    break;
//                case("R"):
//                    auctionTable.removeExpiredAuctions();
//                    break;
//                case("T"):
//                    System.out.println("How many hours should pass: ");
//                    auctionTable.letTimePass(input.nextInt());
//                    System.out.println("Time passing....");
//                    System.out.println("Auction Times Updated.");
//                    break;
//                case("Q"):
//                    run = false;
//                    System.out.println("Writing Auction Table to file...");
//                    FileOutputStream outFile;
//            try {
//                outFile = new FileOutputStream("auction.obj");
//                ObjectOutputStream outStream = new ObjectOutputStream(outFile);
//                outStream.writeObject(auctionTable);
//            } catch (Exception ex) {
//                System.out.println("Error Writing Data back to file");
//            }
//                    System.out.println("Done.");
//                    System.out.println("Quitting...");
//                    break;
//            }
//        }
