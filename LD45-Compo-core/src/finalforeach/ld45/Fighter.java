package finalforeach.ld45;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Fighter
{
	float hp, invulnerableTime;
	float x, y;
	Rectangle rect;
	public Fighter(float x, float y) {
		this.x=x;
		this.y=y;
		this.hp=getMaxHP();
		rect = new Rectangle(x-32,y,64,64);
	}
	public final void hit(float dmg)
	{
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
	
	boolean movedLeftLast;
	public void moveLeft(){
		movingLeft=true;
		movedLeftLast=true;
	}
	public void moveRight(){
		movingRight=true;
		movedLeftLast=false;
	}
	public void moveUp(){
		movingUp=true;
	}
	public void moveDown(){
		movingDown=true;
	}
	public void update(float deltaTime)
	{
		invulnerableTime-=deltaTime;
		if(invulnerableTime<0)invulnerableTime=0;
		float speed = getSpeed()*deltaTime;
		if(canMove)
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
	public abstract void attack();
}
