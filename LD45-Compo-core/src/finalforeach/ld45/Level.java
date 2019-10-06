package finalforeach.ld45;

public abstract class Level 
{
	public static Level[] levels = new Level[]{new Level1()};
	public static int curLvlIndex=0;
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
		//addWarrior(new MeleeAI(new Gladiator("Enemy",1024,50)));
		//addWarrior(new MeleeAI(new GladiatorTrident("Enemy",256,20)));
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",1024,20)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",256,50)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",1000,50)));
		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",2000,50)));
	}
}