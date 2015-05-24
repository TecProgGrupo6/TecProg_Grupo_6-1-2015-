package trl.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;

public class WinGameState extends GameState{

	private int enemiesDefeated;
	private int turns;
	private String[] choices;
	public static boolean up = false , down = false , enter = false;
	private int choice;
	private final static Logger LOGGER = Logger.getLogger( WinGameState.class.getName() );

	// public WinGameState(int enemiesDefeated, int turns) {
	public WinGameState (){

		// this.enemiesDefeated = enemiesDefeated;
		init();
	}

	public void init (){

		choices = new String[] { "Yes" , "No" };
		choice = 0;
		enemiesDefeated = GameplayState.getPlayer().getEnemiesDefeated();
		turns = Game.turnCounter;
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Initializing choice array after win.");
		
	}

	public void tick (){

		if ( up ){
			if ( choice == 0 ){
				choice = 1;
			}else{
				choice = 0;
			}
			up = false;
		}else{
			// Nothing
		}
		if ( down ){
			if ( choice == 1 ){
				choice = 0;
			}else{
				choice = 1;
			}
			down = false;
		}else{
			// Nothing
		}
		if ( enter ){
			if ( choice == 0 ){
				Game.getGameStateManager().removeGameState( 0 );
				Game.getGameStateManager().addGameState( 0 , new MenuState() );
				Game.getGameStateManager().setGameState( 0 );
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Option 'YES' choiced");
			}else{
				// Nothing
			}
			if ( choice == 1 ){
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Option 'NO' choiced");
				Game.running = false;
			}else{
				// Nothing
			}
			enter = false;
		}else{
			// Nothing
		}

	}

	public void render ( Graphics g ){

		// int turnsPlayed = Game.turnCounter;
		int originX = Game.W_WIDTH / 4;
		int originY = Game.W_HEIGHT / 4;
		int width = Game.W_WIDTH / 2;
		int height = Game.W_HEIGHT / 2;
		g.setColor( Color.BLACK );
		g.fillRect( originX , originY , width , height );
		int linePosition = originY;
		linePosition += 16;
		String exitMessage = "You won the game in " + turns + "turns after killing " + enemiesDefeated + " enemies";
		g.setColor( Color.WHITE );
		// g.drawString(exitMessage, (Game.W_WIDTH -
		// g.getFontMetrics().stringWidth(exitMessage)) / 2, 0);
		g.drawString( exitMessage , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( exitMessage ) ) / 2 , linePosition );
		linePosition += 16;
		g.drawString( "Play again?" , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( "Play again?" ) ) / 2 , linePosition );
		linePosition += 16;
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Rendering graph game");
		for ( int i = 0 ; i < choices.length ; i++ ){
			if ( i == choice ){
				g.setColor( Color.YELLOW );
			}else{
				g.setColor( Color.WHITE );
			}
			g.drawString( choices[i] , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( choices[i] ) ) / 2 , linePosition );
			linePosition += 16;
		}
	}
}
