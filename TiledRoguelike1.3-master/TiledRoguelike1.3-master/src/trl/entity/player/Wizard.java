package trl.entity.player;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.enemy.Enemy;
import trl.gamestate.GameplayState;
import trl.main.Game;
import trl.map.Map;
import trl.map.Node;

public class Wizard extends Player{

	public Wizard ( Map map ){

		super( map );
		this.maxHP = 30;
		this.attack = 5;
		this.image = Game.getImageManager().wizard;
		init();
	}
	
	// Log system from Wizard Class
	private final static Logger LOGGER = Logger.getLogger( Wizard.class.getName() );

	// Initialize Wizard
	public void init (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wizard intialized");

		this.hp = this.maxHP;
		this.myTurn = true;
		// timers[0] = blink, timers[1] = explode, timers[2] = quicken
		this.timers = new int[3];
		for ( @SuppressWarnings ( "unused" )
		int i : this.timers ){
			i = 0;
		}
	}

	// Magic action
	public void quicken (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wizard has used quicken magic");

		this.hp = (int) ( this.hp * .75 );
		this.timers[2] = 3;
		this.timers[1] = 0;
	}

	// Magic action
	public void explode (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wizard has used explode magic");

		List<Node> blastArea = this.map.getAoENodes( this.loc , 2 );
		int blastDamage = (int) ( Math.ceil( 6.0 + GameplayState.getPlayer().getLevel() / 1.1 ) );
		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){

			{
				if ( blastArea.contains( enemy.getLoc() ) ){

					enemy.setHP( enemy.getHP() - blastDamage );
					enemy.setStance( false , false , true , false );
				}else{

					// Nothing to do
				}

			}
		}
		this.timers[1] = 11;
	}

	// Magic action
	public void blink (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Wizzard has blinked");

		// Move to random node, make enemies forget player and player forget
		// enemies
		move( this.map.getRandomNode() );
		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){

			enemy.setAwareOfPlayer( false );
			enemy.setSeenByPlayer( false );
		}
		this.timers[0] = 31;
	}
}
