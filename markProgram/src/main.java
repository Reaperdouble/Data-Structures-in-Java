
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author radra_000
 */
public class main {

    public static void main(String[] args) {
        int x = 60;
        while (x > 0) {
            ArrayList<String> list = new ArrayList<>();
            String[] array = {" Second Person ", "Three-Part List", " Presupposition",
                "  First Person ", "Parallel Syntax", "     Simile    ", "    Contrast   ", "    Metaphor   ",
                "  Collocation  ", "   Repetition  ", " Backgrounding ", " Foregrounding ", "   Euphemism   ",
                "   Dysphemism  ", "Personification", "Intertextuality", " Nominalization"};
            list.add(" LEXICAL CHOICE");
            for (int i = 0; i < 17; i++) {
                list.add(array[i]);
            }
            String[] finalList = new String[16];
            int j = 1;
            String temp;
            System.out.print("------------------------------------------------------------------------------\n\n");
            for (int i = 1; i <= 16; i++) {
                temp = "| " + randomVariable(list) + " |";
                System.out.print(String.format("%16s", temp));
                if (j % 4 == 0) {
                    System.out.println("\n");
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("");
                }
                j++;
            }
            System.out.println("\n");
            x--;
        }
    }

    public static String randomVariable(ArrayList finalList) {
        int random = (int) (Math.random() * finalList.size());
        //System.out.println(random);
        String output = (String) finalList.get(random);
        finalList.remove(random);
        return output;
    }
}
