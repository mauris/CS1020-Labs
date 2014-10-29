/**
 *	Name			: Yong Shan Xian
 *	Matric-number	: A0132763H
 *	Plab account	: plab0112
 */

import java.util.*;

class Solver
{
	private int[][] _world;

	private int _mines = 0;

	private int _gold = 0;

	public Solver(int[][] world)
	{
		_world = world;
	}

        /// no need to for pre/post conditions for getter and setter
	/**
	 * Get the number of mines from the previous calculation. 
	 * Pre-condition : A calculation must have been performed before calling this method.
	 * Post-condition: Returns the number of mines from the calculated sub-grid area.
	 */
	public int getMines()
	{
		return _mines;
	}

	/**
	 * Get the overrall gold value from the previous calculation.
	 * Pre-condition : A calculation must have been performed before calling this method.
	 * Post-condition: Returns the gold value from the calculated sub-grid area.
	 */
	public int getGold()
	{
		return _gold;
	}

        /// pre/post conditions are not numerical constraints, please clarify with me in class
	/**
	 * Performs calculation of the number of mines and gold value in a sub-grid of the world.
	 * Pre-condition:  1 <= row <= R
	 * 				   1 <= column <= C
	 * Post-condition : Once calculated, the number of mines and the value of gold can be found in the getMines() and getGold() methods respectively.
	 */
	public void calculate(int row, int column, int height, int width)
	{
		int mines = 0, gold = 0;
		for (int r = row; r < row + height; ++r) {
			for (int c = column; c < column + width; ++c) {
				int cellValue = _world[r][c];

				if (cellValue == 0) { // 0 means a mine
					++mines;
				} else {
					gold += _world[r][c];
				}
			}
		}
		_mines = mines;
		_gold = gold;
	}
}

public class Mines
{
	/**
	 *	Count the answer
	 *	Pre-condition  : R must be more than or equals to 1, but less than or equals to 10
	 *                   C must be more than or equals to 1, but less than or equals to 10
	 *				     K must be more than or equals to 0, but less than or equals to R * C
	 *					 field must be an initialized 2-D array of R rows and C columns
	 *	Post-condition : The method gets the total number of gold value possible.
	 */ 
	private static int solve(int R, int C, int K, int field[][])
	{
		Solver solver = new Solver(field);
		int maxGold = 0;

		// Start calculating sub-grids from the top-left corner of the field
		for (int row = 0; row < R; ++row) {
			for (int column = 0; column < C; ++column) {
				int maxWidth = C - column;
				int maxHeight = R - row;

				// Start calculating from the largest rectangle onwards
				for (int width = maxWidth; width > 0; --width) {
					for (int height = maxHeight; height > 0; --height) {

						// calculate the gold value and number of mines
						// within the sub-grid area specified.
						solver.calculate(row, column, height, width);

						// ensure that within the rectangle, the number of mines is lower
						// than or equals to the flags available (K)
						if (solver.getMines() <= K && solver.getGold() > maxGold) {
							// Since the gold we have is greater than previous found values,
							// Let's take this value as our solution first.
							maxGold = solver.getGold();
						}
					}
				}
			}
		}
		return maxGold;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		do {
			// Start a new case //

                /// your method of processing input is unnecessarily complicated, check out other functions in the API
			// Read in the RCK values
			String rckLine = sc.nextLine();
			String[] parts = rckLine.split("\\s+");
			int rows = Integer.parseInt(parts[0]);
			int columns = Integer.parseInt(parts[1]);
			int k = Integer.parseInt(parts[2]);

			// heal the world~
			int[][] world = new int[rows][columns];

			// Start building the case's world by
			// accepting rows of values from the user
			for (int i = 0; i < rows; ++i) {
				String inputLine = sc.nextLine();
				String[] lineParts = inputLine.split("\\s+");
				for (int j = 0; j < columns; ++j) {
					world[i][j] = Integer.parseInt(lineParts[j]);
				}
			}

			// Perform solving and printing of the result.
			int result = solve(rows, columns, k, world);
			System.out.println(result);

			// Let's continue looping to get more cases
		} while(sc.hasNext());
	}
}
