/*****************************
 *  Name: Yong Shan Xian 
 *  Matric Number: A0132763H
 *  Plab account: plab0711
******************************/

import java.util.*;

class QueueNode extends Stack<Integer>
{
	// well instead of storing another stack in our node, we can make the node our stack too (:
	private int stackIndex;

	public QueueNode(int index)
	{
		stackIndex = index;
	}

	public int getIndex()
	{
		return stackIndex;
	}
}

class QueueStack
{
	// our beloved big queue must be static. 
	static Queue<QueueNode> bigQueue;

	public static void main(String[] args)
	{
		bigQueue = new LinkedList<QueueNode>();
		Scanner sc = new Scanner (System.in);
		int queries = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < queries; ++i) {
			// take in and process the command
			String command = sc.next();
			switch (command.toLowerCase()) {

				case "create":
					int id = sc.nextInt();
					bigQueue.offer(new QueueNode(id));
					break;

				case "insert":
					insert(sc);
					break;

				case "merge":
					merge(sc);
					break;

				case "print":
					print();
					break;

			}
		}

		// last sit in lab lo lulz
	}

	/**
	 * Performs an insert operation for a particular stack in the big queue
	 * Pre-Cond:  The big queue must be initialised.
	 * Post-Cond: The value provided by the user is pushed into the stack with the corresponding ID.
	 */
	public static void insert(Scanner sc)
	{
		int value = sc.nextInt();
		int id = sc.nextInt();
		Queue<QueueNode> temporaryQueue = new LinkedList<QueueNode>();

		// let's go through the bigQueue to find our buddy stack
		while (bigQueue.size() > 0) {
			if (bigQueue.peek().getIndex() == id) {
				// found our buddy stack, let's push the value in.
				bigQueue.peek().push(value);
			}
			// continue to transfer all the nodes...
			temporaryQueue.offer(bigQueue.poll());
		}

		bigQueue = temporaryQueue;
	}

	/**
	 * Performs a merge operation for the first two stacks in the queue.
	 * Operation will do nothing if the bigQueue has less than two stacks inside.
	 * Pre-Cond:  The big queue must be initialised.
	 * Post-Cond: If there are two stacks or more in the big queue, the first two stacks are merged and
	 *            Re-inserted back into the bigQueue as one stack.
	 */
	public static void merge(Scanner sc)
	{
		if (bigQueue.size() >= 2) {
			QueueNode A = bigQueue.poll();
			QueueNode B = bigQueue.poll();

			// THE MAGIC ALGORITHM FOR TRANSFERRING ELEMENTS GIVEN IN QUESTION PAPER
			while (!A.empty()) {
				B.push(A.peek());
				A.pop();
			}
			// SACRED. DO NOT REMOVE.

			Queue<QueueNode> temporaryQueue = new LinkedList<QueueNode>();
			// don't forget to insert our merged stack first... 
			temporaryQueue.offer(B);

			// then, transfer the rest of the stacks in the big queue.
			while (bigQueue.size() > 0) {
				temporaryQueue.offer(bigQueue.poll());
			}

			bigQueue = temporaryQueue;
		}
	}

	/**
	 * Performs printing of the integer at the top of the stack in front of the big queue.
	 * Pre-Cond:  The big queue must be initialised.
	 * Post-Cond: The result of the print operation gets printed.
	 */
	public static void print()
	{
		if (bigQueue.size() == 0) {
			System.out.println("BIG QUEUE IS EMPTY");
		} else if (bigQueue.peek().size() == 0) {
			System.out.println("STACK IS EMPTY");
		} else {
			System.out.println(bigQueue.peek().peek());
		}
	}

	// but still got PE ):
}
