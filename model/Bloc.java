package model;

import java.awt.Rectangle;

public class Bloc extends Objet{
	
	public Bloc(int x, int y, String str) {
		super(x, y, str);
		super.hauteur=30;
		super.largeur=30;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
