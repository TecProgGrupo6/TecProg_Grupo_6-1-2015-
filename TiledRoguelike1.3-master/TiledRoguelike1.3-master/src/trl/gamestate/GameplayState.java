package trl.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.Entity;
import trl.entity.actor.ActorQueue;
import trl.entity.enemy.EnemyGroup;
import trl.entity.player.Barbarian;
import trl.entity.player.Player;
import trl.entity.player.Ranger;
import trl.entity.player.Thief;
import trl.entity.player.Wizard;
import trl.main.Game;
import trl.map.Map;
import trl.map.Node;

public class GameplayState extends GameState{

	public static final int TURN_DELAY = 10;

	public static int tickTimer;

	private static EnemyGroup enemyGroup;

	private static ActorQueue actorQueue;

	private static Player player;

	public static Map map;

	private int classChoice;

	public static int turnCounter;

	public static int dungeonLevel;

	public static int addEnemyInterval = 70; /*
											 * Number of turns on each level
											 * after which an enemy is added.
											 */
	public static boolean tickEnemies;

	// Log system from GameplayState Class
	private final static Logger LOGGER = Logger.getLogger( GameplayState.class.getName() );

	public GameplayState ( int classChoice ){

		this.classChoice = classChoice;
		dungeonLevel = 1;
		init();
	}

	public void init (){

		// If we're building the state from scratch
		setDungeonLevel();

		map.updateDisplayedNodes();
		map.updateVisibleToPlayer();
		map.updateImageMap();
		tickTimer = 0;

		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Player turns on level = " + player.getTurnsOnLevel() );

		int maxEnemies = dungeonLevel + ( GameplayState.getPlayer().getTurnsOnLevel() / GameplayState.addEnemyInterval );

		LOGGER.setLevel( Level.INFO );
		LOGGER.info( "Max enemies: " + dungeonLevel + " + (" + player.getTurnsOnLevel() + " / " + addEnemyInterval + ")" );

		// System.out.println("Enemy group size = " +
		// enemyGroup.getEnemies().size());
		// System.out.println("Actor queue size = ");
		enemyGroup.listEnemies();

	}

	// Set the new map depending on the level of the player and class
	public void setDungeonLevel (){

		if ( dungeonLevel == 1 ){
			map = new Map();
			enemyGroup = new EnemyGroup( map );
			actorQueue = new ActorQueue();
			switch ( classChoice ) {

			case 0:{

				LOGGER.setLevel( Level.FINE );
				LOGGER.fine( "Player chose barbarian" );

				player = new Barbarian( map );
				break;

			}
			case 1:{

				LOGGER.setLevel( Level.FINE );
				LOGGER.fine( "Player chose Thief" );

				player = new Thief( map );
				break;

			}
			case 2:{

				LOGGER.setLevel( Level.FINE );
				LOGGER.fine( "Player chose wizzard" );

				player = new Wizard( map );
				break;

			}
			case 3:{

				LOGGER.setLevel( Level.FINE );
				LOGGER.fine( "Player chose ranger" );

				player = new Ranger( map );
				break;

			}
			}
			actorQueue.addActor( player );

			enemyGroup.spawnEnemies();
		}else if ( dungeonLevel == 5 ){
			map.init();
			// map = new Map();
			enemyGroup.flush();
			enemyGroup = new EnemyGroup( map );
			actorQueue = new ActorQueue();
			player.refresh();
			actorQueue.addActor( player );
			enemyGroup.spawnEnemies();

			/*
			 * For now, we will randomly place the player in the newly created
			 * map. Later, we will add logic to ensure that the player enters
			 * the new map at the same coordinates he left the previous level
			 * from. This will involve modifying room generation to make sure
			 * that a room is generated around the location of the down stairway
			 * such that it doesn't end up in a wall or void space.
			 */

			player.initActor();

			player.refresh();

			if ( player instanceof trl.entity.player.Thief ){

				map.revealAll();
			}else{

				// Nothing to do
			}
			player.endTurn( actorQueue.getActor( 0 ) );
		}else{
			// map = new Map();

			LOGGER.setLevel( Level.INFO );
			LOGGER.info( "Refresh dungeon level " + dungeonLevel + "." );

			enemyGroup.flush();
			enemyGroup = null;
			actorQueue.flush();
			actorQueue = null;
			map.init();
			enemyGroup = new EnemyGroup( map );
			actorQueue = new ActorQueue();
			// Set turns on level to zero before spawning enemies.
			// player.setTurnsOnLevel(0);
			actorQueue.addActor( player );
			enemyGroup.spawnEnemies();
			/*
			 * For now, we will randomly place the player in the newly created
			 * map. Later, we will add logic to ensure that the player enters
			 * the new map at the same coordinates he left the previous level
			 * from. This will involve modifying room generation to make sure
			 * that a room is generated around the location of the down stairway
			 * such that it doesn't end up in a wall or void space.
			 */
			player.initActor();
			player.refresh();

			if ( player instanceof trl.entity.player.Thief ){

				map.revealAll();
			}else{

				// Nothing to do
			}

			player.endTurn( actorQueue.getActor( 0 ) );
		}

	}

