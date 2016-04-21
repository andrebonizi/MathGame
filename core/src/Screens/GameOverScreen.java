package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

public class GameOverScreen implements Screen {

	private Viewport viewport;
	private Stage stage;
	
	private Game game;
	
	public GameOverScreen(Game game){
		this.game = game;
		viewport = new FitViewport(MathGame.V_WIDTH, MathGame.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport,((MathGame) game).batch);
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
				
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("./ProtestMed.fnt"),
		Gdx.files.internal("./ProtestMed.png"), false);
		Label.LabelStyle fontLabel = new Label.LabelStyle(font,Color.WHITE);
		
		Label gameOverLabel = new Label("GAME OVER", fontLabel);
				
		Label playAgainLabel = new Label("CLICK TO PLAY AGAIN", fontLabel);
		
		table.add(gameOverLabel).expandX();
		table.row();
		table.add(playAgainLabel).expandX().padTop(10);
		
		stage.addActor(table);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()){
			game.setScreen(new PlayScreen((MathGame) game));
			dispose();
		}
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		stage.dispose();
	}

}
