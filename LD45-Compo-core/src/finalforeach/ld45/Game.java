package finalforeach.ld45;

import java.util.Comparator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	public static Texture bkgTex, youWinTex, gameOverTex;
	public static OrthographicCamera cam, uiCam;
	Viewport viewport;
	Viewport uiViewport;
	public static Array<Particle> particles;
	public static Array<Fighter> fighters;
	public static Array<AIController> ais;
	public static Array<Item> items;
	public static Player player;
	HealthBar healthBar;
	Level currentLevel;
	Music bkgMusic;
	Music winMusic, loseMusic;
	public static Texture[] lvlTextures = new Texture[10];
	@Override
	public void create () {
		cam = new OrthographicCamera(512,512);
		uiCam = new OrthographicCamera(512,512);
		viewport = new FitViewport(1280, 720,cam);
		uiViewport = new FitViewport(1280, 720,uiCam);
		batch = new SpriteBatch();
		bkgTex = new Texture("background.png");
		youWinTex = new Texture("YouWin.png");
		gameOverTex = new Texture("GameOver.png");
		fighters = new Array<Fighter>();
		particles = new Array<Particle>();
		ais = new Array<AIController>();
		items =new Array<Item>();
		
		healthBar = new HealthBar();
		Fighter playerFighter = new Gladiator("Player",30, 30);
		player = new Player(playerFighter);
		
		fighters.add(playerFighter);
		currentLevel = Level.levels[Level.curLvlIndex];
		currentLevel.spawnWarriors();
		
		
		cam.zoom=0.5f;
		uiCam.zoom=0.5f;
		bkgMusic = Gdx.audio.newMusic(Gdx.files.internal("March of the Gladiator.ogg"));
		bkgMusic.play();
		bkgMusic.setLooping(true);
		winMusic = Gdx.audio.newMusic(Gdx.files.internal("You win!.ogg"));
		loseMusic = Gdx.audio.newMusic(Gdx.files.internal("Game Over.ogg"));
		
		for(int i=0;i<lvlTextures.length;i++)
		{
			lvlTextures[i] = new Texture("Level-"+(i+1)+".png");
		}
	}
	boolean mute=false;
	boolean gameJustEnded=false;
	public void update(float deltaTime)
	{
		if(Gdx.input.isKeyJustPressed(Keys.F11))
		{
			if(!Gdx.graphics.isFullscreen())
			{
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			}else
			{
				Gdx.graphics.setWindowedMode(1280, 720);
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.M))
		{
			mute=!mute;
			if(mute)
			{
				bkgMusic.setVolume(0);
			}else
			{
				bkgMusic.setVolume(1);
			}
		}
		boolean allDead=fighters.size>0;//Only count if there ARE fighters
		Array<Fighter> deadFighters = new Array<Fighter>();
		for(Fighter f : fighters)
		{
			if(f!=player.fighter)
			{
				if(f.isDead())
				{
					deadFighters.add(f);
				}else
				{
					allDead=false;
				}
			}
			
		}
		
		if(allDead && Level.curLvlIndex<Level.levels.length)
		{
			Level.curLvlIndex++;
			
			if(Level.curLvlIndex<Level.levels.length)
			{
				currentLevel=Level.levels[Level.curLvlIndex];
				currentLevel.spawnWarriors();
				if(player.fighter.hp<player.fighter.getMaxHP())
				{
					player.fighter.hp=player.fighter.getMaxHP();
				}
				fighters.removeAll(deadFighters, false);
			}
		}
		Item.playerPickingItemUp=false;
		Array<AIController> deadAIs = new Array<AIController>();
		for(AIController ai : ais)
		{
			ai.update(deltaTime);
			if(ai.fighter.isDead())deadAIs.add(ai);
		}
		ais.removeAll(deadAIs, false);
		for(Fighter f : fighters)
		{
			f.update(deltaTime);
			
			f.y=MathUtils.clamp(f.y, 0, 100);
			f.x=MathUtils.clamp(f.x, 64, 2048-64);
		}
		Array<Item> deadItems = new Array<Item>();
		for(Item item : items)
		{
			item.update(deltaTime);
			if(item.pickedUp)deadItems.add(item);
		}
		items.removeAll(deadItems, false);
		Array<Particle> deadParticles = new Array<Particle>();
		for(Particle p : particles)
		{
			p.update(deltaTime);
			if(p.lifetime>p.getMaxLifetime())
				deadParticles.add(p);
		}
		particles.removeAll(deadParticles, true);
		player.update(deltaTime);
		float w=1280;
		float h=720;
		cam.position.set(MathUtils.clamp(player.fighter.x,w/2*cam.zoom,2048-w/2*cam.zoom),
				Math.max(player.fighter.y+ h/4*cam.zoom,h/2*cam.zoom), 0);
		
	}
    public void resize(int width, int height) {
        viewport.update(width, height);
        uiViewport.update(width, height);
    }
    Comparator<Fighter> fighterComparator =new Comparator<Fighter>(){

		@Override
		public int compare(Fighter o1, Fighter o2) {
			return (int)Math.signum(o2.y-o1.y);
		}};
	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		cam.update();
		
		batch.setProjectionMatrix(cam.projection);
		batch.setTransformMatrix(cam.view);
		
		batch.begin();
		batch.draw(bkgTex, 0, 0);
		fighters.sort(fighterComparator);
		for(Fighter f : fighters)
		{
			f.draw(batch);
		}
		for(Item item : items)
		{
			item.draw(batch);
		}
		for(Particle p : particles)
		{
			p.draw(batch);
		}
		batch.end();
		
		uiCam.position.x=320;
		uiCam.position.y=-128-32-4;
		uiCam.zoom=0.5f;
		uiCam.update();
		batch.setProjectionMatrix(uiCam.projection);
		batch.setTransformMatrix(uiCam.view);
		batch.begin();
		healthBar.draw(batch);
		batch.end();

		
		uiCam.position.x=0;
		uiCam.position.y=0;
		uiCam.zoom=1f;
		uiCam.update();
		batch.setProjectionMatrix(uiCam.projection);
		batch.setTransformMatrix(uiCam.view);
		batch.begin();
		if(Game.player.fighter.isDead())
		{

			if(!gameJustEnded)
			{
				bkgMusic.stop();
				loseMusic.play();
				gameJustEnded=true;
			}
			batch.draw(gameOverTex, -256, -256);
			if(Gdx.input.isKeyJustPressed(Keys.SPACE))
			{
				resetGame();
			}
		}
		else
		{
			if(Level.curLvlIndex>=Level.levels.length)
			{
				if(!gameJustEnded)
				{
					bkgMusic.stop();
					winMusic.play();
					gameJustEnded=true;
				}
				batch.draw(youWinTex, -256, -256);
				if(Gdx.input.isKeyJustPressed(Keys.SPACE))
				{
					resetGame();
				}
			}
		}
		if(Level.curLvlIndex<lvlTextures.length)
		{
			batch.draw(lvlTextures[Level.curLvlIndex], -64, 256-64,128,128);
		}
		batch.end();
	}
	public void resetGame()
	{
		Level.curLvlIndex=0;
		Game.player.fighter = new Gladiator("Player", 30, 30);
		items.clear();
		fighters.clear();
		ais.clear();
		fighters.add(Game.player.fighter);
		currentLevel = Level.levels[Level.curLvlIndex];
		currentLevel.spawnWarriors();
		winMusic.stop();
		loseMusic.stop();
		bkgMusic.play();
		gameJustEnded=false;
	}
	@Override
	public void dispose () {
		batch.dispose();
		bkgTex.dispose();
	}
}
