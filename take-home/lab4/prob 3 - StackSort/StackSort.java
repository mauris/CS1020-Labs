import java.util.*;

class StackSort
{
	public static void main(String [] args)
	{
		Scanner sc = new Scanner (System.in);
		int testCases = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < testCases; ++i) {
			processCase(sc);
		}
	}

	public static void processCase(Scanner sc) {
		int soldiers = sc.nextInt();
		sc.nextLine();
		Stack<Integer> junction = new Stack<Integer>();
		LinkedList<Integer> tunnelA = new LinkedList<Integer>();
		LinkedList<Integer> tunnelB = new LinkedList<Integer>();
		for (int j = 0; j < soldiers; ++j) {
			int height = sc.nextInt();
			tunnelA.offer(height);
		}
		sc.nextLine();

		boolean possible = true;
		for (Integer soldier: tunnelA) {
			if (junction.empty()) {
				junction.push(soldier);
			} else {
				if (!tunnelB.isEmpty() && soldier < tunnelB.peekLast()) {
					// gg.. no longer possible
					possible = false;
					break;
				}

				// must be shorter to be on the stack
				if (soldier > junction.peek()) {
					// this guy is taller, so we remove all from junction first
					while (!junction.empty() && junction.peek() < soldier) {
						tunnelB.offer(junction.pop());
					}
				}
				junction.push(soldier);
			}
		}

		System.out.println(possible ? "YES" : "NO");
	}
}
