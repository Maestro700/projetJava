package model;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Wyart Guillaume et Jacobs David
 * Cette classe permet de mettre de la musique dans le jeu.
 */
public class Son {
	
	//Cette variable récupère le fichier audio d'une musique.
	private Clip clip;
	
	/**
	 * Cette méthode est le constructeur de la classe et instancie la variable Clip et lance le fichier audio.
	 * @param str donne le chemin d'accès au fichier audio.
	 */
	public Son(String str) {
		try {
			AudioInputStream audio= AudioSystem.getAudioInputStream(getClass().getResource(str));
			clip = AudioSystem.getClip();
			clip.open(audio);
			if(str=="/son/marioMenuSon.wav") clip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl control= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(-20);
			clip.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode permet d'arrêter le fichier audio.
	 */
	public void arreteSon() {
		clip.stop();
	}
}
