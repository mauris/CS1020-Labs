/**
 *	name	  :
 *	matric no.:
 */

import java.util.*;

class Jogging {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int timeLimit = sc.nextInt();
		int roadCount = sc.nextInt();

		int uphill = sc.nextInt();
		int flat = sc.nextInt();
		int downhill = sc.nextInt();
		sc.nextLine();

		ArrayList<Integer> roadCosts = new ArrayList<Integer>();

		for (int i = 0; i < roadCount; ++i) {
			String type = sc.next();
			switch(type){
				case "u":
				case "d":
					roadCosts.add(uphill + downhill);
					break;
				case "f":
					roadCosts.add(flat + flat);
					break;
			}
		}

		int farthest = 0;
		for(Integer cost: roadCosts){
			timeLimit -= cost;
			if (timeLimit >= 0) {
				farthest++;
			} else {
				break;
			}
		}
		System.out.println(farthest);
	}
}