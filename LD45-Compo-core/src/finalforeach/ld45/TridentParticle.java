package finalforeach.ld45;

public class TridentParticle extends Particle {

	Fighter owner;
	String team;
	public TridentParticle(String team, float x, float y, float vx, float vy, Fighter owner) {
		super(x, y, vx, vy, 0, 1);
		this.owner=owner;
		this.team=team;
	}

	@Override
	public float getMaxLifetime() {
		return 0.4f;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		for(Fighter f : Game.fighters)
		{
			if(f!=owner && f.team!=team &&f.rect.overlaps(bounds))
			{
				f.hit(2);
			}
		}
	}
}
