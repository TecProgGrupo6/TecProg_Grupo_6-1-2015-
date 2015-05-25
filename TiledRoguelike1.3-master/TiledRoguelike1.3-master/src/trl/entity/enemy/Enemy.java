package trl.entity.enemy;

import java.util.logging.Logger;

import trl.entity.actor.Actor;
import trl.gamestate.GameplayState;
import trl.main.Game;
import trl.map.Map;

public abstract class Enemy extends Actor{

	protected boolean awareOfPlayer = false;

	protected int xpReward;

	protected boolean targeted;

	public Enemy ( Map map ){

		super( map );
	}
	
	// Initialize enemy
	public void init (){

		this.hp = this.maxHP;
		this.myTurn = false;
		this.awareOfPlayer = false;
	}

	// Responses of the enemy
	@Override
	public void tick (){

		clearFlags(); // Set acted, attacked, moved to false.

		// Set stance to normal
		if ( Game.tickTimer == 0 ){
			setStance( true , false , false , false );
		}else{

			// Nothing to do
		}

		boolean myTurnAndGameTimer = ( this.myTurn && Game.tickTimer == 0 );

		if ( myTurnAndGameTimer ){

			if ( !this.awareOfPlayer ){

				// If all path nodes are consumed, get a new path.
				// If enemy has been on the same node for 3 turns, get new path.

				enemyNotAware();

			}

			// If enemy is aware of player, attack or update path to player
			else{
				// If adjacent to player, attack.

				enemyAware();

			}

			/*
			 * If enemy didn't attack, move to next node in path. Compare loc
			 * after movement with current node. If they're equal, we didn't
			 * change location, so we should increment turnsOnNode.
			 * setDamageTaken(0) is a clumsy fix to prevent player from
			 * indicating damage on rounds after he wasn't in combat.
			 */
			if ( !this.attacked ){

				enemyNotAttacked();
			}else{

				// Nothing to do
			}

		}
	}

	// returns zero damage to the player
	public void enemyNotAttacked (){

		this.previousNode = this.loc;
		move( getNextPathNode() );
		this.moved = true;

		if ( this.loc.equals( this.previousNode ) ){

			this.turnsOnNode++;
		}else{

			this.turnsOnNode = 0;
		}

		GameplayState.getPlayer().setDamageTaken( 0 );

	}

	// If enemy is aware of player, attack or update path to player
	public void enemyAware (){

		if ( this.loc.adjacent( GameplayState.getPlayer().getLoc() ) ){

			attack( GameplayState.getPlayer() );
			GameplayState.getPlayer().setDamageTaken( this.damageDealt );
			this.attacked = true;
		}else{

			// Set path to player's location
			setPathTo( GameplayState.getPlayer().getLoc() );
		}

	}

	// If enemy is not aware, changes the path
	public void enemyNotAware (){

		if ( this.path.size() == 0 || this.turnsOnNode >= 3 ){

			// setPathTo(map.getRandomNode());
			setPathToConnectedRoom();
		}else{

			// Nothing to do
		}

	}

	public int getXP (){

		return this.xpReward;
	}

	public void setAwareOfPlayer ( boolean awareOfPlayer ){

		this.awareOfPlayer = awareOfPlayer;
	}

	// returns the atribute
	public boolean awareOfPlayer (){

		return this.awareOfPlayer;
	}

	// returns the atribute
	public boolean getTargeted (){

		return this.targeted;
	}

	public void setTargeted ( boolean targeted ){

		this.targeted = targeted;
	}

}
