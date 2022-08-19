/*************************************************************************
 *  Compilation:  javac LargestOfFive.java
 *  Execution:    java LargestOfFive 35 10 32 1 8
 *
 *  @author: Samhitha Padmanabhini
 *
 *  Takes five distinct integers as command-line arguments and prints the 
 *  largest value
 *
 *
 *  % java LargestOfFive 35 10 32 1 8
 *  35
 *
 *  Assume the inputs are 5 distinct integers.
 *  Print only the largest value, nothing else.
 *
 *************************************************************************/

public class LargestOfFive {

    public static void main(String[] args){
        int n1 = Integer.parseInt(args[0]);
        int i = 0;

        while (i < 5) {
            if (n1 < Integer.parseInt(args[i])) {
                n1 = Integer.parseInt(args[i]);
            }
            i++;
        }
        System.out.println(n1);

    }
}
