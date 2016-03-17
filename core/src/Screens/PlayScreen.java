package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
	
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	public PlayScreen(MathGame game){
		this.game = game;
		//texture = new Texture("badlogic.jpg");
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(MathGame.V_WIDTH, MathGame.V_HEIGHT, gamecam);
		battleHud = new BattleHud(game.batch);
		
		maploader = new TmxMapLoader();
		map = maploader.load("test2.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
	}
	
	@Override
	public void show() {
	}

	public void handleInput(float dt){
		if(Gdx.input.isTouched()){
			gamecam.position.x += 100 * dt;
		}
	}
	
	public void update(float dt){
		handleInput(dt);
		
		gamecam.update();
		renderer.setView(gamecam);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
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
