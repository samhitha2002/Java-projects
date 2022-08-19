/*************************************************************************
 *  Compilation:  javac RURottenTomatoes.java
 *  Execution:    java RURottenTomatoes
 *
 *  @author: Samhitha Padmanabhini, sp1949@scarletmail.rutgers.edu, sp1949
 *
 * RURottenTomatoes creates a 2 dimensional array of movie ratings 
 * from the command line arguments and displays the index of the movie 
 * that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) {

		int row = Integer.parseInt(args[0]);
		int col = Integer.parseInt(args[1]);
		int[][] array = new int[row][col];
		int n = 2; 
		int sum = 0;
		int largestsum = 0;
		int index = 0;

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++){
				array[r][c] = Integer.parseInt(args[n]);
				n++;
			}
		}
		
		for (int i = 0; i < col; i++){
			for (int j = 0; j < row; j++){
				sum += array[j][i];
			}
			if (sum > largestsum){
				largestsum = sum;
				index = i;
			}
			sum = 0;
		}
		System.out.println(index);
	}
}