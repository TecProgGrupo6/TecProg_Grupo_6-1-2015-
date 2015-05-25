package trl.sound;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.Clip;

public class SoundManager{
	
	private final static Logger LOGGER = Logger.getLogger( SoundManager.class.getName() );


	@SuppressWarnings ( "unused" )
	private Clip[] sounds;

	public SoundManager (){

		init();
	}

	public void init (){

		sounds = new Clip[1];
	}

	public void playSound ( String sound ){
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Playing sound");

		// String file;
		// switch (sound) {
		// case "strike": {
		// file = "strike.aiff";
		// break;
		// }
		// case "miss": {
		// file = "miss.aiff";
		// break;
		// }
		// default: {
		// file = "";
		// }
		// }

		// try {
		// AudioInputStream audio = AudioSystem.getAudioInputStream(new
		// File("./res/" + file));
		// Clip clip = AudioSystem.getClip();
		// // sounds[0] = clip;
		// clip.open(audio);
		// clip.start();
		// }

		// catch(UnsupportedAudioFileException uae) {
		// System.out.println(uae);
		// }
		// catch(IOException ioe) {
		// System.out.println(ioe);
		// }
		// catch(LineUnavailableException lua) {
		// System.out.println(lua);
		// }
	}
}
