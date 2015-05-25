package trl.gamestate;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameStateManager{

	private List<GameState> gameStates;
	public static final int MENU_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;
	private GameState currentGameState;
	private final static Logger LOGGER = Logger.getLogger( GameStateManager.class.getName() );

	public GameStateManager (){

		init();
	}

	public void init (){

		this.gameStates = new ArrayList<GameState>();
		this.gameStates.add( 0 , new MenuState() ); // Add menu state in the first position of list.
		this.currentGameState = this.gameStates.get( 0 ); // Get the first game state of list.
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Initializing GameStateManager. Current game state = " + this.currentGameState.toString() );
	}

	public void tick (){

		this.currentGameState.tick();
	}

	public void render ( Graphics g ){

		this.currentGameState.render( g );
	}

	public void setGameState ( int indexState ){

		this.currentGameState = this.gameStates.get( indexState );
	}

	public int getGameState (){

		return this.gameStates.indexOf( this.currentGameState );
	}

	public GameState getGameState ( int index ){

		return this.gameStates.get( index );
	}

	public void addGameState ( int index , GameState gameState ){

		this.gameStates.add( index , gameState );
	}

	public void removeGameState ( int indexGameState ){
		
		int i = 0;
		for ( Iterator<GameState> iteratorGameState = this.gameStates.iterator() ; iteratorGameState.hasNext() ; ){
			@SuppressWarnings ( "unused" )
			GameState nextGameState = iteratorGameState.next();
			if ( i == indexGameState ){
				iteratorGameState.remove();
				
				
			}else{
				// nothing
			}
			i++;
		}
		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Game state complete removed." );
	}
}
