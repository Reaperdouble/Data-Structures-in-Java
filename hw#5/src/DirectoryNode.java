/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class DirectoryNode {
    private String name;
    private DirectoryNode left;
    private DirectoryNode right;
    private DirectoryNode middle;
    private boolean isFile;
    private String ancestry = "";
    public DirectoryNode(String name,boolean isFile, DirectoryNode parent) {
        this.setAncestry(parent);
        this.setName(name);
        this.setIsFile(isFile);
    }
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
    public String getAncestry(){
        return ancestry;
    }
    public void setAncestry(DirectoryNode parent){
        if(parent!=null){
            ancestry = (parent.getAncestry()+"/"+parent.getName());
        }
    }

    public boolean isIsFile() {
        return isFile;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DirectoryNode getLeft() {
        return left;
    }

    public void setLeft(DirectoryNode left) {
        this.left = left;
    }

    public DirectoryNode getRight() {
        return right;
    }

    public void setRight(DirectoryNode right) {
        this.right = right;
    }

    public DirectoryNode getMiddle() {
        return middle;
    }

    public void setMiddle(DirectoryNode middle) {
        this.middle = middle;
    }
    
}
