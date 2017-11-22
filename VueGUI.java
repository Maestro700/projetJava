package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Mario;

public class VueGUI extends VueGenerale implements KeyListener{
	
	private JFrame frame;
	private JPanel conteneur;
	private ImageIcon fond;
	private Image fondImg;
	private Image fondImg2;
	
	public VueGUI(Mario mario, Controller control) {
		
		super(mario, control);
		
		this.fond= new ImageIcon(getClass().getResource("/images/fondEcran.png"));
		this.fondImg = this.fond.getImage();
		this.fondImg2= this.fond.getImage();
		
		frame = new JFrame("mario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1996, 437);
		frame.setLocationRelativeTo(null);
	 	frame.setAlwaysOnTop(true);              
		frame.setResizable(false);
		frame.setVisible(true);
		
		conteneur = new JPanel() {
			public void paintComponent(Graphics g) {
				conteneur.paintComponents(g);
				g.drawImage(fondImg, 0, 0, null);
				g.drawImage(fondImg2, 993, 0, null);
				g.drawImage(mario.getImg(), mario.getX(), mario.getY(), null);
			}
		};
		
		conteneur.setFocusable(true);
		conteneur.addKeyListener(this);
		
		frame.setContentPane(conteneur);
	}
	
	@Override
	public void update(Observable observ, Object obj) {
		conteneur.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE :
				control.saut(-1);
				break;
			case KeyEvent.VK_LEFT :
				mario.changeImg("marioMarcheGauche.png");
				control.avancer(-1, true);
				break;
			case KeyEvent.VK_RIGHT :
				mario.changeImg("marioMarcheDroite.png"); 
				control.avancer(1, true);
				break;
			}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) { 
			control.avancer(0, false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
