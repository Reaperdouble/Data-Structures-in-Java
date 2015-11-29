
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class WebGraph {

    public static final int MAX_PAGES = 40;
    private static ArrayList<WebPage> pages = new ArrayList();
    private ArrayList<Integer> array = new ArrayList(MAX_PAGES);
    //private ArrayList<ArrayList<Integer>> links = new ArrayList(MAX_PAGES);
    private int[][] links = new int[MAX_PAGES][MAX_PAGES];
    private int count = 0;
    
    public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException, FileNotFoundException {
        WebGraph graph = new WebGraph();
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

    public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException {
        if (url == null || keywords == null || alreadyExists(url)) {
            throw new IllegalArgumentException("Either the URL already exists or the URL/keyword is null");
        } else {
            pages.add(new WebPage(url, count, 0, keywords));
            count++;
        }
    }

    /**
     *
     * @param url
     * @return
     */
    public boolean alreadyExists(String url) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }

    public void addlink(String source, String destination) throws IllegalArgumentException {
        int x = -1, y = -1;
        for (int i = 0; i <pages.size(); i++) {
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
           // links.add(array);
           // System.out.println(links.get(0).size());
           // links.get(x).set(y, 1);             //check if it works
            String test = "";
            for(int i = 0; i < links.length;i++){
                for(int j = 0; j< links.length; j++){
                    System.out.print(links[i][j].get(0));
                }
                System.out.println("");
            }
            System.out.println();
            links[x][y].add(1);
        }
        updatePageRanks();
    }

    public void removePage(String url) {
        WebPage[] webpage = (WebPage[]) pages.toArray();
        for (int i = 0; i < webpage.length; i++) {
            if (pages.get(i).getUrl().equals(url)) {
                pages.remove(pages.get(i));
                //removing links
                System.out.println(links.toString());
                for (int k = 0; k < links.length; k++) {
                   // links.get(webpage[j].getIndex()).remove(pages.get(i).getIndex());  //check if it removes the intended column
                    links[k][i].removeElementAt(i);
                }
                for (int j = 0; j < links.length; j++) {
                   // links.get(webpage[j].getIndex()).remove(pages.get(i).getIndex());  //check if it removes the intended column
                    links[i][j].removeElementAt(i);
                }
                System.out.println(links.toString());
               // links.remove(pages.get(i).getIndex());                             //check if it removes the intended row
                
            }
        }
    }

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
            links[x][y].add(1);            //check if it works
        }
        updatePageRanks();
    }

    public void updatePageRanks() {
        int count = 0;
        for (int i = 0; i < pages.size(); i++) {
            for (int j = 0; j < pages.size(); j++) {
                count += links[j][i].elementAt(i);
                System.out.println(links[j][i].elementAt(i));
            }
            pages.get(i).setRank(count);
            count = 0;
        }
    }

    public void printTable(String sorting) {
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
                return;
        }
        WebPage[] webpage = (WebPage[]) copy.toArray();
        String heading = String.format("%10s", "Index")
                + String.format("%20s", "URL")
                + String.format("%10s", "PageRank")
                + String.format("%20s", "Links")
                + String.format("%40s", "Keywords") + "\n"
                + "---------------------------------------------------------------"
                + "-------------------------------------------------------------";
        System.out.println(heading);
        String linkNum = "";
        for (int i = 0; i < webpage.length; i++) {
            for (int j = 0; j < webpage.length; j++) {
                linkNum += "," + links[i][j].get(j);
            }
            String output = webpage[i].toString();
            output.replaceAll("****", linkNum);
            System.out.println(output);
        }
    }

    public String search(String keyword) {
        ArrayList<WebPage> copy = new ArrayList<>();
        copy.addAll(pages);
        Collections.sort(copy, new RankComparator());
        WebPage[] webpage = (WebPage[]) copy.toArray();
        String output = String.format("%10s", "Rank") + 
                String.format("%20s", "PageRank") + 
                String.format("%40s", "URL")+
                "\n-------------------------------------------------------------"
                + "------------------------------------------";
        int count = 1;
        for (int i = 0; i < webpage.length; i++) {
            if (webpage[i].getKeyword().contains(keyword)){
                output += ("\n"+String.format("%10s", count++)+String.format("%20s", webpage[i].getRank())+String.format("%40s", webpage[i].getUrl()));
            }
        }
        return output;
    }
}
