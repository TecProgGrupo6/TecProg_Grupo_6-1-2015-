package trl.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;

public class MenuState extends GameState{

	private String[] menuOptions;
	public static boolean up = false , down = false , enter = false;
	private int classChoice;
	private static final int FONT_SIZE = 24;
	private final static Logger LOGGER = Logger.getLogger( GameStateManager.class.getName() );

	public MenuState (){

		init();
	}

	public void init (){

		menuOptions = new String[] { "Barbarian" , "Thief" , "Wizard" , "Ranger" };
		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Initializing player's options" );
	}

	public void tick (){

		if ( down ){

			if ( classChoice < menuOptions.length - 1 ){

				classChoice += 1;
			}else{

				classChoice = 0;
			}

			down = false;
		}else{
			// nothing
		}
		if ( up ){

			if ( classChoice > 0 ){

				classChoice -= 1;
			}else{

				classChoice = menuOptions.length - 1;
			}

			up = false;
		}else{

			// nothing
		}
		if ( enter ){

			Game.getGameStateManager().addGameState( 1 , new GameplayState( classChoice ) );
			Game.getGameStateManager().setGameState( 1 );
			LOGGER.setLevel( Level.INFO );
			LOGGER.info( "Option '" + menuOptions[classChoice] + "' chosen" );
			enter = false;
			
		}else{

			// nothing
		}
		// Game.classChoice = classChoice;
	}

	public void render ( Graphics g ){

		final int HALF = 2;
		final int QUARTER = 4;

		g.setColor( Color.BLACK );
		g.fillRect( 0 , 0 , Game.W_WIDTH , Game.W_HEIGHT );
		g.fillRect( 0 , Game.W_HEIGHT , Game.W_WIDTH , Game.MSG_HEIGHT );
		g.setFont( new Font( "Fixed" , Font.PLAIN , FONT_SIZE ) );
		FontMetrics fm = g.getFontMetrics();
		g.setColor( Color.WHITE );
		g.drawString( "Choose Your Class" , ( Game.W_WIDTH - fm.stringWidth( "Choose Your Class" ) ) / HALF , FONT_SIZE );

		for ( int i = 0 ; i < menuOptions.length ; i++ ){

			if ( i == classChoice ){

				g.setColor( Color.YELLOW );
			}else{

				g.setColor( Color.WHITE );
			}

			g.drawString( menuOptions[i] , ( Game.W_WIDTH - fm.stringWidth( menuOptions[i] ) ) / HALF , i * FONT_SIZE + 2 * FONT_SIZE );
		}

		BufferedImage classImage = null;
		g.setColor( Color.WHITE );
		int linePosition = Game.W_HEIGHT - Game.W_HEIGHT / QUARTER;

		switch ( classChoice ) {

		case 0:{

			classImage = renderBarbarian( g , linePosition , classImage );
			
			break;

		}
		case 1:{

			classImage = renderThief( g , linePosition , classImage );
			
			break;

		}
		case 2:{

			classImage = renderWizard( g , linePosition , classImage );
			

			break;

		}
		case 3:{

			classImage = renderRanger( g , linePosition , classImage );
			

		}
		}
		g.setColor( Color.LIGHT_GRAY );

		// in pixels
		int xSize = ( Game.W_WIDTH / HALF ) - ( ( Game.TILE_SIZE * QUARTER + Game.TILE_SIZE ) / HALF );
		final int Y_SIZE = 200;
		int widthSize = Game.TILE_SIZE * QUARTER + Game.TILE_SIZE;
		int heightSize = Game.TILE_SIZE * QUARTER + Game.TILE_SIZE;

		g.fillRect( xSize , Y_SIZE , widthSize , heightSize );

		// in pixels
		final int IMAGE_Y_SIZE = 200 + ( Game.TILE_SIZE / HALF );
		int imageXSize = ( Game.W_WIDTH / 2 ) - ( ( Game.TILE_SIZE * QUARTER + Game.TILE_SIZE ) / HALF ) + ( Game.TILE_SIZE / HALF );
		int imageWidthSize = Game.TILE_SIZE * QUARTER;
		int imageHeithSize = Game.TILE_SIZE * QUARTER;

		g.drawImage( classImage , imageXSize , IMAGE_Y_SIZE , imageWidthSize , imageHeithSize , null );

	}

	public BufferedImage renderBarbarian ( Graphics g , int linePosition , BufferedImage classImage ){

		classImage = Game.getImageManager().barbarian;

		g.drawString( "Highest max HP and attack. Regenerates health" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "when not in combat. Can shout to lure enemies" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "to his current position." , 0 , linePosition );

		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config( "Rendering Barbarian image." );
		return classImage;

	}

	public BufferedImage renderThief ( Graphics g , int linePosition , BufferedImage classImage ){

		classImage = Game.getImageManager().thief;

		g.drawString( "Medium max HP and attack. Has full awareness" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "of all map features and entities. (Pretty awesome.)" , 0 , linePosition );

		LOGGER.setLevel( Level.CONFIG);
		LOGGER.config( "Rendering Thief image." );
		return classImage;

	}

	public BufferedImage renderRanger ( Graphics g , int linePosition , BufferedImage classImage ){

		classImage = Game.getImageManager().ranger;

		g.drawString( "Medium max HP and attack, but makes it up with" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "[experimental] ranged attack. Press (f) to enter " , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "target mode. If more than one enemy is visible," , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "cycle through enemies with L/R arrows, then (f)" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "again to fire. Not well tested, so expect weirdness." , 0 , linePosition );

		LOGGER.setLevel( Level.CONFIG);
		LOGGER.config( "Rendering Ranger image." );
		return classImage;

	}

	public BufferedImage renderWizard ( Graphics g , int linePosition , BufferedImage classImage ){

		classImage = Game.getImageManager().wizard;;

		g.drawString( "Physically weak, but has a powerful magic attack " , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "that deals damage to all enemies within a radius of" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "two tiles. Can also blink (random teleport) and" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "quicken to ready AoE spell by sacrificing 25% of" , 0 , linePosition );
		linePosition += g.getFont().getSize();
		g.drawString( "current health." , 0 , linePosition );

		LOGGER.setLevel( Level.CONFIG);
		LOGGER.config( "Rendering Wizard image." );
		return classImage;

	}

}
