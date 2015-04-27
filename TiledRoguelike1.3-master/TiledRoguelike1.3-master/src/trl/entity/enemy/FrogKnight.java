package trl.entity.enemy;

import trl.main.Game;
import trl.map.Map;

public class FrogKnight extends Enemy{

	public FrogKnight ( Map map ){

		super( map );
		init();
	}

	// Initialize a frog knight
	public void init (){

		maxHP = 10;
		attack = 8;
		image = Game.getImageManager().frogKnight;
		hp = maxHP;
		level = 1;
		xpReward = 1;
	}
}
