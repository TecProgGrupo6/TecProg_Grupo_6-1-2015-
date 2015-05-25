package trl.graphics;

import java.awt.image.BufferedImage;

import trl.main.Game;

public class SpriteSheet{

	// Sheet image
	private BufferedImage sheet;

	public SpriteSheet ( BufferedImage sheet ){

		this.sheet = sheet;
	}

	public BufferedImage crop ( int col , int row , int w , int h ){

		return this.sheet.getSubimage( col * Game.TILE_SIZE , row * Game.TILE_SIZE , w , h );
	}
}
