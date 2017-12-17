package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import controller.Controller;
import model.Mario;

public class VueConsole extends VueGenerale {

	private String currentLine;
	private String [][] grille;
	private String allLine;
	private String lineCarte;
	private int compteur;
	
	public VueConsole(ArrayList<Mario> mario, Controller control) {
		super(mario, control);
		this.grille= new String [78][8];
		this.currentLine= "";
		this.allLine= "";
		this.lineCarte= "";
		this.compteur=0;
	}
	
	public void scan() {
		Scanner scanner= new Scanner(System.in);
		if(scanner.nextLine()=="d") {
			mario.get(0).avancer(1);
		}
	}
	
	public void createGrille() {
		try {
			BufferedReader reader =
				        new BufferedReader(new InputStreamReader(new FileInputStream("C:/2T/java/Projet_V2/src/images/carte.txt")));
			while((currentLine=reader.readLine())!=null) {
					allLine=allLine.concat(currentLine + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String [] tab=allLine.split("\\s+");
		for(int y=0; y<8; y++) {
			for(int x=0; x<78; x++) {
				grille[x][y]=tab[x+y*78];
				lineCarte+=grille[x][y];
			}
			System.out.println(lineCarte);
			lineCarte= "";
		}
	}
	
	public void afficheGrille() {
		for(int y=0; y<8; y++) {
			for(int x=0; x<78; x++) {
				lineCarte+=grille[x][y];
			}
			System.out.println(lineCarte);
			lineCarte= "";
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		compteur++;
		if(compteur%10==0) {
			scan();
			this.afficheGrille();
		}
	}

	public String[][] getGrille() {
		return grille;
	}

	public void setGrille(String[][] grille) {
		this.grille = grille;
	}
}
