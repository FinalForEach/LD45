package finalforeach.ld45;

public class AIController {
	Fighter fighter;
	public AIController(Fighter fighter)
	{
		this.fighter=fighter;
	}
	public void update(float deltaTime)
	{
		atkCooldown-=deltaTime;
	}
	public void lookAtPlayer()
	{
		float tx = Game.player.fighter.x,ty = Game.player.fighter.y;
		if(fighter.x-tx>1)
		{
			fighter.movedLeftLast=true;
		}
		if(tx-fighter.x>1)
		{
			fighter.movedLeftLast=false;
		}
	}
	public void followPlayer(float maxDist)
	{
		float tx = Game.player.fighter.x,ty = Game.player.fighter.y;
		float dxSq = (fighter.x- tx)*(fighter.x- tx) ;
		float dySq = (fighter.y- ty)*(fighter.y- ty);
		float distSq = dxSq+dySq;
		if(distSq>maxDist*maxDist)
		{
			if(fighter.x-tx>1)
			{
				fighter.moveLeft();
			}
			if(tx-fighter.x>1)
			{
				fighter.moveRight();
			}
			if(fighter.y-ty>1)
			{
				fighter.moveDown();
			}
			if(ty-fighter.y>1)
			{
				fighter.moveUp();
			}
		}
	}
	float atkCooldown;
	public void attackPlayer(float interval) 
	{
		Fighter target = Game.player.fighter;
		if(atkCooldown<=0 && !target.isDead())
		{
			fighter.attack();
			atkCooldown=interval;
		}
	}
}
