package finalforeach.ld45;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Item {
	public static TextureRegion[][] texReg;
	{
		texReg = TextureRegion.split(new Texture("items.png"), 64, 64);
	}
	float x, y;
	float groundY;
	int i,j;
	Rectangle bounds;
	boolean pickedUp;
	float fallSpeed;
	public Item(float x, float y, int i,int j)
	{
		this.x=x;
		this.groundY=y;
		this.y=groundY;
		bounds = new Rectangle(x,y,64,64);
		this.i=i;
		this.j=j;
		fallSpeed=-150;
	}
	
	public void update(float deltaTime)
	{
		y-=deltaTime*fallSpeed;
		//y=groundY;fallSpeed=-150;
		fallSpeed+=250*deltaTime;
		if(y<groundY)
		{
			y=groundY;
			fallSpeed=-fallSpeed/8;
		}
		bounds.set(x,y,64,64);
		if(Game.player.fighter.rect.overlaps(bounds))
		{
			if(onPickup())
			{
				pickedUp=true;
			}
		}
	}
	/**Event occurs when item is picked up. Returns whether the item can be destroyed.*/
	protected abstract boolean onPickup();

	public void draw(SpriteBatch batch)
	{
		batch.draw(texReg[i][j],x,y);
	}
}
