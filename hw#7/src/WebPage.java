
import java.util.ArrayList;

/**
 *WebPage class represents a hyperlinked document. It provides a base to store 
 * the information about a webpage. It contains String member variable url, 
 * int representing the position in the adjacency matrix, and rank of the webpage.
 * Collection of string in this case arraylist of string to store the keywords
 * defining the webpage. 
 * @author radra_000
 */
public class WebPage {
    private String url;
    private int index;
    private int rank;
    private ArrayList<String> keywords;

    /**
     *Contructor method that will assign the url, index, rank and keywords of this
     * class to the parameters that was passed in 
     * @param url
     *     the link url of the webpage passed in as a string by the user or read 
     * from file. 
     * @param index
     *      the index is the integer represents the position on the list
     * @param rank
     *      rank is the integer that represents the popularity of a the webpage
     * @param keywords
     *      keywords is a list of string used to define a webpage. usually the 
     *      main categories of a webpage. 
     */
    public WebPage(String url, int index, int rank, ArrayList<String> keywords) {
        this.url = url;
        this.index = index;
        this.rank = rank;
        this.keywords = keywords;
    }

    /**
     *Default constructor used to create a object of this class.  
     */
    public WebPage(){}

    /**
     *Getter method that returns the url of this webpage. 
     * @return
     *      String containing information about the url of this webpage. 
     */
    public String getUrl(){
        return url;
    }

    /**
     * Getter method that return the index of this webpage 
     * @return
     *      Integer value containing information about the index location of this
     *      webpage.
     */
    public int getIndex(){
        return index;
    }

    /**
     *Setter method to set the value of the index value of this class. 
     * @param index
     *  The index is the integer represents the position on the list
     *      
     */
    public void setIndex(int index){
        this.index =index;
    }

    /**
     *Getter method to return the rank of this webpage
     * @return
     *     returns the integer value, representing the ranking of this webpage. 
     *      which is the popularity of a webpage. 
     */
    public int getRank(){
        return rank;
    }

    /**
     * getter method to return the keywords pertaining to this web page
     * @return
     *      an ArrayList of string with the keyword. 
     */
    public ArrayList<String> getKeyword(){
        return keywords;
    }

    /**
     *Setter method to set the rank. so when new link is created its ranking 
     * will be updated. so we will use the setter method to update it. 
     * @param rank
     *      new int rank parameter contains the information about the ranking of 
     *      the webpage
     */
    public void setRank(int rank){
        this.rank = rank;
    }
    public String toString(){
        String output = String.format("%-10s", this.index) + "|"+
                String.format("%-20s", this.url) +"|"+
                String.format("%-10s", this.rank) + "|"+
                String.format("%-20s", "****") +"|"+
                String.format("%-40s", this.keywords);
        return output;
    }
}
