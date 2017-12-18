package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 *
 * @author Wyart Guillaume et Jacobs David
 * Cette classe génère les pièces.
 */
public class Piece extends Objet{
	
	//Cette variable permet de vérifier si la pièce est ramassé au non.
	private boolean estRamasse;
	//Cette variable crée un compteur pour crée la mouvement des pièces.
	private int compteur;
	
	/**
	 * Cette méthode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pièce.
	 * @param y donne la position en y de la pièce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public Piece(int x, int y, String str) {
		super(x, y, str);
		super.largeur=25;
		super.hauteur=30;
		super.hitBox= new Rectangle(x, y, super.largeur, super.hauteur);
		this.estRamasse=false;
		this.compteur=0;
	}
	
	/**
	 * Cette méthode permet de changer l'image de la pièce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}
	
	/**
	 * Cette méthode permet de faire "bouger" la pièce.
	 */
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
	
	/*****GETTERS*****/
	public boolean isEstRamasse() {
		return estRamasse;
	}
	
	/*****SETTERS*****/
	public void setEstRamasse(boolean estRamasse) {
		this.estRamasse = estRamasse;
	}
}
