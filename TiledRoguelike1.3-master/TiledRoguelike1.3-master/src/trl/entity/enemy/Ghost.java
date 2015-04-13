package trl.entity.enemy;

import trl.map.Map;

public class Ghost extends Enemy{

	public Ghost ( Map map ){

		super ( map );
		init ();
	}

	// Initiliaze Ghost
	public void init(){

		maxHP = 5;
		attack = 5;
		// image = Game.getImageManager().ghost;
		hp = maxHP;
		xpReward = 1;
		level = 1;
	}
}
