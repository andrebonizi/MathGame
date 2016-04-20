package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.coco.mathgame.MathGame;

import Screens.PlayScreen;

public class Portal extends InteractiveTileObject {
	
	private String newMap;
	
	public Portal(PlayScreen screen, Rectangle bounds){
		super(screen, bounds);
		fixture.setUserData(this);
		setCategoryFilter(MathGame.PORTAL_BIT);
		newMap = new String("Stage2.tmx");
	}

	@Override
	public void onArmHit() {
		Gdx.app.log("Portal", "Collision");
		screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 128 / MathGame.PPM), PortalSign.class));
	}
	
	public void hit(){
		screen.enterPortal(newMap);
	}
	
	
}
