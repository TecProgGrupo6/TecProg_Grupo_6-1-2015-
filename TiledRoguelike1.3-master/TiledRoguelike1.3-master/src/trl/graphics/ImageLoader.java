package trl.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class ImageLoader{

	private final static Logger LOGGER = Logger.getLogger( ImageLoader.class.getName() );
	public BufferedImage load ( String path ){

		try{
			LOGGER.setLevel( Level.CONFIG );
			LOGGER.config("Buffering image completed");
			return ImageIO.read( getClass().getResource( path ) );
		}catch ( IOException e ){
			e.printStackTrace();
		}
		return null;
	}
}
