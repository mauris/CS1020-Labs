/**
 * Author:      Yong Shan Xian
 * Matric No.:  A0132763H
 */

import java.util.*;

class Matrix
{
    int size;
    int matrix[][];
    public Matrix(int size)
    {
        this.size = size;
        this.matrix = new int[size][size];
    }

    public int getSize()
    {
        return size;
    }

    public void fillRow(int row, int[] data)
    {
        matrix[row] = data;
    }

    public void rotateClockwise()
    {
        int[][] result = new int[size][size];
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                result[row][column] = matrix[size - column - 1][row];
            }
        }
        matrix = result;
    }

    public void reflectX()
    {
        int half = (int)Math.floor(size / 2);
        for (int row = 0; row < half; ++row) {
            for (int column = 0; column < size; ++column) {
                int temp = matrix[row][column];
                matrix[row][column] = matrix[size - row - 1][column];
                matrix[size - row - 1][column] = temp;
            }
        }
    }

    public void reflectY()
    {
        int half = (int)Math.floor(size / 2);
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < half; ++column) {
                int temp = matrix[row][column];
                matrix[row][column] = matrix[row][size - column - 1];
                matrix[row][size - column - 1] = temp;
            }
        }
    }

    public String output()
    {
        String result = "";
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                result += matrix[row][column] + " ";
            }
            result = result.trim() + "\n";
        }
        return result;
    }
}

class Transformation
{
    private static Scanner sc;

    public static void main(String[] args)
    {
        sc = new Scanner(System.in);

        int size = sc.nextInt();
        sc.nextLine();

        Matrix matrix = new Matrix(size);

        buildMatrix(matrix);

        readExecute(matrix);

        System.out.print(matrix.output());
    }

    public static void buildMatrix(Matrix matrix)
    {
        int max = matrix.getSize();
        for (int i = 0; i < max; ++i) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            int[] row = new int[max];
            for (int j = 0; j < max; ++j) {
                row[j] = Integer.parseInt(parts[j]);
            }
            matrix.fillRow(i, row);
        }
    }

    public static void readExecute(Matrix matrix)
    {

        int commandCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < commandCount; ++i) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            String command = parts[0].toLowerCase();
            switch (command) {
                case "rotate":
                    int count = Integer.parseInt(parts[1]) / 90;
                    for (int j = 0; j < count; ++j) {
                        matrix.rotateClockwise();
                    }
                    break;
                case "reflect":
                    if (parts[1].equals("x")) {
                        matrix.reflectX();
                    } else {
                        matrix.reflectY();
                    }
                    break;
            }
        }
    }
}