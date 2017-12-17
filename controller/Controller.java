package controller;

import java.util.ArrayList;

import model.Ennemi;
import model.Mario;
import model.Piece;
import model.Son;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

public class Controller{
	
	private ArrayList <Mario> model;
	private VueGUI vue;
	private VueConsole console;
	private int compteur;
	
	public Controller(ArrayList <Mario> model) {
		this.model = model;
		this.compteur=0;
	}

	public void addViewGUI(VueGUI vue) {
		this.vue = vue;
	}
	
	public void addViewConsole(VueGenerale vue) {
		this.console = (VueConsole) vue;
	}
	
	public void moveMario(){
		Thread move= new Thread(new Runnable(){
			public void run() {
					while(Mario.isRunning()){
						for(int i=0; i < model.size(); i++) {
							if(model.get(i).getTouches()[0] == true) {
								compteur++;
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
								compteur++;
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
	
	public void moveEnnemi() {
		Thread move= new Thread(new Runnable() {
			public void run() {
					while(Mario.isRunning()) {
						for(int i=0; i<vue.getTabEnnemi().size();i++) {
						if(vue.getTabEnnemi().get(i).isVivant()==true) {
							if(vue.getTabEnnemi().get(i).getDx()==-1) {
								if(vue.getTabEnnemi().get(i).getClass().getName()=="model.koopa") {
									vue.getTabEnnemi().get(i).changeImg("champMarcheGauche.png");
								}
								else {
									vue.getTabEnnemi().get(i).changeImg("tortueMarcheGauche.png");
								}
							}
							else {
								if(vue.getTabEnnemi().get(i).getClass().getName()=="model.koopa") {
									vue.getTabEnnemi().get(i).changeImg("champMarcheDroite.png");
								}
								else {
									vue.getTabEnnemi().get(i).changeImg("tortueMarcheDroite.png");
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
	
	public int fps(int nb, int fps) {
		return fps/nb;
	}
	
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
	
	public void collisionConsole(ArrayList <Mario> mario) {
		if(console.getGrille()[mario.get(0).getxCase()+mario.get(0).getDx()][7]=="X") {
			
		}
	}
	
	public void ennemiRevive() {
		for(int i=0; i<vue.getTabEnnemi().size();i++) {
			if(vue.getTabEnnemi().get(i).isVivant()==false) {
				Mario.setScore(Mario.getScore()-200);
			}
			vue.getTabEnnemi().get(i).setVivant(true);
		}
	}
	
	public void restart() {
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
		this.ennemiRevive();
		for(int i=0; i< vue.getTab().size();i++) {
			if(vue.getTab().get(i).getClass().getName()=="model.Piece") {
				Mario.setScore(0);
				Piece piece= (Piece) vue.getTab().get(i);
				piece.setEstRamasse(false);
			}
		}
		vue.getConteneur().remove(vue.getRestart());
	}
	
	public void nextLevel() {
		vue.setLevel(2);
		vue.createLevel();
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
	}
	
	public void solo() {
		Mario.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.getFrame().setContentPane(vue.getConteneur());
		vue.getConteneur().requestFocusInWindow();
		vue.getFrame().setVisible(true);
		vue.chrono();
	}
	
	public void multi() {
		vue.createJoueur2();
		Mario.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.getFrame().setContentPane(vue.getConteneur());
		vue.getConteneur().requestFocusInWindow();
		vue.getFrame().setVisible(true);
		vue.chrono();
	}
}
