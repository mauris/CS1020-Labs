## Commentaries

Again, Binary Tree or searching in a Binary was not taught in lecture. However, you can think of each unique path (whether left or right of a node), to be a linked list (hey this was taught!!).

This sit in lab was testing our use of generics and data structures (in particular a "modified" version of a linked list). 

In the comparison of generics, you can either choose to do casting/parsing (`Integer.parseInt()`) or use a generic `E extends Comparable<E>` (as in my code). This means to say that my generic type must be a type Comparable, and it must be able to compare with another object of type E. 

The advantage of having a Comparable generic is that you get to use the `.compareTo()` method. Sweetly, `Integer` type is actually a `Comparable` type too, which you can use to compare two elements.

`findMin` and `findMax` just requires you to traverse left or right respectively all the way until the very last node. This will work out nicely if your `insert()` method does it right. 