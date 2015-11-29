
import java.util.Collection;


public class WebPage {
    private String url;
    private int index;
    private int rank;
    private Collection<String> keywords;

    public WebPage(String url, int index, int rank, Collection<String> keywords) {
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
    public void setRank(int rank){
        this.rank = rank;
    }
    public String toString(){
        String output = String.format("%10s", this.index) + 
                String.format("%20s", this.url) +
                String.format("%10s", this.rank) + 
                String.format("%20s", "****") +
                String.format("%40s", this.keywords);
        return output;
    }
}
