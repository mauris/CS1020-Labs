## Commentaries

Finally. I was only able to implement the code after working on some exercises found in [CodingBat](http://codingbat.com/java/Recursion-2)'s Java Recursion-2.

The trick here is to the ability to write the `counter()` method that performs the recursion. This recursion performs the calculation of the various combinations. Each of the counter run will determine both cases of whether the current item (`items[n]`) is considered in the final combination.

I only understood this after attending recent lectures in CS1231 on Permutations and Combinations. (well, subsets are considered a selection (choosing) from the main set). 