	public void tick (){

		if ( tickEnemies ){

			tickEnemies = false;
		}else{

			tickEnemies = true;
		}
		if ( !player.isAlive() ){

			Game.getGameStateManager().addGameState( 2 , new LoseGameState( player.getEnemiesDefeated() ) );
			Game.getGameStateManager().setGameState( 2 );
		}else{

			// Nothing to do
		}

		actorQueue.tick();
	}

	public void render ( Graphics g ){

		map.render( g );
		player.render( g );
		enemyGroup.render( g );
		drawStatus( g );
		drawMiniMap( g );
	}

	public static Player getPlayer (){

		return player;
	}

	public static ActorQueue getActorQueue (){

		return actorQueue;
	}

	public void drawStatus ( Graphics g ){

		g.setColor( Color.black );
		g.fillRect( 0 , Game.W_HEIGHT + Game.SCALE , Game.W_WIDTH , Game.MSG_HEIGHT );
		g.setColor( Color.yellow );
		g.drawString( "Level:" + player.getLevel() , 0 , Game.W_HEIGHT + g.getFont().getSize() );
		g.drawString( "Player HP: " + player.getHP() , 0 , Game.W_HEIGHT + 2 * g.getFont().getSize() + 2 );
		g.drawString( "Dungeon Level: " + dungeonLevel , 0 , Game.W_HEIGHT + 3 * g.getFont().getSize() + 2 );
		g.drawString( "Player XP: " + player.getXPEarned() , 0 , Game.W_HEIGHT + 4 * g.getFont().getSize() + 2 );
		g.drawString( "Enemies: " + player.getEnemiesDefeated() , 0 , Game.W_HEIGHT + 5 * g.getFont().getSize() + 2 );

		if ( player instanceof trl.entity.player.Barbarian ){

			drawStatusBarbarian( g );
		}
		if ( player instanceof trl.entity.player.Ranger ){

			// if (player.getTimers()[0] <= 0) {
			g.setColor( Color.green );
			g.drawString( "(f) Fire Arrow" , 200 , Game.W_HEIGHT + g.getFont().getSize() );
			// }
			// else {
			// g.setColor(Color.red);
			// g.drawString("(f) Fire Arrow (" + player.getTimers()[0] + ")",
			// 200, Game.W_HEIGHT + 16);
			// }
		}
		if ( player instanceof trl.entity.player.Wizard ){

			drawStatusWizzard( g );

		}else{

			// Nothing to do
		}

		g.setColor( Color.green );
		g.drawString( "(c) Close Door" , 300 , Game.W_HEIGHT + g.getFont().getSize() );

		boolean ifPlayerHasKeyAndDoorIsClosed = player.getHasKey() && !player.getOpenedLock();

		if ( ifPlayerHasKeyAndDoorIsClosed ){

			g.drawImage( Game.getImageManager().key , 400 , Game.W_HEIGHT , Game.TILE_SIZE , Game.TILE_SIZE , null );
		}else{

			// Nothing to do
		}

		if ( player.getOpenedLock() ){

			g.drawImage( Game.getImageManager().lockOpen , 400 , Game.W_HEIGHT , Game.TILE_SIZE , Game.TILE_SIZE , null );
		}else{

			// Nothing to do
		}

	}

	public void drawStatusBarbarian ( Graphics g ){

		if ( player.getTimers()[0] <= 0 ){

			g.setColor( Color.green );
			g.drawString( "(s) Shout" , 200 , Game.W_HEIGHT + g.getFont().getSize() );
		}else{

			g.setColor( Color.red );
			g.drawString( "(s) Shout (" + player.getTimers()[0] + ")" , 200 , Game.W_HEIGHT + g.getFont().getSize() );
		}

	}

