package finalforeach.ld45;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class GladiatorTrident extends Gladiator
{
	public static TextureRegion[][] tridentTexReg;
	{
		tridentTexReg = TextureRegion.split(new Texture("gladiator-trident.png"), 64, 64);
	}
	public GladiatorTrident(String team,float x, float y) {
		super(team,x, y);
	}
	@Override
	protected void drawWarrior(SpriteBatch batch) {
		batch.draw(tridentTexReg[i][j],x-47,y,64-47,0,64,64,lookingLeft?-1:1,1,0);
	}

	@Override
	public void onAttack()
	{
		if(atkTimer==-1)
		{
			atkTimer=0;
			swishSounds[MathUtils.random(0, swishSounds.length-1)].play();
			float velX = lookingLeft? -150:150;
			Game.particles.add(new TridentParticle(team,x+(lookingLeft?-56:0), y+24, velX, 0,this));
		}
	}
}
