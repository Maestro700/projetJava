package model;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.Objet;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe crée un joueur.
 */
public class Mario extends Personnage{
	
	//Cette variable nous donne le chiffre, "id", du joueur.
	private int nbJoueur;
	//Cette variable permet de ne pas spamer le touche espace en limitant le nombre de saut.
	private int nbSaut;
	//Cette variable est un compteur qui, en fonction de celui-ci, gère les différentes "étapes" du saut.
	private int TempsSaut;
	//Cette variable récupère la coordonnée en y du personnage.
	private int YSolCurrent;
	//Cette variable nous dit s'il on peut sauter ou pas. Cela évite que s'il on spam la touche pour sauter, le personnage s'envole.
	private boolean isSaut;
	//Cette variable permet de savoir si le niveau actuel a fini.
	private boolean fin;
	//Cette variable récupère le score de la partie.
	private static int score;
	//Cette variable gère l'activité de tout les threads du code.
	private static boolean running;
	//Cette variable gère le nombre d'HP du joueur.
	private static int HP;
	//Cette variable nous permet de compter le nombre de mort.
	private static int nbMort;
	//Cette variable permet de savoir si le joueur a appuier sur une touche.
	private boolean [] touches;
	//cette variable retient la denière touche pressée.
	private boolean lastKeypressed;
	//Cette variable est un compteur pour le déplacement en console.
	private int compteurDx;
	
	/**
	 * Cette méthode est le constructeur de la classe et instancie toutes les variables de la classe.
	 * @param x donne la position initiale en x du joueur.
	 * @param y donne la position initiale en y du joueur.
	 * @param str donne l'image initiale du joueur.
	 */
	public Mario(int x, int y, String str) {
		super.x= x;
		super.dx=0;
		super.y= y;
		super.fps=10;
		super.speed= 2;
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
		nbMort=0;
		running=false;
		this.nbJoueur=1;
		this.touches= new boolean [3];
		for(int i=0; i<=2; i++) {
			touches[i]=false;
		}
		this.lastKeypressed=false;
		this.compteurDx=0;
	}
	
	/**
	 * Cette méthode permet de déplacer le joueur sur l'axe des x.
	 * @param dx récupère le déplacement en x.
	 */
	@Override
	public void avancer(int dx) {
		this.compteurDx+=dx;
		if(compteurDx%10==0) {
			this.xCase+=dx;
		}
		this.dx=dx*speed;
		if(this.checkCollision()==false || this.YSolCurrent<320) {
			if(this.x+dx>=0) {
				this.x+=dx*speed;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Cette méthode déplace le joueur sur l'axe des y.
	 * @param dy récupère le déplacement en y.
	 */
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
	
	/**
	 * Cette méthode gère les collsions entre le ou les joueurs et les objets.
	 */
	@Override
	public void collison(ArrayList<Objet> obj) {
		super.hitBox= new Rectangle(this.x+dx, this.y, this.largeur, this.hauteur);
		for(int i=0; i<obj.size(); i++) {
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
					this.getIsCollision().set(i, true);
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
						if(this.YSolCurrent<320 && this.isSaut==false) {
							this.nbSaut=1;
						}
						this.YSolCurrent=320;
					}
				}
				this.getIsCollision().set(i, false);
			}
		}
		notifyObservers();
	}
	
	/*****GETTERS*****/
	public boolean isSaut() {
		return isSaut;
	}
	
	public static int getHP() {
		return HP;
	}
	
	public int getNbSaut() {
		return nbSaut;
	}
	
	public boolean isFin() {
		return fin;
	}
	
	public static boolean isRunning() {
		return running;
	}
	
	public static int getScore() {
		return score;
	}
	
	public int getNbJoueur() {
		return nbJoueur;
	}

	public boolean[] getTouches() {
		return touches;
	}
	
	public boolean isLastKeypressed() {
		return lastKeypressed;
	}
	
	public static int getNbMort() {
		return nbMort;
	}
	
	/*****SETTERS*****/
	public void setSaut(boolean isSaut) {
		this.isSaut = isSaut;
	}

	public void setNbSaut(int nbSaut) {
		this.nbSaut = nbSaut;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public static void setScore(int score) {
		Mario.score = score;
	}
	public static void setRunning(boolean running) {
		Mario.running = running;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	public void setTouches(boolean touches, int pos) {
		this.touches[pos] = touches;
	}

	public void setLastKeypressed(boolean lastKeypressed) {
		this.lastKeypressed = lastKeypressed;
	}

	public static void setHP(int hP) {
		Mario.HP = hP;
	}

	public static void setNbMort(int nbMort) {
		Mario.nbMort = nbMort;
	}
}
