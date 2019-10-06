package finalforeach.ld45;

public class MeleeAI extends AIController {

	public MeleeAI(Fighter fighter) {
		super(fighter);
	}
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		lookAtPlayer();
		followPlayer(32);
		if(fighter.invulnerableTime<=0)
		{
			attackPlayer(1,128);	
		}
	}
}
