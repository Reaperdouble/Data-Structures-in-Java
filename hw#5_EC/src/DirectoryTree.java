
/**
 * Rajith Radhakrishnan 
 * 109061463 
 * rajith.radhakrishnan@stonybrook.edu 
 * HW#5 CSE 214- R06 
 * TA - Frank Migliorino 
 * GA - Yu Wang
 * @author radra_000
 */
/**
 * DirectoryTree class contains the method that is used to do all the operation
 * associated with the ternery tree to implement a command line style interface.
 * Operations such as change directory(cd), move(mv), list name of the child(ls),
 * print the tree(ls -R), present working directory(pwd), make new directory(mkdir).
 * make new file(touch). 
 * @author radra_000
 */
public class DirectoryTree {

    DirectoryNode root = new DirectoryNode("root", false, null);
    DirectoryNode cursor;

    private boolean found = false;

    /**
     *Default constructor to create a will assign the cursor to the root after 
     * create it. 
     */
    
    public DirectoryTree() {
        cursor = root;
    }

    /**
     *resetCursor method is used to reset the cursor position to point a the root. 
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     *changeDirectory method is used to traverse the tree. When the parameter name 
     * is .., the cursor will be changed to the parent directory, this is done 
     * using the getAncestry method in the DirectoryNode class, and using the 
     * findNode method of this class. Since this method can also be used by 
     * passing the name of a directory in the parameter the else method is used
     * to search for that particular method. If nothing is found a exception will
     * be thrown
     * @param name
     *      String name contain the name of the directory to be change the cursor
     *      or .. to change the cursor to the parent.
     * @throws NotADirectoryException
     *      When a string is entered that is not a name of the directory of this 
     *      tree then the exception is thrown. 
     */
    public void changeDirectory(String name) throws NotADirectoryException {
        if ("..".equals(name)) {
            if(cursor == root){
                System.out.println("Already at the root directory");
            }
            else{
            String parent = cursor.getAncestry();
            parent = parent.substring(parent.lastIndexOf("/") + 1, parent.length());
            findNode(parent, root, false);
            }
        } else {
            DirectoryNode temp = cursor;
            findNode(name, root, false);
            if (cursor == null || cursor.isIsFile()) {
                String parent = cursor.getAncestry();
                parent = parent.substring(parent.lastIndexOf("/") + 1, parent.length());
                //System.out.println(parent);
                findNode(parent, root, false);
                throw new NotADirectoryException("No such Directory exists"
                        + " with a name: " + name);
            } else if (cursor.equals(temp)) {
                System.out.println("no such directory exists with the name " + name);
            }
        }
    }

    /**
     *changeAncestry method is used in conjuction with the moveNode method. When 
     * a node is moved to a different location, its ancestry details will be
     * changed, so this method will obtain the new parent and pass in to the
     * setAncestry method of the DirectoryNode class. Since the a node might 
     * contain children we also have to change all its children's ancestry address
     * this is done using preorder algorithm. 
     * @param child
     *      DirectoryNode child is a node that is being moved. 
     * @param parent
     *      DirectoryNode parent is a node to which the child is relocated.
     */
    public void changeAncestry(DirectoryNode child, DirectoryNode parent) {
        child.setAncestry(parent);
        if (child.getLeft() != null) {
            parent = child;
            child = parent.getLeft();
            changeAncestry(child, parent);
        }
        //cursorNode = node;
        if (child.getMiddle() != null) {
            parent = child;
            child = parent.getMiddle();
            changeAncestry(child, parent);
        }
        //cursorNode = node;
        if (child.getRight() != null) {
            parent = child;
            child = parent.getRight();
            changeAncestry(child, parent);
        }

    }

