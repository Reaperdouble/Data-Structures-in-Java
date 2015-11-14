
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
public class Main {

    public static int[][] tent;// = new int[5][5];
    public static int num;
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);
        boolean run = true;
        
        //while (run) {
            System.out.println("Enter the Test case number or 0 to quit: ");
            num = input.nextInt();
            count = 1;
            switch (num) {
                case (1):
                    tent = new int[1][3];
                    simulate();
                    break;
                case (2):
                    tent = new int[3][3];
                    simulate();
                    break;
                case (3):
                    tent = new int[5][5];
                    tent[2][2] = -1;
                    simulate();
                    break;
                case (4):
                    tent = new int[2][5];
                    tent[0][4] = -1;
                    simulate();
                    break;
                case (5):
                    tent = new int[6][5];
                    tent[3][0] = -1;
                    tent[3][2] = -1;
                    tent[0][4] = -1;
                    simulate();
                    break;
                case (6):
                    tent = new int[6][6];
                    simulate();
                    break;
                case (7):
                    tent = new int[7][9];
                    tent[2][0] = -1;
                    tent[2][2] = -1;
                    tent[4][2] = -1;
                    tent[4][3] = -1;
                    tent[4][7] = -1;
                    tent[5][4] = -1;
                    simulate();
                    break;
                case (8):
                    tent = new int[8][10];
                    tent[7][6] = -1;
                    simulate();
                    break;
                case (9):
                    tent = new int[7][8];
                    tent[0][7] = -1;
                    tent[2][2] = -1;
                    tent[3][6] = -1;
                    tent[5][4] = -1;
                    tent[5][0] = -1;
                    simulate();
                    break;
                case (10):
                    tent = new int[8][8];
                    tent[0][4] = -1;
                    tent[2][4] = -1;
                    tent[5][2] = -1;
                    tent[5][0] = -1;
                    simulate();
                    break;
                case (0):
                   // run = false;
                    break;
               // default:
                    //run = false;
            //}
        }
    }
    public static void switchCase(){
    switch (num) {
                case (1):
                    tent = new int[1][3];
                    break;
                case (2):
                    tent = new int[3][3];
                    break;
                case (3):
                    tent = new int[5][5];
                    tent[2][2] = -1;
                    break;
                case (4):
                    tent = new int[2][5];
                    tent[0][4] = -1;
                    break;
                case (5):
                    tent = new int[6][5];
                    tent[3][0] = -1;
                    tent[3][2] = -1;
                    tent[0][4] = -1;
                    break;
                case (6):
                    tent = new int[6][6];
                    break;
                case (7):
                    tent = new int[7][9];
                    tent[2][0] = -1;
                    tent[2][2] = -1;
                    tent[4][2] = -1;
                    tent[4][3] = -1;
                    tent[4][7] = -1;
                    tent[5][4] = -1;
                    break;
                case (8):
                    tent = new int[8][10];
                    tent[7][6] = -1;
                    break;
                case (9):
                    tent = new int[7][8];
                    tent[0][7] = -1;
                    tent[2][2] = -1;
                    tent[3][6] = -1;
                    tent[5][4] = -1;
                    tent[5][0] = -1;
                    break;
                case (10):
                    tent = new int[8][8];
                    tent[0][4] = -1;
                    tent[2][4] = -1;
                    tent[5][2] = -1;
                    tent[5][0] = -1;
                    break;
            }
}
    public static void simulate() {

        for (int i = 0; i < tent.length; i++) {
            for (int j = 0; j < tent[0].length; j++) {
                Main.swap(i, j);
            }
        }
        int temp = count;
        switchCase();
        for (int i = 0; i < tent.length; i++) {
            for (int j = 0; j < tent[0].length; j++) {
                Main.swap(i, j);
            }
        }
        if (temp > count) {
            for (int i = 0; i < tent.length; i++) {
                for (int j = 0; j < tent[0].length; j++) {
                    Main.swap(i, j);
                }
            }
        }

        for (int i = 0; i < tent.length; i++) {
            for (int j = 0; j < tent[0].length; j++) {
                System.out.print(tent[i][j] + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("maximum sleeping bags: " + (count - 1));
    }
    public static int run = 0;
    public static int count = 1;

    public static void swap(int i, int j) {
        switch ((run % 2)) {
            case (0):
                int test = 0;
                switch ((test % 2)) {//case (0):
                    case (0):
                        if (HorizontalBag.createHorizontal(i, j, count)) {
                            count++;
                        } else if (VerticalBag.createVertical(i, j, count)) {
                            count++;
                        }
                        break;
                    case (1):
                        if (VerticalBag.createVertical(i, j, count)) {
                            count++;
                        } else if (HorizontalBag.createHorizontal(i, j, count)) {
                            count++;
                        }
                        break;
                }
                test++;
                break;
            case (1):
                if (HorizontalBag.createHorizontal(i, j, count)) {
                    count++;
                } else if (VerticalBag.createVertical(i, j, count)) {
                    count++;
                }
                break;
        }
        run++;

    }

}
