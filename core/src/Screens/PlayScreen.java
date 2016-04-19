package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

import Scenes.BattleHud;
import Sprites.Player;
import Tools.B2WorldCreator;

public class PlayScreen implements Screen{

	private MathGame game;
	private TextureAtlas atlas;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private BattleHud battleHud;
	
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	private Player player;
	
	//box2d
	private World world;
	private Box2DDebugRenderer b2dr;
	
	
	
	public PlayScreen(MathGame game){
		atlas = new TextureAtlas("MathAssets.pack");
		this.game = game;
		//texture = new Texture("badlogic.jpg");
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(MathGame.V_WIDTH / MathGame.PPM, MathGame.V_HEIGHT / MathGame.PPM, gamecam);
		
		//battleHud = new BattleHud(game.batch);
		
		maploader = new TmxMapLoader();
		map = maploader.load("Stage1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / MathGame.PPM);
		
		gamecam.position.set(gamePort.getWorldWidth() , gamePort.getWorldHeight() / 2, 0);
	
		world = new World(new Vector2(0,-10), true);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		player = new Player(world,this);
		
	}
	
	public TextureAtlas getAtlas(){
		return this.atlas;
	}
	
	@Override
	public void show() {
	}

	public void handleInput(float dt){
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 3) // && player.b2body.getLinearVelocity().x <= 2 ## to limit velocity
			player.b2body.applyLinearImpulse(new Vector2(0.1f,0), player.b2body.getWorldCenter(), true);
			
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -3) //  ## to limit velocity
			player.b2body.applyLinearImpulse(new Vector2(-0.1f,0), player.b2body.getWorldCenter(), true);
	}
	
	public void update(float dt){
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		player.update(dt);
		gamecam.position.x = player.b2body.getPosition().x;
		
		gamecam.update();
		renderer.setView(gamecam);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();
		
		//game.batch.setProjectionMatrix(battleHud.stage.getCamera().combined);
		//battleHud.stage.draw();
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
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		battleHud.dispose();
		
		
	}

}
