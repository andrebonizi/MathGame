package Screens;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coco.mathgame.MathGame;

import Scenes.BattleHud;
import Sprites.Enemy;
import Sprites.Item;
import Sprites.ItemDef;
import Sprites.Player;
import Sprites.PortalSign;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;

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
	private B2WorldCreator creator;
	
	// sprites
	private Array<Item> items;
	private LinkedBlockingQueue<ItemDef> itemsToSpawn; 
	private String portalDest;
	
	public PlayScreen(MathGame game){
		portalDest = new String("");
		atlas = new TextureAtlas("MathAsset.pack");
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
		
		creator = new B2WorldCreator(this);
		
		player = new Player(this);
		
		world.setContactListener(new WorldContactListener());
		
		items = new Array<Item>();
		itemsToSpawn = new LinkedBlockingQueue<ItemDef>();		
	}
	
	public void spawnItem(ItemDef idef){
		itemsToSpawn.add(idef);
	}
	
	public void handleSpawningItems(){
		if(!itemsToSpawn.isEmpty()){
			ItemDef idef = itemsToSpawn.poll();
			if (idef.type == PortalSign.class)
				items.add(new PortalSign(this, idef.position.x, idef.position.y));
		}
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
		handleSpawningItems();
		
		world.step(1/60f, 6, 2);
		
		player.update(dt);
		for(Enemy e: creator.getShadows()){
			e.update(dt);
			if(e.getX() < player.getX() + 5)
				e.b2body.setActive(true);
		}
		
		for(Item i : items)
			i.update(dt);
		
		gamecam.position.x = player.b2body.getPosition().x;
		
		gamecam.update();
		renderer.setView(gamecam);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		
		Gdx.gl.glClearColor(1, 1,1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		//b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		
		for(Enemy e: creator.getShadows()){
			e.setSize(2,4);
			e.draw(game.batch);
		}
		
		for(Item i : items)
			i.draw(game.batch);
		
		player.setSize(2,4);
		player.draw(game.batch);
		
		game.batch.end();
		
		//game.batch.setProjectionMatrix(battleHud.stage.getCamera().combined);
		//battleHud.stage.draw();
		if (!portalDest.isEmpty()){
			portalDest = new String("");
			dispose();
		}	
			
	}
	
	public void enterPortal(String dest){
		portalDest = dest;
	}
	
	public void initBattle(){
		game.setScreen(new Battle(game));
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
	
	public TiledMap getMap(){
		return map;
	}
	
	public World getWorld(){
		return world;
	}

}
