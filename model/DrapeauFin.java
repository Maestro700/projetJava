package model;

import java.awt.Rectangle;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe g�n�re le drapeau de fin.
 */
public class DrapeauFin extends Objet{
	
	/**
	 * Cette m�thode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pi�ce.
	 * @param y donne la position en y de la pi�ce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public DrapeauFin(int x, int y, String str) {
		super(x, y, str);
		super.largeur=10;
		super.hauteur=180;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
