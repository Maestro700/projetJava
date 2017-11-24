package model;

import java.util.Observable;

public abstract class Personnage extends Observable{
	protected int x;
	protected int y;
	protected int speed;
	
	protected abstract void avancer(int dx);
	
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