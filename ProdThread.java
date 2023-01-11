import java.rmi.RemoteException;

public class ProdThread extends Thread {
	Buffer b;
	public ProdThread(Buffer b) {
		this.b=b;
	}
	
	public void run() {
		
		for(int i=0;i<10000;i++) {
			try {
				b.put(1);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }
	}

}
