package csp_problems;

import java.util.*;

public class N_Queens implements CSPProblem<String,Boolean>{

    private final Map<String, Variable<String,Boolean>> allVariables;

    private final Map<String, Set<String>> neighbors = new HashMap<>();
    private int board_size;
    public N_Queens(int board_size) {
        this.board_size = board_size;
        allVariables = getAllVariables();
        for (int i=0; i<this.board_size;i++){
            //rowNeighborSet
            Set<String> rowNeighbors = new HashSet<>();

            for (int j=0; j<this.board_size;j++){
                String name = i + String.valueOf(j);
                rowNeighbors.add(name);
            }
            for(int j=0; j<board_size; j++){
                String name = i + String.valueOf(j);
                neighbors.put(name, new HashSet<>(rowNeighbors));
            }
        }
        for (int j=0; j<this.board_size; j++) {
            //build the column neighbor set (all variables in the same column)
            Set<String> columnNeighbors = new HashSet<>();
            for (int i=0; i<this.board_size; i++) {
                String name = i + String.valueOf(j);
                columnNeighbors.add(name);
            }
            for (int i=0; i<board_size; i++) {
                String name = i + String.valueOf(j);
                neighbors.get(name).addAll(columnNeighbors);
            }
        }
        //get diagonal(right to left, top to bottom) neighbor set (all the variables in the same diagonal)
        for (int i=0; i<board_size;i++){
            Set<String> diagonalNeighbors = new HashSet<>();
            for (int j=0; j<=i;j++){
                String name = String.valueOf(i-j) + j;
                diagonalNeighbors.add(name);
            }
            for(String name: diagonalNeighbors){
                List<String> diagCopy = new ArrayList<>(diagonalNeighbors);
                diagCopy.remove(name);
                neighbors.get(name).addAll(diagCopy);
            }
        }
        for (int i=0; i<this.board_size;i++){
            Set<String> diagonalNeighbors = new HashSet<>();
            for (int j=i; j<board_size; j++){
                String name = j + String.valueOf((board_size-1)-(j-i));
                diagonalNeighbors.add(name);
            }
            for(String name: diagonalNeighbors){
                List<String> diagCopy = new ArrayList<>(diagonalNeighbors);
                diagCopy.remove(name);
                neighbors.get(name).addAll(diagCopy);
            }
        }
        //get diagonal(left to right, top to bottom) neighbor set (all the variables in the same diagonal)
        for (int i=0; i<board_size;i++){
            Set<String> diagonalNeighbors = new HashSet<>();
            for (int j=0; j<(board_size-i);j++){
                String name = j + String.valueOf(i+j);
                diagonalNeighbors.add(name);
            }
            for(String name: diagonalNeighbors){
                List<String> diagCopy = new ArrayList<>(diagonalNeighbors);
                diagCopy.remove(name);
                neighbors.get(name).addAll(diagCopy);
            }
        }
        for (int i=1; i<this.board_size;i++){
            Set<String> diagonalNeighbors = new HashSet<>();
            for (int j=0; j<(board_size-i); j++){
                String name = String.valueOf(i+j) + j;
                diagonalNeighbors.add(name);
            }
            for(String name: diagonalNeighbors){
                List<String> diagCopy = new ArrayList<>(diagonalNeighbors);
                diagCopy.remove(name);
                neighbors.get(name).addAll(diagCopy);
            }
        }
        for(Map.Entry<String,Set<String>> e : neighbors.entrySet()){
            e.getValue().remove(e.getKey());
            //   System.out.println(e.getValue().size());
        }
    }

    public Map<String, Variable<String, Boolean>> getAllVariables() {
        Map<String,Variable<String,Boolean>> allVariables = new HashMap<>();
        List<Boolean> defaultDomain = List.of(true, false);
        for (int i=0; i<board_size; i++) {
            for (int j=0; j<board_size; j++) {
                String name = i +String.valueOf(j);
                Variable<String,Boolean> v =
                        new Variable<>(name, new LinkedList<>(defaultDomain));
                allVariables.put(name,v);
            }
        }
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

    public void printPuzzle(Map<String,Variable<String,Boolean>> allVariables) {
        System.out.println("printing puzzle:");
        for (int i=0; i<board_size; i++) {
            for (int j=0; j<board_size; j++) {
                if (allVariables.get(i+String.valueOf(j)).domain().size() > 1) {
                    System.out.print("[ ]");
                } else {
                    if (allVariables.get(i+String.valueOf(j)).domain().get(0)){
                        System.out.print("["+"Q"+"]");
                    } else {
                        System.out.print("["+"_"+"]");
                    }
                }
            }
            System.out.print("\r\n");
        }
    }
}
