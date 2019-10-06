package finalforeach.ld45;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthBar
{
	public static Texture barTex = new Texture("healthbar.png");
	public static TextureRegion bar = new TextureRegion(barTex,0,0,122,16);
	public static TextureRegion minusHP = new TextureRegion(barTex,0,16,22,14);
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(bar,0,0);
		int hp = (int)((Game.player.fighter.hp/Game.player.fighter.getMaxHP())*10);
		for(int h=10;h>hp;h--)
		{
			batch.draw(minusHP, 99-((10-h)*9), 1);
		}
	}
}
