package test;

import java.util.ArrayList;

import controller.Controller;
import model.Mario;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

public class Main {
	
	public Main() {
		ArrayList <Mario> tabMario = new ArrayList<Mario>();
		Mario mario= new Mario(100, 320, "marioMarcheDroite.png");
		tabMario.add(mario);
		Controller control= new Controller(tabMario);
		VueGUI vueGui= new VueGUI(tabMario, control);
		//VueConsole vueConsole= new VueConsole(tabMario, control);
		//vueConsole.createGrille();
		control.addViewGUI(vueGui);
		//control.addViewConsole(vueConsole);
		vueGui.createLevel();
		vueGui.chrono();
		control.moveMario();
		control.moveEnnemi();
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}