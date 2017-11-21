package model;

import java.awt.Rectangle;

public class Bloc extends Objet{

	public Bloc(int x, int y, String str) {
		super(x, y, str);
		super.hitBox= new Rectangle(x, y, 30, 30);
	}
}
