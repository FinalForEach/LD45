package finalforeach.ld45;

public class MeleeAI extends AIController {

	public MeleeAI(Fighter fighter) {
		super(fighter);
	}
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		lookAtPlayer();
		followPlayer(64);
		attackPlayer(1);
	}
}
