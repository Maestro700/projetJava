package model;

import java.awt.Rectangle;

public class Piece extends Objet{
	
	private boolean estRamasse;
	private int compteur;
	
	public Piece(int x, int y, String str) {
		super(x, y, str);
		super.largeur=25;
		super.hauteur=30;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
		this.estRamasse=false;
		this.compteur=0;
	}
	
	public void movePiece() {
		Thread move= new Thread(new Runnable() {
			public void run() {
				while(true) {
					compteur++;
					if(compteur%2==0) {
						changeImg("piece.png");
					}
					else {
						changeImg("piece2.png");
					}	
					try {
						Thread.sleep(500);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		move.start();
	}

	public boolean isEstRamasse() {
		return estRamasse;
	}

	public void setEstRamasse(boolean estRamasse) {
		this.estRamasse = estRamasse;
	}
}
