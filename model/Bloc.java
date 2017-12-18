package model;

import java.awt.Rectangle;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe genère les blocs.
 */
public class Bloc extends Objet{
	
	/**
	 * Cette méthode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pièce.
	 * @param y donne la position en y de la pièce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public Bloc(int x, int y, String str) {
		super(x, y, str);
		super.hauteur=30;
		super.largeur=30;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
