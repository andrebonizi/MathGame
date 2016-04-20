package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.coco.mathgame.MathGame;

import Sprites.Enemy;
import Sprites.InteractiveTileObject;
import Sprites.Portal;

public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
		
		if (fixA.getUserData() == "arm" || fixB.getUserData() == "arm"){
			Fixture arm = fixA.getUserData() == "arm" ? fixA : fixB;
			Fixture object = arm == fixA ? fixB : fixA;
			
			if (object.getUserData() instanceof InteractiveTileObject){
				((InteractiveTileObject) object.getUserData()).onArmHit();
			}
		}
		
		switch(cDef){
			case MathGame.ENEMY_BIT | MathGame.PLAYER_BIT:
				if (fixA.getFilterData().categoryBits == MathGame.ENEMY_BIT)
					((Enemy)fixA.getUserData()).initBattle(); // call for battle
				else if(fixB.getFilterData().categoryBits == MathGame.ENEMY_BIT)
					((Enemy)fixB.getUserData()).initBattle(); // call for battle
				break;
			case MathGame.ENEMY_BIT | MathGame.ENEMY_BIT:
					((Enemy)fixA.getUserData()).reverseVelocity(true, false); // call for battle
					((Enemy)fixB.getUserData()).reverseVelocity(true, false); // call for battle
				break;
			case MathGame.ENEMY_BIT | MathGame.WALL_BIT:
				if (fixA.getFilterData().categoryBits == MathGame.ENEMY_BIT)
					((Enemy)fixA.getUserData()).reverseVelocity(true,false); // call for battle
				else if(fixB.getFilterData().categoryBits == MathGame.ENEMY_BIT)
					((Enemy)fixB.getUserData()).reverseVelocity(true,false); // call for battle
				break;
			
		 	case MathGame.PORTAL_BIT | MathGame.PLAYER_BIT:
				if (fixA.getFilterData().categoryBits == MathGame.PORTAL_BIT)
					if(Gdx.input.isKeyPressed(Input.Keys.UP))
						((Portal)fixA.getUserData()).hit();
				else if(fixB.getFilterData().categoryBits == MathGame.PORTAL_BIT)
					if(Gdx.input.isKeyPressed(Input.Keys.UP))
						((Portal)fixB.getUserData()).hit();
				break;
		
		}
			
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
