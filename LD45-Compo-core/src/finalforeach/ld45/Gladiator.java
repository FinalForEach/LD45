package finalforeach.ld45;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gladiator extends Fighter
{
	public static Texture gladiatorTex = new Texture("gladiator-naked.png");
	
	public Gladiator(float x,float y)
	{
		super(x,y);
	}
	
	@Override
	public void hit(float dmg) {
		super.hit(dmg);
	}

	@Override
	public float getMaxHP() {
		return 10;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(gladiatorTex, x-32, y,64,64,0,0,64,64,movedLeftLast,false);
	}


}
