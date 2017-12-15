package controller;

import model.Ennemi;
import model.Mario;
import model.Piece;
import model.Son;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

public class Controller{
	
	private Mario model;
	private VueGUI vue;
	private VueConsole console;
	private int compteur;
	private int fps;
	
	public Controller(Mario model) {
		this.model = model;
		this.compteur=0;
		this.fps=30;
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
				while(model.isRunning()){
						if(vue.getTouches()[0] == true) {
							compteur++;
							model.avancer(1);
							if(compteur%2==0) {
								model.changeImg("marioMarcheDroite.png");
							}
							else {
								model.changeImg("marioArretDroite.png");
							}
						}
						else {
							if(vue.isLastKeypressed()==true) {
								model.changeImg("marioArretDroite.png");
							}
						}
						if(vue.getTouches()[1] == true) {
							compteur++;
							model.avancer(-1);
							if(compteur%2==0) {
								model.changeImg("marioMarcheGauche.png");
							}
							else {
								model.changeImg("marioArretGauche.png");
							}
						}	
						else {
							if(vue.isLastKeypressed()==false) {
								model.changeImg("marioArretGauche.png");
							}
						}
						if(vue.getTouches()[2] == true) {
							if(vue.getTouches()[0] == true || model.isSaut()==false) {
								model.changeImg("marioSautDroite.png");
							}
							else {
								model.changeImg("marioSautGauche.png");
							}
							model.saut(4);
							model.setSaut(true);
						}
						else {
							model.saut(4);
							model.setSaut(false);
						}
					try {
						Thread.sleep(10);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		move.start();
	}
	
	public void moveEnnemi() {
		Thread move= new Thread(new Runnable() {
			public void run() {
				while(model.isRunning()) {
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
							Thread.sleep(fps());
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
	
	public int fps() {
		return this.fps/vue.getTabEnnemi().size();
	}
	
	public void collisionEnnemi(Ennemi ennemi) {
		if(model.getHitBox().intersects(ennemi.getHitBox())) {
			if(model.getY()<(ennemi.getY())) {
				ennemi.setVivant(false);
				Son ecrase = new Son("/son/ecrasePersonnage.mp3");
				model.setScore(model.getScore()+200);
			}
			if(model.getX()<(ennemi.getX()-ennemi.getLargeur()/2)) {
				if(model.getHP()>1) {
					model.setHP(model.getHP()-1);
					model.setX(100);
					this.ennemiRevive();
				}
				else {
					model.setVivant(false);
				}
			}
			else if(model.getX()>(ennemi.getX()+ennemi.getLargeur())) {
				if(model.getHP()>1) {
					model.setHP(model.getHP()-1);
					model.setX(100);
					this.ennemiRevive();
				}
				else {
					model.setVivant(false);
				}
			}
		}
	}
	
	public void ennemiRevive() {
		for(int i=0; i<vue.getTabEnnemi().size();i++) {
			if(vue.getTabEnnemi().get(i).isVivant()==false) {
				model.setScore(model.getScore()-200);
			}
			vue.getTabEnnemi().get(i).setVivant(true);
		}
	}
	
	public void restart() {
		model.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.setChrono(60);
		vue.chrono();
		model.setVivant(true);
		model.setHP(3);
		model.setX(100);
		model.changeImg("marioMarcheDroite.png");
		this.ennemiRevive();
		for(int i=0; i< vue.getTab().size();i++) {
			if(vue.getTab().get(i).getClass().getName()=="model.Piece") {
				model.setScore(0);
				Piece piece= (Piece) vue.getTab().get(i);
				piece.setEstRamasse(false);
			}
		}
		vue.getConteneur().remove(vue.getRestart());
	}
	
	public void nextLevel() {
		vue.setLevel(2);
		vue.createLevel();
		model.setX(100);
		model.setHP(3);
		model.setRunning(true);
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
		model.changeImg("marioMarcheDroite.png");
		vue.getConteneur().remove(vue.getNextLevel());
	}
	
	public void solo() {
		model.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.getFrame().setContentPane(vue.getConteneur());
		vue.getConteneur().requestFocusInWindow();
		vue.getFrame().setVisible(true);
		vue.chrono();
	}
	
	public void multi() {
		vue.createJoueur2();
		model.setRunning(true);
		this.moveMario();
		this.moveEnnemi();
		vue.getFrame().setContentPane(vue.getConteneur());
		vue.getConteneur().requestFocusInWindow();
		vue.getFrame().setVisible(true);
		vue.chrono();
	}
}
