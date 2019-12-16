## Implementation:

- There are three files : ```ServerRMI.java``` , ```ClientRMI.java``` and ```GraphsInterface.java```. 
- GraphsInterface is the name of the interface which contains the abstract functions to be invocked by remote clients. It extends Remote interface.
- ServerRMI class implements the remote GraphsInterface and extends UnicastRemoteObject class of java.rmi package. 
- Remote connection is established between the ```ServerRMI``` and ```ClientRMI```. 
- Registry is created at server side on a particular IP and port. I have used port 8800. 
- ```ServerRMI``` also contains Graph class which has all the functions related to the graph like addEdge, BFS and getGraph.
- A remote object created at client side is able to access the graph class functions and returns the result wherever required.
- GraphsInterface contains all the abstract functions that are to be used on client side. 
- I have also implemented multiple graphs function (**BONUS Part**).

## Instructions to run the code

- ```javac ServerRMI.java``` and ```javac ClientRMI.java``` compiles both the server and client files.
- Run the command ```rmiregistry``` on one terminal.
- Run client and server on different terminals or machine (provided they are on same network) using ```java ServerRMI``` and ```java ClientRMI```.
- Since I have implemented multiple graphs, the command are entered as follows:
- ```add_edge node1 node2 graphName``` Example: add_edge 1 2 a1, where a1 is graph name and 1 & 2 are node values.
- ```shortest_distance node1 node2 graphName``` Example: shortest_distance 1 2 a1, where a1 is graph name and 1 & 2 are node values. 
- ```get_graph graphName``` Example: get_graph a1, a1 is graph name.
