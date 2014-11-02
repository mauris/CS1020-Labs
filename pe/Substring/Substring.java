import java.util.*;

class Substring
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int cases = sc.nextInt();
        for (int i = 0; i < cases; ++i) {
            int length = sc.nextInt();
            System.out.println(check(sc.next()) ? "YES" : "NO");
        }
    }

    public static boolean check(String binStr)
    {
        return !binStr.contains("00") && !binStr.contains("010");
    }
}
