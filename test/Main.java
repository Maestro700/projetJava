package test;

import controller.Controller;
import model.Mario;
import view.VueConsole;
import view.VueGUI;
import view.VueGenerale;

public class Main {
	
	public Main() {
		Mario mario= new Mario(100, 320, "marioMarcheDroite.png");
		Controller control= new Controller(mario);
		VueGenerale vueGui= new VueGUI(mario, control);
		VueGenerale vueConsole= new VueConsole(mario, control);
		control.addViewGUI(vueGui);
		control.addViewConsole(vueConsole);
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
