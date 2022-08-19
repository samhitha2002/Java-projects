public class Digit {
    
    public static void main (String[] args) {
        long number = Long.parseLong(args[0]); 
        int sum = 0;
        int sum1 = 0;
        int count = 0;

        while (number>0) {
            if (count%2 == 0) {
                sum += (number % 10);
            } else {
                sum1 += (number % 10); 
            }
            count++;
            number /= 10;
        }     
        
        sum = sum%10;
        sum1 = (sum1%10) * 3;
        int answer= sum + sum1;
        answer = answer%10;
        System.out.println(answer);
    }
}
