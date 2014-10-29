/*****************************
 *  Name:			Yong Shan Xian 
 *  Matric Number:	A0132763H
 *  Plab account:   plab0471
*i****************************/

import java.util.*;

class TreeNode <E> 
{
	protected E element; 
	protected TreeNode <E> leftChild;
	protected TreeNode <E> rightChild;

	/* constructors */
	public TreeNode(E item) { element = item; leftChild = null; rightChild = null; }

	/* get the next ListNode */
	public TreeNode <E> getLeftChild() {
		return this.leftChild;
	}

	public TreeNode <E> getRightChild() {
		return this.rightChild;
	}

	/* get the element of the ListNode */
	public E getElement() {
		return this.element;
	}

	public void setLeftChild(TreeNode <E> item) {
		this.leftChild = item;
	}

	public void setRightChild(TreeNode <E> item) {
		this.rightChild = item;
	}

	// actually hor, this setElement damn useless in this lab. 
	public void setElement(E item){
		this.element = item;
	}
}

class BinaryTree <E extends Comparable<E>>
{
	protected TreeNode<E> root = null;

	/**
	 * Insert the element into the tree.
	 * Pre-cond: 	The tree and element to be must have been initialised.
	 * Post-cond:	The element gets inserted into the tree at the right position.
	 */
	public void insert(E item) {
		TreeNode<E> node = new TreeNode<E>(item);
		if(root == null){
			// special case, first node
			root = node;
		} else {
			// start from the root, slowly traverse
			TreeNode<E> current = root;
			boolean inserted = false;
			while (!inserted) {
				if (item.compareTo(current.getElement()) <= 0) {
					// this is the case where the item being inserted
					// is less than or equals to the value that we are looking at now
					if (current.getLeftChild() == null) {
						// give birth to child on the left side
						current.setLeftChild(node);
						inserted = true;
						// give birth liao must rest, so get out of the while loop
					} else {
						current = current.getLeftChild();
					}
				} else {
					// this is the case where the item being inserted
					// is more than the value that we are looking at now.
					if (current.getRightChild() == null) {
						// give birth to child on the right side
						current.setRightChild(node);
						inserted = true;
						// give birth liao must rest, so get out of the while loop
					} else {
						current = current.getRightChild();
					}
				}
			}
		}
	}

	/**
	 * Find the minimum value of the tree.
	 * Pre-cond:	The tree must have been initialised.
	 * Post-cond:	The minimum value is returned if the tree contains at least one item, returns null if the tree is empty.
	 */
	public E findMin() {
		// start from the root, slowly traverse leftwards to find the smallest value
		TreeNode<E> current = root;
		while (current != null) {
			if(current.getLeftChild() == null) {
				// there is no more smaller child than this one, return this
				return current.getElement();
			} else {
				current = current.getLeftChild();
			}
		}
		return null;  // must return the correct result
	}

	/**
	 * Find the maximum value of the tree
	 * Pre-cond:	The tree must have been initialised.
	 * Post-cond:	The maximum value is returned if the tree contains at least one item, returns null if the tree is empty.
	 */
	public E findMax() {
		// start from the root, slowly traverse rightwards to find the largest value
		TreeNode<E> current = root;
		while (current != null) {
			if (current.getRightChild() == null) {
				// there is no more child that is greater than this one, so return this
				return current.getElement();
			} else {
				current = current.getRightChild();
			}
		}
	    return null;	  // must return the correct result
	}

	/**
	 * Find the depth of a particular item where it is stored.
	 * Pre-cond: 	The tree must have been initialised.
	 * Post-cond:	The level of the item where it is stored will be returned if it is found. Returns 0 if the item is not found or the tree is empty.
	 */
	public int findDepth(E item) {
		// start from the root. increment later if root is not null.
		TreeNode<E> current = root;
		int level = 0;
		while (current != null) {
			++level; // increase the level
			if (item.compareTo(current.getElement()) == 0) {
				// the item that we're looking for is at this level, return it. 
				return level;
			} else {
				// get next level
				if (item.compareTo(current.getElement()) < 0) {
					current = current.getLeftChild();
				} else {
					current = current.getRightChild();
				}
				// LEFT, LEFT, LEFT RIGHT LEFT
			}
		}
	    return 0;  // must return the correct result. wrong result will minus mark one leh  	
	}
}

public class BinarySearchTree
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		// read in the number of queries to process
		int queryCount = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < queryCount; ++i) {
			String command = sc.next();
			switch (command.toLowerCase()) {

				case "insert": // insert a number into the tree
					tree.insert(sc.nextInt());
					sc.nextLine();
					break;

				case "findmin": // find the minimum value
					Integer min = tree.findMin();
					System.out.println(min == null ? 0 : min);
					break;

				case "findmax": // find the maximum value
					Integer max = tree.findMax();
					System.out.println(max == null ? 0 : max);
					break;

				case "find": // find the depth. under the sea~
					int query = sc.nextInt();
					System.out.println(tree.findDepth(query));
					break;

			}
		}
	}
}



