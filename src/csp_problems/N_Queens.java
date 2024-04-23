package csp_problems;

import java.util.*;

public class N_Queens implements CSPProblem<String,Boolean>{

    private final Map<String, Variable<String,Boolean>> allVariables;

    private final Map<String, Set<String>> neighbors = new HashMap<>();
    private int board_size;
    public N_Queens(int board_size) {
        allVariables = getAllVariables();

        for (int i=0; i<board_size;i++){
            //rowNeighborSet
            Set<String> rowNeighbors = new HashSet<>();

            for (int j=0; j<board_size;j++){
                String name = i + String.valueOf(j);
                rowNeighbors.add(name);
            }
            for(int j=0; j<board_size; j++){
                String name = i + String.valueOf(j);
                neighbors.put(name, new HashSet<>(rowNeighbors));
            }
        }
        for (int j=0; j<board_size; j++) {
            //build the column neighbor set (all variables in the same column)
            Set<String> columnNeighbors = new HashSet<>();
            for (int i=0; i<board_size; i++) {
                String name = i + String.valueOf(j);
                columnNeighbors.add(name);
            }
            for (int i=0; i<board_size; i++) {
                String name = i + String.valueOf(j);
                neighbors.get(name).addAll(columnNeighbors);
            }
        }
        //get diagonal neighbor set (all the variables in the same diagonal)
        for (int i=0; i<board_size;i++){
            Set<String> diagonalNeighbors = new HashSet<>();
            for (int j=0; j<board_size;j++){
                int k = i+j;
                if (k < board_size) {
                    String name = j + String.valueOf(j);
                    diagonalNeighbors.add(name);
                }
            }
            for(int j=0; j<board_size; j++){
                String name = j + String.valueOf(j);
                neighbors.put(name, new HashSet<>(diagonalNeighbors));
            }
        }
        for (int i=0; i<board_size;i++){
            Set<String> diagonalNeighbors = new HashSet<>();
            for (int j=board_size; j>0; j--){
                int k = i+j;
                if (k < board_size) {
                    String name = j + String.valueOf(j);
                    diagonalNeighbors.add(name);
                }
            }
            for(int j=0; j<board_size; j++){
                String name = j + String.valueOf(j);
                neighbors.put(name, new HashSet<>(diagonalNeighbors));
            }
        }
        for(Map.Entry<String,Set<String>> e : neighbors.entrySet()){
            e.getValue().remove(e.getKey());
            //   System.out.println(e.getValue().size());
        }
    }

    public Map<String, Variable<String, Boolean>> getAllVariables() {
        return allVariables;
    }

    public List<String> getNeighborsOf(String name){
        return(new ArrayList<>(neighbors.get(name)));
    }


    public List<String> getAssigned() {
        List<String> assigned = new ArrayList<>();
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                if (allVariables.get(i + String.valueOf(j)).domain().size() == 1) {
                    assigned.add(i + String.valueOf(j));
                }
            }
        }
        return assigned;
    }
}
