package model;

import javax.swing.ImageIcon;

public class koopa extends Ennemi{
	
	public koopa(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed=5;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.largeur=27;
		super.hauteur=30;
	}
}
