package trl.entity.enemy;

import trl.main.Game;
import trl.map.Map;

public class Panther extends Enemy{

	public Panther ( Map map ){

		super( map );
		init();
	}

	// Initiliaze Panther
	public void init (){

		maxHP = 18;
		attack = 5;
		image = Game.getImageManager().panther;
		hp = maxHP;
		xpReward = 9;
		level = 9;
	}
}
