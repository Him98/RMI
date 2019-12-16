import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.io.* ;
import java.net.* ;
import java.util.* ;
import java.awt.* ;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  


public class ServerRMI extends UnicastRemoteObject implements GraphsInterface {

	// public Graph g = new Graph();
	public Map<String, Graph> mulGraphs = new HashMap<String, Graph>();

	public static void main(String[] args) throws RemoteException, MalformedURLException {
        // Naming.rebind("graph" ,new ServerRMI());
        System.setProperty("java.rmi.server.hostname", "localhost");  
        Registry ser = LocateRegistry.createRegistry(8800);  
        try{ 
            ser.bind("graph", new ServerRMI());
        }
        catch (Exception e) {System.out.println(e);}
    }

    public void addingEdge(int u, int v, String gname) throws RemoteException {
    	if(mulGraphs.containsKey(gname))
    		mulGraphs.get(gname).addEdge(u,v);
    	else
    	{
    		mulGraphs.put(gname,new Graph());
    		mulGraphs.get(gname).addEdge(u,v);
    	}
    }

    public String doBFS(int u, int v, String gname) throws RemoteException {
    	if(mulGraphs.containsKey(gname))
    		return mulGraphs.get(gname).BFS(u,v);
    	else
    		return "Graph doesn't exist, add some edges";
    }

    public String traverse( String gname) throws RemoteException {
    	if(mulGraphs.containsKey(gname))
    		return mulGraphs.get(gname).getGraph();
    	else
    		return "Graph doesn't exist, add some edges";
    }

    public ServerRMI() throws RemoteException {
    }

    public int sendMessage (String message) throws RemoteException, IOException {return 0;}
}

class Graph 
{
    private Map<Integer, Vector<Integer>> gr;
    private Map<Integer, Integer> dist;
    private Map<Integer, Integer> par;
    Graph() 
    { 
        // V = v; 
        gr = new HashMap<Integer, Vector<Integer>>(); 
        dist = new HashMap<Integer, Integer>();
        par = new HashMap<Integer, Integer>();
        // for (int i=0; i<V; ++i) 
        // {
        //     gr.add(i,new HashMap<Integer>); 
        //     dist.add(Integer.MAX_VALUE);
        //     par.add(-1);
        // }
    }
    void addEdge(int v,int w) 
    {
    	if(gr.containsKey(v))
    		gr.get(v).add(w);
    	else
    	{
    		gr.put(v,new Vector<Integer>());
    		gr.get(v).add(w);
			dist.put(v,Integer.MAX_VALUE);
			par.put(v,-1);
    	}
    	if(gr.containsKey(w))
    		gr.get(w).add(v);
    	else
    	{
    		gr.put(w,new Vector<Integer>());
    		gr.get(w).add(v);
			dist.put(w,Integer.MAX_VALUE);
			par.put(w,-1);
    	}
    }
    public String BFS(int s,int dest)
    {
    	try
    	{
    	Map<Integer, Boolean> vis = new HashMap<Integer,Boolean>(); 
  		for (Map.Entry<Integer,Integer> entry : dist.entrySet())
        {
            dist.put(entry.getKey(),Integer.MAX_VALUE);
            par.put(entry.getKey(),-1);
            vis.put(entry.getKey(),false);
        }
    	// System.out.println(gr.get(s));
        Queue<Integer> q = new LinkedList<Integer>(); 
        vis.put(s, true); 
        q.add(s); 
        dist.put(s,0);
        while (q.size() != 0) 
        { 
            s = q.poll(); 
            // System.out.print(s+" ");
            Iterator<Integer> i = gr.get(s).iterator(); 
            while (i.hasNext()) 
            { 
                int n = i.next(); 
                if (!vis.get(n)) 
                { 
                    vis.put(n, true); 
                    // System.out.println(dist.get(s) + 1);
                    dist.put(n,dist.get(s) + 1);
                    par.put(n,s);
                    q.add(n); 
                    if(n == dest)
                    {
                    	// g1.sendMessage(Integer.toString(dist.get(n)));
                    	return Integer.toString(dist.get(n));
                    }
                } 
            } 
        }
        return Integer.toString(-1);
        // g1.sendMessage(Integer.toString(-1));
    	}
    	catch(Exception e){
    			return Integer.toString(-1);
    			// g1.sendMessage(Integer.toString(-1));}
    	}
    }
    public String getGraph()
    {
    	try{
    		String output = "";
    	Map<Integer, Boolean> vis = new HashMap<Integer,Boolean>();
    	// g1.sendMessage("Vertices: ");
    	output += "\nVertices: ";
    	for (Map.Entry<Integer, Vector<Integer>> entry : gr.entrySet())
    	{
            vis.put(entry.getKey(),false);
            output += Integer.toString(entry.getKey())+" ";
            // g1.sendMessage(Integer.toString(entry.getKey())+" ");
    	}
    	// g1.sendMessage("");
    	output += "\nEdges: \n";
    	// g1.sendMessage("Edges: ");
        for (Map.Entry<Integer, Vector<Integer>> entry : gr.entrySet())
        {
        	Vector<Integer> tmp = entry.getValue();
        	Iterator<Integer> it = tmp.iterator();
        	vis.put(entry.getKey(),true);
        	// System.out.println(entry.getKey());
        	while(it.hasNext())
        	{
        		int n = it.next();
        		if(!vis.get(n))
        		{
        			vis.put(n, true);
        			output += Integer.toString(entry.getKey())+" "+n+"\n";
        			// g1.sendMessage(Integer.toString(entry.getKey())+" "+n);
        		}
        	}
        }
        return output;
    	}
    	catch(Exception e){return "exception";}
    }
} 