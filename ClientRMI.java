import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  


public class ClientRMI extends UnicastRemoteObject implements GraphsInterface {
	public GraphsInterface server;

	protected ClientRMI() throws MalformedURLException, RemoteException, NotBoundException, IOException { 
		// this.server = (GraphsInterface) Naming.lookup("rmi://10.1.33.180:1900/graph");
		Registry registry = LocateRegistry.getRegistry("localhost", 8800); 
        this.server = (GraphsInterface) registry.lookup("graph");
	}
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, IOException {
        System.out.println("Connecting to Server");
        ClientRMI obj  = new ClientRMI();
        
        System.out.println("Conected to Server");
        int u,v;
        String gname;
        
        while(true)
        {
        	Scanner cmd = new Scanner(System.in);
        	String comm = cmd.nextLine();
        	StringTokenizer tokenedcommand = new StringTokenizer(comm," ");
        	comm = tokenedcommand.nextToken();
        	// System.out.println(tokenedcommand.nextToken());
        	if(comm.equals("add_edge"))
        	{
        		u = Integer.parseInt(tokenedcommand.nextToken());
        		v = Integer.parseInt(tokenedcommand.nextToken());
        		gname = tokenedcommand.nextToken();
        		obj.server.addingEdge(u,v,gname);
		    }
		    else if(comm.equals("shortest_distance"))
		    {
		    	u = Integer.parseInt(tokenedcommand.nextToken());
        		v = Integer.parseInt(tokenedcommand.nextToken());
			    gname = tokenedcommand.nextToken();
			    // int shortest_dis = g.BFS(u,v);
			    
			    String shortest = obj.server.doBFS(u,v,gname);
			    System.out.println("Shortest distance between given nodes is: "+shortest);
			    // System.out.println(shortest_dis);
			}
			else if(comm.equals("get_graph"))
			{
				gname = tokenedcommand.nextToken();
				String graph_traversed = obj.server.traverse(gname);
				System.out.println("Graph "+gname+":"+graph_traversed);
			}
	    }
    }

    public void addingEdge(int u, int v, String gname) throws RemoteException {}
    public String doBFS(int u, int v, String gname) throws RemoteException {return "exception";}
    public String traverse(String gname) throws RemoteException {return "exception";}
}