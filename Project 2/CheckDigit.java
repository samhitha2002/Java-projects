/*************************************************************************
 *  Compilation:  javac CheckDigit.java
 *  Execution:    java CheckDigit 020131452
 *
 *  @author: Samhitha Padmanabhini
 *
 *  Takes a 12 or 13 digit integer as a command line argument, then computes
 *  and displays the check digit
 *
 *  java CheckDigit 048231312622
 *  0
 *
 *  java CheckDigit 9780470458310
 *  0
 * 
 *  java CheckDigit 9780470454310
 *  8
 * 
 *  Print only the check digit character, nothing else.
 *
 *************************************************************************/

public class CheckDigit {

    public static void main (String[] args) {
        long number = Long.parseLong(args[0]); 
        int sum = 0;
        int sum1 = 0; 

        for (int i=0; 0 < number; i++){
            if (i%2 == 0) {
                sum += (number % 10);
            } else {
                sum1 += (number % 10); 
            }
            number /= 10;
        }  
        
        sum = sum%10;
        sum1 = (sum1%10) * 3;
        sum1 = (sum1%10);
        int answer= sum + sum1;
        answer = answer%10;
        System.out.println(answer);
    }
}
