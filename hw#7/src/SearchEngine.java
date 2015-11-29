
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
    public static final String PAGES_FILE ="pages.txt";
    public static final String LINKS_FILE = "links.txt";
    private WebGraph web;
    Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("(AP) - Add a new page to the graph."
                + "\n(RP) - Remove a page from the graph"
                + "\n(AL) - Add a link between pages in the graph"
                + "\n(RM) - Remove a link between pages in the graph"
                + "\n(P) - Print the graph."
                + "\n\t(I) - Sort based on index (ASC)"
                + "\n\t(U) - Sort based on URL (ASC)"
                + "\n\t(R) - Sort based on Rank (DSC)"
                + "\n\t(Q) - Quit (return to previous menu)"
                + "\n(S) - Search for pages with a keywork"
                + "\n(Q) - Quit");
    
    }
}
