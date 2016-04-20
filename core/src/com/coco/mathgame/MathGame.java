package com.coco.mathgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScreen;

public class MathGame extends Game {
	public static final int V_WIDTH = 1280;//400; //temp
	public static final int V_HEIGHT = 768; //temp
	public static final float PPM = 100; // pixels per meter
	
	public static final short GROUND_BIT = 1;
	public static final short PLAYER_BIT = 2;
	public static final short WALL_BIT = 4;
	public static final short PORTAL_BIT = 8;
	public static final short ENEMY_BIT = 16;
	
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
		
	}

	@Override
	public void render () {
		super.render();
	}
}
