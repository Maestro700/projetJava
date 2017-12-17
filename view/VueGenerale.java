package view;

import java.util.ArrayList;
import java.util.Observer;

import controller.Controller;
import model.Mario;

public abstract class VueGenerale implements Observer {
	
	protected ArrayList <Mario> mario;
	protected Controller control;
	
	public VueGenerale(ArrayList <Mario> mario, Controller control) {
		this.mario=mario;
		this.control= control;
		for(int i=0; i < mario.size(); i++) {
			mario.get(i).addObserver(this);
		}
	}
	
	
}
