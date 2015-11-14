/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#5 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 *
 * @author radra_000
 */
/**
 * The DirectoryNode class is implemented similar to a linked list. It contains
 * references to the nodes of the tree. For this assignment, the tree contains
 * 3 children (left, middle, right). The node also contains the properties such
 * as name, isFile boolean variable, and ancestry. ancestry is not required 
 * by the assignment, but it has been added to store information about where 
 * in the tree its location. Its contains the address similar to the file address
 * in windows. It simplifies finding the parent of the node. 
 * @author radra_000
 */
public class DirectoryNode {

    private String name;
    private DirectoryNode left;
    private DirectoryNode right;
    private DirectoryNode middle;
    private boolean isFile;
    private String ancestry = "";

    /**
     *Default constructor method. that will set the name, isFile, ancestry 
     * when ever a new DirectoryNode object is created. The provided information is
     * applied to this node using the setter method.
     * @param name
     *      String name contains the information about the name of the current node
     * @param isFile
     *      Boolean variable to keep track of weather a node is a leaf or directory
     * @param parent
     *      DirectoryNode parent is the current node's parent node. It is used
     *      to set up ancestry information. 
     */
    public DirectoryNode(String name, boolean isFile, DirectoryNode parent) {
        this.setAncestry(parent);
        this.setName(name);
        this.setIsFile(isFile);
    }

    /**
     *Add child method, will add a child to left, middle, right, depending on 
     * which one is null. if the current node already has three children then 
     * it will throw exceptions
     * @param newChild
     *      DirectoryNode newChild is the node that has to be assigned as a child. 
     * @throws NotADirectoryException
     *      NotADirecotyException is thrown when the current node is a tree not a directory
     * @throws FullDirectoryException
     *      FullDirectoryException is thrown when the node already has 3 children
     */
    public void addChild(DirectoryNode newChild) throws NotADirectoryException,
            FullDirectoryException {
        if (this.isFile) {
            throw new NotADirectoryException();
        } else {
            if (this.getLeft() == null) {
                this.setLeft(newChild);
            } else if (this.getMiddle() == null) {
                this.setMiddle(newChild);
            } else if (this.getRight() == null) {
                this.setRight(newChild);
            } else {
                throw new FullDirectoryException();
            }

        }
    }

    /**
     * getter method for the ancestry variable. This method was created to ease 
     * the process of finding the parent of a node. 
     * @return
     *      return the String ancestry variable of this node. 
     */
    public String getAncestry() {
        return ancestry;
    }

    /**
     *Setter method to assign the ancestry details to the current node. whenever 
     * the node is created the details of the parent is obtained and the parents'
     * ancestry is taken and name of the parent is appended to form the ancestry 
     * address. If the parent is root. then root has no ancestry, so just the 
     * word root is assigned as the ancestry address to the new node. 
     * @param parent
     *      DirectoryNode parent is the parent of the current node. The details of 
     *      parents' ancestry will be used to form current node's ancestry. 
     */
    public void setAncestry(DirectoryNode parent) {
        if (parent != null) {
            if (parent.getName().equals("root")) {
                ancestry = "root";
            } else {
                ancestry = (parent.getAncestry() + "/" + parent.getName());
            }
        }
    }

    /**
     *Boolean method to check weather the current node is a leaf or directory
     * @return
     *      the boolean value, true if its a leaf, false if its a directory
     */
    public boolean isIsFile() {
        return isFile;
    }

    /**
     *Setter method to assign the isFile variable with a boolean value depending
     * on weather the new node being created is a file or directory.
     * @param isFile
     *      isFile is a boolean value passed in by the user, when a new node is 
     *      created. 
     */
    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    /**
     *Getter method to supply the information about the name of this node. 
     * @return
     *      String value containing information about the name of the current node. 
     */
    public String getName() {
        return name;
    }

    /**
     *Setter method to assign the name variable of this node with the String name 
     * that is passed in by the user. 
     * @param name
     *       String value passed in with the information about the name of the node
     *      being created. 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getLeft method is used to obtain the reference of the left child of the 
     * this node
     * @return
     *      DirectoryNode reference to the left child. 
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     * setLeft method is used to set reference of the left to the left child of 
     * this node. 
     * @param left
     *      DirecotoryNode left is the node to be assigned as the left child
     */
    public void setLeft(DirectoryNode left) {
        this.left = left;
    }

    /**
     *getRight method is used to obtain the reference of the right child of this 
     * node
     * @return
     *      DirectoryNode reference to the right child
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     *setRight method is used to set the reference of right to the right child of 
     * this node. 
     * @param right
     *      DirectoryNode right is the node to be assigned as the right child. 
     */
    public void setRight(DirectoryNode right) {
        this.right = right;
    }

    /**
     *getMiddle method is used to obtain the reference of the middle child of this 
     * node. 
     * @return
     *      DIrectoryNode reference to the middle child. 
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     *setMidddle method is used to set the reference of middle to the middle child
     * of this node
     * @param middle
     *      DirectoryNode middle is the node to be assigned as the middle child. 
     */
    public void setMiddle(DirectoryNode middle) {
        this.middle = middle;
    }

}
