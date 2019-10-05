package finalforeach.ld45;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Fighter
{
	float hp;
	float x, y;
	public Fighter(float x, float y) {
		this.x=x;
		this.y=y;
		this.hp=getMaxHP();
	}
	public void hit(float dmg)
	{
		hp-=dmg;
	}
	public abstract float getMaxHP();
	boolean movingLeft,movingRight;
	boolean movingUp,movingDown;
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
	public void update(float deltaTime)
	{
		float speed = getSpeed()*deltaTime;
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
		
		
		movingLeft=movingRight=movingUp=movingDown=false;
	}
	private float getSpeed() {
		return 60;
	}
	public abstract void draw(SpriteBatch batch);
}
