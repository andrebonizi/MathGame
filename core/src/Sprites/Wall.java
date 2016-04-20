package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.coco.mathgame.MathGame;

import Screens.PlayScreen;

public class Wall extends InteractiveTileObject {
	public Wall(PlayScreen screen, Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MathGame.WALL_BIT);
	}

	@Override
	public void onArmHit() {
		Gdx.app.log("Wall", "Collision");
	}
	
	
}
