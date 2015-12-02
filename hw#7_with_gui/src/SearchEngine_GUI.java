
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#7 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 */
/**
 * ********************GUI Extra Credit************************************
 * search engine class will initialize a WebGraph from the appropriate text
 * files and allows the user to search for the keywords in the graph. The class
 * also provides a functionality to to add and remove pages to and from the
 * graph, in addition to allowing the user to modify the hyperlinks between the
 * graph.This is the main class of this assignment.
 *
 * To implement the GUI, JTable was used, in conjuction with the TableRowSorter.
 * This class helped to sort the table by click of the header. references for
 * gui taken from. To access, GUI table use print P and then Type G for GUI. 
 * http://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way
 *
 * @author radra_000
 */
public class SearchEngine_GUI {

    /**
     * PAGES_FILE variable is a constant string containing information about the
     * name of the pages text file.
     */
    public static final String PAGES_FILE = "pages.txt";
    /**
     * LINKS_FILE variable is a constant string containing information about the
     * name of the links text file.
     */
    public static final String LINKS_FILE = "links.txt";

    /**
     * main method that will prompt the user for the instruction to run the
     * program it will display the menu option for the user to select from and
     * enter the information. The print method is modified to include the GUI.
     *
     * @param args arguments that will be passed in when the program is run from
     * command line
     */
    public static void main(String[] args) {
        WebGraph_GUI web = new WebGraph_GUI();
        boolean run = true;
        try {
            web = WebGraph_GUI.buildFromFiles(PAGES_FILE, LINKS_FILE);
        } catch (IllegalArgumentException ex) {
            System.out.println("file contains erraneous characters");
            run = false;
        } catch (FileNotFoundException ex) {
            System.out.println("The file name cannot be found");
            run = false;
        }
        Scanner input = new Scanner(System.in);
        while (run) {
            System.out.println("Menu"
                    + "\n\t(AP) - Add a new page to the graph."
                    + "\n\t(RP) - Remove a page from the graph"
                    + "\n\t(AL) - Add a link between pages in the graph"
                    + "\n\t(RL) - Remove a link between pages in the graph"
                    + "\n\t(P) - Print the graph."
                    + "\n\t(S) - Search for pages with a keyword"
                    + "\n\t(Q) - Quit"
                    + "\nSelect an Option: ");
            String entry = input.nextLine().toUpperCase();
            switch (entry) {
                case ("AP"):
                    try {
                        System.out.println("Enter a URL: ");
                        String url = input.nextLine();
                        System.out.println("Enter keywords(space - seperated): ");
                        ArrayList<String> keywords = new ArrayList<>(Arrays.asList(input.nextLine().split(" ")));
                        web.addPage(url, keywords);
                        System.out.println(url + " succesfully added to the WebGraph");
                    } catch (Exception e) {
                        System.out.println("The url already exists");
                    }
                    break;
                case ("AL"):
                    try {
                        System.out.println("Enter a source URL: ");
                        String source = input.nextLine();
                        System.out.println("Enter a destination URL: ");
                        String destination = input.nextLine();
                        web.addlink(source, destination);
                        System.out.println("Link Sucessfully added from " + source + " to " + destination + "!");
                    } catch (Exception e) {
                        System.out.println("The URL for source/destinaion is not there");
                    }
                    break;
                case ("RP"):
                    System.out.println("Enter a URL");
                    String webUrl = input.nextLine();
                    web.removePage(webUrl);
                    System.out.println(webUrl + " has been removed from the graph");
                    break;
                case ("RL"):
                    System.out.println("Enter a source URL: ");
                    String source1 = input.nextLine();
                    System.out.println("Enter a destination URL: ");
                    String destination1 = input.nextLine();
                    web.removeLink(source1, destination1);
                    System.out.println("Link removed from " + source1 + " to " + destination1 + "!");
                    break;
                case ("P"):
                    System.out.println("\t"+"(G) - Display the GUI"
                            + "\n\t(I) - Sort based on index (ASC)"
                            + "\n\t(U) - Sort based on URL (ASC)"
                            + "\n\t(R) - Sort based on Rank (DSC)"
                            + "\n\t(Q) - Quit (return to previous menu)"
                            + "\n Enter an option : ");
                    entry = input.nextLine().toUpperCase();
                    //gui addition
                    String out = web.printTable(entry);
                    if (entry.equals("G")) {
                        out = web.printTable("I");
                        String[] line = out.split("\n");
                        String[][] array = new String[line.length][5];
                        for (int x = 0; x < line.length; x++) {
                            array[x] = line[x].split("\\|");
                            //System.out.println(Arrays.toString(array[x]));
                        }
//                        System.out.println(array.length);
//                        for (int x = 0; x < line.length; x++) {
//                            for (int y = 0; y < 5; y++) {
//                                array[x][y] = array[x][y].trim();
//                                System.out.print(array[x][y] + "   ");
//                            }
//                        }
                        String[] heading = {"Index", "URL", "PageRank", "Links", "Keywords"};
//                        JButton[] button = new JButton[5];
//                        for (int i = 0; i < heading.length; i++) {
//                            button[i] = new JButton(heading[i]);
//                        }
                        JTable table = new JTable(array, heading);
                        TableRowSorter<TableModel> tableSort = new TableRowSorter<TableModel>(table.getModel());
                        table.setRowSorter(tableSort);
                        ArrayList<RowSorter.SortKey> sort = new ArrayList<>();
                        sort.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                        tableSort.setSortKeys(sort);
                        JFrame frame = new JFrame("PrintTable");
                        frame.add(new JScrollPane(table));
                        frame.setSize(500, 800);
                        frame.setVisible(true);
                        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    break;
                case ("S"):
                    System.out.println("Search Keyword: ");
                    String keyword = input.nextLine();
                    String output = web.search(keyword);
                    System.out.println(output);
                    break;
                case ("Q"):
                    System.out.println("Program terminating....");
                    run = false;
                    break;
            }
        }
    }
}
