package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 *
 * @author Wyart Guillaume et Jacobs David
 * Cette classe g�n�re les pi�ces.
 */
public class Piece extends Objet{
	
	//Cette variable permet de v�rifier si la pi�ce est ramass� au non.
	private boolean estRamasse;
	//Cette variable cr�e un compteur pour cr�e la mouvement des pi�ces.
	private int compteur;
	
	/**
	 * Cette m�thode est le constructeur de la classe et instancie toutes les variables.
	 * @param x donne la position en x de la pi�ce.
	 * @param y donne la position en y de la pi�ce.
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
	 * Cette m�thode permet de changer l'image de la pi�ce.
	 * @param str donne le chemi pour avoir l'image.
	 */
	public void changeImg(String str) {
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}
	
	/**
	 * Cette m�thode permet de faire "bouger" la pi�ce.
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
