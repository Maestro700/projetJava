package model;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.Objet;

public class Mario extends Personnage{
	
	private int nbJoueur;
	private int nbSaut;
	private int TempsSaut;
	private int YSolCurrent;
	private boolean isSaut;
	private boolean fin;
	private static int score;
	private static boolean running;
	private static int HP;
	private boolean [] touches;
	private boolean lastKeypressed;
	private int compteurDx;

	public Mario(int x, int y, String str) {
		super.x= x;
		super.dx=0;
		super.y= y;
		super.fps=10;
		super.speed= 2;
		super.collision=false;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.isVivant=true;
		super.largeur=28;
		super.hauteur=50;
		super.xCase=5;
		this.TempsSaut=0;
		this.nbSaut= 0;
		this.fin=false;
		this.YSolCurrent=320;
		this.isSaut=false;
		score=0;
		HP=3;
		running=false;
		this.nbJoueur=1;
		this.touches= new boolean [3];
		for(int i=0; i<=2; i++) {
			touches[i]=false;
		}
		this.lastKeypressed=false;
		this.compteurDx=0;
	}
	
	@Override
	public void avancer(int dx) {
		this.compteurDx+=dx;
		if(compteurDx%10==0) {
			this.xCase+=dx;
		}
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
						score+=100;
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
						running=false;
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

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Mario.score = score;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void setRunning(boolean running) {
		Mario.running = running;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	public boolean[] getTouches() {
		return touches;
	}

	public void setTouches(boolean touches, int pos) {
		this.touches[pos] = touches;
	}

	public boolean isLastKeypressed() {
		return lastKeypressed;
	}

	public void setLastKeypressed(boolean lastKeypressed) {
		this.lastKeypressed = lastKeypressed;
	}
	
	public static int getHP() {
		return HP;
	}

	public static void setHP(int hP) {
		Mario.HP = hP;
	}
	
	
}
