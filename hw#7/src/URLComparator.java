
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
public class URLComparator implements Comparator<WebPage>{

    @Override
    public int compare(WebPage o1, WebPage o2) {
          return o1.getUrl().compareToIgnoreCase(o2.getUrl());
    }
    
}
