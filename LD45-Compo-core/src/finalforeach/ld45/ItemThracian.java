package finalforeach.ld45;

public class ItemThracian extends Item {

	public ItemThracian(float x, float y) {
		super(x, y, 0, 2);
	}

	@Override
	protected boolean onPickup() {
		Game.fighters.removeValue(Game.player.fighter, true);
		Game.player.fighter.dropItem();
		Game.player.fighter = new GladiatorThracian("Player", Game.player.fighter.x, Game.player.fighter.y);
		Game.fighters.add(Game.player.fighter);
		return true;
	}

}
