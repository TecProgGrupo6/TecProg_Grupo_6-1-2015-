package trl.entity.player;

import java.util.logging.Level;
import java.util.logging.Logger;

import trl.main.Game;
import trl.map.Map;

public class Thief extends Player{

	public Thief ( Map map ){

		super( map );
		this.maxHP = 40;
		this.attack = 7;
		this.image = Game.getImageManager().thief;
		init();
	}
	// Log system from Thief Class
	private final static Logger LOGGER = Logger.getLogger( Thief.class.getName() );

	// Initialize Thief
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Thief intialized");

		this.hp = maxHP;
		this.myTurn = true;
		map.revealAll();
		timers = new int[0];
	}
}
