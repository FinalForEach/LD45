package finalforeach.ld45;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

public class Player {
	final Fighter fighter;
	private final Vector3 screenCoords = new Vector3(0,0,0);
	public Player(Fighter fighter)
	{
		this.fighter=fighter;
	}
	public void update(float deltaTime)
	{
		screenCoords.set(Gdx.input.getX(),Gdx.input.getY(),0);
		Game.cam.unproject(screenCoords);
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
		if(Gdx.input.isKeyJustPressed(Keys.P))
		{
			fighter.hp=fighter.getMaxHP();
		}
		if(Gdx.input.isButtonPressed(Buttons.LEFT))
		{
			fighter.attack();
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
