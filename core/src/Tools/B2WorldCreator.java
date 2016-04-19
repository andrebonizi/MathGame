package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.coco.mathgame.MathGame;

public class B2WorldCreator {
	
	public B2WorldCreator(World world, TiledMap map){
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){ // remember to put scale if use it
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth()/2)/MathGame.PPM, (rect.getY() + rect.getHeight()/2)/MathGame.PPM);
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth()/2/MathGame.PPM, rect.getHeight()/2/MathGame.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
		}
		
	}
}
