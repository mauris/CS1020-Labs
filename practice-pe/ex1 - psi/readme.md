## Commentaries

This past-year Practical Exam question is TOUGH. 

I spent hours and the best I could do was a O(n<sup>2</sup>) smart bruteforce algorithm (which hit 8/10 test case pass). Decided to turn to the hint in the question paper on using *mergesort* to get a O(n logn) solution for a 100% test pass.

One of the keyword that held me back was "counting inversions". I did not understand what that meant until I came across two articles:

- [http://stackoverflow.com/questions/25861012/counting-number-of-contiguous-subarrays-with-positive-sum-in-onlogn-using-on](http://stackoverflow.com/questions/25861012/counting-number-of-contiguous-subarrays-with-positive-sum-in-onlogn-using-on)
- [http://www.geeksforgeeks.org/counting-inversions/](http://www.geeksforgeeks.org/counting-inversions/)

The first step of the algorithm is to create an array that contains sums, not the input itself.

Instead of writing

    int[] sequence = new int[count];
    for (int i = 0; i < count; ++i) {
        sequence[i] = sc.nextInt();
    }

The better solution is to write an array of sums, such that:

    long[] sums = new long[count + 1]; // count + 1 because we are inserting zero as first element.
    sums[0] = 0;
    for (int i = 0; i < count; ++i) {
        sums[i + 1] = sums[i] + sc.nextInt();        
    }

> Note that I switched from int to long, because following the advise of a fellow classmate, the test cases actually exhaust the spans of an integer. Wow. Even the number of inversions can go beyond integer space. Hence, you can play save by switching everything from int to long for the tests to work.

The method of storing the sums in this manner is such that you can find **the sum of data[i] to data[j]**, where **i <= j**, in **O(1)** time. - Ivan Chew on the IVLE lab forum. (See also, Soap Opera ep. 3)

Considering the array `data` consist of the input from the user, you can say that the sums array's psuedo-code is

    int[] sums = new int[data.length + 1];
    sum[0] = 0;
    sum[k] = sum[k - 1] + data[k - 1] for k = 1 to data.length
        => sum[k] = data[0] + data[1] + ... + data[k - 1];

The time complexity of creating the `sums` array is O(n).

So how do we check whether `data[i] + ... + data[j]` is a PSI? Well the `sums` actually nicely does the job. Simply check if `sums[j] - sums[i] > 0`, then `data[i] + ... + data[j]` is a PSI.

We can again bruteforce to check for each combination of `i` and `j` values in the sums - which results in a O(n<sup>2</sup>) solution.

The better solution is to use *mergesort*. The explanation on how to use the merge sort to count the inversions can be found on the geeksforgeeks website (2nd link). The key is that you need to modify the recursion in mergesort to return the number of inversions made. Also, you need to sort the `sums` array decreasing. Whenever a sum hops from the right partition to the left partition, you add the number of hops it made. 

Mergesort has a time complexity of O(n logn). Since O(n) + O(n logn) = O(n logn), your final solution reaches O(n logn) time complexity (and getting 100% pass). 