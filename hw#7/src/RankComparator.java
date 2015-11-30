
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class RankComparator implements Comparator<WebPage>{

    @Override
    public int compare(WebPage o1, WebPage o2) {
        if (o1.getRank()== o2.getRank()) {
            return 0;
        }
        else if(o1.getRank()< o2.getRank()){
            return 1;
        }
        else{
            return -1;
        }
    }
}

    