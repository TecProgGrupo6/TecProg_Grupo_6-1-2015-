package trl.entity.actor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import trl.entity.Entity;
import trl.entity.enemy.Enemy;
import trl.gamestate.GameplayState;
import trl.main.Game;
import trl.map.Map;
import trl.map.Node;
import trl.map.Room;
import trl.sound.SoundManager;

public abstract class Actor extends Entity{

	// Life of the Player
	protected int hp;

	// Represent maximum amount of hp
	protected int maxHP;

	// Represent the attack force
	protected int attack;

	// Quantity of damage caused in player
	protected int damageTaken;

	// Quantity of damage caused in other
	protected int damageDealt;

	// Sound Manager
	protected SoundManager soundManager;

	// Represent if something was done in the player's turn
	protected boolean acted;

	// Describe if the player was attacked or not
	protected boolean attacked;

	// Describe if the player was moved or not
	protected boolean moved; // Did I move this tick?

	// Describe if the current turn is the Player's turn
	protected boolean myTurn;

	// Represent the timers of the game
	protected int[] timers;

	// Describes the conditions: normal, attacking, hit and shooting
	protected boolean[] stance;

	// List contains all map's places
	protected List<Node> path;

	// Actual place of the player in map
	protected int initialPathSize;

	// Represent the Player's level
	protected int level;

	// Saves the previous place
	protected Node previousNode;

	// Turns on node
	protected int turnsOnNode;
	
	private final static Logger LOGGER = Logger.getLogger( Actor.class.getName() );
	

	public Actor ( Map map ){

		super( map );
		initActor();
	}

	// Initiliaze player
	public void initActor (){
		
		//LOGGER.setLevel( Level.INFO );
		//LOGGER.info("Actor intialized");
		
		this.loc = this.map.placeEntity( this , this.map.getRandomNodeInRoom() );
		this.path = new ArrayList<Node>();
		this.stance = new boolean[] { true , false , false , false };
	}
	
	
	// Returns the player HP at frame-enter event
	public int getHP (){

		return this.hp;
	}
	
	// Sets the player HP at frame-enter event
	public void setHP ( int hp ){

		this.hp = hp;
	}

	// Checks if the player is still alive
	public boolean isAlive (){

		return this.hp > 0;
	}
	
	// Checks the damage taken at frame-enter event
	public int getDamageTaken (){
		
		return this.damageTaken;
	}
	
	// Sets the damage taken at frame-enter event
	public void setDamageTaken ( int damageTaken ){

		this.damageTaken = damageTaken;
	}

	// Deals damage to an enemy
	protected void attack ( Actor defender ){

		Random r = new Random();
		double random_attack = r.nextDouble() * this.attack;

		this.damageDealt = (int) Math.round( random_attack ) + this.level;
		
		
		if ( this.damageDealt > this.attack ){
			this.damageDealt = this.attack;
		}else{

			// Nothing to do
		}
		
		
		// makes the calculation of new hp from enemies
		defender.setHP( defender.getHP() - this.damageDealt );
		defender.setDamageTaken( this.damageDealt );
		this.setStance( false , true , false , false );

		if ( this.damageDealt > 0 ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Battling Enemmy. Damage =" + this.damageDealt);
			
			defender.setStance( false , false , true , false );
		}else{

			// Nothing to do
		}
	}

	// set image at frame-inter event
	public void setImage ( BufferedImage image ){

		this.image = image;
	}

	// Ends the turn of action
	public void endTurn ( Actor nextActor ){

		LOGGER.setLevel( Level.CONFIG );
		LOGGER.config("End Turn");
		
		this.myTurn = false;
		nextActor.setTurn( true );

		/*
		 * Set turn delay if actor is visible. tickTimer = TURN_DELAY if there
		 * are any enemies currently visible to the player If player is ending
		 * turn
		 */

		if ( this instanceof trl.entity.player.Player ){

			// Verify if there any currently visible enemies
			for ( Enemy enemy : GameplayState.getEnemyGroup().getEnemies() ){

				if ( enemy.getVisibleToPlayer() ){

					Game.tickTimer = Game.TURN_DELAY;
					break;
				}else{

					// Nothing to do
				}
			}
			// Am I a wizard who just exploded (even though I am alone)
			boolean isWizzardAndTime = this instanceof trl.entity.player.Wizard && getTimers()[1] == 10;

			if ( isWizzardAndTime ){

				Game.tickTimer = Game.TURN_DELAY;
			}
		}
		// If enemy ending its turn
		if ( this instanceof trl.entity.enemy.Enemy ){

			if ( this.getVisibleToPlayer() ){

				Game.tickTimer = Game.TURN_DELAY;
			}else{

				// Nothing to do
			}
		}
	}

	public void setTurn ( boolean myTurn ){

		this.myTurn = myTurn;
	}

	public boolean getTurn (){

		return this.myTurn;
	}

