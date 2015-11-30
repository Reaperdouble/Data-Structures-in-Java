
import java.util.ArrayList;
import java.util.Collection;


public class WebPage {
    private String url;
    private int index;
    private int rank;
    private ArrayList<String> keywords;

    public WebPage(String url, int index, int rank, ArrayList<String> keywords) {
        this.url = url;
        this.index = index;
        this.rank = rank;
        this.keywords = keywords;
    }
    public WebPage(){}
    public String getUrl(){
        return url;
    }
    public int getIndex(){
        return index;
    }
    public void setIndex(int index){
        this.index =index;
    }
    public int getRank(){
        return rank;
    }
    public ArrayList<String> getKeyword(){
        return keywords;
    }
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