    /**
     *removeNode method is also implemented in conjuction with the moveNode method
     * When a method is moved to a new location, the current parent has to have
     * its child set to null, this is accomplished using this method. 
     * @param tempCursor
     *      DirectoryNode tempCursor is the node that is being moved. So its parent's
     *      subbranch containing tempCursor will be set to null
     */
    public void removeNode(DirectoryNode tempCursor) {
        String parent = tempCursor.getAncestry();
        parent = parent.substring(parent.lastIndexOf("/") + 1, parent.length());
        DirectoryNode temp = cursor;
        findNode(parent, root, false);
        if (cursor.getLeft() != null && cursor.getLeft().getName().equals(tempCursor.getName())) {
            cursor.setLeft(null);
        }
        if (cursor.getRight() != null && cursor.getRight().getName().equals(tempCursor.getName())) {
            cursor.setRight(null);
        }
        if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(tempCursor.getName())) {
            cursor.setMiddle(null);
        }
        cursor = temp;
    }

    /**
     *moveNode method is implemented for the extra credit. It will be called
     * whenever a mv command is typed in the console. This method will first find
     * the location of the directory/file and then it will find the destination
     * node and then it will move the node, by setting the current location to null
     * and making it a new child of the destination node. In the process, the 
     * ancestry of the nodes that are moved will also be changed. 
     * @param src
     *      String src is the name of the directory to be moved. 
     * @param dest
     *      String dest is the name of the directory to be assigned as a parent
     *      to the source
     * @return
     *      return a string informing the user if the node was successfully moved. 
     * @throws FullDirectoryException
     *      when the destination node already has 3 children the exception will 
     *      be thrown and the node will remain in its current location. 
     */
    public String moveNode(String src, String dest) throws FullDirectoryException {
        if(src.contains("/")){
            findAncestry(src, root);
        }else{          //checking weather to use findNode method or findAncestry method
            findNode(src, root, false);
        }
        DirectoryNode tempCursor = cursor;
        if(dest.contains("/")){
            findAncestry(dest, root);
        }else{          //checking weather to use findNode method or findAncestry method
            find(dest);
        }
        DirectoryNode temp = cursor; //to hold the location of the cursor for resetting later. 
        if (cursor.getLeft() == null) {
            removeNode(tempCursor);
            DirectoryNode node = tempCursor;
            changeAncestry(node, cursor);
            cursor.setLeft(node);
        } else if (cursor.getMiddle() == null) {
            removeNode(tempCursor);
            DirectoryNode node = tempCursor;
            changeAncestry(node, cursor);
            cursor.setMiddle(node);
        } else if (cursor.getRight() == null) {
            removeNode(tempCursor);
            DirectoryNode node = tempCursor;
            changeAncestry(node, cursor);
            cursor.setRight(node);
        } else {
            throw new FullDirectoryException("The Parent already has 3 children");
        }
        cursor = temp;
        return ("The node has been moved to " + dest+". The cursor is now at "+cursor.getName());
    }

    /**
     *presentWorkingDirectory method will return the string of the current location 
     * of the cursor. Since we also have to provide the string in this format
     * root/src/home/documents , we will utilize the getAncestry method which 
     * will return exactly the string in the above way. Invoked when the user 
     * types in pwd.
     * @return
     *      return String in this format root/src/home/documents of the node that 
     *      the cursor is currently pointing at. 
     */
    public String presentWorkingDirectory() {
        if (cursor == root) {
            return "root";
        } else {
            return (cursor.getAncestry() + "/" + cursor.getName());
        }
    }

    /**
     *listDirectory method is used to output a name of the children of the current 
     * node's(where cursor is at). invoked when the user types in ls. 
     * @return
     *      String of the name of the children of the node. 
     */
    public String listDirectory() {
        String left = (cursor.getLeft() != null) ? cursor.getLeft().getName() : "";
        String middle = (cursor.getMiddle() != null) ? cursor.getMiddle().getName() : "";
        String right = (cursor.getRight() != null) ? cursor.getRight().getName() : "";
        return (left + " " + middle + " " + right);
    }

    /**
     *PrintDirectoryTree method is used to print the name of the node in the 
     * subtree of the node that the cursor is currently pointing at. The output
     * will look similar to a tree. This method utilizes a recursive function
     * to print the tree. Depending on the level we have to add the corresponding
     * spaces in the front. The recursion is done using a preorder type implementation.
     * @param level
     *      int level is the number representing the number of spaces that should 
     *      be placed before a node of a tree is printed. 
     */
    public void printDirectoryTree(int level) {
        DirectoryNode temp = cursor;
        //int level = 0;
        String notation = (cursor.isIsFile()) ? "-" : "|-";
        String spacing = "";
        for (int i = 0; i < level; i++) {
            spacing = spacing.concat("  ");
        }
        System.out.println(spacing + notation + cursor.getName());
        level++;
        if (cursor.getLeft() != null) {
            cursor = cursor.getLeft();
            printDirectoryTree(level);
        }
        cursor = temp;
        if (cursor.getMiddle() != null) {
            cursor = cursor.getMiddle();
            printDirectoryTree(level);
        }
        cursor = temp;
        if (cursor.getRight() != null) {
            cursor = cursor.getRight();
            printDirectoryTree(level);
        }
        level = 0;
        cursor = temp;
    }

    /**
     *makeDirectory method is used to create a new node in the tree. 
     * if the name of the string contains any spaces or forward slash an exception
     * will be thrown. if not then the method will look for weather the node that
     * the cursor is pointing at has 3 children, if not then it will assign to the
     * spot that is null. if it already has 3 children then the fullDirectoryException
     * is thrown
     * @param name
     *      String name is the name of the node(directory) being created. 
     * @throws IllegalArgumentException
     *      When the name contains illegal characters such as space or forward slash
     * @throws FullDirectoryException
     *      When the node already has three children then the exception is thrown
     */
    public void makeDirectory(String name) throws IllegalArgumentException,
            FullDirectoryException {
        if (name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException("The String contain illegal characters");
        }
        if (cursor.getLeft() == null) {
            DirectoryNode node = new DirectoryNode(name, false, cursor);
            cursor.setLeft(node);
        } else if (cursor.getMiddle() == null) {
            DirectoryNode node = new DirectoryNode(name, false, cursor);
            cursor.setMiddle(node);
        } else if (cursor.getRight() == null) {
            DirectoryNode node = new DirectoryNode(name, false, cursor);
            cursor.setRight(node);
        } else {
            throw new FullDirectoryException("The Parent already has 3 children");
        }
    }

    /**
     *makefile Method is used to created a file as a child rather than a direcotry
     * This method is invoked when touch is entered by the user. This method
     * will set the isFile variable in the DirectoryNode to true when created 
     * a node. Again it check weather the parent already has three children, and
     * insert it if any spot was null. 
     * @param name
     *      The String name of the file node to be created. 
     * @throws IllegalArgumentException
     *      if the string contains any space or forward slash it throws an error
     * @throws FullDirectoryException
     *      if the parent node already contains 3 children, the exception is thrown
     */
    public void makeFile(String name) throws IllegalArgumentException,
            FullDirectoryException {
        if (name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException("The String contain illegal characters");
        }
        if (cursor.getLeft() == null) {
            DirectoryNode node = new DirectoryNode(name, true, cursor);
            cursor.setLeft(node);
        } else if (cursor.getMiddle() == null) {
            DirectoryNode node = new DirectoryNode(name, true, cursor);
            cursor.setMiddle(node);
        } else if (cursor.getRight() == null) {
            DirectoryNode node = new DirectoryNode(name, true, cursor);
            cursor.setRight(node);
        } else {
            throw new FullDirectoryException("The Parent already has 3 children");
        }
    }

    /**
     * find method is like a dummy method that calls the findNode Method. The reason
     * for this is so that the search will begin from root and also that it will 
     * print out the ancestry address everytime a node is found with that name.
     * so the duplicates will not be ignored. 
     * @param name
     *      String name is the name of the node that is being searched for. 
     */
    public void find(String name) {
        findNode(name, root, true);
        if(!found){
            System.out.println("no such file/directory exists");
        }
        found = false;
    }

    /**
     *findNode method is a recursive method to set the cursor to the location of
     * node with the name. It uses a preorder traversal to find the node. 
     * @param name
     *      String name of the node that needs to be found
     * @param node
     *      DirectoryNode node is the node at which the search should begin
     * @param print
     *      Print is a boolean variable. if its true then it will print the 
     *      ancestry address. so it will simplify the find extra credit assignment. 
     */
    public void findNode(String name, DirectoryNode node, boolean print) {
        DirectoryNode cursorNode = node;
        //String nodeName = cursorNode.getName();
        //System.out.println(nodeName);
        //System.out.println(name);
        if (cursorNode.getName().equals(name)) {
            cursor = cursorNode;
            if (print) {
                System.out.println(cursor.getAncestry()+"/"+name);
                found = true;
            }
        } else {
            if (cursorNode.getLeft() != null) {
                cursorNode = cursorNode.getLeft();
                findNode(name, cursorNode, print);
            }
            cursorNode = node;
            if (cursorNode.getMiddle() != null) {
                cursorNode = cursorNode.getMiddle();
                findNode(name, cursorNode, print);
            }
            cursorNode = node;
            if (cursorNode.getRight() != null) {
                cursorNode = cursorNode.getRight();
                findNode(name, cursorNode, print);
            }
        }
        //findNode(name, node);
    }
    /**
     * findAncestry method is used when user types in .../.../..../... address
     * for cd or mv. This method will take the path that was entered
     * and searches each node recursively to see if the ancestry contains these 
     * strings. The method traverses the list in a preorder fashion. 
     * @param name
     *      String name contains the path address to be searched for. 
     * @param node 
     *      DirectoryNode node is the location to start the search from. 
     */
    public void findAncestry(String name, DirectoryNode node){
        String ancester = name.substring(0, name.lastIndexOf("/"));
        if(node == null){
            node = root;
        }
        DirectoryNode cursorNode = node;
        if (cursorNode.getAncestry().contains(ancester)) {
            cursor = cursorNode;
            findNode(name.substring(name.lastIndexOf("/"), name.length()-1),cursor,false);
            } 
        else {
            if (cursorNode.getLeft() != null) {
                cursorNode = cursorNode.getLeft();
                findAncestry(name, cursorNode);
            }
            cursorNode = node;
            if (cursorNode.getMiddle() != null) {
                cursorNode = cursorNode.getMiddle();
                findAncestry(name, cursorNode);
            }
            cursorNode = node;
            if (cursorNode.getRight() != null) {
                cursorNode = cursorNode.getRight();
                findAncestry(name, cursorNode);
            }
        }
    }
}
