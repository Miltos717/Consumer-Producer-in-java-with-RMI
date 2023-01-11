import java.rmi.RemoteException;
import java.rmi.*;
public interface Buffer extends Remote {
	
	// remote method signature
	public int get() throws RemoteException;
	public void put(int data) throws RemoteException;

}
