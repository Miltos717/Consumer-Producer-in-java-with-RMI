import java.io.IOException;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerBuffer {
	
	private final static int PORT = Registry.REGISTRY_PORT;
	private final static String HOST = "localhost";
	
	public static void main(String[] args) throws IOException, NotBoundException  {
		
		System.setProperty("java.rmi.server.hostname", HOST);
		
		Registry reg = LocateRegistry.createRegistry(PORT);
		
		Buffer buffer = new BufferImplements();
		
		String rmiObj = "Buffer";
		
		reg.rebind(rmiObj, buffer);			
		
		System.out.println("Press <Enter> for exit.");
		System.in.read();
		
		// Free space and clear rmi registry
		
		UnicastRemoteObject.unexportObject(buffer, true);
		reg.unbind(rmiObj);
		System.out.println("Remote object unbounded.");
	}
}
