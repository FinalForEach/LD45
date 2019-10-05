package finalforeach.ld45;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player {
	final Fighter fighter;
	public Player(Fighter fighter)
	{
		this.fighter=fighter;
	}
	public void update(float deltaTime)
	{
		if(Gdx.input.isKeyPressed(Keys.A)){
			fighter.moveLeft();
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			fighter.moveRight();
		}
		if(Gdx.input.isKeyPressed(Keys.W)){
			fighter.moveUp();
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			fighter.moveDown();
		}
	}
}
