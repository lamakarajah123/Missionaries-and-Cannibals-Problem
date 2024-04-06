package application;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public State exec(State inState) {
        if (inState.isGoal()) {
            return inState;
        }

        Queue<Node> front = new LinkedList<>(); // FIFO queue
        HashSet<State> visited = new HashSet<>(); // Used to track visited states

        front.add(new Node(inState, null));

        while (!front.isEmpty()) {
            Node curNode = front.poll();
            State curState = curNode.state;

            if (!visited.contains(curState)) {
                visited.add(curState); // Mark current state as visited

                if (curState.isGoal()) {
                    return curState;
                }

                List<State> suc = curState.Gsuc();
                for (State child : suc) {
                    if (!visited.contains(child)) {
                        front.add(new Node(child, curNode));
                    }
                }
            }
        }
        return null; 
    }
}