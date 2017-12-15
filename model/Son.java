package model;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Son {
	
	private Clip clip;
	
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
	
	public void arreteSon() {
		clip.stop();
	}
}
