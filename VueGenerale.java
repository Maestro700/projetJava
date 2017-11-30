package view;

import java.util.Observer;

import controller.Controller;
import model.Mario;

public abstract class VueGenerale implements Observer {
	
	protected Mario mario;
	protected Controller control;
	
	public VueGenerale(Mario mario, Controller control) {
		this.mario=mario;
		this.control= control;
		mario.addObserver(this);
	}
}
