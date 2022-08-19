package prereqchecker;
import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE
        HashMap<String, ArrayList<String>> preReq =  AdjList.createrAdj(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);

        HashMap<String,ArrayList<String>> courseList = new HashMap<String,ArrayList<String>>();
        String inputs = args[1];
        StdIn.setFile(inputs);
        int a = StdIn.readInt();
        
        for(int i = 0; i < a; i++){
            String courseName = StdIn.readString();
            ArrayList <String> value = new ArrayList<>();
            courseList.put(courseName, value);
        }

        HashMap<String,Boolean> visited = new HashMap<String,Boolean>();
        String outputFile = args[2];
        StdOut.setFile(outputFile);
        for(String key : courseList.keySet()){
            dfs(visited, preReq, key);
        }

        for(String req : preReq.keySet()){
            boolean allVisit = true;

            for(String c : preReq.get(req)){
                if(!visited.containsKey(c)){
                    allVisit = false;
                }
            } 

            if(allVisit && !visited.containsKey(req)){
                StdOut.println(req);
            }
        }
    }

    public static void dfs(HashMap<String,Boolean> visited, HashMap<String, ArrayList<String>> preReq, String key){
        visited.put(key, true);

        for(String a : preReq.get(key)){
            if(!visited.containsKey(a)){
                dfs(visited, preReq, a);
            }
        }
    }
}