package finalforeach.ld45;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Fighter
{
	String team;
	float hp, invulnerableTime;
	float x, y;
	Rectangle rect;
	public Fighter(String team,float x, float y) {
		this.team=team;
		this.x=x;
		this.y=y;
		this.hp=getMaxHP();
		rect = new Rectangle(x-32,y,64,64);
	}
	public final void hit(float dmg)
	{
		if(isDead())return;
		if(invulnerableTime>0)return;
		onHit(dmg);
		invulnerableTime=0.4f;
	}
	protected void onHit(float dmg)
	{
		hp-=dmg;
	}
	
	public abstract float getMaxHP();
	boolean movingLeft,movingRight;
	boolean movingUp,movingDown;
	boolean isMoving;
	boolean canMove;
	
	boolean lookingLeft;
	public void moveLeft(){
		movingLeft=true;
	}
	public void moveRight(){
		movingRight=true;
	}
	public void moveUp(){
		movingUp=true;
	}
	public void moveDown(){
		movingDown=true;
	}
	public void roll()
	{
		if(invulnerableTime<=0)
		{
			onRoll();
			invulnerableTime=0.5f;
		}
	}
	public abstract void onRoll();
	public void update(float deltaTime)
	{
		invulnerableTime-=deltaTime;
		if(invulnerableTime<0)invulnerableTime=0;
		float speed = getSpeed()*deltaTime;
		if(canMove&&!isDead())
		{
			if(movingLeft){
				x-=speed;
			}
			if(movingRight){
				x+=speed;
			}
			if(movingUp){
				y+=speed;
			}
			if(movingDown){
				y-=speed;
			}
		}
		isMoving=(movingLeft^movingRight || movingUp ^ movingDown) && canMove;
		
		movingLeft=movingRight=movingUp=movingDown=false;
		canMove=true;
		rect.set(x-32,y,64,64);
	}
	private float getSpeed() {
		return 90;
	}
	public abstract void draw(SpriteBatch batch);
	public void attack()
	{
		if(isDead())return;
		onAttack();
	}
	public abstract void onAttack();
	public boolean isDead()
	{
		return hp<=0;
	}
}
