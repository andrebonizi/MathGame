package Tools;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.coco.mathgame.MathGame;

import Screens.PlayScreen;
import Sprites.Portal;
import Sprites.Shadow;
import Sprites.Wall;

public class B2WorldCreator {
	private Array<Shadow> shadows;
	
	public B2WorldCreator(PlayScreen screen){
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		// Ground
		for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){ // remember to put scale if use it
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth()/2)/MathGame.PPM, (rect.getY() + rect.getHeight()/2)/MathGame.PPM);
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2/MathGame.PPM, rect.getHeight()/2/MathGame.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
		// Portals
		for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){ // remember to put scale if use it
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Portal(screen, rect);
		}
		
		// Shadows
		shadows = new Array<Shadow>();
		for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){ // remember to put scale if use it
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			shadows.add(new Shadow(screen, rect.getX()/MathGame.PPM, rect.getY()/MathGame.PPM));
		}
		
		// Wall
		for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){ // remember to put scale if use it
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Wall(screen, rect);
		}
	}
	
	public Array<Shadow> getShadows(){
		return shadows;
	}
}
