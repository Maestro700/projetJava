package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class koopa extends Personnage{
	
	private Image img;
	
	public koopa(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed= 3;
		super.collision=false;
		this.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
	}
	@Override
	protected void avancer(int dx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void collison(Objet obj) {
		// TODO Auto-generated method stub
		
	}
	public Image getImg() {
		return img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
}
