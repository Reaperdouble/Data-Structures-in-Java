import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class WebGraph {
    public static final int MAX_PAGES = 40;
    private static ArrayList<WebPage> pages = new ArrayList(); 
    private ArrayList<ArrayList<Integer>>links = new ArrayList<ArrayList<Integer>>(MAX_PAGES);
    private int count = 0;
    public static WebGraph buildFromFiles(String pagesFile,String linksFile) throws IllegalArgumentException, FileNotFoundException{
        WebGraph graph = new WebGraph();
        //entering the pages from the file
        FileReader file = new FileReader(pagesFile);
        Scanner input = new Scanner(file);
        while(input.hasNextLine()){
            String data = input.nextLine();
            ArrayList<String> words = new ArrayList <> (Arrays.asList(data.split(" ")));
            String url = words.get(0);
            words.remove(0);
            graph.addPage(url, words);
        }
        //entering the links from the file
        file = new FileReader(linksFile);
        input = new Scanner(file);
         while(input.hasNextLine()){
            String data = input.nextLine();
            String[] link = data.split(" ");
            graph.addlink(link[0], link[1]);
        }
        return graph;
    }
    public void addPage(String url, Collection<String> keywords) throws IllegalArgumentException{
        if(url == null || keywords == null||alreadyExists(url)){
            throw new IllegalArgumentException("Either the URL already exists or the URL/keyword is null");
        }
        else{
                pages.add(new WebPage(url, count, 0, keywords));
                count++;
        }
    }

    /**
     *
     * @param url
     * @return
     */
    public boolean alreadyExists(String url){
       WebPage[] webpage = (WebPage[])pages.toArray();
       for(int i =0; i<webpage.length;i++){
           if(webpage[i].getUrl().equals(url)){
               return true;
           }
       }
       return false;
    }
    public void addlink(String source, String destination) throws IllegalArgumentException{
       WebPage[] webpage = (WebPage[])pages.toArray();
       int x = -1,y = -1;
       for(int i =0; i<webpage.length;i++){
           if(webpage[i].getUrl().equals(source)){
               x = webpage[i].getIndex();
           }
           if(webpage[i].getUrl().equals(destination)){
               y = webpage[i].getIndex();
           }
       }
       if(x==-1||y==-1){
           throw new IllegalArgumentException("source or destination does not exist");
       }
       else{
         links.get(x).set(y,1);             //check if it works
       }
    }
    public void removePage(String url){
        WebPage[] webpage = (WebPage[]) pages.toArray();
        for(int i =0; i<webpage.length;i++){
           if(webpage[i].getUrl().equals(url)){
               pages.remove(webpage[i]);
               for(int j=0; j<links.size();j++){
                 links.get(webpage[j].getIndex()).remove(webpage[i].getIndex());  //check if it removes the intended column
               }
               links.remove(webpage[i].getIndex());                             //check if it removes the intended row
           }
       }
    }
    public void removeLink(String source, String destination){
       WebPage[] webpage = (WebPage[])pages.toArray();
       int x = -1,y = -1;
       for(int i =0; i<webpage.length;i++){
           if(webpage[i].getUrl().equals(source)){
               x = webpage[i].getIndex();
           }
           if(webpage[i].getUrl().equals(destination)){
               y = webpage[i].getIndex();
           }
       }
       if(x==-1||y==-1){
       }
       else{
         links.get(x).set(y,0);             //check if it works
       }
    }
    public void updatePageRanks(){
        WebPage[] webpage = (WebPage[])pages.toArray();
        int count=0;
       for(int i =0; i<webpage.length;i++){
           for(int j = 0; j<webpage.length;j++){
               count+=links.get(j).get(i);
           }
           pages.get(i).setRank(count);
           count = 0;
       }
    }
    public void printTable(){
        String heading= String.format("%10s", "Index") + 
                String.format("%20s", "URL") +
                String.format("%10s", "PageRank") + 
                String.format("%20s", "Links") +
                String.format("%40s", "Keywords")+"\n"+
                "---------------------------------------------------------------"
                + "-------------------------------------------------------------";
        System.out.println(heading);
        WebPage[] webpage = (WebPage[])pages.toArray();
        String linkNum = ""; 
        for(int i =0; i<webpage.length;i++){
            for(int j = 0; j<webpage.length;j++){
               linkNum += ","+ links.get(i).get(j);
            }
            String output =  webpage[i].toString();
            output.replaceAll("****", linkNum);
            System.out.println(output);
        }
    }
}

