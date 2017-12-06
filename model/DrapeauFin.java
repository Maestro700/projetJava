package model;

import java.awt.Rectangle;

public class DrapeauFin extends Objet{

	public DrapeauFin(int x, int y, String str) {
		super(x, y, str);
		super.largeur=10;
		super.hauteur=180;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
