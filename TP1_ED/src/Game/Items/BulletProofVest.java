package Game.Items;

import Game.Interfaces.IItem;
import Game.Entitys.Player;

public class BulletProofVest implements IItem {

    private final int extraPoints;

    public BulletProofVest() {
        this.extraPoints = 10;
    }


    @Override
    public int getPoints() {
        return this.extraPoints;
    }

    @Override
    public void applyEffect(Player player) {
        player.setHealth(player.getHealth()+this.extraPoints);
    }
}
