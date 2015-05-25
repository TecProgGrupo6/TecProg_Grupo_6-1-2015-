package trl.entity.player;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.Entity;
import trl.entity.actor.Actor;
import trl.entity.enemy.Enemy;
import trl.entity.item.Goal;
import trl.entity.item.Hammer;
import trl.entity.item.Lock;
import trl.entity.item.Potion;
import trl.gamestate.GameplayState;
import trl.gamestate.WinGameState;
import trl.main.Game;
import trl.map.Map;
import trl.map.Node;
import trl.map.feature.StairDown;

public abstract class Player extends Actor{

	// Counts the total of enemies defeated
	protected int enemiesDefeated;

	// Checks if the player has the key
	protected boolean hasKey = false;

	// Represents the enemies visible
	protected boolean newEnemies = false; // if new enemy became visible, set
											// true

	// Checks if a lock is opened
	protected boolean openedLock = false;

	// Represents the enemy which the player can interect
	protected Enemy target;

	// Represents the list of enemies
	protected List<Enemy> targets;

	// Represents the regen of the health
	protected int turnsOnLevel;

	// Represents the regen on battle of the barbarian character
	protected int turnsSinceCombat;

	public boolean upDirection = false;

	public boolean downDirection = false;

	public boolean leftDirection = false;

	public boolean rightDirection = false;

	public boolean upRightDirection = false;

	public boolean downRightDirection = false;

	public boolean downLeftDirection = false;

	public boolean upLeftDirection = false;

	// Represents the ending of turn witout action
	public boolean wait = false;

	// Shout action of the player
	public boolean shout = false;

	// Represents blink state of the player
	public boolean blink = false;

	// Action from the wizzard character
	public boolean explode = false;

	// Represents the action to close
	public boolean close = false;

	// Action from the wizzard character
	public boolean quicken = false;

	public boolean targetEnemy = false;

	// Action of the the ranger character
	public boolean fireArrow = false;

	// Target got
	public boolean gotTargets = false;

	// Represents the order of the actions on the targets
	public boolean nextTarget = false;

	// Represents the order of the actions on the targets
	public boolean previousTarget = false;

	// Action to interect with target
	public boolean getTargets = false;

	// Represents cancel state ( action on target ) of the player
	public boolean cancel = false;

	// Represents the total amount of xp from the player
	protected double xpEarned;
	
	// Log system from Player Class
	private final static Logger LOGGER = Logger.getLogger( Player.class.getName() );

	public Player ( Map map ){

		super( map );
		this.level = 1;
	}

	// ?
	public boolean adjacentEnemy (){

		boolean adjacentEnemy = false;

		for ( int x = -1 ; x <= 1 ; x++ ){

			for ( int y = 1 ; y >= 0 ; y-- ){

				boolean xGreaterThanZeroAndLessThanColumns = x >= 0 && x < Game.COLUMNS;
				boolean yGreaterThanZeroAndLessThanRows = y >= 0 && y < Game.ROWS;

				if ( xGreaterThanZeroAndLessThanColumns && yGreaterThanZeroAndLessThanRows ){

					int summX = getAxisX() + x;
					int summY = getAxisY() + y;

					Node adjacent = this.map.getNode( summX , summY );

					if ( adjacent != null ){

						if ( adjacent.checkEntityByID( (byte) 0 ) ){
							
							LOGGER.setLevel( Level.INFO );
							LOGGER.info("Adjacent enemy. Can't shoot.");

							adjacentEnemy = true;
						}else{

							adjacentEnemy = false;

						}
					}
				}
			}
		}

		return adjacentEnemy;
	}

