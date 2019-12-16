import java.rmi.RemoteException;
import java.io.IOException;

public interface GraphsInterface extends java.rmi.Remote {
    public String doBFS(int u,int v, String gname) throws RemoteException ;
    public String traverse(String gname) throws RemoteException ;
    public void addingEdge(int u,int v, String gname) throws RemoteException ;
}
