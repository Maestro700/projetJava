package projet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class deplacement implements KeyListener{
	
	private int dx =0;
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT : this.dx=-1; break;
		case KeyEvent.VK_RIGHT : this.dx=1; break;
		}
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		 this.dx=0;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}	
