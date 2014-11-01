## Commentaries

It is interesting to finally have a path-finding problem in CS1020. In one of the lectures:

> **Student**: Prof Tan, do you find the Rubaiyat to be a difficult problem?  
> **Prof Tan**: Yes, very difficult.

I had the advantage in this problem thanks to a project my friends and I worked on in RHyMeS centre, Ngee Ann Polytechnic. We worked on Dijkstra's Algorithm in that project. 

I created a Coordinate class to help me pass down the path of vertices (or coordinates) that I have visited. The Stack ADT seemed to be one of the best options for me to pass down the vertices that I have visited so that I do not get stuck. Notice how I push in the coordinate that I want to visit, and pop it after visiting it. 

In Dijkstra's Algorithm, we look for the path with the smallest combined weight (i.e. shortest path). However, in this problem, we are finding the path that yields the highest weightage. Hence the addition and `Collections.max`.