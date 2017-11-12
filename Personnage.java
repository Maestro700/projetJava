package projet;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Personnage{
	protected int x;
	protected int y;
	protected float gravite;
	protected int speed;
	protected int HP;
	
	protected abstract boolean estAuSol(Objet obj);
	protected abstract boolean collison(Objet obj);
	protected abstract void avancer();
	protected abstract void reculer();
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
