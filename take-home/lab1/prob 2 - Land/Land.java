/**
 * name      : Yong Shan Xian
 * matric no.: A0132763H
 */

import java.util.*;

class Grid
{
    // declare the member field
    boolean[][] grid;

    int gridSize;

    // declare the constructor
    public Grid(int size)
    {
        grid = new boolean[size][size];
        gridSize = size;
    }

    /**
     *      setTree: Plant a tree on the grid and let it grow!
     *      Pre-condition : 0 <= x < size
     *                      0 <= y < size
     *      Post0condition: The tree gets planted and nothing more happens!
     */
    public void setTree(int x, int y)
    {
        grid[x][y] = true;
    }

    /**
     *     checkNoTree   : to check whether the (size x size) square with upper-left coordinate 
     *                     (x, y) contains a tree
     *     Pre-condition : The grid must already be initialized with the trees already set.
     *                     0 <= x < gridSize - size
     *                     0 <= y < gridSize - size
     *                     1 <= size <= gridSize
     *     Post-condition: The method returns true if there is no trees in the square specified by x, y and size. False if there are trees in it.
     */
    public boolean checkNoTree(int x, int y, int size) {
        int limitX = x + size;
        int limitY = y + size;
        for (int i = x; i < limitX; ++i) {
            for (int j = y; j < limitY; ++j) {
                if(grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }



    /**
     *     checkValidSize: to check whether it is possible to find a (size x size) square that contains 
     *                     no tree
     *     Pre-condition : The grid must already be initialized with the trees already set.
     *                     1 <= size <= gridSize
     *     Post-condition: The method returns true if there is a square with the specified size with no trees in the grid, false otherwise.
     */
    public boolean checkValidSize(int size) {
        int limitX = gridSize - size + 1;
        int limitY = gridSize - size + 1;


        for (int i = 0; i < limitX; ++i) {
            for (int j = 0; j < limitY; ++j) {
                if (checkNoTree(i, j, size)) {
                    return true;
                }
            }
        }
        return false;
    }



    /** 
     *     solve         : use this method to find the largest size of a square with no trees
     *     Pre-condition : The grid must already be initialized with the trees already set.
     *     Post-condition: The method returns the biggest square size possible in the grid with no trees.
     */
    public int solve() {
        for (int l = gridSize; l > 0; --l) {
            if (checkValidSize(l)) {
                return l;
            }
        }
        return 0;
    }

}

class Land
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int gridSize = sc.nextInt();
        sc.nextLine();

        Grid grid = new Grid(gridSize);

        int commands = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < commands; ++i) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            int treeX = Integer.parseInt(parts[0]);
            int treeY = Integer.parseInt(parts[1]);

            // need to minus one because the input is not zero-index based
            grid.setTree(treeX - 1, treeY - 1);
        }

        int result = grid.solve();
        System.out.println(result);
    }
}