	public void drawStatusWizzard ( Graphics g ){

		if ( player.getTimers()[0] <= 0 ){

			g.setColor( Color.green );
			g.drawString( "(b) Blink" , 200 , Game.W_HEIGHT + g.getFont().getSize() );
		}else{

			g.setColor( Color.red );
			g.drawString( "(b) Blink (" + player.getTimers()[0] + ")" , 200 , Game.W_HEIGHT + g.getFont().getSize() );
		}

		if ( player.getTimers()[1] <= 0 ){

			g.setColor( Color.green );
			g.drawString( "(e) Explode" , 200 , Game.W_HEIGHT + 2 * g.getFont().getSize() + 2 );
		}else{

			g.setColor( Color.red );
			g.drawString( "(e) Explode (" + player.getTimers()[1] + ")" , 200 , Game.W_HEIGHT + 2 * g.getFont().getSize() + 2 );
		}

		if ( player.getTimers()[2] <= 0 ){

			if ( player.getHP() > 4 ){

				g.setColor( Color.green );
			}else{

				g.setColor( Color.red );
			}

			g.drawString( "(q) Quicken" , 200 , Game.W_HEIGHT + 3 * g.getFont().getSize() + 2 );
		}else{

			g.setColor( Color.red );
			g.drawString( "(q) Quicken (" + player.getTimers()[2] + ")" , 200 , Game.W_HEIGHT + 3 * g.getFont().getSize() + 2 );
		}

	}

	public void drawMiniMap ( Graphics g ){

		Color trBlack = new Color( 0 , 0 , 0 , 64 );
		Color trGray = new Color( 128 , 128 , 128 , 196 );
		Color trWhite = new Color( 255 , 255 , 255 , 196 );
		Color brown = new Color( 128 , 69 , 19 );

		int scale = 3;
		int startX = Game.W_WIDTH - ( Game.COLUMNS * scale ) - Game.W_ROWS;

		for ( int x = 0 ; x < Game.COLUMNS ; x++ ){

			for ( int y = 0 ; y < Game.ROWS ; y++ ){

				// Default color. Carries through if no other condition matches
				g.setColor( trBlack );

				// Set borders white

				boolean xOrYEqualsZero = ( x == 0 || y == 0 );
				boolean xOrYEqualsColumsOrRowsMinusOne = ( x == Game.COLUMNS - 1 || y == Game.ROWS - 1 );

				if ( xOrYEqualsZero || xOrYEqualsColumsOrRowsMinusOne ){

					g.setColor( trWhite );
				}else{

					// Nothing to do
				}

				Node node = map.getNode( x , y );

				if ( node != null ){

					if ( node.seenByPlayer() ){

						drawMiniMapViaPlayer( node , g , trGray , brown );

					}
				}
				// if (g.getColor() == null) {
				// System.out.println("Color NULL");
				// g.setColor(trBlack);
				// }
				g.fillRect( startX + ( x * scale ) , ( Game.ROWS * scale ) - y * scale , scale , scale );
			}
		}
	}

	// Draw mini map as the player walks
	public void drawMiniMapViaPlayer ( Node node , Graphics g , Color trGray , Color brown ){

		if ( node.getFeature().isPassable() ){

			g.setColor( trGray );
		}else{

			// Nothing to do
		}

		if ( node.isStairDown() ){

			g.setColor( Color.PINK );
		}else{

			// Nothing to do
		}

		if ( node.isClosedDoor() ){

			g.setColor( brown );
		}else{

			// Nothing to do
		}

		if ( node.getEntities() != null && node.getEntities().size() > 0 ){

			for ( Entity entity : node.getEntities() ){

				if ( entity.seenByPlayer() ){

					if ( entity instanceof trl.entity.item.Potion ){

						g.setColor( Color.GREEN );
					}else{

						// Nothing to do
					}

					if ( entity instanceof trl.entity.player.Player ){

						g.setColor( Color.WHITE );
					}else{

						// Nothing to do
					}

					if ( entity instanceof trl.entity.enemy.Enemy ){

						g.setColor( Color.RED );
					}else{

						// Nothing to do
					}

					if ( entity instanceof trl.entity.item.Hammer ){

						g.setColor( Color.GREEN );
					}else{

						// Nothing to do
					}

					if ( entity instanceof trl.entity.item.Key ){

						g.setColor( Color.YELLOW );
					}else{

						// Nothing to do
					}

					if ( entity instanceof trl.entity.item.Lock ){

						g.setColor( Color.YELLOW );
					}else{

						// Nothing to do
					}
				}else{

					// Nothing to do
				}
			}
		}

	}

	public static EnemyGroup getEnemyGroup (){

		return enemyGroup;
	}

	public static Map getMap (){

		return map;
	}

	// public static ImageManager getImageManager() {
	// return im;
	// }

	public int getDungeonLevel (){

		return dungeonLevel;
	}

	public void setDungeonLevel ( int dungeonLevel ){

		this.dungeonLevel = dungeonLevel;
	}
}
