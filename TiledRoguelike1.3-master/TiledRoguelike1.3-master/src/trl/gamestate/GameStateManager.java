package trl.gamestate;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameStateManager{

	private List<GameState> gameStates;
	public static final int MENU_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;
	private GameState currentGameState;

	public GameStateManager (){

		init();
	}

	public void init (){

		gameStates = new ArrayList<GameState>();
		gameStates.add( 0 , new MenuState() ); // Add menu state in the first position of list.
		currentGameState = gameStates.get( 0 ); // Get the first game state of list.
	}

	public void tick (){

		currentGameState.tick();
	}

	public void render ( Graphics g ){

		currentGameState.render( g );
	}

	public void setGameState ( int indexState ){

		currentGameState = gameStates.get( indexState );
	}

	public int getGameState (){

		return gameStates.indexOf( currentGameState );
	}

	public GameState getGameState ( int index ){

		return gameStates.get( index );
	}

	public void addGameState ( int index , GameState gameState ){

		gameStates.add( index , gameState );
	}

	public void removeGameState ( int indexGameState ){

		int i = 0;
		for ( Iterator<GameState> iteratorGameState = gameStates.iterator() ; iteratorGameState.hasNext() ; ){
			GameState nextGameState = iteratorGameState.next();
			if ( i == indexGameState ){
				iteratorGameState.remove();
				
			}else{
				// nothing
			}
			i++;
		}
	}
}
