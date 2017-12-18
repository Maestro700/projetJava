package model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe crée tou les objets du jeu.
 */
public class Objet {
	//Cette variable donne la position en x de l'objet.
	protected int x;
	//Cette variable donne la position en y de l'objet.
	protected int y;
	//Cette variable donne la hitBox de l'objet.
	protected Rectangle hitBox;
	//Cette variable donne la hauteur de l'objet pour la hitBox.
	protected int hauteur;
	//Cette variable donne la largeur de l'objet pour la hitBox.
	protected int largeur;
	//Cette variable donne l'image de l'objet.
	protected Image img;
	//Cette variable donne le chemin pour avoir l'image.
	protected String str;
	
	/**
	 * Cette m"thode est le constructeur de la classe, elle instancie toute les variables.
	 * @param x donne la position de l'objet.
	 * @param y donne la position de l'objet.
	 * @param str donne le chemin pour avoir l'image.
	 */
	public Objet(int x, int y, String str) {
		this.x=x;
		this.y=y;
		this.str=str;
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
	}
	
	/*****GETTERS*****/
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public Image getImg() {
		return img;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public String getStr() {
		return str;
	}
	
	/*****SETTERS*****/
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
}
