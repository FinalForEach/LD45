package finalforeach.ld45;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Elephant extends Fighter 
{
	float hitTimer;
	int i,j;
	float animationTime=0, atkTimer=-1, deathTime=0;
	public static TextureRegion[][] texReg = TextureRegion.split(new Texture("Elephant.png"), 128, 128);
	public static Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("elephant-death.ogg"));
	public static Sound[] owSounds = new Sound[2];
	static {
		for(int i =0;i<owSounds.length;i++)
		{
			 owSounds[i]= Gdx.audio.newSound(Gdx.files.internal("elephant-hurt-"+(i+1)+".ogg"));	
		}
	}
	public Elephant(String team, float x, float y) {
		super(team, x, y);
		rect.set(x-64,y,128,128);
	}

	@Override
	public float getMaxHP() {
		return 100;
	}

	@Override
	public void onRoll() {
		return;
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(hitTimer>0){
			batch.setColor(1, 0, 0, 1);	
		}
		batch.draw(texReg[i][j],x-64,y,64,0,128,128,lookingLeft?-1:1,1,0);
		if(hitTimer>0){
			batch.setColor(1, 1, 1, 1);	
		}
	}
	@Override
	public void onHit(float dmg) {
		super.onHit(dmg);
		hitTimer=0.2f;
		owSounds[MathUtils.random(0, owSounds.length-1)].play();
	}	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		hitTimer=Math.max(0,hitTimer-deltaTime);
		animationTime+=deltaTime * 3;
		if(isDead())
		{
			deathTime+=deltaTime*5;
			i=2;
			j = Math.min((int)deathTime,2);
		}else
		{
			if(atkTimer>-1)
			{
				canMove=false;
				atkTimer+=deltaTime;
				i=1;
				j=0;
				if(atkTimer>0.2)
				{
					i=1;
					j=1;
					if(atkTimer>0.3)
					{
						j=2;
						if(j!=2 && atkTimer<0.4)
						{
							slamAttack();//Hit again!
						}
						if(atkTimer>0.6)
						{
							i=0;j=0;
							canMove=true;
						}
					}
				}
			}
			if(isMoving&&canMove)
			{
				switch(((int)animationTime) % 4)
				{
				case 0:j=0;break;
				case 1:j=1;break;
				case 2:j=2;break;
				case 3:j=1;break;
				}
			}
			if(atkTimer>getAttackCooldown())
			{
				atkTimer=-1;
			}
		}
	}
	@Override
	public void onAttack()
	{
		if(canAttack())
		{
			atkTimer=0;
			slamAttack();
		}
	}
	protected void slamAttack()
	{
		Gladiator.swishSounds[MathUtils.random(0, Gladiator.swishSounds.length-1)].play();
		float velX = lookingLeft? -200:200;
		Game.particles.add(new SlashParticle(team,x+(lookingLeft?-14:14), y+24, velX, 0,this));
	}
	public boolean canAttack()
	{
		return (atkTimer==-1);
	}
	public float getAttackCooldown()
	{
		return 3f;
	}
	@Override
	public float getSpeed()
	{
		return 30;
	}
	public void onDeath()
	{
		deathSound.play();
	}
}
