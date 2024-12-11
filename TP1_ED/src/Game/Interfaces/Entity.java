package Game.Interfaces;

import ExceptionsAulas.InvalidElementException;
import Game.Mission.Division;

public interface Entity {

    public String getName();

    public Division getDivision();

    public void setDivision(Division division);

    public void setName(String name);

    public int getPower();

    public void setPower(int power);

    public int getHealth();

    public void setHealth(int health);

    public int calculateHealth(int OpositePower) throws InvalidElementException;

    public boolean isAlive();

    public void takeDamage(int damage);
}
