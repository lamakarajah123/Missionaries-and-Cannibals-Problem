package application;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DFS {

    public State exec(State inState) {
        return iterativeDFS(inState);
    }

    private State iterativeDFS(State inState) {
        Stack<State> stack = new Stack<>();
        Set<String> visited = new HashSet<>(); // Use a set of strings to store visited states

        stack.push(inState);

        while (!stack.isEmpty()) {
            State curState = stack.pop();
            String stateString = curState.toString(); // Convert state to string

            if (!visited.contains(stateString)) {
                visited.add(stateString); // Mark current state as visited

                if (curState.isGoal()) {
                    return curState;
                }

                List<State> suc = curState.Gsuc();
                for (State child : suc) {
                    stack.push(child);
                }
            }
        }

        return null;
    }
}
