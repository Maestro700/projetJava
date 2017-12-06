package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Mario;
import model.Objet;
import model.Bloc;
import model.DrapeauFin;
import model.Ennemi;
import model.Tuyau;
import model.goomba;
import model.koopa;

public class VueGUI extends VueGenerale implements KeyListener, ActionListener{
	
	private JButton restart;
	
	private JFrame frame;
	private JPanel conteneur;
	private ImageIcon fond;
	private Image fondImg;
	private Image fondImg2;
	private Image chateau;
	private Image depart;
	private Image chateauFin;
	private Image gameOver;
	private Image victory;
	
	private Tuyau tuyau1= new Tuyau(450, 302, "tuyauRouge.png");
	private Tuyau tuyau2= new Tuyau(1000, 302, "tuyauRouge.png");
	private Bloc bloc1= new Bloc(600, 250, "bloc.png");
	private Bloc bloc2= new Bloc(900, 302, "bloc.png");
	private Bloc bloc3= new Bloc(250, 337, "bloc.png");
	private Bloc bloc4= new Bloc(280, 308, "bloc.png");
	private Bloc bloc5= new Bloc(280, 337, "bloc.png");
	private DrapeauFin drapeauFin= new DrapeauFin(1700, 187, "drapeau.png");
	private ArrayList <Objet> tab;
	private koopa koopa1= new koopa(600, 340, "champMarcheDroite.png");
	private koopa koopa2= new koopa(900, 340, "champMarcheDroite.png");
	private koopa koopa3= new koopa(270, 340, "champMarcheDroite.png");
	private goomba goomba1= new goomba(850, 320, "tortueMarcheDroite.png");
	private goomba goomba2= new goomba(550, 320, "tortueMarcheDroite.png");
	private ArrayList <Ennemi> tabEnnemi;
	
	private boolean [] touches;
	
	@SuppressWarnings("serial")
	public VueGUI(Mario mario, Controller control) {
		
		super(mario, control);
		
		this.fond= new ImageIcon(getClass().getResource("/images/fondEcran.png"));
		this.fondImg = this.fond.getImage();
		this.fondImg2= this.fond.getImage();
		this.chateau=new ImageIcon(getClass().getResource("/images/chateau.png")).getImage();
		this.depart=new ImageIcon(getClass().getResource("/images/depart.png")).getImage();
		this.chateauFin=new ImageIcon(getClass().getResource("/images/chateauFin.png")).getImage();
		this.gameOver=new ImageIcon(getClass().getResource("/images/gameOver.png")).getImage();
		this.victory=new ImageIcon(getClass().getResource("/images/Victory.png")).getImage();
		this.touches= new boolean [3];
		for(int i=0; i<=2; i++) {
			touches[i]=false;
		}
		this.tab=new ArrayList<Objet>();
		this.tab.add(bloc1);
		this.tab.add(bloc2);
		this.tab.add(bloc3);
		this.tab.add(bloc4);
		this.tab.add(bloc5);
		this.tab.add(tuyau1);
		this.tab.add(tuyau2);
		this.tab.add(drapeauFin);
		this.tabEnnemi=new ArrayList<Ennemi>();
		this.tabEnnemi.add(koopa1);
		this.tabEnnemi.add(koopa2);
		this.tabEnnemi.add(koopa3);
		this.tabEnnemi.add(goomba1);
		this.tabEnnemi.add(goomba2);
		control.addModel(tabEnnemi);

		frame = new JFrame("mario");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1996, 437);
		frame.setLocationRelativeTo(null);
	 	frame.setAlwaysOnTop(true);              
		frame.setResizable(false);
		frame.setVisible(true);
		
		restart= new JButton("restart");
		restart.setBounds(850, 300, 100, 30);
		restart.addActionListener(this);

		conteneur = new JPanel() {
			public void paintComponent(Graphics g) {
				conteneur.paintComponents(g);
				g.drawImage(fondImg, 0, 0, null);
				g.drawImage(fondImg2, 993, 0, null);
				g.drawImage(chateau, 0, 167, null);
				g.drawImage(depart, 200, 305, null);
				g.drawImage(chateauFin, 1800, 218, null);
				for(int i=0; i<tab.size(); i++) {
					g.drawImage(tab.get(i).getImg(), tab.get(i).getX(), tab.get(i).getY(), null);
					if(tab.get(i).getClass().getName()=="model.DrapeauFin") {
						if(mario.isFin()==true) {
							g.drawImage(victory, 700, 0,500, 300, null);
						}
					}
				}
				for(int i=0; i<tabEnnemi.size(); i++) {
					if(tabEnnemi.get(i).isVivant()==true) {
						g.drawImage(tabEnnemi.get(i).getImg(), tabEnnemi.get(i).getX(), tabEnnemi.get(i).getY(), null);
					}	
					else {
						if(tabEnnemi.get(i).getClass().getName()=="model.koopa") {
							tabEnnemi.get(i).changeImg("champEcraseDroite.png");
							g.drawImage(tabEnnemi.get(i).getImg(), tabEnnemi.get(i).getX(), tabEnnemi.get(i).getY()+17, null);
						}
						else {
							tabEnnemi.get(i).changeImg("tortueFermee.png");
							g.drawImage(tabEnnemi.get(i).getImg(), tabEnnemi.get(i).getX(), tabEnnemi.get(i).getY()+25, null);
						}
					}
				}
				if(mario.isVivant()==true) {
					g.drawImage(mario.getImg(), mario.getX(), mario.getY(), null);
				}
				else {
					mario.changeImg("marioMeurt.png");
					g.drawImage(mario.getImg(), mario.getX(), mario.getY()+10, null);
					g.drawImage(gameOver, 750, 0, 300,300, null);
					conteneur.add(restart);
				}
			}
		};
		
		conteneur.setFocusable(true);
		conteneur.addKeyListener(this);
		
		frame.setContentPane(conteneur);
	}
	
	@Override
	public void update(Observable observ, Object obj) {
		for(int i=0; i<tab.size(); i++) {
			mario.collison(tab.get(i));
			for(int j=0; j<tabEnnemi.size(); j++) {
				if(tabEnnemi.get(j).isVivant()==true) {
					tabEnnemi.get(j).collison(tab.get(i));
					control.collisionEnnemi(tabEnnemi.get(j));
				}
			}
			conteneur.repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(mario.isVivant()==true) {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== restart) {
			control.restart();
		}
	}

	public boolean[] getTouches() {
		return touches;
	}

	public void setTouches(boolean[] touches) {
		this.touches = touches;
	}

	public JPanel getConteneur() {
		return conteneur;
	}

	public void setConteneur(JPanel conteneur) {
		this.conteneur = conteneur;
	}

	public JButton getRestart() {
		return restart;
	}

	public void setRestart(JButton restart) {
		this.restart = restart;
	}
}
