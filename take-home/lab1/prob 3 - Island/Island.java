/**
 * Author:      Yong Shan Xian
 * Matric No.:  A0132763H
 */

import java.util.*;

class Map
{
    int rows, columns;
    int map[][];

    public Map(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        this.map = new int[rows][columns];
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public void fillRow(int row, int[] data)
    {
        map[row] = data;
    }

    protected int[] explore(int row, int column)
    {
        int x = row;
        int y = column;

        for (y = column; y < columns; ++y) {
            if (map[x][y] == 0) {
                break;
            }
        }
        if((y - column) > 0) {
            for (x = row; x < rows; ++x) {
                boolean result = true;
                for(int i = column; i < y; ++i) {
                    if(map[x][i] == 0) {
                        result = false;
                        break;
                    }
                }
                if (!result) {
                    break;
                }
            }
        }

        int[] size = new int[2];
        size[0] = x - row;
        size[1] = y - column;
        return size;
    }

    public int countIslands()
    {
        int result = 0;
        boolean[][] markers = new boolean[rows][columns];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (!markers[i][j]) {
                    int[] size = explore(i, j);
                    if (size[0] > 0) {
                        ++result;
                        for (int x = i; x < i + size[0]; ++x) {
                            for (int y = j; y < j + size[1]; ++y) {
                                markers[x][y] = true;
                            }
                        }
                        j += size[1];
                    } else {
                        markers[i][j] = true;
                    }
                }
            }
        }

        return result;
    }
}

class Island {
    private static Scanner sc;
    public static void main(String[] args)
    {
        sc = new Scanner(System.in);
        Map map = createMap();

        buildMap(map);

        System.out.println(map.countIslands());
    }

    public static Map createMap()
    {
        String line = sc.nextLine();
        String[] parts = line.split("\\s+");

        int rows = Integer.parseInt(parts[0]);
        int columns = Integer.parseInt(parts[1]);
        return new Map(rows, columns);
    }

    public static void buildMap(Map map)
    {
        int maxRows = map.getRows();
        int maxColumns = map.getColumns();
        for (int i = 0; i < maxRows; ++i) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            int[] row = new int[maxColumns];
            for (int j = 0; j < maxColumns; ++j) {
                row[j] = Integer.parseInt(parts[j]);
            }
            map.fillRow(i, row);
        }
    }
}