package trl.entity.enemy;

import trl.main.Game;
import trl.map.Map;

public class Gargoyle extends Enemy{

	public Gargoyle ( Map map ){

		super( map );
		init();
	}

	// Initiliaze a gargoyle
	public void init (){

		maxHP = 30;
		attack = 5;
		image = Game.getImageManager().gargoyle;
		hp = maxHP;
		xpReward = 15;
		level = 15;
	}
}
