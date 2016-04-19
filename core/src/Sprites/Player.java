package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.coco.mathgame.MathGame;

import Screens.PlayScreen;

public class Player extends Sprite{

	public World world;
	public Body b2body;
	private TextureRegion playerStand;
	
	public Player(World world, PlayScreen screen){
		super(screen.getAtlas().findRegion("player"));
		this.world = world;
		definePlayer();
		playerStand = new TextureRegion(getTexture(), 0,0,128,256);
		setBounds(0,0, 128 / MathGame.PPM, 256 / MathGame.PPM);
		setRegion(playerStand);
	}
	
	public void update(float dt){
		setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y/2);
	}
	
	public void definePlayer(){
		BodyDef bdef = new BodyDef();
		bdef.position.set(128 / MathGame.PPM,256 / MathGame.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(64/MathGame.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}
	
}

