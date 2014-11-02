import java.util.*;

class Golds
{
	private static int[] cache = new int[9999];

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		int goldCount = sc.nextInt();

		for (int i = 0; i < goldCount; ++i) {
			System.out.println(goldsmith(sc.nextInt()));
		}
	}

	public static int goldsmith(int weight)
	{
		if (weight < 12) {
			return weight;
		} else {
			if (cache[weight] == 0) {
				cache[weight] = goldsmith((int)Math.floor(weight / 2))
					+ goldsmith((int)Math.floor(weight / 3))
					+ goldsmith((int)Math.floor(weight / 4));
			}
			return cache[weight];
		}
	}
}
