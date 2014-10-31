## Commentaries

Stick is kind of similar to Knapsack. You need to get all the different subsets and determine which [subset sums](http://en.wikipedia.org/wiki/Subset_sum_problem) up to the target length. Not only that, you need to find the subset that contains the least number of elements (sticks). 

Again, `solve(int[] items, int n, int target, int counter)` is the key recursive method for this problem. The `counter` argument counts the number of elements that were accounted for in the subset.

The difference with Stick and Knapsack is that Stick requires the subset to sum up to an exact number, whether Knapsack considers all subsets that sums up to a number less than the target number.