package com.coco.mathgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.PlayScreen;

public class MathGame extends Game {
	public static final int V_WIDTH = 400; //temp
	public static final int V_HEIGHT = 208; //temp
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
