package controller;

import model.Mario;
import view.VueGenerale;

public class Controller {
	private Mario model; 
	private VueGenerale vue;
	
	public Controller(Mario model) {
		this.model = model;
	}
	
	public void addView(VueGenerale vue) {
		this.vue = vue;
	}
}
