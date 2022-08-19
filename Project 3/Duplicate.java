public class Duplicate {
    public static void main(String[] args) {
        Boolean duplicate = false;
        for (int i = 0; i < args.length; i++){
            for (int j = 0; j < args.length; j++){
                if (i != j){
                    if (Integer.parseInt(args[j]) == Integer.parseInt(args[i])){
                        duplicate = true; 
                    }
                }
            }
        }
        System.out.println(duplicate);
    }    
}
