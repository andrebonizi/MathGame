package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

import Sprites.Enemy;
import Sprites.Item;
import Sprites.Player;

public class Battle implements Screen {

	private Viewport viewport;
	private Stage stage;
	private Skin skin;
	
	private MathGame game;
	
	
	private int playheart;
	private int eneheart;
	
	public Battle(MathGame game){
		this.game = game;
		viewport = new FitViewport(MathGame.V_WIDTH, MathGame.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport,((MathGame) game).batch);
		
		playheart = 5;
		eneheart = 3;
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(),Color.WHITE);
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		Label BattleLabel = new Label("Battle", font);
		table.add(BattleLabel).expandX();
		Label BattleLabel2 = new Label("Battleaiusdhaodhfosapjgk;sdghlm", font);
		table.add(BattleLabel2).expandX();
		table.row();
		Label BattleLabel3 = new Label("Battle16545", font);
		table.add(BattleLabel3).expandX();
		Label BattleLabel4 = new Label("Batt984651651hlm", font);
		table.add(BattleLabel4).expandX();
		
		stage.addActor(table);
		
		question();
		
		//input handler
		
        
	}
	
	public void question(){
		
		while(playheart > 0 && eneheart > 0){
			
			int keyPressed = 0;
			while(keyPressed == 0){
				
				keyPressed = handleInput();
			}
			
			
		}
		if(playheart == 0){
			game.setScreen(new GameOverScreen(game));
			this.dispose();
			
		}else{
			if(eneheart == 0){
				game.setScreen(game.getMain());
				this.dispose();
				
			}
			
		}
		
	}
	
	public int handleInput(){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			return 1;
		}else{
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				return 2;
			}else{
				if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
					return 3;
				}else{
					if (Gdx.input.isKeyPressed(Input.Keys.UP)){
						return 4;
					}else{
						return 0;
					}
				}
			}
		}
		
		
		
	}
	

	private void createBasicSkin(){
	  //Create a font
	  BitmapFont font = new BitmapFont();
	  skin = new Skin();
	  skin.add("default", font);
	 
	  //Create a texture
	  Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
	  pixmap.setColor(Color.WHITE);
	  pixmap.fill();
	  skin.add("background",new Texture(pixmap));
	 
	  //Create a button style
	  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
	  textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
	  textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
	  textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
	  textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
	  textButtonStyle.font = skin.getFont("default");
	  skin.add("default", textButtonStyle);
	 
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
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
		// TODO Auto-generated method stub
		
	}

}
