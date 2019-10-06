package finalforeach.ld45;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Particle 
{
	public static Texture particleTex = new Texture("particles.png");
	public static TextureRegion[][] texReg = TextureRegion.split(particleTex, 16, 16);
	
	
	Vector2 pos, vel;
	final int i,j;
	public float lifetime;
	Rectangle bounds;
	public Particle(float x, float y, float vx, float vy, int i,int j)
	{
		this.i=i;
		this.j=j;
		pos = new Vector2(x,y);
		vel = new Vector2(vx,vy);
		bounds = new Rectangle(x,y,16,16);
	}
	
	public void update(float deltaTime)
	{
		pos.mulAdd(vel, deltaTime);
		lifetime+=deltaTime;
		bounds.setPosition(pos.x, pos.y);
	}
	public void draw(SpriteBatch batch)
	{
		float scaleX=1;
		if(vel.x<0)scaleX=-1;
		batch.draw(texReg[i][j],pos.x,pos.y,0,0,16,16,scaleX,1,0);
	}
	public abstract float getMaxLifetime();
}
