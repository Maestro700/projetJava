package controller;

import model.Mario;
import view.VueGenerale;

public class Controller {
	
	private int tauxRefresh = 10;
	private Thread sauter;
	private Thread avance;
	private Mario model; 
	private VueGenerale vue;
	
	public Controller(Mario model) {
		this.model = model;
		this.avance= new Thread();
	}
	
	public void addView(VueGenerale vue) {
		this.vue = vue;
	}
	
	public void saut(int dy) {
		sauter= new Thread(new Runnable() {
			public void run() {
				while(true) {
					model.saut(dy);
					try {
						Thread.sleep(tauxRefresh);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		sauter.start();
	}
	
	public void avancer(int dx, boolean av) {
		this.avance.new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
				
		}; {
				while(av==true) {
					model.avancer(dx);
					try {
						Thread.sleep(tauxRefresh);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public Thread getSauter() {
		return sauter;
	}

	public void setSauter(Thread sauter) {
		this.sauter = sauter;
	}

	public Thread getAvance() {
		return avance;
	}

	public void setAvance(Thread avance) {
		this.avance = avance;
	}
}
