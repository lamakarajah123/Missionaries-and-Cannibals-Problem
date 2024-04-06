package application;

import java.util.ArrayList;
import java.util.List;

enum Position {
	LEFT, RIGHT
}

public class State {

	private int C_Left;
	private int M_Left;
	private int C_Right;
	private int M_Right;
	private Position boat;
	private State pState;

	public State(int C_Left, int M_Left, Position boat, int C_Right, int M_Right) {
		this.C_Left = C_Left;
		this.M_Left = M_Left;
		this.boat = boat;
		this.C_Right = C_Right;
		this.M_Right = M_Right;
	}
	
	// Method to check if the current state is the goal state
	public boolean isGoal() {
		return C_Left == 0 && M_Left == 0;
	}
    // Method to check if the current state is valid
	public boolean isValid() {
		if (M_Left >= 0 && M_Right >= 0 && C_Left >= 0 && C_Right >= 0
				&& (M_Left == 0 || M_Left >= C_Left)
				&& (M_Right == 0 || M_Right >= C_Right)) {
			return true;
		}
		return false;
	}

	public List<State> Gsuc() {
	    List<State> suc = new ArrayList<>();
	    if (boat == Position.LEFT) {
	        testAndAdd(suc, new State(C_Left, M_Left - 2, Position.RIGHT, C_Right, M_Right + 2)); // 2 M  left to right.
	       
	        testAndAdd(suc, new State(C_Left - 2, M_Left, Position.RIGHT, C_Right + 2, M_Right)); // 2 C  left to right.
	       
	        testAndAdd(suc, new State(C_Left - 1, M_Left - 1, Position.RIGHT, C_Right + 1, M_Right + 1)); // 1 M and 1 C  left to right.
	       
	        testAndAdd(suc, new State(C_Left, M_Left - 1, Position.RIGHT, C_Right, M_Right + 1)); // 1 M left to right.
	        
	        testAndAdd(suc, new State(C_Left - 1, M_Left, Position.RIGHT, C_Right + 1, M_Right)); // 1 C left to right.
	    } else {
	       
	    	testAndAdd(suc, new State(C_Left, M_Left + 2, Position.LEFT, C_Right, M_Right - 2)); // 2 M  right to left.
	       
	    	testAndAdd(suc, new State(C_Left + 2, M_Left, Position.LEFT, C_Right - 2, M_Right)); // 2 C  right to left.
	        
	    	testAndAdd(suc, new State(C_Left + 1, M_Left + 1, Position.LEFT,  C_Right - 1, M_Right - 1)); // 1 M and 1 C  right to left.
	       
	    	testAndAdd(suc, new State(C_Left, M_Left + 1, Position.LEFT, C_Right, M_Right - 1)); // 1 M right to left.
	       
	    	testAndAdd(suc, new State(C_Left + 1, M_Left, Position.LEFT, C_Right - 1, M_Right)); // 1 C right to left.
	    }
	    return suc;
	}

	private void testAndAdd(List<State> suc, State newState) {
	    if (newState.isValid()) {
	        newState.setpState(this);
	        suc.add(newState);
	    }
	}
	

    // Getter and setter methods
	public int getC_Left() {
		return C_Left;
	}

	public void setC_Left(int C_Left) {
		this.C_Left = C_Left;
	}

	public int getM_Left() {
		return M_Left;
	}

	public void setM_Left(int M_Left) {
		this.M_Left = M_Left;
	}

	public int getC_Right() {
		return C_Right;
	}

	public void setC_Right(int C_Right) {
		this.C_Right = C_Right;
	}

	public int getM_Right() {
		return M_Right;
	}

	public void setM_Right(int M_Right) {
		this.M_Right = M_Right;
	}
	// Method to set the boat position to the left or right
	public void goToLeft() {
		boat = Position.LEFT;
	}
	

	public void goToRight() {
		boat = Position.RIGHT;
	}
    // Methods to check if the boat is on the left or right
	public boolean isOnLeft() {
		return boat == Position.LEFT;
	}

	public boolean isOnRight() {
		return boat == Position.RIGHT;
	}

	public State getpState() {
		return pState;
	}

	public void setpState(State pState) {
		this.pState = pState;
	}

	@Override
	public String toString() {
		if (boat == Position.LEFT) {
			return "(" + C_Left + "," + M_Left + ",L," + C_Right + "," + M_Right + ")";
		} else {
			return "(" + C_Left + "," + M_Left + ",R," + C_Right + "," + M_Right + ")";
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof State)) {
			return false;
		}
		State s = (State) obj;
		return (s.C_Left == C_Left && s.M_Left == M_Left && s.boat == boat
				&& s.C_Right == C_Right && s.M_Right == M_Right);
	}



}
