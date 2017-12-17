package model;

import java.awt.Rectangle;
import java.util.ArrayList;
import model.Objet;

public class Ennemi extends Personnage{
	
	public Ennemi() {
		super.isVivant=true;
		super.fps=30;
		super.dx=1;
	}
	
	@Override
	public void avancer(int dx) {
		this.x+=(dx*speed);
		setChanged();
		notifyObservers();
	}
	
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
