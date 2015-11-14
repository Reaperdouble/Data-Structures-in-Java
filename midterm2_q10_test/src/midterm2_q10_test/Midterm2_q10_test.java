/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midterm2_q10_test;

/**
 *
 * @author radra_000
 */
public class Midterm2_q10_test {

    public static void main(String[] args) {
       BTNode root = new BTNode();
       root.setLeft(new BTNode());
       root.getLeft().setLeft(new BTNode());
       root.getLeft().getLeft().setLeft(new BTNode());
       root.getLeft().setRight(new BTNode());
       root.getLeft().getRight().setLeft(new BTNode());
       root.setRight(new BTNode());
       root.getRight().setLeft(new BTNode());
       root.getRight().getLeft().setRight(new BTNode());
       root.setInorder(1);
       root.inOrderPrint();
    }
    
}
