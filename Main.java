package test;

import controller.Controller;
import model.Mario;
import view.VueGUI;
import view.VueGenerale;

public class Main {
	public Main() {
		Mario mario= new Mario(100, 320);
		Controller control= new Controller(mario);
		VueGenerale vueGui= new VueGUI(mario, control);
		control.addView(vueGui);
	}
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}
}
