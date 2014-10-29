/**
 *
 * author   : Yong Shan Xian
 * matric no: A0132763H
 *
 */

import java.io.*;
import java.util.Scanner;

public class HelloWorld
{
    private int maxOperation = 100;
    private int[] results;

    /**
     * Main application entry point
     */
    public static void main (String[] args)
    {
        HelloWorld app = new HelloWorld();
        app.run();
    }

    /**
     * Run the main application's functions
     */
    public void run ()
    {
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        scanner.nextLine(); // consume the new line pls

        if (type == 1) {
            maxOperation = scanner.nextInt();
            scanner.nextLine(); // consume the new line pls
        }
        results = new int[maxOperation];

        int count = 0;
        for (; count < maxOperation && scanner.hasNext(); ++count) {
            // read in 1 operation (1 line)
            String line = scanner.nextLine();
            if (type == 2 && line.equals("0")) {
                break;
            } else if (!line.trim().equals("")) {
                results[count] = calculateLine(line);
            }
        }
        maxOperation = count;

        // print out all the results now
        for (int i = 0; i < maxOperation; ++i) {
            System.out.println(results[i]);
        }
    }

    /**
     * Perform a calculation for a line of "OPERATOR OPERAND OPERAND" 
     */
    public int calculateLine (String line)
    {
        // perform splitting of the string into its parts
        String[] parts = line.split("\\s+");
        String operator = parts[0].toUpperCase();
        int operandA = Integer.parseInt(parts[1]);
        int operandB = Integer.parseInt(parts[2]);

        int result = 0;
        if (operator.equals("AND")) {
            result = (operandA == 1 && operandA == operandB) ? 1 : 0;
        } else if (operator.equals("OR")) {
            result = (operandA == 1 || operandB == 1) ? 1 : 0;
        }

        return result;
    }
}
