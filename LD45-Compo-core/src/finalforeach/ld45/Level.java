package finalforeach.ld45;

public abstract class Level 
{
	public abstract void spawnWarriors();
	protected void addWarrior(AIController aiFighter)
	{
		Game.fighters.add(aiFighter.fighter);
		Game.ais.add(aiFighter);
	}
}
class Level1 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		addWarrior(new MeleeAI(new Gladiator(1024,50)));
	}
}