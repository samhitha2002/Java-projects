/*************************************************************************
 *  Compilation:  javac FindDuplicate.java
 *  Execution:    java FindDuplicate
 *
 *  @author: Samhitha Padmanabhini
 *
 * FindDuplicate that reads n integer arguments from the command line 
 * into an integer array of length n, where each value is between is 1 and n, 
 * and displays true if there are any duplicate values, false otherwise.
 *
 *  % java FindDuplicate 10 8 5 4 1 3 6 7 9
 *  false
 *
 *  % java FindDuplicate 4 5 2 1 2
 *  true
 *************************************************************************/

public class FindDuplicate {

    public static void main(String[] args) {

	int input = args.length;
	int[] array = new int[input];
	Boolean duplicate = false;
	for (int i = 0; i < array.length; i++){
		int n = Integer.parseInt(args[i]);
		array[i] = n;
	}
	for (int i = 0; i < array.length; i++){
		for (int j = 0; j < array.length; j++){
			if (i != j){
				if (array[j] == array[i]){
					duplicate = true; 
				}
			}
		}
	}
	System.out.println(duplicate);	
	}
}

