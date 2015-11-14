package midterm2_q10_test;
public class BTNode{
    private int data;
    private BTNode left;
    private BTNode right;
    public BTNode getLeft(){
        return left;
    }
    public BTNode getRight(){
        return right;
    }
    public void inOrderPrint(){
        if(left!=null)
            left.inOrderPrint();
        System.out.println(data);
        if(right!=null){
            right.inOrderPrint();
        }
    }
    public void setData(int data){
        this.data = data;
    }
    public void setLeft(BTNode newLeft){
        left = newLeft;
    }
    public void setRight(BTNode newRight){
        right = newRight;
    }
    
    public int getData(){
        return data;
    }
    public int setInorder(int value){
        if(left!= null){
            data = left.setInorder(value)+1;
            value = data;
        }
        if(right!=null){
            data = right.setInorder(value)+1;
            value =data;
        }
        if (left == null && right == null) {
            data = value;
        }
        return data;
    }
}
