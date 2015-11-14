
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
public class BashTerminal {
    public static void main(String[] args) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
        Scanner input = new Scanner(System.in);
        DirectoryTree tree= new DirectoryTree(); 
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
                    name = entry.substring(entry.indexOf(" ")+1, entry.length());
                    tree.makeDirectory(name);
                    break;
                case ("pwd"):
                    System.out.println(tree.presentWorkingDirectory());
                    break;
                case ("ls"):
                    if (entry.contains("-R")) {
                        tree.printDirectoryTree();
                    } else {
                        System.out.println(tree.listDirectory());
                    }
                    break;
                case ("cd"):
                    if (entry.contains("/")) {
                        tree.resetCursor();
                    } else if (entry.contains(" ")) {
                        name = entry.substring(entry.indexOf(" ")+1, entry.length());
                        tree.changeDirectory(name);
                    }
                    break;
                case ("touch"):
                    name = entry.substring(entry.indexOf(" ")+1, entry.length());
                    tree.makeFile(name);
                    break;
                case ("exit"):
                    run = false;
                    break;
                default:
                    System.out.println("Cannot recognize input");
                    break;
            }

        }
    }
}
