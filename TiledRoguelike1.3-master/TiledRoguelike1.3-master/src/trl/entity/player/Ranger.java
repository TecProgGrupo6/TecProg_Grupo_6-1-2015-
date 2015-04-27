package trl.entity.player;

import java.util.ArrayList;
import java.util.List;

import trl.entity.Entity;
import trl.entity.enemy.Enemy;
import trl.main.Game;
import trl.map.Map;
import trl.map.Node;

public class Ranger extends Player{

	// Private Node target;
	private List<Enemy> targets;

	public Ranger ( Map map ){

		super( map );
		this.maxHP = 40;
		this.attack = 7;
		this.image = Game.getImageManager().ranger;
		init();
	}

	// Initializes Ranger
	public void init (){

		this.hp = maxHP;
		this.myTurn = true;
		timers = new int[1];
		targets = new ArrayList<Enemy>();
	}

	public List<Enemy> getTargets (){

		/*
		 * For each node in visibleNodes, look through entities list. If an
		 * enemy is found, add to enemies list, then return the list.
		 */
		targets.clear();
		List<Enemy> enemies = new ArrayList<Enemy>();
		List<Entity> entities = null;
		
		// Add enemies to the list
		addEnemies(enemies, entities);

		return enemies;
	}
	
	// Add enemies to the list
	public void addEnemies ( List<Enemy> enemies , List<Entity> entities ){

		for ( Node node : map.getVisibleToPlayer() ){

			if ( node.getEntities() != null ){

				entities = new ArrayList<Entity>( node.getEntities() );
				for ( Entity entity : entities ){

					if ( entity instanceof trl.entity.enemy.Enemy ){

						enemies.add( (Enemy) entity );
					}else{

						// Nothing to do
					}
				}

			}else{

				// Nothing to do
			}

		}

	}

	// Action to enemy
	public void fireArrow ( Enemy target ){

		int damage = 4;

		if ( getLevel() > 4 ){

			damage = getLevel();
		}else{

			// Nothing to do
		}

		if ( damage > 10 ){

			damage = 10;
		}else{

			// Nothing to do
		}

		target.setHP( target.getHP() - damage );
		damageDealt = damage;
		target.setDamageTaken( damage );
		setStance( false , false , false , true );
		target.setStance( false , false , true , false );
		fireArrow = false;
		// attacked = true;
	}
}
