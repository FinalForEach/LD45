package finalforeach.ld45;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Gladiator extends Fighter
{
	public static Texture gladiatorTex = new Texture("gladiator-naked.png");
	public static Sound[] owSounds = new Sound[4];
	{
		for(int i =0;i<owSounds.length;i++)
		{
			 owSounds[i]= Gdx.audio.newSound(Gdx.files.internal("ow-"+(i+1)+".ogg"));	
		}
	}
	float hitTimer;
	public Gladiator(float x,float y)
	{
		super(x,y);
	}
	
	@Override
	public void hit(float dmg) {
		super.hit(dmg);
		hitTimer=0.2f;
		owSounds[MathUtils.random(0, owSounds.length-1)].play();
	}
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		hitTimer=Math.max(0,hitTimer-deltaTime);
	}
	@Override
	public float getMaxHP() {
		return 10;
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(hitTimer>0){
			batch.setColor(1, 0, 0, 1);	
		}
		batch.draw(gladiatorTex, x-32, y,64,64,0,0,64,64,movedLeftLast,false);		
		if(hitTimer>0){
			batch.setColor(1, 1, 1, 1);	
		}
	}


}
