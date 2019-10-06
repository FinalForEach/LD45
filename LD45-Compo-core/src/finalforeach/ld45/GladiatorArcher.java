package finalforeach.ld45;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class GladiatorArcher extends Gladiator {
	public static TextureRegion[][] archerTexReg;
	{
		archerTexReg = TextureRegion.split(new Texture("gladiator-archer.png"), 64, 64);
	}
	public GladiatorArcher(String team,float x, float y) {
		super(team,x, y);
	}
	@Override
	protected void drawWarrior(SpriteBatch batch) {
		batch.draw(archerTexReg[i][j],x-47,y,64-47,0,64,64,lookingLeft?-1:1,1,0);
	}

	@Override
	public void onAttack()
	{
		if(canAttack())
		{
			atkTimer=0;
			swishSounds[MathUtils.random(0, swishSounds.length-1)].play();
			float velX = lookingLeft? -350:350;
			Game.particles.add(new ArrowParticle(team,x+(lookingLeft?-56:0), y+24, velX, 0,this));
		}
	}
	@Override
	public void onDeath()
	{
		Game.items.add(new ItemBow(x-64, y));
	}
	public float getAttackCooldown()
	{
		return 1f;
	}
}
