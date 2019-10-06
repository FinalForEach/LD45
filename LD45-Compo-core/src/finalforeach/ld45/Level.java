package finalforeach.ld45;

public abstract class Level 
{
	public static Level[] levels = new Level[]
			{
				new Level10(), new Level2()
			};
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
		addWarrior(new MeleeAI(new Gladiator("Enemy",1024,50)));
		//addWarrior(new MeleeAI(new GladiatorTrident("Enemy",256,20)));
		/*addWarrior(new RangedAI(new GladiatorArcher("Enemy",1024,20)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",256,50)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",1000,50)));
		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",2000,50)));*/
		//addWarrior(new MeleeAI(new Lion("Enemy",100,50)));
	}
}

class Level2 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=2;
		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",1024,50)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",700,20)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",500,30)));
	}
}
class Level3 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=2;
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",100,20)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",200,20)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",300,30)));
	}
}
class Level4 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=2;
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",2048,20)));
		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",1700,50)));
		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",1600,10)));
	}
}
class Level5 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=2;
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",100,20)));
		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",100,50)));
		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",200,10)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",200,20)));
		addWarrior(new MeleeAI(new Gladiator("Enemy",300,30)));
	}
}
class Level6 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=5;
		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",2000,50)));
	}
}
class Level7 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=5;
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",2048,20)));
		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",1700,50)));
		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",1600,10)));
	}
}
class Level8 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=5;
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",2048,20)));
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",100,50)));
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",1700,20)));
	}
}
class Level9 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=5;
		addWarrior(new MeleeAI(new Lion("Enemy",100,50)));
	}
}
class Level10 extends Level
{
	@Override
	public void spawnWarriors() 
	{
		Game.player.fighter.hp+=30;
		addWarrior(new MeleeAI(new Lion("Enemy",2000,50)));
		addWarrior(new MeleeAI(new Elephant("Enemy",100,10)));
		addWarrior(new MeleeAI(new Lion("Enemy",1700,50)));
		
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",2048,20)));
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",100,50)));
		addWarrior(new RangedAI(new GladiatorArcher("Enemy",1700,20)));

		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",500,50)));
		addWarrior(new MeleeAI(new GladiatorThracian("Enemy",2000,10)));

		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",100,50)));
		addWarrior(new MeleeAI(new GladiatorTrident("Enemy",200,10)));

		addWarrior(new MeleeAI(new Gladiator("Enemy",1024,10)));
	}
}