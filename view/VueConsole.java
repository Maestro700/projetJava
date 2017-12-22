package view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import controller.Controller;
import model.Mario;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet de cr�er l'interface console de notre jeu.
 */
public class VueConsole extends VueGenerale {
	
	//Cette variable r�cup�re une ligne du fichier texte.
	private String currentLine;
	//Cette variable permet d'avoir la grille du niveau.
	private String [][] grille;
	//Cette variable r�cup�re toutes les lignes pour constituer la map en entier.
	private String allLine;
	//Cette variable affiche la ligne actuelle du fichier texte selon la grille.
	private String lineCarte;
	//Cette variable permet de r�duire le nombre de rafraichisement de l'interface console.
	private int compteur;
	private Scanner scanner;
	
	/**
	 * Cette m�thode est la constructeur de la classe qui instancie toutes les variables.
	 * @param mario r�cup�re la tableau des joueurs.
	 * @param control r�cup�re l'instance du controller.
	 */
	public VueConsole(ArrayList<Mario> mario, Controller control) {
		super(mario, control);
		this.grille= new String [78][8];
		this.currentLine= "";
		this.allLine= "";
		this.lineCarte= "";
		this.compteur=0;
	}
	
	/**
	 * Cette m�thode r�cup�re la lettre entr�e dans l'invite de commande.
	 */
	public void scan() {
		Thread moveConsole= new Thread(new Runnable() {
			public void run() {
				while(true) {
					scanner= new Scanner(System.in);
					if(scanner.nextLine().equals("d")) {
						mario.get(0).avancer(10);
						for(int y=0; y<8; y++) {
							for(int x=0; x<78; x++) {
								if(grille[x][y].equals("M")) {
									grille[x][y]="-";
									int newPos= y-1;
									grille[x][newPos]="M";
								}
							}
						}
					}
					if(scanner.nextLine().equals("q")) {
						mario.get(0).avancer(-10);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		moveConsole.start();
	}
	
	/**
	 * Cette m�thode cr�e la grille de d�part en lisant le fichier texte.
	 */
	public void createGrille() {
		try {
			BufferedReader reader =
				        new BufferedReader(new InputStreamReader(new FileInputStream("C:/2T/java/Projet_V2/src/images/carte.txt")));
			while((currentLine=reader.readLine())!=null) {
					allLine=allLine.concat(currentLine + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String [] tab=allLine.split("\\s+");
		for(int y=0; y<8; y++) {
			for(int x=0; x<78; x++) {
				grille[x][y]=tab[x+y*78];
				lineCarte+=grille[x][y];
			}
			System.out.println(lineCarte);
			lineCarte= "";
		}
	}
	
	/**
	 * Cette m�thode affiche la map selon la grille.
	 */
	public void afficheGrille() {
		for(int y=0; y<8; y++) {
			for(int x=0; x<78; x++) {
				lineCarte+=grille[x][y];
			}
			System.out.println(lineCarte);
			lineCarte= "";
		}
	}
	
	/**
	 * Cette m�thode est appel� � chaque fois que le mod�le la demande via le notifyObserver() et permet de rafraichir la map.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		compteur++;
		if(compteur%300==0) {
			this.afficheGrille();
		}
	}
	
	/*****GETTERS*****/
	public String[][] getGrille() {
		return grille;
	}
	
	/*****SETTERS*****/
	public void setGrille(String[][] grille) {
		this.grille = grille;
	}
}
