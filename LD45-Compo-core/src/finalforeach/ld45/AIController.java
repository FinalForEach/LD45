package finalforeach.ld45;

public class AIController {
	Fighter fighter;
	float followCooldown=-1;
	float atkCooldown;
	public AIController(Fighter fighter)
	{
		this.fighter=fighter;
	}
	public void update(float deltaTime)
	{
		atkCooldown-=deltaTime;
		followCooldown-=deltaTime;
		if(followCooldown<=0)followCooldown=-1;
	}
	public void lookAtPlayer()
	{
		if(fighter.isDead())return;
		float tx = Game.player.fighter.x;
		if(fighter.x-tx>1)
		{
			fighter.lookingLeft=true;
		}
		if(tx-fighter.x>1)
		{
			fighter.lookingLeft=false;
		}
	}
	public void followPlayer(float maxDist)
	{
		if(fighter.isDead())return;
		if(followCooldown>0)return;
		float tx = Game.player.fighter.x,ty = Game.player.fighter.y;
		float dy = Math.abs(ty - fighter.y);
		if(getDistToPlayer()>maxDist || dy>20)
		{
			followCooldown=-1;
			if(fighter.x-tx>1)
			{
				fighter.moveLeft();
				fighter.lookingLeft=true;
			}
			if(tx-fighter.x>1)
			{
				fighter.moveRight();
				fighter.lookingLeft=false;
			}
			if(fighter.y-ty>1)
			{
				fighter.moveDown();
			}
			if(ty-fighter.y>1)
			{
				fighter.moveUp();
			}
		}else
		{
			if(followCooldown==-1)
				followCooldown=1;
		}
	}
	public double getDistToPlayer()
	{
		float tx = Game.player.fighter.x,ty = Game.player.fighter.y;
		float dxSq = (fighter.x- tx)*(fighter.x- tx) ;
		float dySq = (fighter.y- ty)*(fighter.y- ty);
		return Math.sqrt(dxSq+dySq);
	}
	public void attackPlayer(float interval,float maxDist) 
	{
		if(fighter.isDead())return;
		if(Game.player.fighter.isDead())return;
		if(atkCooldown<=0 && getDistToPlayer()<maxDist)
		{
			fighter.attack();
			atkCooldown=interval;
		}
	}
}
