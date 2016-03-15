package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

import Scenes.BattleHud;

public class PlayScreen implements Screen{

	private MathGame game;
	//Texture texture;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private BattleHud battleHud;
	
	public PlayScreen(MathGame game){
		this.game = game;
		//texture = new Texture("badlogic.jpg");
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(MathGame.V_WIDTH, MathGame.V_HEIGHT, gamecam);
		battleHud = new BattleHud(game.batch);
	}
	
	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(battleHud.stage.getCamera().combined);
		battleHud.stage.draw();
		//game.batch.setProjectionMatrix(gamecam.combined);
		//game.batch.begin();
		//game.batch.draw(texture, 0, 0);
		//game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	
		gamePort.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
