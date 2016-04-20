package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.coco.mathgame.MathGame;

import Screens.PlayScreen;

public class PortalSign extends Item {
	private float stateTime;
	private boolean setToDestroy;
	private Animation bounceAnimation;
	private Array<TextureRegion> frames;
	
	public PortalSign(PlayScreen screen, float x, float y){
		super(screen, x, y);
		
		frames = new Array<TextureRegion>();
		for (int i=0;i<2;i++)
			frames.add(new TextureRegion(screen.getAtlas().findRegion("player"),i*128, 0,128,256));
		bounceAnimation = new Animation(0.4f, frames);
		
		setRegion(screen.getAtlas().findRegion("player"),0,0,128,128);
		stateTime = 0;
		setToDestroy = true;
	}

	@Override
	public void defineItem() {
		BodyDef bdef = new BodyDef();
		bdef.position.set( getX(), getY());
		bdef.type = BodyDef.BodyType.StaticBody;
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10/MathGame.PPM);
		
		fdef.shape = shape;
		body.createFixture(fdef).setUserData(this);
	}

	@Override
	public void use() {
		
	}
	
	public void update(float dt){
		super.update(dt);
		stateTime += dt;
		
		setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y + getHeight()/2);
		setRegion(bounceAnimation.getKeyFrame(stateTime, true));		
	}
	
	public void draw(Batch batch){
		if(stateTime < 3)
			super.draw(batch);
		else
			destroyed = true;
	}

}
