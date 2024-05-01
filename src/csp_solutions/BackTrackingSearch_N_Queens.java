package csp_solutions;

import core_algorithms.BacktrackingSearch;
import csp_problems.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BackTrackingSearch_N_Queens extends BacktrackingSearch<String,Boolean>{

    private CSPProblem problem;

    public BackTrackingSearch_N_Queens(N_Queens problem){
        super(problem);
        this.problem = problem;
    }

    /**
     * To revise an arc: for each value in tail's domain, there must be a value in head's domain that's different
     *                   if not, delete the value from the tail's domain
     * @param head head of the arc to be revised
     * @param tail tail of the arc to be revised
     * @return true if the tail has been revised (lost some values), false otherwise
     */
    public boolean revise(String head, String tail) {
        Boolean removed = false;
        if (getAllVariables().get(head).domain().size() == 1){
            if (getAllVariables().get(tail).domain().contains(getAllVariables().get(head).domain().get(0))) {
                if (getAllVariables().get(tail).domain().size() > 1){
                    getAllVariables().get(tail).domain().remove(getAllVariables().get(head).domain().get(0));
                    removed = true;
                }
            }
        }
        return removed;
    }

    /**
     * Implementing the minimum-remaining-values(MRV) ordering heuristic.
     * @return the variable with the smallest domain among all the unassigned variables;
     *         null if all variables have been assigned
     */
    public String selectUnassigned() {
        for (CSPProblem.Variable x: getAllVariables().values()){
            if (x.domain().size() == 1 && x.domain().contains(true)){
                blockNeighbors(x);
            }
        }
        List<String> vars = new ArrayList<>();
        for (String variable : getAllVariables().keySet()) {
            if (!assigned(variable)) {
                vars.add(variable);
            }
        }
        Random random = new Random();
        if (vars.size() > 1){
            return vars.get(random.nextInt(vars.size()-1));
        } else if (vars.size() == 1) {
            return vars.get(0);
        } else {
            return null;
        }
    }

    private void blockNeighbors(CSPProblem.Variable x) {
        System.out.println(problem.getNeighborsOf(x.name()));
        for (Object y: problem.getNeighborsOf(x.name())){
            if (getAllVariables().get(y).domain().size() == 2){
                getAllVariables().get(y).domain().remove(true);
            } else if (getAllVariables().get(y).domain().contains(true)){
                getAllVariables().get(y).domain().remove(true);
                getAllVariables().get(y).domain().add(false);
            }
        }
    }

    /**
     * @param args (no command-line argument is needed to run this program)
     */
    public static void main(String[] args) {
        N_Queens problem = new N_Queens(9);
        BackTrackingSearch_N_Queens agent = new BackTrackingSearch_N_Queens(problem);
        problem.printPuzzle(problem.getAllVariables());
        if(agent.initAC3() && agent.search()){
            System.out.println("Solution found:");
            problem.printPuzzle(agent.getAllVariables());
        }else{
            System.out.println("Unable to find a solution.");
            problem.printPuzzle(problem.getAllVariables());
        }
    }
}
