package application;

class Node {
	State state;
	Node parent;

	public Node(State state, Node parent) {
		this.state = state;
		this.parent = parent;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}
