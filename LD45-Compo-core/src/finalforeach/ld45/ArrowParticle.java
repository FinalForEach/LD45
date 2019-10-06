package finalforeach.ld45;

public class ArrowParticle extends Particle {

	Fighter owner;
	String team;
	public ArrowParticle(String team, float x, float y, float vx, float vy, Fighter owner) {
		super(x, y, vx, vy, 0, 2);
		this.owner=owner;
		this.team=team;
	}

	@Override
	public float getMaxLifetime() {
		return 1f;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		for(Fighter f : Game.fighters)
		{
			if(f!=owner && f.team!=team &&f.rect.overlaps(bounds))
			{
				f.hit(3);
			}
		}
	}
}
