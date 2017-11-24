package controller;

import model.Mario;
import view.VueGenerale;

public class Controller{
	
	private Mario model; 
	private VueGenerale vue;
	private volatile boolean isMove;
	
	public Controller(Mario model) {
		this.model = model;
		this.isMove= true;
	}

	public void addView(VueGenerale vue) {
		this.vue = vue;
	}
	
	public void moveMario(int dx, int dy){
		Thread move= new Thread(new Runnable(){
			public void run() {
				isMove=true;
				while(isMove){
					model.avancer(dx);
					model.saut(dy);
					try {
						Thread.sleep(10);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					isMove=false;
				}
			}
		});
		move.start();
	}
	
	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}
}