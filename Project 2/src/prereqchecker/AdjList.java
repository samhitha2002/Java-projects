package prereqchecker;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE
        HashMap<String, ArrayList<String>> courseID = createrAdj (args[0]);
        createOutput(courseID, args[1]);
    }

    public static HashMap<String, ArrayList<String>> createrAdj ( String inptutFileS ){
        HashMap<String, ArrayList<String>> coursesName = new HashMap<>();
        StdIn.setFile(inptutFileS);
        int a = StdIn.readInt();
        String aLines = StdIn.readLine();
        for(int i = 0; i < a; i++){
            String nameOfClass = StdIn.readLine();
            ArrayList <String> empty = new ArrayList<>();
            coursesName.put(nameOfClass, empty);
        }
        int b = StdIn.readInt();
        String bLines = StdIn.readLine();
        for(int j = 0; j < b; j++){
            String nameOfClass = StdIn.readLine();
            String [] values = nameOfClass.split(" ");
            ArrayList <String> listS = coursesName.get(values[0]);
            listS.add(values[1]);
            coursesName.put(values[0], listS);
        }
        return coursesName;
    }

    public static void createOutput(HashMap<String,ArrayList <String>> outputValues, String outputFileS){
        StdOut.setFile(outputFileS);
        for(String key : outputValues.keySet()){
            StdOut.print(key + " ");
            for(int i = 0; i < outputValues.get(key).size(); i++){
                StdOut.print(outputValues.get(key).get(i) + " ");
            }
            StdOut.println();
        }
    }
}
