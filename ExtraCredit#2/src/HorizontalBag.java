/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class HorizontalBag {

    public static boolean createHorizontal(int x, int y, int count) {
        if (x < Main.tent.length && x + 1 < Main.tent.length && x + 2 < Main.tent.length) {
            for (int i = 0; i < 3; i++) {

                if (Main.tent[x + i][y] == -1 || Main.tent[x + i][y] > 0) {
                    return false;
                }

            }
            for (int i = 0; i < 3; i++) {

                if (Main.tent[x + i][y] == 0) {
                    Main.tent[x + i][y] = count;
                }

            }
            return true;
        }
        else{
            return false;
        }
    }
}
