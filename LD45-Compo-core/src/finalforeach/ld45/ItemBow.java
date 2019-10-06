package finalforeach.ld45;

public class ItemBow extends Item {
	
	public ItemBow(float x, float y) {
		super(x, y, 0, 1);
	}
	@Override
	protected boolean onPickup() {
		Game.fighters.removeValue(Game.player.fighter, true);
		Game.player.fighter = new GladiatorArcher("Player", Game.player.fighter.x, Game.player.fighter.y);
		Game.fighters.add(Game.player.fighter);
		return true;
	}

}
