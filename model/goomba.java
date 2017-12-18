package model;

import javax.swing.ImageIcon;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe génère les goombas.
 */
public class goomba extends Ennemi{
	
	/**
	 * Cette méthode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pièce.
	 * @param y donne la position en y de la pièce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public goomba(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed=4;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.largeur=43;
		super.hauteur=50;
	}
}
