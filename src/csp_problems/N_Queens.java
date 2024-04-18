package csp_problems;

import java.util.List;
import java.util.Map;

public class N_Queens implements CSPProblem<String,Integer>{
    @Override
    public Map<String, Variable<String, Integer>> getAllVariables() {
        return null;
    }

    @Override
    public List<String> getNeighborsOf(String vName) {
        return null;
    }

    @Override
    public List<String> getAssigned() {
        return null;
    }
}
