package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Objet;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 *Classe qui va gérer le personnage Mario
 */
public class Mario extends Personnage{
	
	private String str;
	private Image img;
	private int nbSaut;
	private int TempsSaut;
	private int YSolCurrent;
	private boolean isSaut;
	
	/**
	 * Constructeur qui crée Mario et ses differents mouvements
	 * C'est à dire le saut, les collisions et les déplacments
	 * @param x, position actuelle de mario sur l'abscisse de l'inertface
	 * @param y, position actuelle de mario sur l'ordonnée de l'inertface
	 */

	public Mario(int x, int y) {
		super.x= x;
		super.y= y;
		super.speed= 3;
		super.collision=false;
		this.str="marioMarcheDroite.png";
		this.img=new ImageIcon(getClass().getResource("/images/"+this.str)).getImage();
		this.TempsSaut=0;
		this.nbSaut= 0;
		this.YSolCurrent=320;
		this.isSaut=false;
	}
	
	/**
	 * Methode qui va contenir le lien de l'image Mario et le changer lors des differents mouvements
	 * @param str, contient l'image
	 */
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}
	

	@Override
	/**
	 * Méthode qui va faire avancer ou reculer Mario sur l'interface avec un deplacment de dx pixel
	 * @param dx, déplacement d'un certain nombre de pixel
	 */
	public void avancer(int dx) {
		this.x=this.x+(dx*speed);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Méthode qui va permettre de faire sauter le mario et de revenir sur le sol via un compteur TempsSaut
	 * et  un maximum de nombre de saut que nous pouvons réaliser
	 * @param dy, déplacement de Mario vers le haut
	 */
	public void saut(int dy) {
		if(this.nbSaut==0 && isSaut==true) {
			if(this.TempsSaut<25) {
				this.TempsSaut++;
				this.y-=dy;
			}
			else {
				this.nbSaut=1;   
				this.isSaut=false;
			}
			setChanged();
			notifyObservers();
		}
		else {
			if(this.y<this.YSolCurrent) {
				this.y+=dy;
				this.TempsSaut=0;
			}
			else {
				this.nbSaut=0;
			}
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Methode qui va gérer les collisions via une hitbox des differents objets
	 */
	public void collison(Objet obj) {
		super.hitBox= new Rectangle(this.x, this.y, 28, 50);
		if(this.hitBox.intersects(obj.hitBox)) {
			this.collision = true;
			if(this.x<(obj.x-obj.largeur/2)) {
				this.x-=1;
			}
			else if(this.x>(10+obj.x+obj.largeur/2)) {
				this.x+=1;
			}
			else if(this.y<(obj.y-obj.hauteur/2)) {
	 			this.y-=1;
	 			this.nbSaut=0;
				this.YSolCurrent=(obj.y-(obj.hauteur/2));
			}
			else if(this.y>(obj.y+obj.hauteur/2)) {
	 			this.y+=1;
			}
			setChanged();
			notifyObservers();
		}
		else {
			this.collision = false;
			this.YSolCurrent=320;
		}
	}
	
	
	//Getter et Setter


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
	
	public boolean isSaut() {
		return isSaut;
	}

	public void setSaut(boolean isSaut) {
		this.isSaut = isSaut;
	}

	public int getNbSaut() {
		return nbSaut;
	}

	public void setNbSaut(int nbSaut) {
		this.nbSaut = nbSaut;
	}
}
