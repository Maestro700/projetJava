package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;

import model.Objet;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe crée tout les personnages du jeu et leur attribut des variables communes.
 */
public abstract class Personnage extends Observable{
	
	//Cette variable donne la position en x du personnage.
	protected int x;
	//Cette variable donne le déplacement en x.
	protected int dx;
	//Cette variable donne la position en y du personnage.
	protected int y;
	//Cette variable donne le vitesse du personnage.
	protected int speed;
	//Cette variable donne l'image du personnage.
	protected Image img;
	//Cette variable donne la hitBox du personnage.
	protected Rectangle hitBox;
	//Cette variable nous dit si le le personnage est en vie.
	protected boolean isVivant;
	//Cette variable donne la largeur du personnage pour la hitBox.
	protected int largeur;
	//Cette variable donne la hauteur du personnage pour la hitBox.
	protected int hauteur;
	//Cette variable gère toute les collsions des objets et les placent dans le tableau.
	protected ArrayList <Boolean> isCollision= new ArrayList<Boolean>();
	//Cette variable donne la constante pour connaitre le temps a mettre dans le sleep des threads qui gèrent le déplacement.
	protected int fps;
	//Cette variable donne la position en x dans la console.
	protected int xCase;
	
	/**
	 * Cette méthode gère les collisions avec les objets.
	 * @param obj tableau de tout les objets du niveau.
	 */
	public abstract void collison(ArrayList<Objet> obj);
	
	/**
	 * Cette méthode gère le déplacement des personnages.
	 * @param dx récupère le déplacement.
	 */
	public abstract void avancer(int dx);
	
	/**
	 * Cette méthode permet de changer l'image du personnage.
	 * @param str chemin ou se trouve l'image.
	 */
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}
	
	/**
	 * Cette méthode parcout le tableau qui contient les collisions.
	 * @return renvoie true si il y en a un a true, au sinon renvoie false.
	 */
	public boolean checkCollision() {
		if(isCollision.contains(true)) {
			return true;
		}
		return false;
	}
	
	/*****GETTERS*****/
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public Image getImg() {
		return img;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public boolean isVivant() {
		return isVivant;
	}
	
	public int getFps() {
		return fps;
	}
	
	public int getxCase() {
		return xCase;
	}
	
	public int getDx() {
		return dx;
	}
	
	/*****SETTERS*****/
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public void setVivant(boolean isVivant) {
		this.isVivant = isVivant;
	}
	
	public void setFps(int fps) {
		this.fps = fps;
	}
	
	public void setxCase(int xCase) {
		this.xCase = xCase;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}

	public ArrayList<Boolean> getIsCollision() {
		return isCollision;
	}

	public void setIsCollision(ArrayList<Boolean> isCollision) {
		this.isCollision = isCollision;
	}
}
