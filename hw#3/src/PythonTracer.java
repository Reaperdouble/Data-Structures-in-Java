
/**
 * Rajith Radhakrishnan 109061463 rajith.radhakrishnan@stonybrook.edu HW#3 CSE
 * 214- R06 TA - Frank Migliorino GA - Yu Wang
 *
 * @author radra_000
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * The python tracer class contains the main method of this program. The class
 * will obtain the information from the user about the name of the python file
 * to be analyzed. It contains a constant integer Space count which is assigned
 * to 4, to indicate the spaces used when a tab is pressed. it contains
 * traceFile method will is responsible for opening the file and analyzing line
 * by line.
 *
 * For this assignment we use the java.util.Stack class, rather than creating
 * our own stack class.
 */
public class PythonTracer {

    public static final int SPACE_COUNT = 4;

    /**
     * indentCount method was added in order to simply the process of counting
     * the number of spaces before each line of the file.
     *
     * @param line line is of the type string which is the line read from the
     * file.
     * @return int value that represents the spaces in terms of the no of tabs.
     */
    private static int indentCount(String line) {
        int spaces = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                spaces++;
            } else {
                break;
            }
        }
        return spaces / SPACE_COUNT;
    }

    /**
     * printProgress was implement to simplify to task of keeping the user
     * updated on what the program is currently doing. depending on indentation
     * of the block of code the program will print and assign the name for the
     * block. The block from the top of the stack is removed and its name its
     * name is converted to char array to certain digit at the particular
     * location. if the indentation is less than the number present in the block
     * name, that means we have a new subBlock(1.1.1). otherwise we have a new
     * block.(1.2)
     *
     * @param stack Stack to which all the blocks are pushed onto
     * @param indentLength String representation of the name of the block.
     * depending on the lenght of the name, indentLenght will store the block
     * will longest name. So that it will easier to identify where there is a
     * new block.
     * @param indent indent is the integer value of the no of tabs in front of
     * the current line that has been read.
     * @param loopName loopName is string that tells the method which block of
     * code is read. ie, for, def, while,.....
     *
     */
    public static void printProgress(Stack stack, String indentLength, int indent, String loopName) {
        CodeBlock block = (CodeBlock) stack.pop();
        String name = (stack.isEmpty()) ? "0" : ((CodeBlock) stack.peek()).getName();
        char[] blockName = name.toCharArray();

        if (indent <= blockName.length / 2) {
            int i = (int) (blockName[indent * 2] - '0');
            i++;
            blockName[indent * 2] = (char) ('0' + i);
            String newName = new String(blockName);
            newName = newName.substring(0, indent * 2 + 1);
            if (newName.length() > name.length()) {//checking if it entered new block or not
                printLeaving(name, newName, block);
            } else {
                System.out.println("Entering block " + newName + " '" + loopName + "':");
            }
            name = newName;
            System.out.println("\tblock " + name + ":\t" + block.toString());
        } else {
            if (indentLength.length() > name.length()) {
                char i = indentLength.charAt(name.length());
                indentLength = indentLength.substring(0, name.length());
                int blockNum = (int) ('0' - i);
                name = name.concat("." + blockNum);
            } else {
                name = name.concat(".1");
            }
            System.out.println("Entering block " + name + " '" + loopName + "':");
            System.out.println("\tblock " + name + ":\t" + block.toString());
        }
        block.setName(name);
        stack.push(block);
    }

    /**
     * printLeaving method is assist printProgress method. This method is called
     * when the indentation is less than than the previous block of code, which
     * indicates the current block of code is over is python, so we have to
     * print leaving message on the screen the updated values of the complexity
     * of the parent block.
     *
     * @param name name of the block(subblock)(string) that we are leaving
     * @param newName name of the parent block that we have to update the
     * complexity
     * @param block block is the current block that was on top of the stack, so
     * we can update its complexity. (usually the parent block)
     */
    public static void printLeaving(String name, String newName, CodeBlock block) {
        System.out.println("Leaving block " + name + ", updating block " + newName
                + ":");
        block.updateSubComplexity(block);
        name = newName;
        System.out.println("\tblock " + name + ":\t" + block.toString());
    }

    /**
     * traceFile method is a static method that is essential to this assignment.
     * it perform the operation of openning and reading the file and analyzing
     * line by line and call the above methods to keep the user updated on the
     * processes It contains a lot of while loop to check for each scenarios,
     * such as weather we left the block or its a new sub block etc. the while
     * loop will help read each line until we came to the end of the file. if
     * the no such file exists in the fileName, an FileNotFoundException will be
     * thrown. so the try catch method is implemented to catch this.
     *
     * @param fileName name of the file entered by the user and passed in from
     * the main method
     * @return returns the totalComplexity(complexity) of the whole file.
     */
    public static Complexity traceFile(String fileName) {
        try {
            int indentPrevious = 0;
            Stack stack = new Stack();
            Stack indentStack = new Stack();
            Complexity totalComplexity = new Complexity(0, 0);
            //CodeBlock whileBlock = new CodeBlock();
            String indentLength = "";
            Complexity bigO = new Complexity();
            int count = 0;
            Scanner input = new Scanner(new File(fileName));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                int indent = indentCount(line);
                if (!(line.length() == 0 || line.contains("#"))) {
                    if ((!indentStack.isEmpty()) && (!stack.isEmpty())
                            && indent <= (int) indentStack.peek()) {
                        indentStack.pop();
                        CodeBlock block2 = (CodeBlock) stack.pop();
                        CodeBlock block1 = (CodeBlock) stack.pop();
                        count -= 2;
                        indentLength = block2.getName();
                        // printLeaving(block2.getName(), block1.getName(), block1);
                        block1.updateSubComplexity(block2);
                        System.out.println("Leaving block " + block2.getName()
                                + ", updating block " + block1.getName() + ":");
                        //block1.updateSubComplexity(block2);
                        block1.setHighSubComplexity(totalComplexity.isGreater(
                                block1.getHighSubComplexity())
                                        ? totalComplexity : (block1.getHighSubComplexity()));
                        System.out.println("\tblock " + block1.getName() + ":\t" + block1.toString());
                        stack.push(block1);
                        totalComplexity.equals(totalComplexity.isGreater(
                                ((CodeBlock) stack.peek()).getHighSubComplexity())
                                        ? totalComplexity : ((CodeBlock) stack.peek()).getHighSubComplexity());
                        count++;
                    }
                    //for loop check
                    if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.FOR])) {
                        CodeBlock forBlock = new CodeBlock();
                        indentStack.push(indent);
                        bigO.setLogPower(line.contains(" log_N:") ? 1 : 0);
                        bigO.setnPower(line.contains(" N:") ? 1 : 0);
                        forBlock.setBlockComplexity(bigO);
                        stack.push(forBlock);
                        printProgress(stack, indentLength, indent, "for");
                        count++;
                    } //while loop check
                    else if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.WHILE])) {
                        CodeBlock whileBlock = new CodeBlock();
                        indentStack.push(indent);
                        bigO.setLogPower(0);
                        bigO.setnPower(0);
                        String loopVariale = line.substring(
                                line.indexOf(" while ") + 7, line.indexOf(" while ") + 8);
                        whileBlock.setLoopVariable(loopVariale);
                        whileBlock.setBlockComplexity(bigO);
                        stack.push(whileBlock);
                        printProgress(stack, indentLength, indent, "while");
                        count++;
                    } //Def loop check
                    else if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.DEF])) {
                        CodeBlock defBlock = new CodeBlock();
                        indentStack.push(indent);
                        bigO.setLogPower(0);
                        bigO.setnPower(0);
                        defBlock.setBlockComplexity(bigO);
                        stack.push(defBlock);
                        printProgress(stack, indentLength, indent, "def");
                        count++;
                    } //if check
                    else if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.IF])) {
                        CodeBlock ifBlock = new CodeBlock();
                        indentStack.push(indent);
                        bigO.setLogPower(0);
                        bigO.setnPower(0);
                        ifBlock.setBlockComplexity(bigO);
                        stack.push(ifBlock);
                        printProgress(stack, indentLength, indent, "if");
                        count++;
                    } //else if check
                    else if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELIF])) {
                        CodeBlock elifBlock = new CodeBlock();
                        indentStack.push(indent);
                        bigO.setLogPower(0);
                        bigO.setnPower(0);
                        elifBlock.setBlockComplexity(bigO);
                        stack.push(elifBlock);
                        printProgress(stack, indentLength, indent, "elif");
                        count++;
                    } //else check
                    else if (line.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELSE])) {
                        CodeBlock elseBlock = new CodeBlock();
                        indentStack.push(indent);
                        bigO.setLogPower(0);
                        bigO.setnPower(0);
                        elseBlock.setBlockComplexity(bigO);
                        stack.push(elseBlock);
                        printProgress(stack, indentLength, indent, "else");
                        count++;
                    } //assignment variable inside while loop check
                    else if (stack.size() == count) {
                        CodeBlock block;
                        block = (CodeBlock) stack.pop();
                        count--;
                        if (line.contains(block.getLoopVariable())) {
                            if (line.contains("/=") || line.contains("*=")) {
                                bigO.setLogPower(1);
                                bigO.setnPower(0);
                                block.setBlockComplexity(bigO);
                            } else if (line.contains("-=") || line.contains("+=")) {
                                bigO.setLogPower(0);
                                bigO.setnPower(1);
                                block.setBlockComplexity(bigO);
                            }
                            System.out.println("Found update statement, updating block "
                                    + block.getName() + ":");
                            System.out.println("\tblock " + block.getName()
                                    + ":\t" + block.toString());

                        }//ask if the break will break the while
                        stack.push(block);
                        count++;
                    }

                    //other statements ignore
                }
            }

            CodeBlock block2 = (CodeBlock) stack.pop();

            while (!stack.isEmpty()) {
                printLeaving(block2.getName(), ((CodeBlock) stack.peek()).getName(), block2);
                ((CodeBlock) stack.peek()).setHighSubComplexity(totalComplexity.isGreater(
                        block2.getHighSubComplexity())
                                ? totalComplexity : (block2.getHighSubComplexity()));
                totalComplexity.equals(totalComplexity.isGreater(
                        (block2).getHighSubComplexity())
                                ? totalComplexity : (block2).getHighSubComplexity());
                block2 = (CodeBlock) stack.pop();
            }
            System.out.println("Leaving block " + block2.getName());
            count--;
            return totalComplexity;
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + fileName + " was not found");
            return null;
        }
    }

    /**
     * main method of the program that will prompt the user for the name of the
     * file
     *
     * @param args arguments that will passed in when using command line.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Enter the name of the file(or 'quit' to quit)");
            String ans = input.nextLine();
            if (ans.toUpperCase().equals("QUIT") || ans.toUpperCase().equals("Q")) {
                run = false;
                System.out.println("Terminating the program...");
            } else {
                System.out.println("Total Complexity: " + traceFile(ans));
            }
        }

    }

}
