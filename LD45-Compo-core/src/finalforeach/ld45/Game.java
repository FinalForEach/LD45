package finalforeach.ld45;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Fighter fighter;
	Player player;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background.png");
		fighter = new Gladiator(30, 30);
		player = new Player(fighter);
	}

	public void update(float deltaTime)
	{
		player.update(deltaTime);
		fighter.update(deltaTime);
	}
	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(img, 0, 0);
		fighter.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
