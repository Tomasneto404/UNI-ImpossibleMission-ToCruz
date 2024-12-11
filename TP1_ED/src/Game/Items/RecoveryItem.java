package Game.Items;

import Game.Interfaces.IItem;
import Game.Entitys.Player;

public class RecoveryItem implements IItem {
    private int recoveryPoints;

    private RecoveryItem(int recoveryPoints){
        this.recoveryPoints = recoveryPoints;
    }

    @Override
    public int getPoints() {
        return this.recoveryPoints;
    }

    @Override
    public void applyEffect(Player player) {
        player.setHealth(player.getHealth()+recoveryPoints);
    }
}
