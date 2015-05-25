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

	@Override
	public void init (){

		this.choices = new String[] { "Yes" , "No" };
		this.choice = 0;
		this.enemiesDefeated = GameplayState.getPlayer().getEnemiesDefeated();
		this.turns = Game.turnCounter;
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Initializing choice array after win.");
		
	}

	@Override
	public void tick (){

		if ( up ){
			
			if ( this.choice == 0 ){
				
				this.choice = 1;
			}else{
				
				this.choice = 0;
			}
			
			up = false;
		}else{
			
			// Nothing
		}
		if ( down ){
			
			if ( this.choice == 1 ){
				
				this.choice = 0;
			}else{
				
				this.choice = 1;
			}
			
			down = false;
		}else{
			
			// Nothing
		}
		if ( enter ){
			
			if ( this.choice == 0 ){
				
				Game.getGameStateManager().removeGameState( 0 );
				Game.getGameStateManager().addGameState( 0 , new MenuState() );
				Game.getGameStateManager().setGameState( 0 );
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Option 'YES' choiced");
			}else{
				
				// Nothing
			}
			if ( this.choice == 1 ){
				
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

	@Override
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
		String exitMessage = "You won the game in " + this.turns + "turns after killing " + this.enemiesDefeated + " enemies";
		g.setColor( Color.WHITE );
		// g.drawString(exitMessage, (Game.W_WIDTH -
		// g.getFontMetrics().stringWidth(exitMessage)) / 2, 0);
		g.drawString( exitMessage , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( exitMessage ) ) / 2 , linePosition );
		linePosition += 16;
		g.drawString( "Play again?" , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( "Play again?" ) ) / 2 , linePosition );
		linePosition += 16;
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Rendering graph game");
		for ( int i = 0 ; i < this.choices.length ; i++ ){
			
			if ( i == this.choice ){
				
				g.setColor( Color.YELLOW );
			}else{
				
				g.setColor( Color.WHITE );
			}
			
			g.drawString( this.choices[i] , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( this.choices[i] ) ) / 2 , linePosition );
			linePosition += 16;
		}
	}
}
