/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class VerticalBag {
      public static boolean createVertical(int x, int y, int count) {
        if (y < Main.tent[0].length && y + 1 < Main.tent[0].length && y + 2 < Main.tent[0].length) {
            for (int i = 0; i < 3; i++) {
                if (Main.tent[x][y + i] == -1 || Main.tent[x][y + i] > 0) {
                    return false;
                }
            }
            for (int i = 0; i < 3; i++) {
                if (Main.tent[x][y + i] == 0) {
                    Main.tent[x][y + i] = count;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
}
