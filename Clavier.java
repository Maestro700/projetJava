package projet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener{
	
	private int dx;
	private int dy;
	
	public Clavier() {
		this.dx=0;
		this.dy=0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE : 
				inter.mario.setSaut(true); 
				this.dy=1;
				break;
			case KeyEvent.VK_LEFT : 
				inter.mario.setStr("marioMarcheGauche.png");
				this.dx=-1;
				break;
			case KeyEvent.VK_RIGHT : 
				inter.mario.setStr("marioMarcheDroite.png"); 
				this.dx=1; 
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { 
			this.dy=-4; 
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) { 
			this.dx=0; 
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	//*****GETTERS*****
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	//*****SETTERS*****
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
}
