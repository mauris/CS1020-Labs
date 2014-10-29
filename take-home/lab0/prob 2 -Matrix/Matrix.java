/**
 *
 * author	: Yong Shan Xian
 * matric no: A0132763H
 *
 */

import java.util.*;

public class Matrix
{
    Scanner scanner;
    int[][] matrix;
    int rows = 0;
    int columns = 0;

	public static void main(String[] args) {
        Matrix matrix = new Matrix();
        matrix.run();
	}

    /**
     * Run this app!!
     */
    public void run()
    {
        scanner = new Scanner(System.in);
        initializeMatrix();
        buildMatrix();
        queryMatrix();
    }

    /**
     * Prompt for the matrix size and initialize the matrix variable
     */
    public void initializeMatrix()
    {
        String line = scanner.nextLine();
        String[] parts = line.split("\\s+");
        rows = Integer.parseInt(parts[0]);
        columns = Integer.parseInt(parts[1]);

        matrix = new int[rows][columns];
    }

    /**
     * Build the matrix by asking the user for the matrix's data
     */
    public void buildMatrix()
    {
        for (int i = 0; i < rows; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            for (int j = 0; j < columns; ++j) {
                matrix[i][j] = Integer.parseInt(parts[j]);
            }
        }
    }

    /**
     * Perform a query of "ROW X" or "COLUMN Y" and return the summation result.
     */
    public void queryMatrix()
    {
        String line = scanner.nextLine();
        String[] parts = line.split("\\s+");
        String type = parts[0]; // ROW or COLUMN
        int value = Integer.parseInt(parts[1]) - 1;

        int result = 0;
        if (type.equals("ROW")) {
            for (int piece : matrix[value]) {
                result += piece;
            }
        } else {
             // we assume that the else-case is COLUMN
             for (int i = 0; i < rows; ++i) {
                result += matrix[i][value];
             }
        }
        System.out.println(result);
    }
}