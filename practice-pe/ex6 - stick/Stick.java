/**
 *	name	  :
 *	matric no.:
 */

import java.util.*;

class Result
{
	private int length;

	public Result(int length)
	{
		this.length = length;
	}

	public int solve(int[] sticks)
	{
		return solve(sticks, sticks.length - 1, 0, 0);
	}

	private int solve(int[] sticks, int n, int target, int counter)
	{
		int result = 0;
		if (n >= 0) {
			int result1 = solve(sticks, n - 1, target, counter);
			int result2 = solve(sticks, n - 1, target + sticks[n], counter + 1);
			if (result1 == -1 && result2 == -1) {
				return -1;
			} else if (result1 == -1) {
				return result2;
			} else if (result2 == -1) {
				return result1;
			}
			return Math.min(result1, result2);
		}
		if(target == this.length) {
			return counter;
		}
		return -1;
	}

	private static void print(int[] arr)
	{
		for(int i = 0; i < arr.length; ++i) {
			System.out.println(arr[i]);
		}
	}
}

class Stick
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int stickCount = sc.nextInt();
		int targetLength = sc.nextInt();

		int[] sticks = new int[stickCount];

		for (int i = 0; i < stickCount; ++i) {
			sticks[i] = sc.nextInt();
		}

		Result result = new Result(targetLength);
		System.out.println(result.solve(sticks));
	}
}
