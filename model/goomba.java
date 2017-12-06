package model;

import javax.swing.ImageIcon;

public class goomba extends Ennemi{
	public goomba(int x, int y, String str) {
		super.x= x;
		super.y= y;
		super.speed=4;
		super.img=new ImageIcon(getClass().getResource("/images/"+str)).getImage();
		super.largeur=43;
		super.hauteur=50;
	}
}
