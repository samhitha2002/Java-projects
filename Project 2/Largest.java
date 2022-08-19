public class Largest {
    public static void main(String[] args){
        int n1 = Integer.parseInt(args[0]);
        
        for (int i = 0; i < 5; i++) {
            if (n1 < Integer.parseInt(args[i])) {
              n1 = Integer.parseInt(args[i]);
            }
        }
        System.out.println(n1);
    }
}
