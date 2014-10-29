import java.util.*;

class MergeCounter
{
    public static long sort(long[] arr)
    {
        return sort(arr, 0, arr.length - 1);
    }

    private static long sort(long[] arr, int i, int j)
    {
        long inverseCounter = 0;
        if (i < j) {
            int mid= (i + j) / 2;
            inverseCounter += sort(arr, i, mid);
            inverseCounter += sort(arr, mid + 1, j);
            inverseCounter += merge(arr, i, mid, j);
        }
        return inverseCounter;
    }

    private static long merge(long[] arr, int i, int mid, int j)
    {
        // Merges a[i..mid] and a[mid+1..j] into a[i..j]
        long[] temp = new long[j - i + 1]; // temp storage
        int left = i, right = mid + 1, it = 0;
        long inverseCounter = 0;
        // it = next index to store merged item in temp[]

        while (left <= mid && right <= j) {
            if (arr[left] >= arr[right]) {
                temp[it++] = arr[left++];
            } else {
                temp[it++] = arr[right++];
                inverseCounter += mid + 1 - left;
            }
        }

        // Copy the remaining elements into temp.
        while (right <= j) {
            temp[it++] = arr[right++];
        }
        while (left <= mid){
            temp[it++] = arr[left++];
        }

        // Copy the result in temp back into array a
        for (int k = 0; k < temp.length; k++) {
            arr[i + k] = temp[k];
        }
        return inverseCounter;
    }
}


class PSI
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();

        long[] sums = new long[count + 1];
        sums[0] = 0;

        for(int i = 0; i < count; ++i) {
            sums[i + 1] = sums[i] + sc.nextInt();
        }
        System.out.println(MergeCounter.sort(sums));
    }

    public static int Q(int[] sequence)
    {
        int result = 0;
        for (int i = 0; i < sequence.length; ++i) {
            int subsetSum = sequence[i];
            if (subsetSum > 0) {
                result++;
            }
            for (int j = i + 1; j < sequence.length; ++j) {
                subsetSum += sequence[j];
                if (subsetSum > 0) {
                    result++;
                }
            }
        }
        return result;
    }
}