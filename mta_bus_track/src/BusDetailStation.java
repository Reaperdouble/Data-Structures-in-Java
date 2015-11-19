
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
public class BusDetailStation {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        InfoTrack info = new InfoTrack();
        System.out.println("What is the bus number: ");
        info.buildFromUrl("Q43");//input.next());
        info.printBuses();
    }
}
