package model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe fait la description de tous les objets dans le jeu (tuyau, bloc,...)
 *
 */
public class Objet {
	protected int x;
	protected int y;
	protected Rectangle hitBox;
	protected int hauteur;
	protected int largeur;
	protected Image img;
	protected String str;
	
	/**
	 * Constructeur qui permet de créer un objet
	 * @param x, position de l'objet sur l'abscisse de l'interface 
	 * @param y, position de l'objet sur l'ordonnée de l'interface
	 * @param str, contient le lien de l'image de l'objet
	 */
	public Objet(int x, int y, String str) {
		this.x=x;
		this.y=y;
		this.str=str;
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
	}
	
	//Getter et Setter

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
}
