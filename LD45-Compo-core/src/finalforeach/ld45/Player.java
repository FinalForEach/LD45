package finalforeach.ld45;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class Player {
	Fighter fighter;
	private final Vector3 screenCoords = new Vector3(0,0,0);
	public Player(Fighter fighter)
	{
		this.fighter=fighter;
	}
	public void update(float deltaTime)
	{
		screenCoords.set(Gdx.input.getX(),Gdx.input.getY(),0);
		Game.cam.unproject(screenCoords);
		if(Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)){
			fighter.moveLeft();
		}
		if(Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)){
			fighter.moveRight();
		}
		if(Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)){
			fighter.moveUp();
		}
		if(Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)){
			fighter.moveDown();
		}
		if(Gdx.input.isButtonPressed(Buttons.LEFT))
		{
			fighter.attack();
		}
		if(Gdx.input.isButtonPressed(Buttons.RIGHT))
		{
			fighter.roll();
		}
		if(screenCoords.x>fighter.x)
		{
			fighter.lookingLeft=false;
		}
		if(screenCoords.x<fighter.x)
		{
			fighter.lookingLeft=true;
		}
	}
}
