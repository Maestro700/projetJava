package model;

import javax.swing.ImageIcon;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe g�n�re les koopas.
 */
public class koopa extends Ennemi{
	
	/**
	 * Cette m�thode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pi�ce.
	 * @param y donne la position en y de la pi�ce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public koopa(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed=5;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.largeur=27;
		super.hauteur=30;
	}
}
