package view;

import java.util.ArrayList;
import java.util.Observer;

import controller.Controller;
import model.Mario;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe passe à ces fils les ressources dont ils ont besoin et implémente Observer pour que les fils gèrent les notifyObserver() et puissent le traiter.
 */
public abstract class VueGenerale implements Observer {
	
	//Cette variable récupère le tableau des joueurs (model).
	protected ArrayList <Mario> mario;
	//Cette variable récupère l'instance du controleur.
	protected Controller control;
	
	/**
	 * Cette méthode est le constructeur de la classe et envoie à ces fils le model et le constructeur.
	 * @param mario récupère le model.
	 * @param control récupère le controlleur.
	 */
	public VueGenerale(ArrayList <Mario> mario, Controller control) {
		this.mario=mario;
		this.control= control;
		for(int i=0; i < mario.size(); i++) {
			mario.get(i).addObserver(this);
		}
	}
	
	
}