	// Moves your character to a place if it is possible
	public void move ( Node node ){

		/*
		 * Remove the actor from current node, set loc to argument node, add
		 * self to current loc, remove argument node from path.
		 */

		boolean isFeaturePassableAndHasNoEnemy = node.getFeature().isPassable() && !node.hasEnemy();

		if ( isFeaturePassableAndHasNoEnemy ){
			
			LOGGER.setLevel( Level.CONFIG);
			LOGGER.config("Moving. No Enemy blocking.");

			this.loc.removeEntity( this );
			this.loc = node;
			this.loc.addEntity( this );
			this.damageDealt = 0;
			this.path.remove( node );
			
		}else{
			// nothing to do
		}
	}

	// Resets some states
	protected void clearFlags (){

		this.acted = false;
		this.moved = false;
		this.attacked = false;
	}

	// Plays some sounds dependending on the action
	protected void playSound (){

		if ( this.attacked && this.damageDealt > 0 ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Strike sound played");

			this.soundManager.playSound( "strike" );
		}else{

			// Nothing to do
		}

		if ( this.attacked && this.damageDealt == 0 ){
			
			LOGGER.setLevel( Level.INFO );
			LOGGER.info("Miss sound played");

			this.soundManager.playSound( "miss" );
		}else{

			// Nothing to do
		}
	}

	// Actions of the character
	public boolean getActed (){

		if ( this.moved || this.attacked || this.acted ){

			return true;
		}else{

			return false;
		}

	}

	public int[] getTimers (){

		return this.timers;
	}

	public void decrementTimers (){

		for ( int i = 0 ; i < this.timers.length ; i++ ){

			if ( this.timers[i] > 0 ){

				this.timers[i]--;
			}
		}
	}

	//
	public Node getNextPathNode (){

		/*
		 * Next path node will always be index 0. Nodes are removed from path
		 * list after any action is taken.
		 */
		return this.path.get( 0 );
	}

	public void setPath ( List<Node> list ){

		this.path = list;
		this.initialPathSize = this.path.size();
	}

	public List<Node> getPath (){

		return this.path;
	}

	public void setPathTo ( Node node ){

		this.path = this.map.findPath( this.loc , node );
		this.initialPathSize = this.path.size();
	}

	public void setPathToConnectedRoom (){

		Room current = null;
		Room nextRoom = null;

		// If the player is currently in a room.
		if ( this.map.inRoom( this.loc ) ){

			current = getOccupiedRoom( this.loc );
		}
		// Else player is in a hallway. Get room in occupied column/row.
		else{

			current = this.map.getNearestRoom( this.loc );
		}

		// If room only has one connection, only option is that room
		if ( current.getConnectedTo().size() == 1 ){

			nextRoom = current.getConnectedTo().get( 0 );
		}else{

			nextRoom = current.getRandomConnectedRoom();
		}

		Node destination = nextRoom.getRandomNodeInRoom();

		this.path = this.map.findPath( this.loc , destination );
	}

	// Renderizes the graphic
	public void render ( Graphics g ){

		if ( this.inDisplayedNodes() ){

			// Row and column in displayed nodes
			int wx = this.map.getDisplayedX( this.loc );
			int wy = this.map.getDisplayedY( this.loc );

			// Pixel x and y in game window
			int px = wx * Game.SCALED_TILE_SIZE;
			int py = Game.W_HEIGHT - ( wy * Game.SCALED_TILE_SIZE ) - Game.SCALED_TILE_SIZE;

			// Height and width in pixels
			int width = Game.SCALED_TILE_SIZE;
			int height = Game.SCALED_TILE_SIZE;

			if ( this.hp > 0 ){

				if ( this.getVisibleToPlayer() ){

					g.drawImage( this.image , px , py , width , height , null );
					renderHealthBar( g , px , py , width , height );
				}else{

					// Nothing to do
				}
				// Draw damage indicator

				boolean myTurnAndTimerDamageGreaterZero = this.myTurn && Game.tickTimer > 0 && getDamageTaken() > 0;

				if ( myTurnAndTimerDamageGreaterZero ){

					g.drawImage( Game.getImageManager().bang , px , py , width , height , null );
					g.setColor( Color.red );
					g.drawString( Integer.toString( getDamageTaken() ) , px + width / 2 , py + height / 2 );
				}else{

					// Nothing to do
				}

				boolean wizzardAndTimersTen = this instanceof trl.entity.player.Wizard && this.timers[1] == 10;

				if ( wizzardAndTimersTen ){

					renderExplosion( g , px , py );
				}else{

					// Nothing to do
				}

				// Draw target box
				if ( this instanceof trl.entity.enemy.Enemy ){

					if ( ( (Enemy) this ).getTargeted() ){
						g.setColor( Color.YELLOW );
						g.drawRect( px , py , width , height );
						g.drawRect( px + 1 , py + 1 , width - 2 , height - 2 );
					}else{

						// Nothing to do
					}

				}else{

					// Nothing to do
				}

				// If attacking
				if ( this.stance[1] ){

					g.drawImage( Game.getImageManager().swords , px , py , width , height , null );
				}else{

					// Nothing to do
				}

				// If shooting
				if ( this.stance[3] ){

					g.drawImage( Game.getImageManager().arrows , px , py , width , height , null );
				}else{

					// Nothing to do
				}

			}else{

				g.drawImage( Game.getImageManager().corpse , px , py , width , height , null );
			}
		}
	}

