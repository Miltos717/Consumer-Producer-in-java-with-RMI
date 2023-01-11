import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientBuffer {
	
	private final static int PORT = Registry.REGISTRY_PORT;
	private final static String HOST = "localhost";
	
	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		
		Registry reg = LocateRegistry.getRegistry(HOST,PORT);
		
		String rmiObj = "Buffer";
		
		Buffer ref = (Buffer)reg.lookup(rmiObj);
		
		// we create 3 threads for produce and 2 for consume
		int noProds = 3;
            int noCons = 2;
        	

		ConThread cons[] = new ConThread[noCons];
		ProdThread prod[] = new ProdThread[noProds];


		
		// Producer threads
		// we create the threads and start them

        	for (int i=0; i<noProds; i++) {
			prod[i] = new ProdThread(ref);
			prod[i].start();
		}

		// Consumer threads
		// we create the threads and start them

         	for (int j=0; j<noCons; j++) {
        	 cons[j] = new ConThread(ref);
        	 cons[j].start();
       	}
	}
}
