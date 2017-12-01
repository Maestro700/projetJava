package controller;

import model.Mario;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

public class Controller{
	
	private Mario model; 
	private VueGUI vue;
	private VueConsole console;
	
	
	public Controller(Mario model) {
		this.model = model;
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
	
	public void moveEnnemi() {
		Thread move= new Thread(new Runnable() {
			public void run() {
				
				try {
					Thread.sleep(10);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		move.start();
	}
}
