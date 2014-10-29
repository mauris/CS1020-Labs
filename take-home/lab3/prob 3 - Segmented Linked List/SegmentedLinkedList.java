import java.util.*;

class SegmentNode<E extends Comparable<E>> implements Comparable<SegmentNode<E>>
{
	private E mVal;
	private SegmentNode<E> mNext;

	public SegmentNode ()
	{
	}

	public SegmentNode(E pVal)
	{
		this.mVal = pVal;
		this.mNext = null;
	}

	public SegmentNode(E pVal, SegmentNode<E> pNext)
	{
		this.mVal = pVal;
		this.mNext = pNext;
	}

	public E getValue()
	{
		return this.mVal;
	}

	public SegmentNode<E> getNext()
	{
		return this.mNext;
	}

	public void setNext(SegmentNode<E> next)
	{
		this.mNext = next;
	}

	public int compareTo(SegmentNode<E> node)
	{
		return mVal.compareTo(node.mVal);
	}
}

class Segment<E extends Comparable<E>>
{
	private SegmentNode<E> head;
	private SegmentNode<E> tail;
	private int length = 0;

	public Segment()
	{
	}

	public Segment(SegmentNode<E> newHead)
	{
		if (newHead != null) {
			head = newHead;
			SegmentNode<E> current = head;
			int count = 1;
			while (current.getNext() != null) {
				current = current.getNext();
				++count;
			}
			tail = current;
			length = count;
		}
	}

	public void add(SegmentNode<E> newNode)
	{
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else if (head.compareTo(newNode) >= 0) {
			newNode.setNext(head);
			head = newNode;
		} else if (tail.compareTo(newNode) <= 0) {
			tail.setNext(newNode);
			tail = newNode;
		} else {
			SegmentNode<E> current = head;
			while(current.getNext() != null)
			{
				SegmentNode<E> next = current.getNext();
				if (current.compareTo(newNode) < 0 && next.compareTo(newNode) >= 0) {
					newNode.setNext(next);
					current.setNext(newNode);
					break;
				}
				current = next;
			}
		}
		++length;
	}

	public Segment<E> split()
	{
		int newLength = (int)Math.floor(length / 2);
		SegmentNode<E> current = head;
		SegmentNode<E> previous = null;
		int count = 0;
		while (current.getNext() != null && count++ < newLength) {
			previous = current;
			current = current.getNext();
		}
		previous.setNext(null);
		length = newLength;
		tail = previous;
		Segment<E> newSegment = new Segment<E>(current);
		return newSegment;
	}

	public SegmentNode<E> getHead()
	{
		return head;
	}

	public SegmentNode<E> getTail()
	{
		return tail;
	}

	public E get(int index)
	{
		if(index >= 0 && index < length) {
			SegmentNode<E> current = head;
			int count = 0;
			while (current != null && count++ < index) {
				current = current.getNext();
			}
			if (current != null) {
				return current.getValue();
			}
		}
		return null;
	}

	public int getLength()
	{
		return length;
	}
}

class Manager<E extends Comparable<E>>
{
	ArrayList<Segment<E>> segments = new ArrayList<Segment<E>>();
	private final int MaxSegmentLength = 6;

	public void add(E value)
	{
		SegmentNode<E> node = new SegmentNode<E>(value);
		if(segments.size() == 0) {
			segments.add(new Segment<E>());
		}
		Segment<E> segment = findSegmentToAdd(node);
		segment.add(node);
		if (segment.getLength() == MaxSegmentLength) {
			Segment<E> newSegment = segment.split();
			segments.add(segments.indexOf(segment) + 1, newSegment);
		}
	}

	private Segment<E> findSegmentToAdd(SegmentNode<E> node)
	{
		for (Segment<E> segment : segments) {
			if(segment.getLength() == 0 || (segment.getTail().compareTo(node) >= 0)) {
				return segment;
			}
		}
		Segment<E> lastSegment = segments.get(segments.size() - 1);
		return lastSegment;
	}

	public E query(int index)
	{
		for (Segment<E> segment : segments) {
			if(segment.getLength() > index) {
				return segment.get(index);
			} else {
				index -= segment.getLength();
			}
		}
		return null;
	}

	public void debug()
	{
		int count = 0;
		for (Segment<E> segment : segments) {
			++count;
			System.out.println("Segment " + count);
			for (int i = 0; i < segment.getLength(); ++i) {
				System.out.println(i + " - " + segment.get(i));
			}
			System.out.println("");
		}
	}
}

public class SegmentedLinkedList
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int operationsCount = sc.nextInt();
		sc.nextLine();

		Manager<Integer> manager = new Manager<Integer>();

		for (int op = 0; op < operationsCount; ++op) {
			String operation = sc.next();
			int number = sc.nextInt();
			sc.nextLine();
			switch(operation.toLowerCase()) {
				case "insert":
					manager.add(number);
					break;
				case "query":
					System.out.println(manager.query(number - 1));
					break;
			}
		}
	}
}
