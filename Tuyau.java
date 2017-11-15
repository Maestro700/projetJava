package projet;

import java.awt.Rectangle;

public class Tuyau extends Objet{
	
	public Tuyau(int x, int y, String str) {
		super(x, y, str);
		super.hitBox= new Rectangle(x, y, 43, 65);
	}
	
	@Override
	protected boolean collison(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
