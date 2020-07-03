

Programming Language details
-----------------------------------
Programming Language: JAVA
Compiler Version : JAVA 8

Instruction to run program
-----------------------------------
1. Go to project folder from command prompt
2. Execute javac Graph.java
3. make sure the class files for Graph,Vertex,Edge,Heap are created in the folder
3. Execute java Graph <network file name with extension><queries file name with extension><outputfile name with extensiobn>
   ex: java Graph network.txt queries.txt output.txt
       This will create an output file output.txt in the same folder

Program Description and data structure details
-----------------------------------------------
Graph.java
---------------
This class represents the graph. It is a hashmap to store the vertex name and Vertex object.
buildGraph() method will build the graph from input network file. 
it contains the method for every operations in queries.txt input file. For adding edges,deleting edge,to make an edge up, to make an edge down
, to make a vertex up and down, finding the shortest path between a source and destination vertex and finding the reachable vertices from a vertex and quit the program
Shortest path operation is implemented using Dijisktra algorithm and min priority queue is implemented using min binary heap. complexity of dijikstra algorithm is 
O((|V| + |E|) lg |V|) since I am using binary heap.

reachable method is implemented using Breadth first search algorithm.
After initialization, each vertex is enqueued and dequeued at most once, and each operation takes O(1).   
So, total time for queuing is O(|V|).The adjacency list of edges of each vertex is scanned at most once.  The sum of lengths of all adjacency lists is Θ(|E|).
Summing up over all vertices. total running time of BFS is O(|V|+|E|).
Since we are performing the BFS for each vertex in the graph, total time complexity is O(V.(|V|+|E|))

Vertex.java
------------------
It is used to represent the vertex in the graph.contains the details of vertex name, vertex status, list of outgoing edges, distance, predecessor and acomparator for sorting based on vertex name

Edge.java
----------
Used to represent an edge between two vertex. it contains the details of source vertex, destination vertex, cost of the edge, status of the edge. it also contains the getter and setter for above variables and a comparator for sorting based on destination vertex name
override the hashcode and equals, and two objects will match based on the  sourceVertex and destVertex

Heap.java
---------------
This is used to implement the min priority queue.  storing the distance to each vertex in a double array and vertexHandler is a handler which holds the vertex name of each distance element in distance array. 
ex: distance[0] will be the distance from source vertex to vertexHandler[0]

