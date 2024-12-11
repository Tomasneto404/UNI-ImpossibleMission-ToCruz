package Game;
//para apagar
import Game.Enums.ItemType;
import Game.Mission.Division;

public class Item {
    private Division division;
    private int extraPoints;
    private int recoveredPoints;
    private ItemType type; //Acrescentar ItemType

    public Item(Division division, int recoveredPoints, ItemType type) {
        this.division = division;
        this.recoveredPoints = recoveredPoints;
        this.extraPoints = 0;
        this.type = type;
    }

    public Item(Division division, ItemType type, int extraPoints) {
        this.division = division;
        this.recoveredPoints = 0;
        this.extraPoints = extraPoints;
        this.type = type;
    }

    public void use(){};
    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getExtraPoints() {
        return extraPoints;
    }

    public void setExtraPoints(int extraPoints) {
        this.extraPoints = extraPoints;
    }

    public int getRecoveredPoints() {
        return recoveredPoints;
    }

    public void setRecoveredPoints(int recoveredPoints) {
        this.recoveredPoints = recoveredPoints;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
