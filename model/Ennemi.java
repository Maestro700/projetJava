package model;

import java.awt.Rectangle;

public class Ennemi extends Personnage{
	
	protected int dx;
	protected int largeur;
	protected int hauteur;
	
	public Ennemi() {
		super.isVivant=true;
		this.dx=1;
	}

	@Override
	public void collison(Objet obj) {
		super.hitBox= new Rectangle(this.x, this.y, this.largeur, this.hauteur);
		if(this.hitBox.intersects(obj.hitBox)) {
			if(this.x<(obj.x-obj.largeur/2)) {
				this.dx=-1;
			}
			else if(this.x>(obj.x+obj.largeur/2)) {
				this.dx=1;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
}
