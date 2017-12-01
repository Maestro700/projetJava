package ephec.tp4;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class Cercle {
	private double rayon;
	private double rayon1;
	
	public Cercle(){
		this.rayon = Double.parseDouble(JOptionPane.showInputDialog("Entrez le rayon"));
		this.rayon1 = Double.parseDouble(JOptionPane.showInputDialog("Entrez le rayon"));
	}
	public double aire(){
		return (Math.PI*Math.pow(this.rayon, 2));
	}
	public double perim(){
		return (2*Math.PI*this.rayon);
	}
	public String toString(){
		return ("Cercle de rayon: "+this.rayon);
	}
	
	public static void main(String[] args){
		Cercle toto = new Cercle();
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.println(toto);
		System.out.println("Aire: "+formatter.format(toto.aire()));
		System.out.println("Perimetre: "+formatter.format(toto.perim()));
		System.out.println("rayon sont-ils egaux? "+toto.equals(toto));
	}
	
	public boolean equals(Object obj){
		if(this == obj) return true;
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		Cercle other = (Cercle)obj; //voir si il est de meme type, donc le caster en etudiant pour acceder dans les élement de Etu
		if(this.rayon != other.rayon1) return false;
		return true;
	}

}
