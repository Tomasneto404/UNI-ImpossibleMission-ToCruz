package Game.Items;

import Game.Mission.Division;

public abstract class Item {

    private Division division;
    private int points;
    private String name;

    public Item(Division division, int points, String name) {
        this.division = division;
        this.points = points;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
