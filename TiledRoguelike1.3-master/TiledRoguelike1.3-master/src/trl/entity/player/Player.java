package trl.entity.player;

import java.util.List;
import java.util.Random;

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

	public Player ( Map map ){

		super( map );
		level = 1;
	}

	// ?
	public boolean adjacentEnemy (){

		for ( int x = -1 ; x <= 1 ; x++ ){

			for ( int y = 1 ; y >= 0 ; y-- ){

				boolean xCoordinateAndColumns = x >= 0 && x < Game.COLUMNS;
				boolean yCoordinateAndRows = y >= 0 && y < Game.ROWS;

				if ( xCoordinateAndColumns && yCoordinateAndRows ){

					int summX = getAxisX() + x;
					int summY = getAxisY() + y;
					Node adjacent = map.getNode( summX , summY );

					if ( adjacent != null ){

						if ( adjacent.checkEntityByID( (byte) 0 ) ){

							System.out.println( "Adjacent enemy. Can't shoot." );

							return true;
						}else{

							// Nothing to do
						}
					}
				}
			}
		}

		System.out.println( "No adjacent enemies found." );

		return false;
	}

	// See enemies on screen
	public void alertEnemies (){

		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){

			if ( map.isVisibleToPlayer( enemy.getLoc() ) ){

				enemy.setAwareOfPlayer( true );
			}else{

				// Nothing to do
			}
		}
	}

	// Clear All
	public void clearAllTargetingFlags (){

		targetEnemy = false;
		fireArrow = false;
		gotTargets = false;
		nextTarget = false;
		previousTarget = false;
		getTargets = false;
		// If (targets != null) { //Will be null if player hasn't targeted
		// anything before
		targets = null;

		if ( target != null ){

			target.setTargeted( false );
		}else{

			// Nothing to do
		}

		target = null;
	}

	// Earning xp
	public void gainXP ( double xp ){

		xpEarned += xp;
	}

	public int getEnemiesDefeated (){

		return enemiesDefeated;
	}

	public boolean getHasKey (){

		return hasKey;
	}

	public boolean getOpenedLock (){

		return openedLock;
	}

	public int getTurnsOnLevel (){

		return turnsOnLevel;
	}

	public double getXPEarned (){

		return xpEarned;
	}

	// Handle movement by some item
	public void handleMovementResult (){

		// If potion in this node, consume and remove
		if ( loc.checkEntityByID( (byte) 1 ) ){

			loc.removeEntityByID( (byte) 1 );
			hp = maxHP;
		}else{

			// Nothing to do
		}

		// If hammer in this node, consume and remove
		if ( loc.checkEntityByID( (byte) 2 ) ){

			useHammer();
			loc.removeEntityByID( (byte) 2 );
		}else{

			// Nothing to do
		}

		// If key in this node, pick it up and spawn lock.
		if ( loc.checkEntityByID( (byte) 5 ) ){

			hasKey = true;
			loc.removeEntityByID( (byte) 5 );

			// Lock lock = new Lock(map);
			Node randomNode = map.getRandomNodeInRoom();
			map.placeEntity( new Lock( map ) , randomNode );
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
		if ( loc.checkEntityByID( (byte) 6 ) ){

			openedLock = true;
			loc.removeEntityByID( (byte) 6 );
			if ( GameplayState.dungeonLevel == 5 ){

				Goal goal = new Goal( map );
				if ( this instanceof trl.entity.player.Thief ){

					goal.setSeenByPlayer( true );
				}else{

					// Nothing to do
				}

			}else{

				map.getRandomNodeInRoom().setFeature( new StairDown() );
			}
		}

		// If down stairway on this node, switch level
		if ( loc.isStairDown() ){

			GameplayState.getEnemyGroup().getEnemies().clear();
			GameplayState gameState = (GameplayState) Game.getGameStateManager().getGameState( 1 );
			GameplayState.getEnemyGroup().listEnemies();
			gameState.setDungeonLevel( gameState.getDungeonLevel() + 1 );
			gameState.init();
		}else{

			// Nothing to do
		}

		if ( loc.checkEntityByID( (byte) 7 ) ){

			Game.getGameStateManager().addGameState( 2 , new WinGameState() );
			Game.getGameStateManager().setGameState( 2 );
		}else{

			// Nothing to do
		}

	}

	// Moves through the map
	public void handlePath (){

		// Ignore all this if path is empty
		boolean isPathEmptyAndSizeGreaterZero = path != null && path.size() > 0;
		if ( isPathEmptyAndSizeGreaterZero ){

			/*
			 * System.out.println("Path size=" + path.size() +
			 * ", initial path size=" + initialPathSize); Path size == 1 and
			 * initial path size == 1 and enemy in next node.Consider this a
			 * deliberate attempt to attack enemy.
			 */

			boolean isPathSizeAndInitialEqualsOne = path.size() == 1 && initialPathSize == 1;
			boolean isNodePathCkecksZero = getNextPathNode().checkEntityByID( (byte) 0 );
			boolean pathLowerInitialAndPathEntity = ( path.size() < initialPathSize ) && getNextPathNode().checkEntityByID( (byte) 0 );

			boolean pathAndInitialPathEqualsOne = path.size() == 1 && initialPathSize == 1;
			boolean nextPathNodeGetFeatureClose = getNextPathNode().getFeature() instanceof trl.map.feature.DoorClosed;
			boolean pathSizeGreaterOneAndPathLowerIniticial = path.size() >= 1 && path.size() < initialPathSize;

			boolean nextPathNodeGetFeatureOpen = getNextPathNode().getFeature() instanceof trl.map.feature.DoorOpen;
			if ( isPathSizeAndInitialEqualsOne && isNodePathCkecksZero ){

				// System.out.println("Attacking.");
				attack( GameplayState.getEnemyGroup().getEnemy( getNextPathNode() ) );
				GameplayState.getEnemyGroup().getEnemy( path.get( 0 ) ).setDamageTaken( damageDealt );
				// path.remove(0);
				path.clear();
				attacked = true;
			}

			// Path size less than initial path size and enemy in next node.
			// This looks like the player ran into
			// the enemy while traveling a longer path. Don't consider this a
			// deliberate attack

			else if ( pathLowerInitialAndPathEntity ){
				// System.out.println("Blocked by enemy/forgetting path.");
				path.clear();
				acted = true;
			}

			/*
			 * Path size == 1 and initial path size == 1 and closed door in next
			 * node. consider this a deliberate attempt to open the door.
			 */
			else if ( pathAndInitialPathEqualsOne && nextPathNodeGetFeatureClose ){

				openDoor( getNextPathNode() );
				// System.out.println("Opening door.");
				path.clear();
				moved = true;
			}

			/*
			 * Path size less than initial path size and closed door in next
			 * node. This looks like the player ran into the closed door while
			 * traveling a longer path. Don't consider this a deliberate attempt
			 * to open the door.
			 */
			else if ( pathSizeGreaterOneAndPathLowerIniticial && nextPathNodeGetFeatureClose ){
				// System.out.println("Blocked by door/forgetting path.");
				path.clear();
				acted = true;
			}

			/*
			 * Player close flag set.
			 */
			else if ( close ){

				if ( nextPathNodeGetFeatureOpen ){

					getNextPathNode().makeClosedDoor();
				}else{

					// Nothing to do
				}

				path.clear();
				close = false;
				acted = true;
			}

			// Move to next node
			else{

				// System.out.println("Moving.");
				move( getNextPathNode() );
				moved = true;
			}
		}
	}

	// Do some special movements
	public void handleSpecialActions (){

		/*
		 * Methods defined in class files for each player class. Actor is upcast
		 * for method calls. Should stay safe due to player class checks.
		 */
		if ( shout ){

			if ( timers[0] <= 0 ){

				( (Barbarian) this ).shout();
				acted = true;
			}else{

				// Nothing to do
			}

			shout = false;
		}else{

			// Nothing to do
		}

		if ( blink ){

			if ( timers[0] <= 0 ){

				( (Wizard) this ).blink();
				acted = true;
			}else{

				// Nothing to do
			}

			blink = false;
		}else{

			// Nothing to do
		}

		if ( explode ){

			if ( timers[1] <= 0 ){
				( (Wizard) this ).explode();
				acted = true;
			}else{

				// Nothing to do
			}

			explode = false;
		}else{

			// Nothing to do
		}

		if ( quicken ){

			if ( timers[2] <= 0 ){

				( (Wizard) this ).quicken();
				acted = true;
			}else{

				// Nothing to do
			}

			quicken = false;
		}else{

			// Nothing to do
		}

		// if (!adjacentEnemy()) {
		// If KeyManager says we're supposed to look for targets (getTargets),
		// but haven't yet (!gotTargets)

		boolean getTargetsAndNotGotTargets = getTargets && !gotTargets;
		boolean notAdjacentEnemy = !adjacentEnemy();

		if ( getTargetsAndNotGotTargets ){

			if ( notAdjacentEnemy ){

				targets = ( (Ranger) this ).getTargets(); // Returns 0-size
															// list if no
															// targets found,
															// not null!
				// Targets list size = 0, clear all flags. Should continue turn
				// as normal.
				if ( targets.size() == 0 ){

					System.out.println( "Targets list size = 0. No targets found. Canceling targeting." );
					clearAllTargetingFlags();
				}
				// targets list size != 0, so we found at least one target. Stop
				// looking (getTargets = false) and
				// allow target switching(gotTargets = true)
				else{

					getTargets = false;
					gotTargets = true;
				}
			}else{

				clearAllTargetingFlags();
			}
		}else{

			// Nothing to do
		}

		// Should only be possible if targets list size > 0
		boolean NotgetTargetsAndGotTargets = !getTargets && gotTargets;

		if ( NotgetTargetsAndGotTargets ){

			// Allow player to cancel targeting
			if ( cancel ){

				clearAllTargetingFlags();
			}else{

				/*
				 * On first pass through, target will be null. If target is
				 * null, it's safe for all conditions to assign target the first
				 * element in targets list. This takes care of 1-size target
				 * lists, since they can never be anything but the first element
				 * in targets list.
				 */
				if ( target == null ){

					// System.out.println("Target list size = " +
					// targets.size());
					target = targets.get( 0 );
					target.setTargeted( true );
				}
				/*
				 * Target is not null, so we must have passed through at least
				 * once. On subsequent passes, we need to handle
				 * nextTarget/previousTarget for enemies lists with size > 1.
				 * 0-size lists should not exist, and 1-size lists keep their
				 * assigned values of enemies.get(0)
				 */
				else{

					if ( targets.size() > 1 ){

						int targetsSizeMinusOne = ( targets.size() - 1 );

						// Targets list size must be > 1. Consider
						// nextTarget/previousTarget meaningful, but
						// clear them after each pass.
						if ( nextTarget ){

							// Changing target, untarget current target
							target.setTargeted( false );
							// If not last target in list, target = current
							// target index + 1

							if ( targetsSizeMinusOne > targets.indexOf( target ) ){

								target = ( targets.get( targets.indexOf( target ) + 1 ) );
							}
							// Else target wraps around to first target
							else{

								target = targets.get( 0 );
							}
							// Changed target, target new target
							target.setTargeted( true );
						}else{

							// Nothing to do
						}

						if ( previousTarget ){

							target.setTargeted( false );
							// If not first target, target = current target
							// index - 1
							if ( targets.indexOf( target ) > 0 ){

								target = targets.get( targets.indexOf( target ) - 1 );
							}
							// Else target wraps around to last in list
							else{

								target = targets.get( targetsSizeMinusOne );
							}
							// Changed target, target new target
							target.setTargeted( true );
						}else{

							// Nothing to do
						}

					}
				}
			}
			previousTarget = false;
			nextTarget = false;
		}

		if ( fireArrow ){

			( (Ranger) this ).fireArrow( target );
			clearAllTargetingFlags();
			acted = true;
		}else{

			// Nothing to do
		}

	}

	// Check number of enemies defetead
	public void incrementEnemiesDefeated ( int enemiesDefeated ){

		this.enemiesDefeated += enemiesDefeated;
	}

	// Check level
	public void levelUp (){

		double percentHealth = (double) hp / (double) maxHP;
		// System.out.println("Percent health = " + percentHealth);
		level++;
		double exponent = 1 / level;
		maxHP = maxHP + (int) Math.pow( level , 1.0 + exponent );
		hp = (int) ( percentHealth * (double) maxHP );
	}

	// Print on screen
	public void printEnemiesList (){

		System.out.println( "=== Enemies ===" );
		System.out.println( "Player at " + getAxisX() + "," + getAxisY() );

		for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){
			String enemyPrint = enemy.toString().substring( enemy.toString().lastIndexOf( '.' ) ) + " at " + enemy.getAxisX() + ","
					+ enemy.getAxisY() + " v2p=" + enemy.getVisibleToPlayer() + " ,aop=" + enemy.awareOfPlayer() + ", sbp="
					+ enemy.seenByPlayer();

			System.out.println( enemyPrint );
		}
	}

	// Refresh screen?
	public void refresh (){

		hasKey = false;
		openedLock = false;
		turnsOnLevel = 0;
	}

	// Random function of items
	public void rollItems (){

		// Roll for item creation
		Random r = new Random();
		double roll = r.nextDouble();
		if ( roll >= .995 ){

			if ( !map.hammerOnMap() ){

				Hammer hammer = new Hammer( map );
				if ( GameplayState.getPlayer() instanceof trl.entity.player.Thief ){

					hammer.setSeenByPlayer( true );
				}else{

					// Nothing to do
				}

			}
		}else if ( roll >= .9 ){

			if ( !map.potionOnMap() ){

				Potion potion = new Potion( map );
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

		if ( upDirection && getAxisY() < Game.ROWS - 1 ){

			upDirection = false;
			nextNode = map.getNode( getAxisX() , getAxisY() + 1 );
		}else{

			// Nothing to do
		}

		if ( downDirection && getAxisY() >= 1 ){

			downDirection = false;
			nextNode = map.getNode( getAxisX() , getAxisY() - 1 );
		}else{

			// Nothing to do
		}

		if ( leftDirection && getAxisX() >= 1 ){

			leftDirection = false;
			nextNode = map.getNode( getAxisX() - 1 , getAxisY() );
		}else{

			// Nothing to do
		}

		if ( rightDirection && getAxisX() < Game.COLUMNS - 1 ){

			rightDirection = false;
			nextNode = map.getNode( getAxisX() + 1 , getAxisY() );
		}else{

			// Nothing to do
		}

		boolean upRightYRowsColumns = upRightDirection && getAxisY() < Game.ROWS - 1 && getAxisX() < Game.COLUMNS - 1;

		if ( upRightYRowsColumns ){

			upRightDirection = false;
			nextNode = map.getNode( getAxisX() + 1 , getAxisY() + 1 );
		}else{

			// Nothing to do
		}

		boolean downRightYXColumns = downRightDirection && getAxisY() >= 1 && getAxisX() < Game.COLUMNS - 1;

		if ( downRightYXColumns ){

			downRightDirection = false;
			nextNode = map.getNode( getAxisX() + 1 , getAxisY() - 1 );
		}else{

			// Nothing to do
		}

		boolean downLeftYX = downLeftDirection && getAxisY() >= 1 && getAxisX() >= 1;

		if ( downLeftYX ){

			downLeftDirection = false;
			nextNode = map.getNode( getAxisX() - 1 , getAxisY() - 1 );
		}else{

			// Nothing to do
		}

		boolean upLeftYRowsX = upLeftDirection && getAxisY() < Game.ROWS - 1 && getAxisX() >= 1;

		if ( upLeftYRowsX ){

			upLeftDirection = false;
			nextNode = map.getNode( getAxisX() - 1 , getAxisY() + 1 );
		}else{

			// Nothing to do
		}

		return nextNode;
	}

	public void setNewEnemies ( boolean newEnemies ){

		this.newEnemies = newEnemies;
	}

	public void setTurnsOnLevel ( int turns ){

		turnsOnLevel = turns;
	}

	// Tick function, gameplay related
	public void tick (){

		Node nextNode; // = null; //prospective node for movement/action
		Enemy enemy; // prospective enemy in nextNode

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
		if ( myTurn && Game.tickTimer == 0 ){

			/*
			 * Get direction corresponding to keypress. Will be null until
			 * player presses a key.
			 */
			nextNode = setDirection();

			/* If not null, add node to path */
			if ( nextNode != null && !( nextNode.isWall() ) ){

				path.clear();
				path.add( nextNode );
				initialPathSize = 1;

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
			if ( wait ){

				wait = false;
				acted = true;
			}else{

				// Nothing to do
			}

		}else{

			// Nothing to do
		}

		if ( moved ){

			handleMovementResult();
		}else{

			// Nothing to do
		}

		if ( acted || moved || attacked ){

			Game.turnCounter++;
			turnsOnLevel++;
			if ( !attacked ){
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
			if ( !attacked && getDamageTaken() == 0 ){

				turnsSinceCombat++;
				if ( this instanceof trl.entity.player.Barbarian ){

					if ( turnsSinceCombat >= 3 && turnsSinceCombat % 2 == 0 ){

						if ( hp < maxHP ){

							hp += 1;
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

				turnsSinceCombat = 0;
			}

			clearAllTargetingFlags();
			rollItems();
			decrementTimers();
			map.updateDisplayedNodes();
			// printEnemiesList();
		}

		if ( xpEarned >= Math.pow( level + 1 , 2 ) ){

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

				boolean isNodeNullAndNodeCheckedID = map.getNode( x , y ) != null && map.getNode( x , y )
													.checkEntityByID( (byte) 0 );

				if ( isNodeNullAndNodeCheckedID ){

					GameplayState.getEnemyGroup().getEnemy( map.getNode( x , y ) ).setHP( 0 );

				}else{

					// Nothing to do
				}

			}
		}
		// Heal player up to 50% health

		int heal = maxHP / 2;

		if ( hp < heal ){

			hp = heal;
		}else{

			// Nothing to do
		}

	}
}
