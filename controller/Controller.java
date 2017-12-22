package controller;

import java.util.ArrayList;

import model.Ennemi;
import model.Mario;
import model.Piece;
import model.Son;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe gère tout les déplacements des entités, les actions spécifique dans le jeu et certaines collisions.
 */
public class Controller{
	
	//Cette variable récupère tout les joueurs présnet dans la partie.
	private ArrayList <Mario> model;
	//Cette variable récupère l'instance de l'interface graphique.
	private VueGUI vue;
	//Cette variable récupère l'instance de l'interface console.
	private VueConsole console;
	//Cette variable permet de lancer un compteur qui gère les images du jeu.
	private int compteur;
	//Cette variable gère le déplacement en y dans la console.
	private int dyCon;
	//Cette variable gère le déplacement en x dans la console.
	private int dxCon;
	
	/**
	 * Cette méthode est le constructeur de la classe Controller.
	 * @param model récupère le modèle.
	 */
	public Controller(ArrayList <Mario> model) {
		this.model = model;
		this.compteur=0;
		this.dxCon=0;
		this.dyCon=0;
	}
	
	/**
	 * Cette méthode récupère l'instance de l'interface graphique.
	 * @param vue l'instance de l'interface graphique.
	 */
	public void addViewGUI(VueGUI vue) {
		this.vue = vue;
	}
	
	/**
	 * Cette méthode récupère l'instance de l'interface console.
	 * @param vue l'instance de l'interface console.
	 */
	public void addViewConsole(VueGenerale vue) {
		this.console = (VueConsole) vue;
	}
	
