package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

import Logic.Question;

public class StartScene {
	public Stage stage;
	private Viewport viewport;
	
	private Integer playerHp;
	private Integer enemyHp;
	private float timeCount;
	
	Label playerHpLabel;
	Label enemyHpLabel;
	
	public StartScene(SpriteBatch sb){
		viewport = new FitViewport(MathGame.V_WIDTH, MathGame.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
			
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		playerHpLabel = new Label(String.format("%02d", playerHp), new Label.LabelStyle(new BitmapFont(), Color.RED));
		enemyHpLabel = new Label(String.format("%02d", enemyHp), new Label.LabelStyle(new BitmapFont(), Color.RED));
		
		table.add(playerHpLabel).expandX().padTop(10);
		table.add(enemyHpLabel).expandX().padTop(10);
		stage.addActor(table);
	
	}
	
}
