package model;

import java.awt.Rectangle;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe g�n�re les tuyaux.
 */
public class Tuyau extends Objet{
	
	/**
	 * Cette m�thode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pi�ce.
	 * @param y donne la position en y de la pi�ce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public Tuyau(int x, int y, String str) {
		super(x, y, str);
		super.hauteur=65;
		super.largeur=43;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
	}
}
