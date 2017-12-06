package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Observable;

import javax.swing.ImageIcon;

import model.Objet;

public abstract class Personnage extends Observable{
	protected int x;
	protected int y;
	protected int speed;
	protected int HP;
	protected boolean collision;
	protected Image img;
	protected Rectangle hitBox;
	protected boolean isVivant;
	protected boolean collisionDirection []= new boolean [4];
	
	public abstract void collison(Objet obj);
	
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}
	
	public void avancer(int dx) {
		this.x+=(dx*speed);
		setChanged();
		notifyObservers();
	}
	
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
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public boolean isVivant() {
		return isVivant;
	}

	public void setVivant(boolean isVivant) {
		this.isVivant = isVivant;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}
}
