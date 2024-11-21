package com.beep;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class SuccessSound {

	public static void musicFile() {

		try {
			String filePath="C:\\Users\\pc\\eclipse-workspace\\automation\\SKillEnhancement\\src\\test\\resources\\AudioFiles\\Pookal pookum _ flute.wav";
//			String filePath="C:\\Users\\iamma\\eclipse-workspace\\SKillEnhancement\\src\\test\\resources\\AudioFiles\\Pookal pookum _ flute.wav";
			File musicPath = new File(filePath);

			if (musicPath.exists()) {

				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				clip.loop(clip.LOOP_CONTINUOUSLY);
				JOptionPane.showMessageDialog(null, "Program Completed" + "\n" + " press OK to stop the Sound");
			} else {
				System.out.println("can't found file");
			}

		} catch (Exception e) {
			System.out.println(e);
		}	
	}

}
