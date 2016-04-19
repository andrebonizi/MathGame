package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

public class BattleHud implements Disposable{

	public Stage stage;
	private Viewport viewport;
	
	private Integer playerHp;
	private Integer enemyHp;
	private float timeCount;
	
	Label playerHpLabel;
	Label enemyHpLabel;
	
	public BattleHud(SpriteBatch sb){
		viewport = new FitViewport(MathGame.V_WIDTH, MathGame.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		playerHp = 100;
		enemyHp = 100;
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		playerHpLabel = new Label(String.format("%02d", playerHp), new Label.LabelStyle(new BitmapFont(), Color.RED));
		enemyHpLabel = new Label(String.format("%02d", enemyHp), new Label.LabelStyle(new BitmapFont(), Color.RED));
		
		table.add(playerHpLabel).expandX().padTop(10);
		table.add(enemyHpLabel).expandX().padTop(10);
		stage.addActor(table);
	}
	
	@Override
	public void dispose() {
		stage.dispose();	
	}
}
