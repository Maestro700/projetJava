package controller;

import java.awt.Rectangle;
import java.util.ArrayList;

import model.Ennemi;
import model.Mario;
import model.Objet;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

public class Controller{
	
	private Mario model;
	private VueGUI vue;
	private VueConsole console;
	private ArrayList <Ennemi> tab;
	private int compteur;
	
	public Controller(Mario model) {
		this.model = model;
		this.compteur=0;
	}
	
	public void addModel(ArrayList <Ennemi> tab) {
		this.tab = tab;
	}

	public void addViewGUI(VueGenerale vue) {
		this.vue = (VueGUI)vue;
	}
	
	public void addViewConsole(VueGenerale vue) {
		this.console = (VueConsole) vue;
	}
	
	public void moveMario(){
		Thread move= new Thread(new Runnable(){
			public void run() {
				while(true){
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
							model.changeImg("marioArretDroite.png");
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
							if(vue.getTouches()[0] == false) {	
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
				while(true) {
					for(int i=0; i<tab.size();i++) {
						if(tab.get(i).isVivant()==true) {
							if(tab.get(i).getDx()==-1) {
								if(tab.get(i).getClass().getName()=="model.koopa") {
									tab.get(i).changeImg("champMarcheGauche.png");
								}
								else {
									tab.get(i).changeImg("tortueMarcheGauche.png");
								}
							}
							else {
								if(tab.get(i).getClass().getName()=="model.koopa") {
									tab.get(i).changeImg("champMarcheDroite.png");
								}
								else {
									tab.get(i).changeImg("tortueMarcheDroite.png");
								}
							}
							tab.get(i).avancer(tab.get(i).getDx());
						}	
						try {
							Thread.sleep(5);
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
	
	public void collisionEnnemi(Ennemi ennemi) {
		if(model.getHitBox().intersects(ennemi.getHitBox())) {
			if(ennemi.getClass().getName()=="model.koopa") {
				if(model.getY()<(ennemi.getY()-ennemi.getHauteur())) {
					ennemi.setVivant(false);
				}
			}
			else {
				if(model.getY()<(ennemi.getY()-ennemi.getHauteur()/2)) {
					ennemi.setVivant(false);
				}
			}
			if(model.getX()<(ennemi.getX()-ennemi.getLargeur()/2)) {
				if(model.getHP()>0) {
					model.setHP(model.getHP()-1);
					model.setX(100);
					this.ennemiRevive();
				}
				else {
					model.setVivant(false);
				}
			}
			else if(model.getX()>(10+ennemi.getX()+ennemi.getLargeur()/2)) {
				if(model.getHP()>0) {
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
		for(int i=0; i<tab.size();i++) {
			tab.get(i).setVivant(true);
		}
	}
	
	public void restart() {
		model.setVivant(true);
		model.setHP(3);
		model.setX(100);
		model.changeImg("marioMarcheDroite.png");
		this.ennemiRevive();
		vue.getConteneur().remove(vue.getRestart());
	}
}
