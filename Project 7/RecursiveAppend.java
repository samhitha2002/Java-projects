/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author: Samhitha Padmanabhini, sp1949@scarletmail.rutgers.edu, sp1949
 *
 *************************************************************************/

public class RecursiveAppend {

    // Returns the original string appended to the original string n times 
    public static String appendNTimes (String original, int n) {
        if(n==0){
            return original;
        }
        return original + appendNTimes(original, n-1);
    }

    public static void main (String[] args) {
        String original = args[0];
        int n = Integer.parseInt(args[1]);
        System.out.println(appendNTimes(original, n));
    }
}
