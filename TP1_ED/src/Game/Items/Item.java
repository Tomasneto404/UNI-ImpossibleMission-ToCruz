package Game.Items;

import Game.Mission.Division;

public abstract class Item {

    private Division division;
    private int points;

    public Item(Division division, int points) {
        this.division = division;
        this.points = points;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
