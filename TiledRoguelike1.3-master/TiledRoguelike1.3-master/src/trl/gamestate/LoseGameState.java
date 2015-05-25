package trl.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;

public class LoseGameState extends GameState{

	private int enemiesDefeated;
	private String[] choices;
	public static boolean up = false , down = false , enter = false;
	private int choice;
	private final static Logger LOGGER = Logger.getLogger( LoseGameState.class.getName() );

	public LoseGameState ( int enemiesDefeated ){

		this.enemiesDefeated = enemiesDefeated;
		init();
	}

	@Override
	public void init (){

		this.choices = new String[] { "Yes" , "No" };
		this.choice = 0;
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Initializing choice array after lose.");
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
			
			// nothing
		}
		if ( down ){
			
			if ( this.choice == 1 ){
				
				this.choice = 0;
			}else{
				
				this.choice = 1;
			}
			
			down = false;
		}else{
			
			// nothing
		}
		if ( enter ){
			
			if ( this.choice == 0 ){
				
				Game.getGameStateManager().removeGameState( 0 );
				Game.getGameStateManager().addGameState( 0 , new MenuState() );
				Game.getGameStateManager().setGameState( 0 );
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Option 'YES' choiced");
				
			}else{
				
				// nothing
			}
			
			if ( this.choice == 1 ){
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Option 'NO' choiced");
				Game.running = false;
			}else{
				
				// nothing
			}
			
			enter = false;
		}else{
			
			// nothing
		}
		
	}

	@Override
	public void render ( Graphics g ){

		final int QUARTER = 4;
		final int HALF = 2;
		
		int originX = Game.W_WIDTH / QUARTER;
		int originY = Game.W_HEIGHT / QUARTER;
		int width = Game.W_WIDTH / HALF;
		int height = Game.W_HEIGHT / HALF;
		
		g.setColor( Color.BLACK );
		g.fillRect( originX , originY , width , height );
		
		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("Rendering graph game");
		
		int linePosition = originY;
		linePosition = linePosition + 16;
		
		String exitMessage = "You were defeated after killing " + this.enemiesDefeated + " enemies";
		
		g.setColor( Color.WHITE );
		// g.drawString(exitMessage, (Game.W_WIDTH -
		// g.getFontMetrics().stringWidth(exitMessage)) / 2, 0);
		
		g.drawString( exitMessage , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( exitMessage ) ) / HALF , linePosition );
		linePosition = linePosition + 16;
		
		g.drawString( "Play again?" , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( "Play again?" ) ) / HALF , linePosition );
		linePosition = linePosition + 16;
		
		for ( int i = 0 ; i < this.choices.length ; i++ ){
			
			if ( i == this.choice ){
				
				g.setColor( Color.YELLOW );
			}else{
				
				g.setColor( Color.WHITE );
			}
			
			g.drawString( this.choices[i] , ( Game.W_WIDTH - g.getFontMetrics().stringWidth( this.choices[i] ) ) / HALF , linePosition );
			linePosition = linePosition + 16;
			
			
		}
	}
}
