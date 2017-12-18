package test;

import java.util.ArrayList;

import controller.Controller;
import model.Client;
import model.Mario;
import model.Serveur;
import view.VueGUI;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet de lancer le jeu et le constructeur d'instancier toutes les classes pour que le jeu puisse tourn�.
 */
public class Main {
	
	public Main() {
		//cr�e le tableau des marios (model);
		ArrayList <Mario> tabMario = new ArrayList<Mario>();
		//cr�e le premier joueur.
		Mario mario= new Mario(100, 320, "marioMarcheDroite.png");
		//ajoute le premier joueur dans le tableau.
		tabMario.add(mario);
		//instancie le controller.
		Controller control= new Controller(tabMario);
		//instancie l'interface graphique.
		VueGUI vueGui= new VueGUI(tabMario, control);
		//cr�e le premier niveau.
		vueGui.createLevel();
		//Serveur serveur= new Serveur();
		//Client client= new Client();
		//instancie l'interface console.
		//VueConsole vueConsole= new VueConsole(tabMario, control);
		//cr�e la map dans l'interface console.
		//vueConsole.createGrille();
		//passe l'instance de l'interface graphique au controller.
		control.addViewGUI(vueGui);
		//passe l'instance de l'interface console au controller.
		//control.addViewConsole(vueConsole);
		//lance le chrono d�s que l'on a choisit le mode de jeu.
		vueGui.chrono();
		//lance le thread qui g�re les d�placements de mario d�s que l'on a choisit le mode de jeu.
		control.moveMario();
		//lance le thread qui g�re les d�placements des ennemis d�s que l'on a choisit le mode de jeu.
		control.moveEnnemi();
	}
	
	/**
	 * Cette m�thode lance le jeu.
	 * @param args ne sert � rien.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}