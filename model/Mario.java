package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Objet;

public class Mario extends Personnage{
	
	private Image img;
	private int nbSaut;
	private int TempsSaut;
	private int YSolCurrent;
	private boolean isSaut;

	public Mario(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed= 3;
		super.collision=false;
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		this.TempsSaut=0;
		this.nbSaut= 0;
		this.YSolCurrent=320;
		this.isSaut=false;
	}
	
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	@Override
	public void avancer(int dx) {
		this.x=this.x+(dx*speed);
		setChanged();
		notifyObservers();
	}
	
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

