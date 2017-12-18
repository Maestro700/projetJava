package model;

import java.awt.Rectangle;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe génère les tuyaux.
 */
public class Tuyau extends Objet{
	
	/**
	 * Cette méthode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pièce.
	 * @param y donne la position en y de la pièce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public Tuyau(int x, int y, String str) {
		super(x, y, str);
		super.hauteur=65;
		super.largeur=43;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
