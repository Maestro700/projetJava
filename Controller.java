package controller;

import java.awt.Image;

import model.Mario;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;
/**
 *@author Wyart Guillaume et Jacobs David
 *Classe qui va controler les differentes vues 
 */
public class Controller{
	
	private Mario model; 
	private VueGUI vue;
	private VueConsole console;
	private Thread move;
	
	/**
	 * Constructeur qui gére le model Mario
	 * @param model
	 */
	public Controller(Mario model) {
		this.model = model;
	}
	
	
	/**
	 * Méthode qui ajoute la vue au controlleur
	 * @param vue
	 */

	public void addViewGUI(VueGenerale vue) {
		this.vue = (VueGUI)vue;
	}
	
	public void addViewConsole(VueGenerale vue) {
		this.console = (VueConsole) vue;
	}
	
	/**
	 * Méthode qui va permettre de "rafraichir" via un Thread l'interface graphique et
	 * ainsi gérer les différents mouvements de Mario pour le faire bouger dans l'interface
	 * Permet aussi de gérer plusieurs mouvements à la fois
	 */
	public void moveMario(){
		move= new Thread(new Runnable(){
			public void run() {
				while(true){
						if(vue.getTouches()[0] == true) {
							model.avancer(1);
							model.changeImg("marioMarcheDroite.png");
						}
						if(vue.getTouches()[1] == true) {
							model.avancer(-1);
							model.changeImg("marioMarcheGauche.png");
						}	
						if(vue.getTouches()[2] == true) {
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
}
