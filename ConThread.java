import java.rmi.RemoteException;

public class ConThread extends Thread {
	
	Buffer b;
	public ConThread(Buffer b) {
		this.b=b;
	}
	
	public void run() {
		int value;
		for(int i=0;i<10000;i++) {
			try {
				value = b.get();
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

