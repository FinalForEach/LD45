package finalforeach.ld45;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player;
	OrthographicCamera cam;
	Viewport viewport;
	public static Array<Particle> particles;
	public static Array<Fighter> fighters;
	@Override
	public void create () {
		cam = new OrthographicCamera(512,512);
		viewport = new FitViewport(512, 512,cam);
		batch = new SpriteBatch();
		img = new Texture("background.png");
		Fighter playerFighter = new Gladiator(30, 30);
		player = new Player(playerFighter);
		Fighter enemyFighter = new Gladiator(90,30);
		
		fighters = new Array<Fighter>();
		particles = new Array<Particle>();
		
		fighters.add(playerFighter);
		fighters.add(enemyFighter);
		cam.zoom=0.5f;
	}

	public void update(float deltaTime)
	{
		for(Fighter f : fighters)
		{
			f.update(deltaTime);
			
			f.y=MathUtils.clamp(f.y, 0, 100);
			f.x=MathUtils.clamp(f.x, 40, 1940);
		}
		Array<Particle> deadParticles = new Array<Particle>();
		for(Particle p : particles)
		{
			p.update(deltaTime);
			if(p.lifetime>p.getMaxLifetime())
				deadParticles.add(p);
		}
		particles.removeAll(deadParticles, true);
		player.update(deltaTime);
		cam.position.set(MathUtils.clamp(player.fighter.x,256*cam.zoom,2048-256*cam.zoom),
				Math.max(player.fighter.y+ 128*cam.zoom,256*cam.zoom), 0);
		
	}
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		
		batch.setProjectionMatrix(cam.projection);
		batch.setTransformMatrix(cam.view);
		
		batch.begin();
		batch.draw(img, 0, 0);
		for(Fighter f : fighters)
		{
			f.draw(batch);
		}

		for(Particle p : particles)
		{
			p.draw(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
