package application;

class Queue {
	private Node front;
	private Node rear;

	public Node getFront() {
		return front;
	}

	public void setFront(Node front) {
		this.front = front;
	}

	public Node getRear() {
		return rear;
	}

	public void setRear(Node rear) {
		this.rear = rear;
	}

	public void enqueue(State state) {
		Node newNode = new Node(state, null);
		if (isEmpty()) {
			front = rear = newNode;
		} else {
			rear.parent = newNode;
			rear = newNode;
		}
	}

	public State dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		State state = front.state;
		front = front.parent;
		if (front == null) {
			rear = null;
		}
		return state;
	}

	public State front() {
		if (isEmpty()) {
			throw new IllegalStateException("Queue is empty");
		}
		return front.state;
	}

	public boolean isEmpty() {
		return front == null;
	}
	
}
