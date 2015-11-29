
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author radra_000
 */
public class SearchEngine {

    public static final String PAGES_FILE = "pages.txt";
    public static final String LINKS_FILE = "links.txt";

    public static void main(String[] args) {
        WebGraph web = new WebGraph();
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Menu"
                    + "\n\t(AP) - Add a new page to the graph."
                    + "\n\t(RP) - Remove a page from the graph"
                    + "\n\t(AL) - Add a link between pages in the graph"
                    + "\n\t(RM) - Remove a link between pages in the graph"
                    + "\n\t(P) - Print the graph."
                    + "\n\t(S) - Search for pages with a keywork"
                    + "\n\t(Q) - Quit"
                    + "\nSelect an Option: ");
            String entry = input.nextLine().toUpperCase();
            switch (entry) {
                case ("AP"):
                    System.out.println("Enter a URL: ");
                    String url = input.nextLine();
                    System.out.println("Enter keywords(space - seperated): ");
                    ArrayList<String> keywords = new ArrayList<>(Arrays.asList(input.nextLine().split(" ")));
                    web.addPage(url, keywords);
                    System.out.println(url + " succesfully added to the WebGraph");
                    break;
                case ("AL"):
                    System.out.println("Enter a source URL: ");
                    String source = input.nextLine();
                    System.out.println("Enter a destination URL: ");
                    String destination = input.nextLine();
                    web.addlink(source, destination);
                    System.out.println("Link Sucessfully added from " + source + " to " + destination + "!");
                    break;
                case ("RP"):
                    System.out.println("Enter a URL");
                    String webUrl = input.nextLine();
                    web.removePage(webUrl);
                    System.out.println(webUrl+" has been removed from the graph");
                    break;
                case ("RL"):
                    System.out.println("Enter a source URL: ");
                    String source1 = input.nextLine();
                    System.out.println("Enter a destination URL: ");
                    String destination1 = input.nextLine();
                    web.removeLink(source1, destination1);
                    System.out.println("Link removed from "+source1+" to "+destination1+"!");
                    break;
                case ("P"):
                    System.out.println("\t(I) - Sort based on index (ASC)"
                            + "\n\t(U) - Sort based on URL (ASC)"
                            + "\n\t(R) - Sort based on Rank (DSC)"
                            + "\n\t(Q) - Quit (return to previous menu)");
                    entry = input.nextLine().toUpperCase();
                    switch (entry) {
                        case ("I"):
                            break;
                        case ("U"):
                            break;
                        case ("R"):
                            break;
                        case ("Q"):
                            break;
                    }
                    web.printTable();
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
