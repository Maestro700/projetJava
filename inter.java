package projet;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class inter extends JPanel{
	private ImageIcon image;
	private ImageIcon fond;
	private Image marioImg;
	private Image fondImg;
	private Image fondImg2;
	private int xFondEcran = -25;
	private int xFondEcran2 = 775;
	public static deplacement depla =new deplacement();
	
	public inter(){ 
		image = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
		fond = new ImageIcon(getClass().getResource("/images/fondEcran.png"));
		this.marioImg = this.image.getImage();
		this.fondImg = this.fond.getImage();
		this.fondImg2 = this.fond.getImage();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(depla);
		
		Thread tauxRefresh= new Thread(new refresh());
		tauxRefresh.start();
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.dx();
		g.drawImage(fondImg, xFondEcran, 0, null);
		g.drawImage(fondImg2, xFondEcran2, 0, null);
		g.drawImage(marioImg, 300, 245, null);
	}
	
	public void dx() {
		this.xFondEcran=this.xFondEcran - depla.getDx();
		this.xFondEcran2=this.xFondEcran2 - depla.getDx();
		  	  if(this.xFondEcran == -800){ 
			   this.xFondEcran = 800; 
			  }
			  else if(this.xFondEcran2 == -800){
			   this.xFondEcran2 = 800;
			  }
			  else if(this.xFondEcran == 800){
				   this.xFondEcran = -800;
			  }
			  else if(this.xFondEcran2 == 800){
			   this.xFondEcran2 = -800;
			  }
	}
}