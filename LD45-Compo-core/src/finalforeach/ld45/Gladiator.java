package finalforeach.ld45;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Gladiator extends Fighter
{
	public static TextureRegion[][] texReg;
	public static Sound[] owSounds = new Sound[4];
	public static Sound[] swishSounds = new Sound[4];
	
	{
		texReg = TextureRegion.split(new Texture("gladiator-naked.png"), 64, 64);
		for(int i =0;i<owSounds.length;i++)
		{
			 owSounds[i]= Gdx.audio.newSound(Gdx.files.internal("ow-"+(i+1)+".ogg"));	
		}
		for(int i =0;i<swishSounds.length;i++)
		{
			swishSounds[i]= Gdx.audio.newSound(Gdx.files.internal("swish-"+(i+1)+".ogg"));	
		}
	}
	
	
	float hitTimer;
	int i,j;
	public Gladiator(String team,float x,float y)
	{
		super(team,x,y);
	}
	
	@Override
	public void onHit(float dmg) {
		super.onHit(dmg);
		hitTimer=0.2f;
		owSounds[MathUtils.random(0, owSounds.length-1)].play();
	}
	float animationTime=0, atkTimer=-1, deathTime=0,rollTimer=-1;
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		hitTimer=Math.max(0,hitTimer-deltaTime);
		animationTime+=deltaTime * 5;
		if(isDead())
		{
			deathTime+=deltaTime*5;
			i=2;
			j = Math.min((int)deathTime,2);
		}else
		{
			if(isMoving&&rollTimer==-1)
			{
				switch(((int)animationTime) % 4)
				{
				case 0:j=0;break;
				case 1:j=1;break;
				case 2:j=2;break;
				case 3:j=1;break;
				}
			}
			if(atkTimer>-1)
			{
				atkTimer+=deltaTime;
				i=1;
				if(atkTimer>0.2)
				{
					i=0;
					if(atkTimer>0.3)
					{
						atkTimer=-1;
					}
				}
			}
			if(rollTimer>-1)
			{
				rollTimer+=deltaTime*3;
				i=3;
				j=(int)(rollTimer*4);
				if(rollTimer>1)
				{
					rollTimer=-1;
					i=0;
					j=0;
				}
			}
		}
	}
	@Override
	public float getMaxHP() {
		return 10;
	}

	@Override
	public final void draw(SpriteBatch batch) {
		if(hitTimer>0){
			batch.setColor(1, 0, 0, 1);	
		}
		 drawWarrior(batch);
		if(hitTimer>0){
			batch.setColor(1, 1, 1, 1);	
		}
	}
	protected void drawWarrior(SpriteBatch batch)
	{
		batch.draw(texReg[i][j],x-32,y,32,0,64,64,lookingLeft?-1:1,1,0);
	}
	@Override
	public void onAttack()
	{
		if(atkTimer==-1 && rollTimer==-1)
		{
			atkTimer=0;
			swishSounds[MathUtils.random(0, swishSounds.length-1)].play();
			float velX = lookingLeft? -100:100;
			Game.particles.add(new PunchParticle(team,x+(lookingLeft?-14:14), y+24, velX, 0,this));
		}
	}

	@Override
	public void onRoll() {
		rollTimer=0;
	}


}
