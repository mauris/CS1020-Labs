import java.util.*;

class Coordinate
{
	int x, y;

	public Coordinate (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Coordinate getTop()
	{
		return new Coordinate(x, y - 1);
	}

	public Coordinate getBottom()
	{
		return new Coordinate(x, y + 1);
	}

	public Coordinate getLeft()
	{
		return new Coordinate(x - 1, y);
	}

	public Coordinate getRight()
	{
		return new Coordinate(x + 1, y);
	}

	public boolean equals(Object c)
	{
	    if(!(c instanceof Coordinate)) {
	        return false;
	    }

	    Coordinate that = (Coordinate)c;
	    return this.x == that.getX() && this.y == that.getY();
	}
}

class Explorer
{
	int[][] matrix;
	int size;
	final int steps = 10;

	public Explorer(int[][] matrix, int size)
	{
		this.matrix = matrix;
		this.size = size;
	}

	public int explore(int x, int y)
	{
		Coordinate start = new Coordinate(x, y);
		Stack<Coordinate> path = new Stack<Coordinate>();
		path.push(start);
		return explore(path);
	}

	public int explore(Stack<Coordinate> path)
	{
		if (path.size() <= steps) {
			ArrayList<Integer> values = new ArrayList<Integer>();

			Coordinate coords = path.peek();

			int x = coords.getX();
			int y = coords.getY();

			if (x > 0 && !path.contains(coords.getLeft())) {
				// has left
				path.push(coords.getLeft());
				values.add(explore(path));
				path.pop();
			}

			if (x < size - 1 && !path.contains(coords.getRight())) {
				// has right
				path.push(coords.getRight());
				values.add(explore(path));
				path.pop();
			}

			if (y > 0 && !path.contains(coords.getTop())) {
				// has top
				path.push(coords.getTop());
				values.add(explore(path));
				path.pop();
			}

			if (y < size - 1 && !path.contains(coords.getBottom())) {
				// has bottom
				path.push(coords.getBottom());
				values.add(explore(path));
				path.pop();
			}

			if (values.size() > 0) {
				return matrix[x][y] + Collections.max(values);
			} else {
				return matrix[x][y];
			}
		}
		return 0;
	}
}

class Rubaiyat
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int[][] matrix;

		int size = sc.nextInt();
		matrix = new int[size][size];

		int startX = 0;
		int startY = 0;

		for (int j = 0; j < size; ++j) {
			for (int i = 0; i < size; ++i) {
				String value = sc.next();
				if (value.equals("*")) {
					startX = i;
					startY = j;
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = Integer.parseInt(value);
				}
			}
		}

		Explorer explorer = new Explorer(matrix, size);
		int result = explorer.explore(startX, startY);
		System.out.println(result);
	}
}
