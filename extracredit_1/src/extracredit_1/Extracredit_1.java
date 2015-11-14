///**
//*Rajith Radhakrishnan
//* 109061463
//* rajith.radhakrishnan@stonybrook.edu
//* cse 214 R06
//* Extra credit
// * TA - Frank Migliorino 
// * GA - Yu Wang
// *
//*The three numbers are 15, 30 , 55 with a maximum loss of 14 dollars
// */
package extracredit_1;
//
///**
// *
// * @author radra_000
// */
//public class Extracredit_1 {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        int a = 2;
//        System.out.print(a++ + ++a +(++a*3));
//   /*    
//        int x = 15;
//        int y = 30;
//        int z = 55;
//        int[] test = new int[7];
//        int ans = 0;
//        int highest = 0;
//        int totalLoss =0;
//        for(int i=1;i<=100;i++){
//            test[0] = x-i;
//            test[1] = (x+y) - i;
//            test[2] = (x+y+z) - i;
//            test[3] = (x+z) - i;
//            test[4] = (y+z) - i;
//            test[5] = y-i;
//            test[6] = z-i;
//            
//            for(int j = 0; j<7; j++){
//                if(test[j]<0){
//                    test[j] = 200;
//                }
//                else{
//                    ans = test[j];
//                }
//            }
//            for(int j = 0; j<7; j++){
//                if(test[j]<ans){
//                    ans = test[j];
//                }
//             }
//            totalLoss += ans;
//            if(ans>highest){
//                        highest = ans;
//                    }
//            System.out.println(i+"\t"+ans);
//        }
//        System.out.println("Higest = "+ highest);
//        System.out.println("Total Loss = "+totalLoss);
//    }
//               /*
//        int max =100;
//        int ans =0;
//        int highest= 0;
//        int[] test = new int[7];
//        int[] large = new int[400];
//        int min = 1;
//        int m = 0;
//        for(int i =1; i<=100;i++){
//        for(int x =min; x<= max; x++){
//            for(int y=min; y<=max;y++){
//                for(int z = min; z<=max;z++){
//                    test[0] = x - i;
//                    test[1] = (x + y) - i;
//                    test[2] = (x + y + z) - i;
//                    test[3] = (x + z) - i;
//                    test[4] = (y + z) - i;
//                    test[5] = y - i;
//                    test[6] = z - i;
//
//                    for (int j = 0; j < 7; j++) {
//                        if (test[j] < 0) {
//                            test[j] = 200;
//                        } else {
//                            ans = test[j];
//                        }
//                    }
//                    for (int j = 0; j < 7; j++) {
//                        if (test[j] < ans) {
//                            ans = test[j];
//                        }
//                    }
//                    if (ans > highest) {
//                        highest = ans;
//                    }
//                    System.out.println(x+"\t"+y+"\t"+z+"\t" + ans);
//                }
//                
//                //System.out.println("Higest = " + highest);
//            }
//        }
//        large[m++] = highest;
//        }
//        int answer = large[0];
//            for(int a=0;a<large.length;a++){
//                if(large[a]<answer){
//                    answer = large[a];
//                }
//            }
//            System.out.println("The lowest value is :"+answer);
//    }
//*/
//    
//}
//}

public class Extracredit_1 {
    public static void main(String args[]){
	String word = "crispix";
        while(word.length()!= 0) {
		int count = 1;
		for(int j = 1; j< word.length(); j++){
                    char x = word.charAt(0);
                    char y = word.charAt(j);
			if(word.charAt(0) == word.charAt(j)){
				count++;
			}
		}
		System.out.println(word.charAt(0)+":"+count);
                String temp = "" +word.charAt(0);
                //System.out.println(word);
                word.replaceAll(temp, "");
                //System.out.println(word);
	}
    }
}