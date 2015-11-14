
import java.util.Scanner;

/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#5 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 * @author radra_000
 */
public class BashTerminal {

    /**
     *Constant variable to store the number of children a node can have. This was
     * implemented to do the extra credit of 10 children. This was not implemented
     * so the number would remain 3. 
     */
    public static final int childs = 3;

    /**
     * This is the main class of this assigment, It will provide the user with 
     * an interface to type in the command line commands, and implement the 
     * ternery tree. The UI is implemented using a switch statment. By 
     * breaking apart the user input, the first word is determined to the command
     * and the word following it will be used inside that command. 
     * @param args
     *      arguments that will be passed in when opened using command prompt. 
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DirectoryTree tree = new DirectoryTree();
        boolean run = true;
        while (run) {
            System.out.print("[user@host]: $ ");
            String entry = input.nextLine();
            String name = "";
            int spaceLocation = entry.contains(" ") ? (entry.indexOf(" ")) : entry.length();
            String command = entry.substring(0, spaceLocation);
            //System.out.println(command);
            switch (command) {
                case ("mkdir"):
                    name = entry.substring(entry.indexOf(" ") + 1, entry.length());
                     {
                        try {
                            tree.makeDirectory(name);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("The text contains illegal characters");
                        } catch (FullDirectoryException ex) {
                            System.out.println("The directory already has three children");;
                        }
                    }
                    break;
                case ("pwd"):
                    System.out.println(tree.presentWorkingDirectory());
                    break;
                case ("ls"):
                    if (entry.contains("-R")) {
                        tree.printDirectoryTree(0);
                    } else {
                        System.out.println(tree.listDirectory());
                    }
                    break;
                case ("find"):
                    name = entry.substring(entry.indexOf(" ") + 1, entry.length());
                    tree.find(name);
                    break;
                case ("cd"):
                    char temp = (entry.charAt(entry.length()-1));
                    if (temp =='/') {
                        tree.resetCursor();
                    }else if (entry.contains(" ")) {
                        name = entry.substring(entry.indexOf(" ") + 1, entry.length());
                        try {
                            if (name.contains("/")){
                                tree.findAncestry(name, null);//fix it.
                            }
                            else{
                                tree.changeDirectory(name);
                            }
                        } catch (NotADirectoryException ex) {
                            System.out.println("The node you are trying to go to is a "
                                    + "leaf not a direcotory");
                        }
                    }
                    break;
                case ("touch"):
                    name = entry.substring(entry.indexOf(" ") + 1, entry.length());
                     {
                        try {
                            tree.makeFile(name);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("The text contains illegal characters");
                        } catch (FullDirectoryException ex) {
                            System.out.println("The directory already has " + childs + " children");
                        }
                    }
                    break;
                case ("mv"):
                    int space = entry.indexOf(" ", (entry.indexOf(" ") + 1));
                    String src = entry.substring(entry.indexOf(" ") + 1, space);
                    String dest = entry.substring(entry.indexOf(" ", entry.indexOf(src)) + 1, entry.length());
                    if (src.equals("root")) {
                        System.out.println("you cannot copy the root. ");
                        break;
                    }
                     {
                        try {
                            System.out.println(tree.moveNode(src, dest));
                        } catch (FullDirectoryException ex) {
                            System.out.println("The directory you are trying to "
                                    + "move to already has " + childs + " children");
                        }
                    }
                    break;
                case ("exit"):
                    run = false;
                    System.out.println("Terminating the program");
                    break;
                default:
                    System.out.println("Cannot recognize input");
                    break;
            }

        }
    }
}
