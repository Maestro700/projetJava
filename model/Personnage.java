package model;

import java.awt.Rectangle;
import java.util.Observable;

import model.Objet;

public abstract class Personnage extends Observable{
	protected int x;
	protected int y;
	protected int speed;
	protected Rectangle hitBox;
	protected int HP;
	protected boolean collision;
	
	protected abstract void avancer(int dx);
	protected abstract void collison(Objet obj);
	
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
	
	public boolean isCollision() {
		return collision;
	}
	
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
