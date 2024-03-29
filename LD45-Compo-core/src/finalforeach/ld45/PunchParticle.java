package finalforeach.ld45;

public class PunchParticle extends Particle {

	Fighter owner;
	String team;
	public PunchParticle(String team, float x, float y, float vx, float vy, Fighter owner) {
		super(x, y, vx, vy, 0, 0);
		this.owner=owner;
		this.team=team;
	}
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		for(Fighter f : Game.fighters)
		{
			if(f!=owner && f.team!=team &&f.rect.overlaps(bounds))
			{
				f.hit(1);
			}
		}
	}
	@Override
	public float getMaxLifetime() {
		return 0.15f;
	}
}