	// Changes the color of health
	public void renderHealthBar ( Graphics g , int x , int y , int width , int height ){

		// Draw bar background
		Color trBlack = new Color( 0 , 0 , 0 , 64 );
		Color trGray = new Color( 128 , 128 , 128 , 64 );
		Color trGreen = new Color( 0 , 255 , 0 , 196 );
		Color trYellow = new Color( 255 , 255 , 0 , 196 );
		Color trRed = new Color( 255 , 0 , 0 , 196 );

		g.setColor( trGray );
		g.fillRect( x + 1 , y + (int) ( width * .75 ) - 1 , width - 2 , 6 );

		// Draw border
		g.setColor( trBlack );
		g.drawRect( x + 1 , y + (int) ( height * .75 ) - 1 , width - 2 , 6 );

		// Calculate width of bar
		double percentHealth = (double) this.hp / (double) this.maxHP;
		if ( percentHealth > .75 ){

			g.setColor( trGreen );
		}else{

			// Nothing to do
		}

		if ( percentHealth > .25 && percentHealth <= .75 ){

			g.setColor( trYellow );
		}else{

			// Nothing to do
		}

		if ( percentHealth <= .25 ){

			g.setColor( trRed );
		}else{

			// Nothing to do
		}

		// Draw bar
		g.fillRect( x + 2 , y + (int) ( height * .75 ) , (int) ( percentHealth * ( width - 4 ) ) , 4 );
	}

	// Renderizes the explosions
	/**
	 * @param x  
	 * @param y 
	 */
	public void renderExplosion ( Graphics g , int x , int y ){

		// Blast radius
		int r = 0;

		boolean wizzardAndTimerGreaterZero = this instanceof trl.entity.player.Wizard && this.timers[1] > 0 && Game.tickTimer > 0;

		if ( wizzardAndTimerGreaterZero ){

			// Determine blast radius at time = timers[1]

			if ( Game.tickTimer == Game.TURN_DELAY ){

				r = 0;
			}else if ( Game.tickTimer >= Game.TURN_DELAY / 2 ){

				r = 1;
			}else if ( Game.tickTimer > 0 ){

				r = 2;
			}else{

				return;
			}

			List<Node> blastArea = this.map.getAoENodes( this.loc , r );

			for ( Node node : blastArea ){

				int gameSizeY = ( Game.W_HEIGHT - Game.SCALED_TILE_SIZE ) - ( this.map.getDisplayedY( node ) * Game.SCALED_TILE_SIZE );

				int gameSizeX = this.map.getDisplayedX( node ) * Game.SCALED_TILE_SIZE;

				g.drawImage( Game.getImageManager().fire , gameSizeX , gameSizeY , Game.SCALED_TILE_SIZE , Game.SCALED_TILE_SIZE , null );
			}
		}
	}

	public void setStance ( boolean normal , boolean attacking , boolean hit , boolean shooting ){

		this.stance[0] = normal;
		this.stance[1] = attacking;
		this.stance[2] = hit;
		this.stance[3] = shooting;
	}

	// Closes the door
	public void closeDoor ( Node node ){

		// If node has open door and no entities occupying node.
		boolean getFeature = node.getFeature() instanceof trl.map.feature.DoorOpen;
		boolean getEntitiesNullAndSizeZero = node.getEntities() == null || node.getEntities().size() == 0;

		if ( getFeature && getEntitiesNullAndSizeZero ){

			node.makeClosedDoor();
		}else{

			// Nothing to do
		}

		if ( this instanceof trl.entity.player.Player ){

			GameplayState.getPlayer().close = false;
			this.path.remove( node );
		}else{

			// Nothing to do
		}

	}

	// Opens the door
	public void openDoor ( Node node ){

		boolean featureAndDoorClosed = node.getFeature() instanceof trl.map.feature.DoorClosed;

		if ( featureAndDoorClosed ){

			node.makeOpenDoor();
		}else{

			// Nothing to do
		}

		if ( this instanceof trl.entity.player.Player ){

			this.path.remove( node );
		}else{

			// Nothing to do
		}

	}

	public abstract void tick ();

	public int getLevel (){

		return this.level;
	}

	public void setActed ( boolean acted ){

		this.acted = acted;
	}
}
