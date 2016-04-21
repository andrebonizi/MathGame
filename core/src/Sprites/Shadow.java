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

public class Shadow extends Enemy{

	private float stateTime;
	private Animation walkAnimation;
	private Array<TextureRegion> frames;
	private boolean setToDestroy;
	private boolean destroyed;
	private boolean runningRight;
	
	public Shadow(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		frames = new Array<TextureRegion>();
		for (int i=0;i<3;i++)
			frames.add(new TextureRegion(screen.getAtlas().findRegion("shadow"),i*128, 0,128,256));
		walkAnimation = new Animation(0.4f, frames);
		stateTime = 0;
		setBounds(0,0, 128 / MathGame.PPM, 256 / MathGame.PPM);
		setToDestroy = false;
		destroyed = false;
		
	}
	
	public void update(float dt){
		stateTime += dt;
		if(setToDestroy && !destroyed){
			world.destroyBody(b2body);
			destroyed = true;
			
			// add texture to dead shadow
			//setRegion(new TextureRegion(screen.getAtlas().findRegion("player"), 128,0,128,128));
			stateTime = 0;
		} else if (!destroyed) {
			b2body.setLinearVelocity(velocity);
			setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2+1.7f);
			setRegion(getFrame(dt));
		}
	}

	@Override
	protected void defineEnemy() {
		BodyDef bdef = new BodyDef();
		bdef.position.set( getX(), getY());
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(32/MathGame.PPM);
		
		fdef.filter.categoryBits = MathGame.ENEMY_BIT;
		fdef.filter.maskBits = MathGame.GROUND_BIT | MathGame.WALL_BIT 
							  | MathGame.PLAYER_BIT | MathGame.ENEMY_BIT;
		
		fdef.shape = shape;
		b2body.createFixture(fdef).setUserData(this);
	}
	
	public TextureRegion getFrame(float dt){
			TextureRegion region;
			
			region = walkAnimation.getKeyFrame(stateTime, true);
			
			if((b2body.getLinearVelocity().x > 0 || !runningRight) && !region.isFlipX()){
				region.flip(true,false);
				runningRight = false;
			} else if ((b2body.getLinearVelocity().x < 0 || runningRight) && region.isFlipX()){
				region.flip(true,false);
				runningRight = true;
			}
			
			return region;
	}
	
	@Override
	public void initBattle() {
		screen.initBattle();
		this.setToDestroy = true;
	}
	
	public void draw(Batch batch){
		if(!destroyed || stateTime < 1)
			super.draw(batch);
	}

}
