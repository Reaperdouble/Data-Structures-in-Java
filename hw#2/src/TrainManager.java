
import java.util.Scanner;

/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#2 
 * CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */

public class TrainManager {

    /**
     * This is the main class of the program. it will provide the user with a
     * menu option with which they can use the program. The program will run
     * until the user enter q. The programs will use methods mainly from
     * TrainLinkedList class. There is a try catch loop, so where there is any 
     * error, the program will just quit, after telling 
     *@param args cmd-line arguments
     */
    public static void main(String[] args) {
        try{
        TrainLinkedList list = new TrainLinkedList();
        boolean run = true;
        Scanner input = new Scanner(System.in);
        while (run) {
            System.out.println("Menu");
            System.out.println("F - Move Cursor Forward");
            System.out.println("B - Move Cursor Backward");
            System.out.println("I - Insert car after Cursor");
            System.out.println("R - Remove Car at Cursor");
            System.out.println("L - Set Load at Cursor");
            System.out.println("S - Search for Product");
            System.out.println("T - Print Train");
            System.out.println("M - Print Manifest");
            System.out.println("D - Remove Dangerous Cars");
            System.out.println("Q - Quit the program");
            System.out.print("\n Enter a selection:");
            String choice = input.next();
            choice = choice.toUpperCase();
            switch (choice) {
                case ("F"):
                    list.cursorForward();
                    break;
                case ("B"):
                    list.cursorBackward();
                    break;
                case ("I"):
                    System.out.print("What is the Length of the Car(m): ");
                    double length = input.nextDouble();
                    System.out.print("what is the weight of the Car(Tons): ");
                    double weight = input.nextDouble();
                    TrainCar newCar = new TrainCar(length, weight);
                    list.insertAfterCursor(newCar);
                    break;
                case ("R"):

                    System.out.println("train cars removed");
                    if(list.getLength()>0){
                    
                    System.out.println(String.format("%-20s%-2s%-60s", "Car:", "|", "Load:"));
                    System.out.println(String.format("%-10s%-10s%-2s%-15s%-13s%-13s%-10s",
                            "Length(m)", "Weight(t)", "|",
                            "Name", "Weight(t)", "Value($)", "Dangerous"));
                    System.out.println("====================+=================="
                            + "========" + "=====================================");
                    System.out.println(list.removeCursor().toString());
                    }
                    break;
                /**
                 * when ever there is a load added, This case will check weather
                 * there was a load present earlier, depending on weather or not
                 * the load was there the total weight and the dangerous count
                 * of the TrainLinkedList class will be updated by calling
                 * updateTotalWeight and UpdateDangerousCount methods.
                 */
                case ("L"):
                    input.nextLine();
                    System.out.println("What is the name of the product: ");
                    String name = input.nextLine();
                    System.out.println("What is the weight of the product: ");
                    double mass = input.nextDouble();
                    System.out.println("What is the value of the product: ");
                    double value = input.nextDouble();
                    input.nextLine();
                    System.out.println("Is the Product Dangerous(Y/N): ");
                    String isDangerous = input.nextLine();
                    isDangerous = isDangerous.toUpperCase();
                    if (list.getCursorData().getLoad() != null) {
                        list.updateTotalWeight(list.getCursorData().getLoad().getWeight() * -1);
                        list.updateDangerousCount(list.getCursorData().getLoad().isProductType());
                    }
                    if (isDangerous.equals("Y") || isDangerous.equals("YES")) {
                        list.updateTotalWeight(mass);
                        list.updateDangerousCount(false);   //false because i dont want to decrement dangerous count
                        ProductLoad load = new ProductLoad(name, mass, value, true);
                        list.getCursorData().setLoad(load);
                    } else {
                        ProductLoad load = new ProductLoad(name, mass, value, false);
                        list.getCursorData().setLoad(load);
                        list.updateTotalWeight(load.getWeight());
                    }

                    break;
                case ("S"):
                    input.nextLine();
                    System.out.println("What is the name of the product: ");
                    String searchName = input.nextLine();
                    list.findProduct(searchName);
                    break;
                case ("T"):
                    System.out.println(list.toString());
                    break;
                case ("M"):
                    list.printManifest();
                    break;
                case ("D"):
                    list.removeDangerousCars();
                    break;
                case ("Q"):
                    run = false;
                    break;
                default:
                    System.out.println("pick a option from the menu");
            }
        }
        System.out.println("The program is quitting....");
        input.close();
        }
        catch(Exception e){
            System.out.println("Some error occured....Program ended");
        }
    }
}
