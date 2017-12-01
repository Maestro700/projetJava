package model;

import java.awt.Rectangle;

public class Tuyau extends Objet{
	
	public Tuyau(int x, int y, String str) {
		super(x, y, str);
		super.hauteur=65;
		super.largeur=43;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