	/**
	 * Cette méthode gère le déplacement du ou des joueurs et leurs images.
	 */
	public void moveMario(){
		Thread move= new Thread(new Runnable(){
			public void run() {
					while(Mario.isRunning()){
						compteur++;
						for(int i=0; i < model.size(); i++) {
							if(model.get(i).getTouches()[0] == true) {
								model.get(i).avancer(1);
								if(compteur%2==0) {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioMarcheDroite.png");
									}
									else {
										model.get(i).changeImg("luigiMarcheDroite.png");
									}
								}
								else {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioArretDroite.png");
									}
									else {
										model.get(i).changeImg("luigiArretDroite.png");	
									}
								}
							}
							else {
								if(model.get(i).isLastKeypressed()==true) {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioArretDroite.png");
									}
									else {
										model.get(i).changeImg("luigiArretDroite.png");	
									}
								}	
							}	
							if(model.get(i).getTouches()[1] == true) {
								model.get(i).avancer(-1);
								if(compteur%2==0) {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioMarcheGauche.png");
									}
									else {
										model.get(i).changeImg("luigiMarcheGauche.png");	
									}
								}
								else {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioArretGauche.png");
									}
									else {
										model.get(i).changeImg("luigiArretGauche.png");	
									}
								}
							}	
							else {
								if(model.get(i).isLastKeypressed()==false) {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioArretGauche.png");
									}
									else {
										model.get(i).changeImg("luigiArretGauche.png");	
									}
								}
							}
							if(model.get(i).getTouches()[2] == true) {
								if(model.get(i).getTouches()[0] == true || model.get(i).isSaut()==false) {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioSautDroite.png");
									}
									else {
										model.get(i).changeImg("luigiMarcheDroite.png");	
									}
								}
								else {
									if(model.get(i).getNbJoueur()==1) {
										model.get(i).changeImg("marioSautGauche.png");
									}
									else {
										model.get(i).changeImg("luigiMarcheGauche.png");	
									}
								}
								model.get(i).saut(4);
								model.get(i).setSaut(true);
							}
							else {
								model.get(i).saut(4);
								model.get(i).setSaut(false);
							}
							try {
								Thread.sleep(fps(model.size(), model.get(0).getFps()));
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
		});
		move.start();
	}
	
	/**
	 * Cette méthode gère le déplacement des ennemis et leurs images.
	 */
	public void moveEnnemi() {
		Thread move= new Thread(new Runnable() {
			public void run() {
					while(Mario.isRunning()) {
						for(int i=0; i<vue.getTabEnnemi().size();i++) {
						if(vue.getTabEnnemi().get(i).isVivant()==true) {
							if(vue.getTabEnnemi().get(i).getDx()==-1) {
									if(vue.getTabEnnemi().get(i).getClass().getName()=="model.koopa") {
										if(compteur%2==0) {
											vue.getTabEnnemi().get(i).changeImg("champMarcheGauche.png");
										}
										else {
											vue.getTabEnnemi().get(i).changeImg("champArretGauche.png");
										}
									}
									else {
										if(compteur%2==0) {
											vue.getTabEnnemi().get(i).changeImg("tortueMarcheGauche.png");
										}
										else {
											vue.getTabEnnemi().get(i).changeImg("tortueArretGauche.png");
										}
									}
							}
							else {
								if(vue.getTabEnnemi().get(i).getClass().getName()=="model.koopa") {
									if(compteur%2==0) {
										vue.getTabEnnemi().get(i).changeImg("champMarcheDroite.png");
									}
									else {
										vue.getTabEnnemi().get(i).changeImg("champArretDroite.png");
									}
								}
								else {
									if(compteur%2==0) {
										vue.getTabEnnemi().get(i).changeImg("tortueMarcheDroite.png");
									}
									else {
										vue.getTabEnnemi().get(i).changeImg("tortueArretDroite.png");
									}
								}
							}
							vue.getTabEnnemi().get(i).avancer(vue.getTabEnnemi().get(i).getDx());
						}	
						try {
							Thread.sleep(fps(vue.getTabEnnemi().size(), vue.getTabEnnemi().get(0).getFps()));
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}	
			}
		});
		move.start();
	}
	
	/**
	 * Cette méthode permet d'avoir les mêmes FPS selon le nombre d'ennemi et de joueur.
	 * @param nb le nombre d'ennemi ou de joueur.
	 * @param fps le nombre de fps qui est constant (30).
	 * @return le nombre qui gère les "FPS" (qui se retrouve dans le sleep.
	 */
	public int fps(int nb, int fps) {
		return fps/nb;
	}
	
	/**
	 * Cette méthode gère les collsions des ennemis avec les joueurs.
	 * @param ennemi représente à chaque fois un ennemi.
	 */
	public void collisionEnnemi(Ennemi ennemi) {
		for(int i=0; i < model.size(); i++) {
			if(model.get(i).getHitBox().intersects(ennemi.getHitBox())) {
				if(model.get(i).getY()<(ennemi.getY())) {
					ennemi.setVivant(false);
					Son ecrase = new Son("/son/ecrasePersonnage.mp3");
					Mario.setScore(Mario.getScore()+200);
				}
				if(model.get(i).getX()<(ennemi.getX()-ennemi.getLargeur()/2)) {
					if(Mario.getHP()>1) {
						Mario.setHP(Mario.getHP()-1);
						model.get(i).setX(100);
						this.ennemiRevive();
					}
					else {
						model.get(i).setVivant(false);
					}
				}
				else if(model.get(i).getX()>(ennemi.getX()+ennemi.getLargeur()/2)) {
					if(Mario.getHP()>1) {
						Mario.setHP(Mario.getHP()-1);
						model.get(i).setX(100);
						this.ennemiRevive();
					}
					else {
						model.get(i).setVivant(false);
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode déplace mario "M" dans la console vers la droite.
	 */
	public void moveForward() {
		this.dxCon=1;
		if(this.collisionMarioConsole()==false) {
			model.get(0).avancer(10);
			for(int y=0; y<8; y++) {
				for(int x=0; x<78; x++) {
					if(console.getGrille()[x][y].equals("M")) {
						console.setGrille(x, y, "-");
						int newPos= x+1;
						console.setGrille(newPos, y, "M");
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode déplace mario "M" dans la console vers la gauche.
	 */
	public void moveBackward() {
		this.dxCon=-1;
		if(this.collisionMarioConsole()==false) {
			model.get(0).avancer(-10);
			for(int y=0; y<8; y++) {
				for(int x=0; x<78; x++) {
					if(console.getGrille()[x][y].equals("M")) {
						console.setGrille(x, y, "-");
						int newPos= x-1;
						console.setGrille(newPos, y, "M");
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode déplace mario "M" dans la console vers le haut.
	 */
	public void moveUp() {
		this.dyCon=-1;
		if(this.collisionMarioConsole()==false) {
			model.get(0).saut(20);
			model.get(0).setSaut(true);
			for(int y=0; y<8; y++) {
				for(int x=0; x<78; x++) {
					if(console.getGrille()[x][y].equals("M")) {
						console.setGrille(x, y, "-");
						int newPos= y-1;
						console.setGrille(x, newPos, "M");
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode déplace mario "M" dans la console vers le bas.
	 */
	public void moveDown() {
		this.dyCon=1;
		if(this.collisionMarioConsole()==false) {
			model.get(0).saut(-20);
			model.get(0).setSaut(true);
			for(int y=0; y<8; y++) {
				for(int x=0; x<78; x++) {
					if(y<6) {
						if(console.getGrille()[x][y].equals("M")) {
							console.setGrille(x, y, "-");
							int newPos= y+1;
							console.setGrille(x, newPos, "M");
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Cette méthode gère les collsions dans l'interface console.
	 * @param mario récupère le tableau des joueurs.
	 */
	public boolean collisionMarioConsole() {
		for(int y=0; y<8; y++) {
			for(int x=0; x<78; x++) {
				if(console.getGrille()[x][y].equals("M")) {
					if(console.getGrille()[x+dxCon][y+dyCon].equals("x")) {
						dxCon=0;
						dyCon=0;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Cette méthode vérifie si les ennemis rentrent en collision.
	 * @return true si ils vont rentrés en collision, au sinon false.
	 */
	public boolean collisionEnnemiConsole() {
		for(int y=0; y<8; y++) {
			for(int x=0; x<78; x++) {
				if(console.getGrille()[x][y].equals("E")) {
					if(console.getGrille()[x+dxCon][y+dyCon].equals("x")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Cette méthode déplace les ennemis "E" dans la console.
	 */
	public void moveEnnemiConsole() {
		Thread moveEnnemi= new Thread(new Runnable() {
			public void run() {
				for(int y=0; y<8; y++) {
					for(int x=0; x<78; x++) {
						while(true) {
							if(console.getGrille()[x][y].equals("E")) {
								console.setGrille(x, y, "-");
								int newPos= x+1;
								console.setGrille(newPos, y, "E");
							}
						}
					}
				}
			}
		});
		moveEnnemi.start();
	}
	
	/**
	 * Cette méthode permet à tout les ennmis d'être "réanimés".
	 */
	public void ennemiRevive() {
		for(int i=0; i<vue.getTabEnnemi().size();i++) {
			if(vue.getTabEnnemi().get(i).isVivant()==false) {
				Mario.setScore(Mario.getScore()-200);
			}
			vue.getTabEnnemi().get(i).setVivant(true);
		}
	}
	
	/**
	 * Cette méthode permet que losque l'on perd et que l'on appui sur le bouton adéquat de relancer le niveau.
	 */
	public void restart() {
		vue.setLevel(1);
		vue.createLevel();
		Mario.setRunning(true);
		for(int i=0; i < model.size(); i++) {
			model.get(i).setVivant(true);
			Mario.setHP(3);
			if(model.get(i).getNbJoueur()==1) {
				model.get(i).setX(100);
			}
			else {
				model.get(i).setX(50);
			}
			model.get(i).changeImg("marioMarcheDroite.png");
		}
		this.moveMario();
		this.moveEnnemi();
		vue.setChrono(60);
		vue.chrono();
		Mario.setNbMort(Mario.getNbMort()+1);
		this.ennemiRevive();
		for(int i=0; i< vue.getTab().size();i++) {
			if(vue.getTab().get(i).getClass().getName()=="model.Piece") {
				Mario.setScore(0);
				Piece piece= (Piece) vue.getTab().get(i);
				piece.setEstRamasse(false);
			}
		}
		vue.getConteneur().remove(vue.getRestart());
		vue.getSql().setNbMortDB(vue.getSql().getNbMortDB()+1);
		vue.getSql().update();
	}
	
	/**
	 * Cette méthode permet que lorsque l'on gagne le niveau et que l'on appui sur le bouton adéquat de lancer le niveau prochain.
	 */
	public void nextLevel() {
		if(vue.getLevel()<3) {
			vue.setLevel(vue.getLevel()+1);
			vue.createLevel();
		}
		Mario.setRunning(true);
		for(int i=0; i < model.size(); i++) {
			if(model.get(i).getNbJoueur()==1) {
				model.get(i).setX(100);
			}
			else {
				model.get(i).setX(50);
			}
			Mario.setHP(3);
			model.get(i).changeImg("marioMarcheDroite.png");
		}	
		for(int i=0; i< vue.getTab().size();i++) {
			if(vue.getTab().get(i).getClass().getName()=="model.Piece") {
				Piece piece= (Piece) vue.getTab().get(i);
				piece.setEstRamasse(false);
			}
		}
		this.moveMario();
		this.moveEnnemi();
		vue.setChrono(60);
		vue.chrono();
		vue.getConteneur().remove(vue.getNextLevel());
		vue.getSql().update();
	}
	
	/**
	 * Cette méthode lance le solo si l'on a appuié sur le bouton solo dans le menu.
	 */
	public void solo() {
		vue.createLevel();
		Mario.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.getFrame().setContentPane(vue.getConteneur());
		vue.getConteneur().requestFocusInWindow();
		vue.getFrame().setVisible(true);
		vue.chrono();
	}
	
	/**
	 * Cette méthode lance le multijoueur si l'on a appuié sur le bouton multi dans le menu.
	 */
	public void multi() {
		vue.createJoueur2();
		vue.createLevel();
		Mario.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.getFrame().setContentPane(vue.getConteneur());
		vue.getConteneur().requestFocusInWindow();
		vue.getFrame().setVisible(true);
		vue.chrono();
	}
}
