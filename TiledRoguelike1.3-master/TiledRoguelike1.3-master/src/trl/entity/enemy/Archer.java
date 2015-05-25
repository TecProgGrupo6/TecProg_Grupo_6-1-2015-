package trl.entity.enemy;

import trl.main.Game;
import trl.map.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Archer extends Enemy{

	public Archer ( Map map ){

		super( map );
		init();
	}
	
	private final static Logger LOGGER = Logger.getLogger( Archer.class.getName() );

	// Initiliaze archer
	@Override
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Archer");

		this.maxHP = 5;
		this.attack = 5;
		this.image = Game.getImageManager().archer;
		this.hp = this.maxHP;
		this.xpReward = 1;
		this.level = 1;
	}
}
