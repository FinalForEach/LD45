package finalforeach.ld45;

public class RangedAI extends AIController {

	public RangedAI(Fighter fighter) {
		super(fighter);
	}
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		lookAtPlayer();
		followPlayer(128);
		if(fighter.invulnerableTime<=0)
		{
			attackPlayer(1,256);	
		}
	}
}
