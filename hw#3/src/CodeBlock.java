
/**
 * Rajith Radhakrishnan 109061463 rajith.radhakrishnan@stonybrook.edu HW#3 CSE
 * 214- R06 TA - Frank Migliorino GA - Yu Wang
 *
 * @author radra_000
 */
/**
 *
 * Code block object describes a nested block of code. Since the file to be read
 * will contain different blocks of code such as while, for, def, if, else, elif
 * we will use the static variables created in this class to check wheather a
 * block of code contains these keywords.
 */
public class CodeBlock {

    /**
     *
     */
    public static final String[] BLOCK_TYPES = {"def ", " for ", "while", " if ", " else ",
        " elif "};
    public static final int DEF = 0, FOR = 1, WHILE = 2, IF = 3, ELSE = 4, ELIF = 5;
    private Complexity blockComplexity = new Complexity(0, 0);  //all blocks starts with a default complexity of O(1)
    private Complexity highSubComplexity = new Complexity(0, 0);
    private String name = "0";
    private String loopVariable = "@";  //assinged to @ in order to avoid null pointers

    /**
     * default constructor that will assign the passed in complexity object to
     * this object. We will not assign the highSubComplexity object because it
     * changes relative to the next code being read.
     *
     * @param blockComplexity BlockCompleixty (complexity) contains information
     * about the complexity of the block of code. for which this object was
     * created
     */
    public CodeBlock(Complexity blockComplexity) {
        this.blockComplexity.setLogPower(blockComplexity.getLogPower());
        this.blockComplexity.setnPower(blockComplexity.getnPower());
    }

    /**
     * Empty constructor to be used to create instance of the CodeBlock object
     */
    public CodeBlock() {
    }    //empty constructor

    /**
     * UpdateSubComplexity method was implemented to solve the problem of
     * changing highSubComplexity whenever we leave a block of Code.
     *
     * @param block block variable of the type CodeBlock which contains the
     * information about the BlockComplexity which will be appended to the
     * highSubComplexity
     */
    public void updateSubComplexity(CodeBlock block) {

        this.highSubComplexity.setLogPower(block.highSubComplexity.getLogPower()
                + block.blockComplexity.getLogPower());
        this.highSubComplexity.setnPower(block.highSubComplexity.getnPower()
                + block.blockComplexity.getnPower());

    }

    /**
     * getter method to return the blockComplexity of this method
     *
     * @return The variable blockComplexity of the type Complexity, of this
     * object
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     * setter method that will assign the blockComplexity object that is passed
     * in to blockComplexity object of this class.
     *
     * @param blockComplexity blockCompleixty object of the type complexity
     * containing information about the complexity of the block of code.
     */
    public void setBlockComplexity(Complexity blockComplexity) {
        this.blockComplexity.setLogPower(blockComplexity.getLogPower());
        this.blockComplexity.setnPower(blockComplexity.getnPower());
    }

    /**
     * compareComplexity method was created to check and see weather any of the
     * two blocks are greater. This method will invoke the isGreater method from
     * the complexity class
     *
     * @param block1 block1 of the type CodeBlock to be compared with
     * @param block2 block2 of the type CodeBlock to be compared with
     * @return boolean. true if block 1 has the greater complxity ie bigO, false
     * otherwise
     */
    public static boolean compareComplexity(Complexity block1, Complexity block2) {
        return block1.isGreater(block2);    //if true block 1 is greater
    }

    /**
     * Getter method use to return the highSubComplexity variable
     *
     * @return highSubComplexity variable of the type Complexity containing the
     * information about npower and logpower of the block.
     */
    public Complexity getHighSubComplexity() {
        return highSubComplexity;
    }

    /**
     * setter method used to assign the highSubComplexity of this object with
     * the information from the highSubComplexity object that is passed in
     *
     * @param highSubComplexity highSubComplexity is of the type complexity
     * containing information about nPower and logPOwer values.
     */
    public void setHighSubComplexity(Complexity highSubComplexity) {
        this.highSubComplexity.setLogPower(highSubComplexity.getLogPower());
        this.highSubComplexity.setnPower(highSubComplexity.getnPower());
    }

    /**
     * getter method that will return the name of the code block. ie, 1,
     * 1.1,1.3.2
     *
     * @return name of the type string which is the name of this block
     */
    public String getName() {
        return name;
    }

    /**
     * assigns the name of this class the name(string) that is passed in
     *
     * @param name name variable of the type string containing information about
     * the name of the block. ie, 1,1.2, 1.1.1 etc.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * loopvariable is only used for the while block of the code. This is a
     * getter method that will return the loopvariable of this object.
     *
     * @return loopVariable of the type string which contains the name of the
     * variable used in the while loop
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /**
     * setter method that will set the loopvariable of this object to the
     * loopvariable of the type string that was passed in. The loopvariable
     * method will be used only when while block of code is found.
     *
     * @param loopVariable loopVariable is the varible that was used in the
     * while block for iterations.
     */
    public void setLoopVariable(String loopVariable) {
        this.loopVariable = loopVariable;
    }

    /**
     * toString method overrides the toString method of the object class. this
     * will return a string representation of the complexity values of the
     * variables blockComplexity and highSubComplexity. This method will call
     * the toString method of the complexity object to obtain the bigO notation
     * representation
     *
     * @return String representation of the complexity of the block complexity
     * and highSubComplexity. The string is nicely formatted
     */
    @Override
    public String toString() {
        String string = String.format("%-20s%-15s%-20s%-5s", "block complexity = ",
                blockComplexity.toString(), "highSubComplexity = ", highSubComplexity.toString());
        return string;
    }

}
