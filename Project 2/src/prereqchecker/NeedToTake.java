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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE
        HashMap<String, ArrayList<String>> preReq =  AdjList.createrAdj(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);

        HashMap<String,ArrayList<String>> needToTake = new HashMap<String,ArrayList<String>>();
        String input = args[1];
        StdIn.setFile(input);
        String targetCourse = StdIn.readString();
        int a = StdIn.readInt();
        for(int i = 0; i < a; i++){
            String courseName = StdIn.readString();
            ArrayList <String> takenCourse = new ArrayList<>();
            needToTake.put(courseName, takenCourse);
            for(String d : preReq.get(courseName)){
                dfs2(preReq, courseName, needToTake, takenCourse);
            }
        }
        String outputFile = args[2];
        StdOut.setFile(outputFile);
        HashMap<String,Boolean> visited = new HashMap<String,Boolean>();

        dfs(visited, preReq, targetCourse, needToTake);
        
        for(String req : preReq.keySet()){
            if( visited.containsKey(req)){
                StdOut.println(req);
            }
        }
    }

    public static void dfs(HashMap<String,Boolean> visited, HashMap<String, ArrayList<String>> preReq, String key, HashMap<String,ArrayList<String>> needToTake){
        for(String a : preReq.get(key)){
            if(!visited.containsKey(a) && !needToTake.containsKey(a)){
                visited.put(a, true);
                dfs(visited, preReq, a, needToTake);
            }
        }
    }

    public static void dfs2(HashMap<String, ArrayList<String>> preReq, String name, HashMap<String,ArrayList<String>> needToTake, ArrayList <String> takenCourse){
        for(String a : preReq.get(name)){
            if(!needToTake.containsKey(a)){
                needToTake.put(a, takenCourse);
                dfs2(preReq, a, needToTake, takenCourse);
            }
        }
    }
}
