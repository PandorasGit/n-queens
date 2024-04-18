package csp_problems;

import java.util.*;

public class N_Queens implements CSPProblem<Integer,Boolean>{

    private final Map<Integer, Variable<Integer,Boolean>> allVariables;

    private final Map<Integer, Set<Integer>> neighbors = new HashMap<>();
    public N_Queens(Integer board_size) {
        allVariables = getAllVariables();

        for (int i=0; i<board_size;i++){
            Set<Integer> rowNeighbors = new HashSet<>();
            for (int j=0; j<board_size;j++){
                
            }
        }
    }

    @Override
    public Map<Integer, Variable<Integer, Boolean>> getAllVariables() {
        return null;
    }

    @Override
    public List<Integer> getNeighborsOf(Integer vName) {
        return null;
    }



    @Override
    public List<Integer> getAssigned() {
        return null;
    }
}
