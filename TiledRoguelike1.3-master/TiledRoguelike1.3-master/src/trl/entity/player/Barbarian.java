package trl.entity.player;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.enemy.Enemy;
import trl.gamestate.GameplayState;
import trl.main.Game;
import trl.map.Map;

public class Barbarian extends Player{

	public Barbarian ( Map map ){

		super( map );
		this.maxHP = 50;
		this.attack = 10;
		this.image = Game.getImageManager().barbarian;
		init();
	}
	
	private final static Logger LOGGER = Logger.getLogger( Barbarian.class.getName() );

	// Initializes Barbarian
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Barbarian intialized");

		this.hp = this.maxHP;
		this.myTurn = true;
		this.timers = new int[1];
	}

	// Action to shout
	public void shout (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Barbarian has shouted");

		// Cause all enemies to set path to node occupied by player
		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){
			enemy.setPathTo( this.loc );
			this.timers[0] = 31;
		}
	}

}
