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
import model.Bloc;
import model.Tuyau;

public class VueGUI extends VueGenerale implements KeyListener{
	
	private JFrame frame;
	private JPanel conteneur;
	private ImageIcon fond;
	private Image fondImg;
	private Image fondImg2;
	private boolean [] touches;
	
	private Tuyau tuyau1= new Tuyau(400, 302, "tuyauRouge.png");
	private Tuyau tuyau2= new Tuyau(800, 302, "tuyauRouge.png");
	private Bloc bloc1= new Bloc(600, 250, "bloc.png");
	private Bloc bloc2= new Bloc(900, 302, "bloc.png");
	private Image chateau;
	
	public VueGUI(Mario mario, Controller control) {
		
		super(mario, control);
		
		this.fond= new ImageIcon(getClass().getResource("/images/fondEcran.png"));
		this.fondImg = this.fond.getImage();
		this.fondImg2= this.fond.getImage();
		this.chateau=new ImageIcon(getClass().getResource("/images/chateau.png")).getImage();
		this.touches= new boolean [3];
		for(int i=0; i<=2; i++) {
			touches[i]=false;
		}

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
				g.drawImage(chateau, 0, 167, null);
				g.drawImage(bloc1.getImg(), bloc1.getX(), bloc1.getY(), null);
				g.drawImage(bloc2.getImg(), bloc2.getX(), bloc2.getY(), null);
				g.drawImage(tuyau1.getImg(), tuyau1.getX(), tuyau1.getY(), null);
				g.drawImage(tuyau2.getImg(), tuyau2.getX(), tuyau2.getY(), null);
				g.drawImage(mario.getImg(), mario.getX(), mario.getY(), null);
			}
		};
		
		conteneur.setFocusable(true);
		conteneur.addKeyListener(this);
		
		frame.setContentPane(conteneur);
	}
	
	@Override
	public void update(Observable observ, Object obj) {
		mario.collison(bloc1);
		mario.collison(bloc2);
		mario.collison(tuyau1);
		mario.collison(tuyau2);
		conteneur.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE :
				if(mario.getNbSaut()==0) {
					this.touches[2]=true;
				}
				break;
			case KeyEvent.VK_LEFT :
				this.touches[1]=true;
				break;
			case KeyEvent.VK_RIGHT :
				this.touches[0]=true;
				break;
		}	
	}

	@Override	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE :
				this.touches[2]=false;
				mario.setNbSaut(1);
				break;
			case KeyEvent.VK_LEFT :
				this.touches[1]=false;
				break;
			case KeyEvent.VK_RIGHT :
				this.touches[0]=false;
				break;
		}	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean[] getTouches() {
		return touches;
	}

	public void setTouches(boolean[] touches) {
		this.touches = touches;
	}
}
