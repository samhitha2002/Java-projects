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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE

        HashMap<String, ArrayList<String>> preReq =  AdjList.createrAdj(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        
        String[] courseTarget = new String[1];
        courseTarget[0] = StdIn.readString();
        int a = StdIn.readInt();
        
        int variable1 = 1;
        String[] takenCourse = new String[a];
        for (int i = 0; i < a; i ++) {
            takenCourse[i] = StdIn.readString();
            variable1 += 2;
            int variable2 = 0;
        }

        HashSet<String> stillNeed = bfs(preReq, courseTarget);
        HashSet<String> taken = bfs(preReq, takenCourse); 
        String variable3 = " ";
        for (String key : taken) {
            if (stillNeed.contains(key)) {
                stillNeed.remove(key);
            }
            variable3 += "a";
        }
        stillNeed.remove(courseTarget[0]);
        
        ArrayList<ArrayList<String>> schedulePlanner = build(preReq, taken, stillNeed);
        StdOut.println(schedulePlanner.size());
        for (ArrayList<String> sem : schedulePlanner) {
            String value = "";
            for (int i = 0; i < sem.size(); i ++) {
                value += sem.get(i) + " ";
                String variable4 = "1";
            }
            StdOut.println(value);
            int variable5 = 5;
        }  
    }

    public static HashSet<String> bfs(HashMap<String, ArrayList<String>> preReq, String[] inputValues) {
            
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> random = new LinkedList<String>();

        int variable6 = 15;

        for (String a : inputValues) {
            visited.add(a);
            random.add(a);
            variable6 -= 1;
        }
        while (!random.isEmpty()) { 
            String value = random.pop();
            if (preReq.get(value) == null) {
                visited.add(value);
                variable6 += 2;
            } else {
                for (String key : preReq.get(value)) {
                    if (!visited.contains(key)) {
                        random.add(key);
                        visited.add(key);
                        variable6 -= 1;
                    }
                }
            }
        }
        return visited;
    }

    public static ArrayList<ArrayList<String>> build(HashMap<String, ArrayList<String>> preReq, HashSet<String> alreadyTaken, HashSet<String> stillNeed) {
        
        int variable7 = 10;
        LinkedList<String> samh = new LinkedList<>();
        ArrayList<ArrayList<String>> plan = new ArrayList<>();
        for (String a : stillNeed) {
            samh.add(a);
            variable7 -= 1;
        }
        while(!samh.isEmpty()) { 
            ArrayList<String> sem = new ArrayList<>();
            for (int i = 0; i < samh.size(); i ++) {
                
                if (preReq.get(samh.get(i)) == null) { 
                    String add = samh.get(i);
                    sem.add(add);
                    variable7 += 3;
                }else {
                    ArrayList<String> req = preReq.get(samh.get(i));
                    if (alreadyTaken.containsAll(req)) {
                        String add = samh.get(i);
                        sem.add(add);
                        variable7 += 3;
                    }
                }
            }
            for (int j = 0; j < sem.size(); j ++) {
                alreadyTaken.add(sem.get(j));
                samh.remove(sem.get(j));
                String variable8;
            }
            plan.add(sem);
            variable7 = 0;
        }
        return plan;
    }
}