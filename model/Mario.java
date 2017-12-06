package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Objet;

public class Mario extends Personnage{
	
	private int nbSaut;
	private int TempsSaut;
	private int YSolCurrent;
	private boolean isSaut;
	private boolean fin;

	public Mario(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed= 2;
		super.collision=false;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.isVivant=true;
		super.HP=3;
		this.TempsSaut=0;
		this.nbSaut= 0;
		this.fin=false;
		this.YSolCurrent=320;
		this.isSaut=false;
	}
	
	public void saut(int dy) {
		if(this.nbSaut==0 && isSaut==true) {
			if(this.TempsSaut<=15) {
				this.TempsSaut++;
				this.y-=dy*speed;
			}
			else if(this.TempsSaut>15 && this.TempsSaut<25) {
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
	
	@Override
	public void collison(Objet obj) {
		super.hitBox= new Rectangle(this.x, this.y, 28, 50);
		if(this.hitBox.intersects(obj.hitBox)) {
			if(obj.getClass().getName()=="model.DrapeauFin") {
				this.fin=true;
				notifyObservers();
			}
			else {
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
		}
		else {
			this.YSolCurrent=320;
		}
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

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}
}

