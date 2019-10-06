package finalforeach.ld45;

public class ItemTrident extends Item {

	public ItemTrident(float x, float y) {
		super(x, y, 0, 0);
	}

	@Override
	protected boolean onPickup() {
		Game.fighters.removeValue(Game.player.fighter, true);
		Game.player.fighter.dropItem();
		Game.player.fighter = new GladiatorTrident("Player", Game.player.fighter.x, Game.player.fighter.y);
		Game.fighters.add(Game.player.fighter);
		return true;
	}

}
