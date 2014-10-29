/**
 *	name	  :
 *	matric no.:
 */

import java.util.*;

class Result {
    // declare the member field
    private ArrayList<Integer> positive;
    private ArrayList<Integer> negative;

    public Result(ArrayList<Integer> positive, ArrayList<Integer> negative)
    {
    	this.positive = positive;
    	this.negative = negative;
    }

	/**
	 * 	solveMin		: to find the minimum product of triplets.
	 * 	Pre-condition  	:
	 * 	Post-condition 	:
	 */
	public int solveMin() {
		ArrayList<Integer> min = new ArrayList<Integer>();
		if (negative.size() > 0 && positive.size() > 1) {
			min.add(negative.get(0) * positive.get(positive.size() - 1) * positive.get(positive.size() - 2));
		}
		if (negative.size() > 2) {
			min.add(negative.get(0) * negative.get(1) * negative.get(2));
		}
		if (positive.size() > 2) {
			min.add(positive.get(0) * positive.get(1) * positive.get(2));
		}
		return Collections.min(min);
	}

	/**
	 *	solveMax		: to find the maximum product of triplets.
	 * 	Pre-condition  	:
	 * 	Post-condition 	:
	 */
	public int solveMax() {
		ArrayList<Integer> max = new ArrayList<Integer>();
		if (positive.size() > 0 && negative.size() > 1) {
			max.add(positive.get(positive.size() - 1) * negative.get(0) * negative.get(1));
		}
		if (positive.size() > 2) {
			max.add(positive.get(positive.size() - 1) * positive.get(positive.size() - 2) * positive.get(positive.size() - 3));
		}
		if (negative.size() > 2) {
			max.add(negative.get(negative.size() - 1) * negative.get(negative.size() - 2) * negative.get(negative.size() - 3));
		}
		return Collections.max(max);
	}
}

class Triplet {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int numberCount = sc.nextInt();
		ArrayList<Integer> positive = new ArrayList<Integer>();
		ArrayList<Integer> negative = new ArrayList<Integer>();
		for (int i = 0; i < numberCount; ++i) {
			int number = sc.nextInt();
			if (number < 0) {
				negative.add(number);
			} else {
				positive.add(number);
			}
		}

		Collections.sort(negative); // O(nlogn)
		Collections.sort(positive); // O(nlogn)
		Result result = new Result(positive, negative);
		System.out.println(result.solveMin() + " " + result.solveMax());
	}
}
