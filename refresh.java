package projet;

public class refresh implements Runnable{
	
	private int tauxRefresh = 40;

	@Override
	public void run() {
		while(true) {
			test.scene.repaint();
			try {
				Thread.sleep(tauxRefresh);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
