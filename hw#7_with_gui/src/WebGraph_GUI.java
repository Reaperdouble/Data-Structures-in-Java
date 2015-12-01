
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#7 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */
/**
 * WebGraph_GUI class is used to organize the webpage object as a directed
 * graph. the class contains a collection of pages, and two dimensional array of
 * integers representing a adjacency matrix called links. The matrix has a size
 * of MAX_PAGES which is a constant value of 40.
 *
 * @author radra_000
 */
public class WebGraph_GUI {

    /**
     * Static final variable MAX_PAGES is a constant integer of 40
     */
    public static final int MAX_PAGES = 40;
    private static ArrayList<WebPage> pages = new ArrayList();
    private int[][] links = new int[MAX_PAGES][MAX_PAGES];
    private int count = 0;

    /**
     * buildFromFiles is a static method that will read the text file, and
     * builds the ArrayList pages using addPage method and builds the links
     * array using addLinks method.we will use the FileReader class to read and
     * obtain data from the files.
     *
     * @param pagesFile pagesFile is a string containing the name of the pages
     * text file
     * @param linksFile linksFile is a string containing the name of the links
     * text file
     * @return WebGraph object with the all the pages and links variable that
     * are filled with the data from the text files.
     * @throws IllegalArgumentException IllegalArgumentException is thrown when
     * the file name entered contains illegal characters.
     * @throws FileNotFoundException FileNotFoundException is thrown when the
     * file path entered cannot be found.
     */
    public static WebGraph_GUI buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException, FileNotFoundException {
        WebGraph_GUI graph = new WebGraph_GUI();
        //entering the pages from the file
        FileReader file = new FileReader(pagesFile);
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String data = input.nextLine().trim();
            ArrayList<String> words = new ArrayList<>(Arrays.asList(data.split(" ")));
            String url = words.get(0);
            words.remove(0);
            graph.addPage(url, words);
        }
        //entering the links from the file
        file = new FileReader(linksFile);
        input = new Scanner(file);
        while (input.hasNextLine()) {
            String data = input.nextLine().trim();
            String[] link = data.split(" ");
            graph.addlink(link[0], link[1]);
        }
        return graph;
    }

    /**
     * addPage method is used to add pages to the WebGraph. The method checks if
     * the url is unique. and if the url and keywords are not null. it will
     * create a new webpage object with the corresponding index, and rank, and
     * keywords
     *
     * @param url url of the webpage to be added to the pages variable.
     * @param keywords arraylist of keywords read from the file.
     * @throws IllegalArgumentException IllegalArgumentException is thrown when
     * the passed in url parameter is null or keyword parameter is null or if
     * the url already exists
     */
    public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException {
        if (url == null || keywords == null || alreadyExists(url)) {
            throw new IllegalArgumentException("Either the URL already exists or the URL/keyword is null");
        } else {
            pages.add(new WebPage(url, count, 0, keywords));
            count++;
        }
    }

    /**
     * alreadyExists method was created to accompany the addPages method. This
     * method will look up all the webpages url inside the pages variable to see
     * if any of them matches and returns a boolean.
     *
     * @param url String url is the name of the webpage to check weather it
     * already exists.
     * @return boolean value. True if the url already exists. false otherwise.
     */
    public boolean alreadyExists(String url) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * addLink method adds a link from the web page with the url indicated by
     * source to the web pages with the URL indicated by destination. source is
     * the url of the page which contains the hyperlink to the destination.
     * Destination is the url of the page which the hyperlink points to. after
     * the links are added the pages ranks are updated.
     *
     * @param source source is the url of the page which contains the hyperlink
     * to the destination
     * @param destination Destination is the url of the page which the hyperlink
     * points to.
     * @throws IllegalArgumentException IllegalArugmentException is thrown when
     * either the source or destination does not exist.
     */
    public void addlink(String source, String destination) throws IllegalArgumentException {
        int x = -1, y = -1;
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getUrl().equals(source)) {
                x = pages.get(i).getIndex();
            }
            if (pages.get(i).getUrl().equals(destination)) {
                y = pages.get(i).getIndex();
            }
        }
        if (x == -1 || y == -1) {
            throw new IllegalArgumentException("source or destination does not exist");
        } else {
            links[x][y] = 1;
        }
        updatePageRanks();
    }

    /**
     * removePage method removes the webpages from the graph with the given URL.
     * when a page is removed all its links also has to removed as well. this is
     * done with two additional helper method removeArray and reassignIndex
     * method.
     *
     * @param url Url is the name of the webpage to remove from the graph
     */
    public void removePage(String url) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getUrl().equals(url)) {
                //removing links
//            for(int k = 0; k < pages.size();k++){
//                for(int j = 0; j< pages.size(); j++){
//                    System.out.print(links[k][j]+"\t");
//                }
//                System.out.println("");
//            }
//                System.out.println("\n\n\n");
                removeArray(i);
                //removing pages
                pages.remove(pages.get(i));
                reassignIndex();
            }
        }
        count = (count==0)?0:count-1;
    }

    /**
     * reassignIndex method to reassign the indexes of the pages from the graph
     */
    public void reassignIndex() {
        for (int i = 0; i < pages.size(); i++) {
            pages.get(i).setIndex(i);
        }
    }

    /**
     * removeArray method is used to remove the row and column of the links
     * array at location index.
     *
     * @param index index of the row and column to be removed from the links
     * array.
     */
    public void removeArray(int index) {
        // System.out.println(pages.size());
        for (int i = 0; i < pages.size(); i++) {
            for (int j = index; j < pages.size() - 1; j++) {
                links[i][j] = links[i][j + 1];

            }
        }
        for (int i = index; i < pages.size(); i++) {
            for (int j = 0; j < pages.size() - 1; j++) {
                links[i][j] = links[i + 1][j];
            }
        }

//        System.out.println(pages.size());
//        for(int k = 0; k < pages.size();k++){
//                for(int j = 0; j< pages.size(); j++){
//                    System.out.print(links[k][j]+"\t");
//                }
//                System.out.println("");
//            }
    }

    /**
     * removeLink method removes the link from webpage with the URL indicated by
     * source to the WebPage with the URL indicated by the destination.after the
     * links are removed the ranks of the pages are updated.
     *
     * @param source the url of the webpage to remove the link
     * @param destination the url of the link to be removed.
     */
    public void removeLink(String source, String destination) {
        int x = -1, y = -1;
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getUrl().equals(source)) {
                x = pages.get(i).getIndex();
            }
            if (pages.get(i).getUrl().equals(destination)) {
                y = pages.get(i).getIndex();
            }
        }
        if (x == -1 || y == -1) {
        } else {
            links[x][y] = 0;            //check if it works
        }
        updatePageRanks();
    }

    /**
     * updatePageRanks method was implemented to redo the ranking of the
     * webpages whenever a hyper link is added. This way if a page increases in
     * popularity its rank will also reflect it. The method uses the setter
     * method to update the ranks. It does not return anything.
     */
    public void updatePageRanks() {
        int count = 0;
        for (int i = 0; i < pages.size(); i++) {
            for (int j = 0; j < pages.size(); j++) {
                count += links[j][i];
            }
            pages.get(i).setRank(count);
            count = 0;
        }
    }

    /**
     * printTable method is used to display the pages in a tabular form. The
     * user has the option to select different sorting methods, index ascending,
     * url ascending, rank descending.
     *
     * @param sorting String sorting contains the letter to select the sorting
     * method either I, U, R, Q is passed in.
     * @return return the String that is a tabular form of webpage table. This
     * String output will be taken and displayed on a GUI JTable.
     */
    public String printTable(String sorting) {
        updatePageRanks();
        ArrayList<WebPage> copy = new ArrayList<>();
        copy.addAll(pages);
        switch (sorting) {
            case ("I"):
                Collections.sort(copy, new IndexComparator());
                break;
            case ("U"):
                Collections.sort(copy, new URLComparator());
                break;
            case ("R"):
                Collections.sort(copy, new RankComparator());
                break;
            case ("Q"):
            default:
                return "";
        }
        WebPage[] webpage = copy.toArray(new WebPage[0]);
        String heading = String.format("%-10s", "Index")
                + String.format("%-20s", "URL")
                + String.format("%-15s", "PageRank")
                + String.format("%-25s", "Links")
                + String.format("%40s", "Keywords") + "\n"
                + "---------------------------------------------------------------"
                + "-------------------------------------------------------------";
        System.out.println(heading);
        String output = "";
        String out = "";
        for (int k = 0; k < webpage.length; k++) {
            int i = webpage[k].getIndex();
            String linkNum = "";
            for (int j = 0; j < webpage.length; j++) {
                linkNum += (links[i][j] == 1) ? j + "," : "";
            }
            linkNum = (linkNum.length() > 1) ? linkNum.substring(0, linkNum.length() - 1) : "";
            output = webpage[k].toString();
            output = output.replace("****", String.format("%-20s", linkNum));
            output = output.replace("[", "");
            output = output.replace("]", "");
            System.out.println(output);
            out += output + "\n";
        }
        //System.out.println(out);
        return (out);
    }

    /**
     * Search method is added to perform search function of the parameter
     * keyword from all the webpages. This method will find the pages with the
     * keyword and print them in a tabular form.
     *
     * @param keyword String keyword is the word that has to be searched from
     * all the webpages
     * @return String format of the output in a tabular form with the list of
     * all the webpages that contains the parameter keyword in its list of
     * keywords.
     */
    public String search(String keyword) {
        ArrayList<WebPage> copy = new ArrayList<>();
        copy.addAll(pages);
        Collections.sort(copy, new RankComparator());
        WebPage[] webpage = copy.toArray(new WebPage[0]);
        String output = String.format("%-10s", "Rank")
                + String.format("%-15s", "PageRank")
                + String.format("%40s", "URL")
                + "\n-------------------------------------------------------------"
                + "------------------------------------------";
        int count = 1;
        for (int i = 0; i < webpage.length; i++) {
            if (webpage[i].getKeyword().contains(keyword)) {
                output += ("\n" + String.format("%-10s", count++) + String.format("%-20s", "|" + webpage[i].getRank()) + String.format("%-40s", "|\t" + webpage[i].getUrl()));
            }
        }
        output = (output.length() > 169) ? output : "no such keyword exists";
        return output;
    }
}
