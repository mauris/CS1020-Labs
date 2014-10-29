/*
 * author       : Yong Shan Xian
 * matric no.   : A0132763H
 */

import java.util.*;

public class Palindrome
{
    /**
     * use this method to check whether the string is palindrome word or not
     *      PRE-Condition  : The string value given must of length that is divisible by two.
     *      POST-Condition : The method returns true if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String value)
    {
        final int half = value.length() / 2;
        String part1 = value.substring(0, half);
        String part2 = value.substring(half);

        String reversed = new StringBuilder(part1).reverse().toString();
        return part2.equals(reversed);
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String firstWord = scanner.nextLine();
        String secondWord = scanner.nextLine();

        System.out.println(isPalindrome(firstWord + secondWord) ? "YES" : "NO");
    }
}