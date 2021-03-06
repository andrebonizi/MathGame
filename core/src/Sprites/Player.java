package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.coco.mathgame.MathGame;

import Screens.PlayScreen;

public class Player extends Sprite{
	public enum State {STANDING, RUNNING}
	public State currentState;
	public State previousState;
	
	public World world;
	public Body b2body;
	private TextureRegion playerStand;
	private Animation playerRun;
	private float stateTimer;
	private boolean runningRight;
	
	public Player(PlayScreen screen){
		super(screen.getAtlas().findRegion("player"));
		
		this.world = screen.getWorld();
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i=0;i<8;i++)
			frames.add(new TextureRegion(screen.getAtlas().findRegion("player"), i * 128,0, 128, 256));
		playerRun = new Animation(0.1f,frames);
		frames.clear();
		
		definePlayer();
		playerStand = new TextureRegion(screen.getAtlas().findRegion("player"), 0,0,128,256);
		setBounds(0,0, 128 / MathGame.PPM, 256 / MathGame.PPM);
		setRegion(playerStand);
		
	}
	
	public void update(float dt){
		setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y/2+0.5f);
		setRegion(getFrame(dt));
		
	}
	
	public TextureRegion getFrame(float dt){
		currentState = getState();
		TextureRegion region;
		
		switch(currentState){
			case RUNNING:
				region = playerRun.getKeyFrame(stateTimer, true);
				break;
			case STANDING:
			default:
				region = playerStand;
				break;
		}
		
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
			region.flip(true,false);
			runningRight = false;
		} else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
			region.flip(true,false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
		
	}
	
	public State getState(){
		if (b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.STANDING;
	}
	
	public void definePlayer(){
		BodyDef bdef = new BodyDef();
		bdef.position.set(12*128 / MathGame.PPM,256 / MathGame.PPM-1f);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(32/MathGame.PPM);
		
		fdef.filter.categoryBits = MathGame.PLAYER_BIT;
		fdef.filter.maskBits = MathGame.GROUND_BIT | MathGame.WALL_BIT 
							 |  MathGame.ENEMY_BIT;
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		//EdgeShape arm = new EdgeShape();
		//arm.set(new Vector2(-32/MathGame.PPM,64/MathGame.PPM),new Vector2(32/MathGame.PPM, 64/MathGame.PPM));
		//fdef.shape = arm;
		
		//b2body.createFixture(fdef).setUserData("arm");
	}

	
}

