package projet;

import javax.swing.JFrame;

public class test {
	public static inter scene;
	
	public static void main(String[] args) {
		//Création de la fenetre
		JFrame fenetre = new JFrame("mario");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(1996, 437);
		fenetre.setLocationRelativeTo(null);
	 	fenetre.setAlwaysOnTop(true);              
		fenetre.setResizable(false);
		scene = new inter();
		fenetre.setContentPane(scene);
		fenetre.setVisible(true);
	}
}
   