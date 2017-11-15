package projet;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Personnage{
	protected int x;
	protected int y;
	protected Rectangle hitBox;
	protected float gravite;
	protected int speed;
	protected int HP;
	
	protected abstract boolean estAuSol(Objet obj);
	protected abstract void collison(Objet obj);
	protected abstract void avancer();
	
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
