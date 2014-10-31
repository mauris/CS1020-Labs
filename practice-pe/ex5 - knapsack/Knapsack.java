/**
 *	name	  :
 *	matric no.:
 */

import java.util.*;

class Result
{
	private int sackWeight;

	public Result(int sackWeight)
	{
		this.sackWeight = sackWeight;
	}

	public int solve(int[] items) {
		return counter(items, 0, 0);
	}

	private int counter(int[] items, int n, int sum) {
		int result = 0;
		if (n < items.length) {
			result += counter(items, n + 1, sum);
			result += counter(items, n + 1, sum + items[n]);
		} else if (sum <= sackWeight) {
			++result;
		}

		return result;
	}
}

class Knapsack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int itemsCount = sc.nextInt();

		int sackWeight = sc.nextInt();

		int[] items = new int[itemsCount];

		for (int i = 0; i < itemsCount; ++i) {
			items[i] = sc.nextInt();
		}

		Result result = new Result(sackWeight);
		System.out.println(result.solve(items));
	}
}
