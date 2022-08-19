package prereqchecker;
import java.util.*;

public class Graph{
    HashMap<String,ArrayList<String>> courseList = new HashMap<String, ArrayList<String>>();
    
    public Graph (String inputFileS){
        
        StdIn.setFile(inputFileS);
        int a = StdIn.readInt();
        String samhitha = StdIn.readLine();
        for(int i = 0; i < a; i++){
            String className = StdIn.readLine();
            ArrayList < String> value = new ArrayList<>();
            courseList.put(className, value);
        }
        
        int b = StdIn.readInt();
        String kayla = StdIn.readLine();
        for(int j = 0; j < b; j++){
            String className = StdIn.readLine();
            String[] storage = className.split(" ");
            ArrayList <String> listOfValues = courseList.get(storage[0]);
            listOfValues.add(storage[1]);
            courseList.put(storage[0],listOfValues);
        }
        return;
    }

    public boolean Cycle(String vist, String target, ArrayList<String> visited, ArrayList<String> visited2, int var){
        visited.add(vist);
        if(vist.equals(target)){  
            return true;
        }
        ArrayList<String> finalVal = courseList.get(vist);
        for(int i = 0; i < finalVal.size(); i++){
            String preReqClass = finalVal.get(i);
            if(Cycle(preReqClass, target, visited, visited2, var)){
                return true;
            }
        }
       
        if(!visited.contains(target)){
            if(var == 1){
                return true;
            }
            Cycle(target, vist, visited2, visited, var + 1);
            if(!visited2.contains(vist) && !visited.contains(target)){
                return true;
            }else{
                return false;
            }
        } 
        return false;
    }
}