	// See enemies on screen
	public void alertEnemies (){

		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){

			if ( this.map.isVisibleToPlayer( enemy.getLoc() ) ){

				enemy.setAwareOfPlayer( true );
				
			}else{

				// Nothing to do
			}
		}
	}

	// Clear All
	public void clearAllTargetingFlags (){

		this.targetEnemy = false;
		this.fireArrow = false;
		this.gotTargets = false;
		this.nextTarget = false;
		this.previousTarget = false;
		this.getTargets = false;
		this.targets = null;

		if ( this.target != null ){

			this.target.setTargeted( false );
		}else{

			// Nothing to do
		}

		this.target = null;
	}

	// Earning xp
	public void gainXP ( double xp ){

		this.xpEarned += xp;
	}

	public int getEnemiesDefeated (){

		return this.enemiesDefeated;
	}

	public boolean getHasKey (){

		return this.hasKey;
	}

	public boolean getOpenedLock (){

		return this.openedLock;
	}

	public int getTurnsOnLevel (){

		return this.turnsOnLevel;
	}

	public double getXPEarned (){

		return this.xpEarned;
	}

	// Handle movement by some item
	public void handleMovementResult (){

		// If potion in this node, consume and remove
		if ( this.loc.checkEntityByID( (byte) 1 ) ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Potion has been used");

			this.loc.removeEntityByID( (byte) 1 );
			this.hp = this.maxHP;
		}else{

			// Nothing to do
		}

		// If hammer in this node, consume and remove
		if ( this.loc.checkEntityByID( (byte) 2 ) ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Hammer has been used");

			useHammer();
			this.loc.removeEntityByID( (byte) 2 );
		}else{

			// Nothing to do
		}

		// If key in this node, pick it up and spawn lock.
		if ( this.loc.checkEntityByID( (byte) 5 ) ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Key has been found");

			this.hasKey = true;
			this.loc.removeEntityByID( (byte) 5 );

			Node randomNode = this.map.getRandomNodeInRoom();
			this.map.placeEntity( new Lock( this.map ) , randomNode );

			if ( this instanceof trl.entity.player.Thief ){

				for ( Entity entity : randomNode.getEntities() ){

					entity.setSeenByPlayer( true );
				}
			}else{

				// Nothing to do
			}

		}else{

			// Nothing to do
		}

		// If lock in this node, remove it and spawn stair (or goal).
		if ( this.loc.checkEntityByID( (byte) 6 ) ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Stair's been spawned");

			this.openedLock = true;
			this.loc.removeEntityByID( (byte) 6 );
			if ( GameplayState.dungeonLevel == 5 ){

				Goal goal = new Goal( this.map );
				if ( this instanceof trl.entity.player.Thief ){

					goal.setSeenByPlayer( true );
				}else{

					// Nothing to do
				}

			}else{

				this.map.getRandomNodeInRoom().setFeature( new StairDown() );
			}
		}

		// If down stairway on this node, switch level
		if ( this.loc.isStairDown() ){

			GameplayState.getEnemyGroup().getEnemies().clear();
			GameplayState gameState = (GameplayState) Game.getGameStateManager().getGameState( 1 );
			GameplayState.getEnemyGroup().listEnemies();
			gameState.setDungeonLevel( gameState.getDungeonLevel() + 1 );
			gameState.init();

		}else{

			// Nothing to do
		}

		if ( this.loc.checkEntityByID( (byte) 7 ) ){

			Game.getGameStateManager().addGameState( 2 , new WinGameState() );
			Game.getGameStateManager().setGameState( 2 );

		}else{

			// Nothing to do
		}

	}

	// Moves through the map
	public void handlePath (){

		// Ignore all this if path is empty

		boolean isPathEmptyAndSizeGreaterZero = ( this.path != null && this.path.size() > 0 );

		if ( isPathEmptyAndSizeGreaterZero ){

			boolean isPathSizeAndInitialEqualsOne = this.path.size() == 1 && this.initialPathSize == 1;
			boolean isNodePathCkecksZero = getNextPathNode().checkEntityByID( (byte) 0 );
			boolean pathLowerInitialAndPathEntity = ( this.path.size() < this.initialPathSize ) && getNextPathNode().checkEntityByID( (byte) 0 );

			boolean pathAndInitialPathEqualsOne = this.path.size() == 1 && this.initialPathSize == 1;
			boolean nextPathNodeGetFeatureClose = getNextPathNode().getFeature() instanceof trl.map.feature.DoorClosed;
			boolean pathSizeGreaterOneAndPathLowerIniticial = this.path.size() >= 1 && this.path.size() < this.initialPathSize;

			boolean nextPathNodeGetFeatureOpen = getNextPathNode().getFeature() instanceof trl.map.feature.DoorOpen;

			// Consider this a deliberate attempt to attack enemy.
			if ( isPathSizeAndInitialEqualsOne && isNodePathCkecksZero ){

				LOGGER.setLevel( Level.FINE );
				LOGGER.fine("Attacking enemy!!!");
				
				attack( GameplayState.getEnemyGroup().getEnemy( getNextPathNode() ) );
				GameplayState.getEnemyGroup().getEnemy( this.path.get( 0 ) ).setDamageTaken( this.damageDealt );

				this.path.clear();
				this.attacked = true;
			}

			/*
			 * Path size less than initial path size and enemy in next node.
			 * This looks like the player ran into the enemy while traveling a
			 * longer path. Don't consider this a deliberate attack
			 */

			else if ( pathLowerInitialAndPathEntity ){
				
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Blocked by enemy/forgetting path.");
				
				this.path.clear();
				this.acted = true;
			}

			/*
			 * Path size == 1 and initial path size == 1 and closed door in next
			 * node. consider this a deliberate attempt to open the door.
			 */
			else if ( pathAndInitialPathEqualsOne && nextPathNodeGetFeatureClose ){
				
				LOGGER.setLevel( Level.FINE );
				LOGGER.fine("Moving.");

				openDoor( getNextPathNode() );
				this.path.clear();
				this.moved = true;
			}

			/*
			 * Path size less than initial path size and closed door in next
			 * node. This looks like the player ran into the closed door while
			 * traveling a longer path. Don't consider this a deliberate attempt
			 * to open the door.
			 */
			else if ( pathSizeGreaterOneAndPathLowerIniticial && nextPathNodeGetFeatureClose ){
				
				LOGGER.setLevel( Level.INFO );
				LOGGER.info("Blocked by door/forgetting path.");
				
				this.path.clear();
				this.acted = true;
			}

			/*
			 * Player close flag set.
			 */
			else if ( this.close ){

				if ( nextPathNodeGetFeatureOpen ){

					getNextPathNode().makeClosedDoor();
				}else{

					// Nothing to do
				}

				this.path.clear();
				this.close = false;
				this.acted = true;
			}

			// Move to next node
			else{

				move( getNextPathNode() );
				this.moved = true;
			}
		}
	}

	// Do some special movements
	public void handleSpecialActions (){

		/*
		 * Methods defined in class files for each player class. Actor is upcast
		 * for method calls. Should stay safe due to player class checks.
		 */
		if ( this.shout ){

			if ( this.timers[0] <= 0 ){

				( (Barbarian) this ).shout();
				this.acted = true;
			}else{

				// Nothing to do
			}

			this.shout = false;
		}else{

			// Nothing to do
		}

		if ( this.blink ){

			if ( this.timers[0] <= 0 ){

				( (Wizard) this ).blink();
				this.acted = true;
			}else{

				// Nothing to do
			}

			this.blink = false;
		}else{

			// Nothing to do
		}

		if ( this.explode ){

			if ( this.timers[1] <= 0 ){
				( (Wizard) this ).explode();
				this.acted = true;
			}else{

				// Nothing to do
			}

			this.explode = false;
		}else{

			// Nothing to do
		}

		if ( this.quicken ){

			if ( this.timers[2] <= 0 ){

				( (Wizard) this ).quicken();
				this.acted = true;
			}else{

				// Nothing to do
			}

			this.quicken = false;
		}else{

			// Nothing to do
		}

		/*
		 * If KeyManager says we're supposed to look for targets (getTargets),
		 * but haven't yet (!gotTargets)
		 */

		if ( this.getTargets && !this.gotTargets ){

			if ( !adjacentEnemy() ){

				/*
				 * Returns 0-size list if no targets found, not null! targets
				 * list size = 0, clear all flags. Should continue turn as
				 * normal.
				 */
				this.targets = ( (Ranger) this ).getTargets();

				if ( this.targets.size() == 0 ){

					System.out.println( "Targets list size = 0. No targets found. Canceling targeting." );
					clearAllTargetingFlags();
				}
				/*
				 * targets list size != 0, so we found at least one target. Stop
				 * looking (getTargets = false) and allow target
				 * switching(gotTargets = true)
				 */
				else{

					this.getTargets = false;
					this.gotTargets = true;
				}
			}else{

				clearAllTargetingFlags();
			}
		}else{

			// Nothing to do
		}

		// Should only be possible if targets list size > 0
		if ( !this.getTargets && this.gotTargets ){

			// Allow player to cancel targeting
			if ( this.cancel ){

				clearAllTargetingFlags();
			}else{

				/*
				 * On first pass through, target will be null. If target is
				 * null, it's safe for all conditions to assign target the first
				 * element in targets list. This takes care of 1-size target
				 * lists, since they can never be anything but the first element
				 * in targets list.
				 */
				if ( this.target == null ){

					// System.out.println("Target list size = " +
					// targets.size());
					this.target = this.targets.get( 0 );
					this.target.setTargeted( true );
				}
				/*
				 * Target is not null, so we must have passed through at least
				 * once. On subsequent passes, we need to handle
				 * nextTarget/previousTarget for enemies lists with size > 1.
				 * 0-size lists should not exist, and 1-size lists keep their
				 * assigned values of enemies.get(0)
				 */
				else{

					if ( this.targets.size() > 1 ){

						int targetsSizeMinusOne = ( this.targets.size() - 1 );

						// Targets list size must be > 1. Consider
						// nextTarget/previousTarget meaningful, but
						// clear them after each pass.
						if ( this.nextTarget ){

							// Changing target, untarget current target
							this.target.setTargeted( false );
							// If not last target in list, target = current
							// target index + 1
							if ( targetsSizeMinusOne > this.targets.indexOf( this.target ) ){

								this.target = ( this.targets.get( this.targets.indexOf( this.target ) + 1 ) );
							}
							// Else target wraps around to first target
							else{

								this.target = this.targets.get( 0 );
							}
							// Changed target, target new target
							this.target.setTargeted( true );
						}else{

							// Nothing to do
						}

						if ( this.previousTarget ){

							this.target.setTargeted( false );
							// If not first target, target = current target
							// index - 1
							if ( this.targets.indexOf( this.target ) > 0 ){

								this.target = this.targets.get( this.targets.indexOf( this.target ) - 1 );
							}
							// Else target wraps around to last in list
							else{

								this.target = this.targets.get( targetsSizeMinusOne );
							}
							// Changed target, target new target
							this.target.setTargeted( true );
						}else{

							// Nothing to do
						}

					}
				}
			}
			this.previousTarget = false;
			this.nextTarget = false;
		}

		if ( this.fireArrow ){

			( (Ranger) this ).fireArrow( this.target );
			clearAllTargetingFlags();
			this.acted = true;
		}else{

			// Nothing to do
		}

	}

	// Check number of enemies defetead
	public void incrementEnemiesDefeated ( int enemiesDefeatedParam ){

		this.enemiesDefeated += enemiesDefeatedParam;
	}

	// Check level
	public void levelUp (){
		
		LOGGER.setLevel( Level.INFO );
		LOGGER.info("Level up!");

		double percentHealth = (double) this.hp / (double) this.maxHP;
		// System.out.println("Percent health = " + percentHealth);
		this.level++;
		double exponent = 1 / this.level;
		this.maxHP = this.maxHP + (int) Math.pow( this.level , 1.0 + exponent );
		this.hp = (int) ( percentHealth * this.maxHP );
	}

	// print on screen
	public void printEnemiesList (){

		System.out.println( "=== Enemies ===" );
		System.out.println( "Player at " + getAxisX() + "," + getAxisY() );

		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){

			String enemyPrint = ( enemy.toString().substring( enemy.toString().lastIndexOf( '.' ) ) + " at " + enemy.getAxisX() + ","
					+ enemy.getAxisY() + " v2p=" + enemy.getVisibleToPlayer() + " ,aop=" + enemy.awareOfPlayer() + ", sbp=" + enemy
					.seenByPlayer() );

			System.out.println( enemyPrint );
		}
	}

	// refresh screen?
	public void refresh (){

		this.hasKey = false;
		this.openedLock = false;
		this.turnsOnLevel = 0;
	}

	// random function of items
	public void rollItems (){

		// Roll for item creation
		Random r = new Random();
		double roll = r.nextDouble();
		if ( roll >= .995 ){

			if ( !this.map.hammerOnMap() ){

				Hammer hammer = new Hammer( this.map );
				if ( GameplayState.getPlayer() instanceof trl.entity.player.Thief ){

					hammer.setSeenByPlayer( true );
				}else{

					// Nothing to do
				}

			}
		}else if ( roll >= .9 ){

			if ( !this.map.potionOnMap() ){

				Potion potion = new Potion( this.map );
				if ( GameplayState.getPlayer() instanceof trl.entity.player.Thief ){

					potion.setSeenByPlayer( true );
				}else{

					// Nothing to do
				}

			}else{

				// Nothing to do
			}

		}
	}

	public Node setDirection (){

		Node nextNode = null;

		final int FIRST_PIXEL = 1;

		if ( this.upDirection && getAxisY() < Game.ROWS - 1 ){

			this.upDirection = false;
			nextNode = this.map.getNode( getAxisX() , getAxisY() + 1 );
		}else{

			// Nothing to do
		}

		if ( this.downDirection && getAxisY() >= FIRST_PIXEL ){

			this.downDirection = false;
			nextNode = this.map.getNode( getAxisX() , getAxisY() - 1 );
		}else{

			// Nothing to do
		}

		if ( this.leftDirection && getAxisX() >= FIRST_PIXEL ){

			this.leftDirection = false;
			nextNode = this.map.getNode( getAxisX() - 1 , getAxisY() );
		}else{

			// Nothing to do
		}

		if ( this.rightDirection && getAxisX() < Game.COLUMNS - 1 ){

			this.rightDirection = false;
			nextNode = this.map.getNode( getAxisX() + 1 , getAxisY() );
		}else{

			// Nothing to do
		}

		if ( this.upRightDirection && getAxisY() < Game.ROWS - 1 && getAxisX() < Game.COLUMNS - 1 ){

			this.upRightDirection = false;
			nextNode = this.map.getNode( getAxisX() + 1 , getAxisY() + 1 );
		}else{

			// Nothing to do
		}

		if ( this.downRightDirection && getAxisY() >= 1 && getAxisX() < Game.COLUMNS - 1 ){

			this.downRightDirection = false;
			nextNode = this.map.getNode( getAxisX() + 1 , getAxisY() - 1 );
		}else{

			// Nothing to do
		}

		if ( this.downLeftDirection && getAxisY() >= 1 && getAxisX() >= FIRST_PIXEL ){

			this.downLeftDirection = false;
			nextNode = this.map.getNode( getAxisX() - 1 , getAxisY() - 1 );
		}else{

			// Nothing to do
		}

		if ( this.upLeftDirection && getAxisY() < Game.ROWS - 1 && getAxisX() >= FIRST_PIXEL ){

			this.upLeftDirection = false;
			nextNode = this.map.getNode( getAxisX() - 1 , getAxisY() + 1 );
		}else{

			// Nothing to do
		}

		return nextNode;
	}

	public void setNewEnemies ( boolean newEnemies ){

		this.newEnemies = newEnemies;
	}

	public void setTurnsOnLevel ( int turns ){

		this.turnsOnLevel = turns;
	}

	// Tick function, gameplay related
	@Override
	public void tick (){

		Node nextNode; // prospective node for movement/action

		// Clear activity flags
		clearFlags();

		// Set stance to normal
		if ( Game.tickTimer == 0 ){

			setStance( true , false , false , false );
		}else{

			// Nothing to do
		}

		// Alert enemies in visibleNodes to player's presence
		alertEnemies();

		// Check for keypresses
		if ( this.myTurn && Game.tickTimer == 0 ){

			/*
			 * Get direction corresponding to keypress. Will be null until
			 * player presses a key.
			 */
			nextNode = setDirection();

			// If not null, add node to path
			if ( nextNode != null && !( nextNode.isWall() ) ){

				this.path.clear();
				this.path.add( nextNode );
				this.initialPathSize = 1;

			}else{

				// Nothing to do
			}

			/*
			 * Check the path and handle action depending on state and contents
			 * of next path node. Could be movement, attacking an enemy, opening
			 * or closing a door
			 */
			handlePath();

			/* Handle non-movement related actions */
			handleSpecialActions();

			/*
			 * End turn without acting. This is equivalent to adding the current
			 * node to the path.
			 */
			if ( this.wait ){

				this.wait = false;
				this.acted = true;
			}else{

				// Nothing to do
			}

		}else{

			// Nothing to do
		}

		if ( this.moved ){

			handleMovementResult();
		}else{

			// Nothing to do
		}

		if ( this.acted || this.moved || this.attacked ){

			Game.turnCounter++;
			this.turnsOnLevel++;
			if ( !this.attacked ){
				/*
				 * If not attacked, set all enemy damageTaken = 0. Prevents
				 * enemies from displaying damage indicator on subsequent rounds
				 * after damage was previously done.
				 */
				for ( Enemy enemies : GameplayState.getEnemyGroup().getEnemies() ){

					enemies.setDamageTaken( 0 );
				}
			}
			// If I attacked this round
			else{
				// Set my stance to attacking
				setStance( false , true , false , false );
			}

			// Health regen for barbarian. Starting 3 turns after last combat,
			// increment
			// health by one every other turn.
			if ( !this.attacked && getDamageTaken() == 0 ){

				this.turnsSinceCombat++;
				if ( this instanceof trl.entity.player.Barbarian ){

					if ( this.turnsSinceCombat >= 3 && this.turnsSinceCombat % 2 == 0 ){

						if ( this.hp < this.maxHP ){

							this.hp += 1;
						}else{

							// Nothing to do
						}

					}else{

						// Nothing to do
					}

				}else{

					// Nothing to do
				}

			}else{

				this.turnsSinceCombat = 0;
			}

			clearAllTargetingFlags();
			rollItems();
			decrementTimers();
			this.map.updateDisplayedNodes();
			// printEnemiesList();
		}

		if ( this.xpEarned >= Math.pow( this.level + 1 , 2 ) ){

			levelUp();
		}else{

			// Nothing to do
		}

		// Kill player
		if ( !this.isAlive() ){

			setImage( Game.getImageManager().corpse );
		}else{

			// Nothing to do
		}
	}

	// Action on player
	public void useHammer (){

		// Set HP of all enemies in visibleNodes = 0
		// int startX = map.getDisplayedNodesMinX();
		int startX = Map.displayedNodesMinX;
		// int startY = map.getDisplayedNodesMinY();
		int startY = Map.displayedNodesMinY;
		for ( int x = startX ; x < startX + Game.W_COLS ; x++ ){

			for ( int y = startY ; y < startY + Game.W_ROWS ; y++ ){

				if ( this.map.getNode( x , y ) != null && this.map.getNode( x , y ).checkEntityByID( (byte) 0 ) ){

					GameplayState.getEnemyGroup().getEnemy( this.map.getNode( x , y ) ).setHP( 0 );

				}else{

					// Nothing to do
				}

			}
		}
		// Heal player up to 50% health

		final int HALF_HEALTH_POINTS = 2;

		if ( this.hp < this.maxHP / HALF_HEALTH_POINTS ){

			this.hp = this.maxHP / HALF_HEALTH_POINTS;
		}else{

			// Nothing to do
		}

	}
}
