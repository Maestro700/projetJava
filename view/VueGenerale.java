package view;

import java.util.ArrayList;
import java.util.Observer;

import controller.Controller;
import model.Mario;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe passe � ces fils les ressources dont ils ont besoin et impl�mente Observer pour que les fils g�rent les notifyObserver() et puissent le traiter.
 */
public abstract class VueGenerale implements Observer {
	
	//Cette variable r�cup�re le tableau des joueurs (model).
	protected ArrayList <Mario> mario;
	//Cette variable r�cup�re l'instance du controleur.
	protected Controller control;
	
	/**
	 * Cette m�thode est le constructeur de la classe et envoie � ces fils le model et le constructeur.
	 * @param mario r�cup�re le model.
	 * @param control r�cup�re le controlleur.
	 */
	public VueGenerale(ArrayList <Mario> mario, Controller control) {
		this.mario=mario;
		this.control= control;
		for(int i=0; i < mario.size(); i++) {
			mario.get(i).addObserver(this);
		}
	}
	
	
}
