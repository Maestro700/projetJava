package model;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.Objet;

public class Mario extends Personnage{
	
	private int nbSaut;
	private int TempsSaut;
	private int YSolCurrent;
	private boolean isSaut;
	private boolean fin;
	private int score;
	private boolean running;
	private static int nbJoueur;

	public Mario(int x, int y, String str) {
		super.x= x;
		super.dx=0;
		super.y= y;
		super.speed= 2;
		super.collision=false;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.isVivant=true;
		super.HP=3;
		super.largeur=28;
		super.hauteur=50;
		this.TempsSaut=0;
		this.nbSaut= 0;
		this.fin=false;
		this.YSolCurrent=320;
		this.isSaut=false;
		this.score=0;
		this.running=false;
		this.nbJoueur=1;
	}
	
	@Override
	public void avancer(int dx) {
		this.dx=dx*speed;
		if(this.checkCollision()==false || this.YSolCurrent<320) {
			this.x+=dx*speed;
		}
		setChanged();
		notifyObservers();
	}
	
	public void saut(int dy) { 
		if(this.nbSaut==0 && isSaut==true) {
			if(this.TempsSaut==0) {
				Son saut = new Son("/son/saut.mp3");
			}
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
	public void collison(ArrayList<Objet> obj) {
		super.hitBox= new Rectangle(this.x+dx, this.y, this.largeur, this.hauteur);
		for(int i=0; i<super.getTabObjSize(); i++) {
			if (this.hitBox.intersects(obj.get(i).hitBox)) {
				if(obj.get(i).getClass().getName()=="model.Piece") {
					Piece piece= (Piece) obj.get(i);
					if(piece.isEstRamasse()==false) {
						Son sonPiece = new Son("/son/piece.mp3");
						piece.setEstRamasse(true);
						this.score+=100;
					}
				}
				else {
					this.isCollision[i]=true;
					if((this.y+this.hauteur/2+20)<obj.get(i).y) {
						this.nbSaut=0;
						this.YSolCurrent=obj.get(i).y-this.hauteur;
					}
					else if(obj.get(i).getClass().getName()=="model.DrapeauFin") {
						this.fin=true;
						this.running=false;
					}
					else {
			 			this.nbSaut=1;
					}
				}
			}
			else {
				if(obj.get(i).getClass().getName()!="model.Piece") {
					if(this.checkCollision()==false){
						this.YSolCurrent=320;
					}
				}
				this.isCollision[i]=false;
			}
		}
		notifyObservers();
	}
	
	public String toString() {
		return ""+this.score;	
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static int getNbJoueur() {
		return nbJoueur;
	}

	public static void setNbJoueur(int nbJoueur) {
		Mario.nbJoueur = nbJoueur;
	}
}
