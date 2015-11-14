/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author radra_000
 */
public class DirectoryTree {
    DirectoryNode root = new DirectoryNode("root", false, null);
    DirectoryNode cursor;
    private int level = 0;
    public DirectoryTree(){
        cursor = root;
    }
    public void resetCursor(){
        cursor = root;
    }
    public void changeDirectory(String name)throws NotADirectoryException{
        findNode(name, root);
        if(cursor == null||cursor.isIsFile()){
            throw new NotADirectoryException("No such Directory exists"
                    + " with a name: "+name);
        }
    }
    public String presentWorkingDirectory(){
        if (cursor == root) {
            return "root";
        } else {
            return (cursor.getAncestry() + "/" + cursor.getName());
        }
    }
    public String listDirectory(){
        String left = (cursor.getLeft()!=null)?cursor.getLeft().getName():"";
        String middle = (cursor.getMiddle()!=null)?cursor.getMiddle().getName():"";
        String right = (cursor.getRight()!=null)?cursor.getRight().getName():"";
        return (left+" "+middle+" "+right);
    }
    public void printDirectoryTree(){
        DirectoryNode temp = cursor;
        //int level = 0;
        String notation = (cursor.isIsFile())?"-":"|-";
        String spacing = "";
        for(int i=0;i<level;i++){
            spacing = spacing.concat("\t");
        }
        if(cursor.getLeft()!=null){
            cursor = cursor.getLeft();
            System.out.println(spacing+notation+cursor.getName());
            level++;
            printDirectoryTree();
        }
        cursor = temp;
        if(cursor.getMiddle()!=null){
            cursor = cursor.getMiddle();
            System.out.println(spacing+notation+cursor.getName());
            level++;
            printDirectoryTree();
        }
        cursor = temp;
        if(cursor.getRight()!=null){
            cursor = cursor.getRight();
            System.out.println(spacing+notation+cursor.getName());
            level++;
            printDirectoryTree();
        }
        cursor = temp;
    }
    public void makeDirectory(String name) throws IllegalArgumentException, 
            FullDirectoryException{
        if(name.contains(" ")||name.contains("/")){
            throw new IllegalArgumentException("The String contain illegal characters");
        }
        if(cursor.getLeft()==null){
            DirectoryNode node  = new DirectoryNode(name, false, cursor);
            cursor.setLeft(node);
        }
        else if(cursor.getMiddle()==null){
            DirectoryNode node  = new DirectoryNode(name, false, cursor);
            cursor.setMiddle(node);
        }
        else if(cursor.getRight()==null){
            DirectoryNode node  = new DirectoryNode(name, false, cursor);
            cursor.setRight(node);
        }
        else{
            throw new FullDirectoryException("The Parent already has 3 children");
        }
    }
    public void makeFile(String name) throws IllegalArgumentException, 
            FullDirectoryException{
        if(name.contains(" ")||name.contains("/")){
            throw new IllegalArgumentException("The String contain illegal characters");
        }
        if(cursor.getLeft()==null){
            DirectoryNode node  = new DirectoryNode(name, true, cursor);
            cursor.setLeft(node);
        }
        else if(cursor.getMiddle()==null){
            DirectoryNode node  = new DirectoryNode(name, true, cursor);
            cursor.setMiddle(node);
        }
        else if(cursor.getRight()==null){
            DirectoryNode node  = new DirectoryNode(name, true, cursor);
            cursor.setRight(node);
        }
        else{
            throw new FullDirectoryException("The Parent already has 3 children");
        }
    }
    public void findNode(String name, DirectoryNode node){
        DirectoryNode cursorNode = node;
        
        if(name.equals((String)cursorNode.getName())){
            cursor = cursorNode;
            return;
        }
        else{
            if(cursorNode.getLeft()!=null){
                cursorNode = cursorNode.getLeft();
                findNode(name, cursorNode);
            }
            cursorNode = node;
            if(cursorNode.getMiddle()!=null){
                cursorNode = cursorNode.getMiddle();
                findNode(name, cursorNode);
            }
            cursorNode = node;
            if(cursorNode.getRight()!=null){
                cursorNode= cursorNode.getRight();
                findNode(name, cursorNode);
            }
        }  
        //findNode(name, node);
    }
}
