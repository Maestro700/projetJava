package model;

import java.awt.Rectangle;

import java.util.ArrayList;
import model.Objet;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet d'avoir une structure commune pour tout les ennemis.
 */
public class Ennemi extends Personnage{
	
	/**
	 * Cette méthode est le constructeur de cette classe et instancie des variables de la classe Personnage.
	 */
	public Ennemi() {
		super.isVivant=true;
		super.fps=30;
		super.dx=1;
	}
	
	/**
	 * Cette méthode déplace les ennemis.
	 * @param dx récupère le déplacement en x. 
	 */
	@Override
	public void avancer(int dx) {
		this.x+=(dx*speed);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Cette méthode gère les collisions entre les ennemis et les objets.
	 */
	@Override
	public void collison(ArrayList<Objet> obj) {
		super.hitBox= new Rectangle(this.x+dx, this.y, this.largeur, this.hauteur);
		for(int i=0; i<obj.size(); i++) {
			if (this.hitBox.intersects(obj.get(i).hitBox)) {
				this.isCollision[i]=true;
				if(this.x<(obj.get(i).x-obj.get(i).largeur/2)) {
					this.dx=-1;
				}
				else if(this.x>(obj.get(i).x+obj.get(i).largeur/2)) {
					this.dx=1;
				}
			}
			else {
				this.isCollision[i]=false;
			}
		}
		notifyObservers();
	}
	
	/*****GETTERS*****/
	public int getDx() {
		return dx;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	/*****SETTERS*****/
	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
}
