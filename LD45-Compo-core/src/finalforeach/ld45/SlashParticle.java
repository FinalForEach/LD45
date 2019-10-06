package finalforeach.ld45;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SlashParticle extends Particle {

	Fighter owner;
	String team;
	public static TextureRegion slashTexReg;
	{
		slashTexReg = new TextureRegion(particleTex,48,0,16,32);
	}
	public SlashParticle(String team, float x, float y, float vx, float vy, Fighter owner) {
		super(x, y, vx, vy, 0, 2);
		this.owner=owner;
		this.team=team;
	}
	public void draw(SpriteBatch batch)
	{
		float scaleX=1;
		if(vel.x<0)scaleX=-1;
		batch.draw(slashTexReg,pos.x,pos.y,0,0,16,32,scaleX,1,0);
	}

	@Override
	public float getMaxLifetime() {
		return 0.4f;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		for(Fighter f : Game.fighters)
		{
			if(f!=owner && f.team!=team &&f.rect.overlaps(bounds))
			{
				f.hit(5);
			}
		}
	}
}
