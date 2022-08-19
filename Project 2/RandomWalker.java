/*************************************************************************
 *  Compilation:  javac RandomWalker.java
 *  Execution:    java RandomWalker 10
 *
 *  @author: Samhitha Padmanabhini
 *
 * The program RandomWalker that takes an int command-line argument n
 * and simulates the motion of a random walk for n steps. Print the
 * location at each step (including the starting point), treating the
 * starting point as the origin (0, 0). Also, print the square of the
 * final Euclidean distance from the origin.
 *
 *  % java RandomWalker 10
 * (0,0)
 * (-1,0)
 * (-1,-1)
 * (-1,-2)
 * (-1,-3)
 * (-1,-4)
 * (-1,-5)
 * (0,-5)
 * (-1,-5)
 * (-2,-5)
 * (-2,-4)
 * Squared distance = 20.0
 *
 *************************************************************************/

public class RandomWalker {

    public static void main(String[] args){
        int input = Integer.parseInt(args[0]);
        int x = 0;
        int y = 0;
        Double Distance = 0.0; 
        System.out.println("(" + x + "," + y +")");
                    
            for (int i = 0; i < input; i++){
               double move = Math.random() * (4);
                if (move <= 1){
                    x += 1;
                }else{
                    if ( move <= 2){
                        x -= 1;
                    }else{
                        if (move <= 3){
                            y += 1;
                        }else{
                            if (move < 4){
                                y -= 1;
                            }
                        }
                    }
                }
                System.out.println("(" + x + "," +y +")");
            }
                Double distance = (double) ( x * x ) + ( y * y );
                System.out.println("Squared distance" + " = " + distance);
    }
}
