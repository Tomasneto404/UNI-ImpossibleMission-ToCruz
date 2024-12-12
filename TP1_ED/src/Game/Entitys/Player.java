package Game.Entitys;

import ClassesAulas.ArrayStack;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Items.Item;
import Game.Mission.Division;
import Game.Interfaces.*;
import Game.Items.BulletProofVest;
import Game.Mission.Target;

public class Player implements Entity {

    private final int DEFAULT_HEALTH = 1;
    private final int DEFAULT_POWER = 10;
    private String name;
    private int power;
    private Division division;
    private ArrayStack<Item> backpack;
    private int health;
    private Target target;
    private boolean usedItem;
    private boolean inTheBuilding;

    {
        this.target = null;
        this.power = DEFAULT_POWER;
        this.division = null;
        this.backpack = new ArrayStack<>();
        this.health = DEFAULT_HEALTH;
    }

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Division getDivision() {
        return this.division;
    }

    @Override
    public void setDivision(Division division) {
        this.division = division;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int calculateHealth(int OpositePower) throws InvalidElementException {
        if (OpositePower <= 0) {
            throw new InvalidElementException("The oposite power is invalid");
        }

        return power - OpositePower;
    }

    public void useRecoveryItem() throws EmptyCollectionException {
        if (backpack.isEmpty()) {
            throw new EmptyCollectionException("There's no items in backpack.");
        }
        Item item = backpack.pop();

        this.setHealth(this.health += item.getPoints());

        usedItem = true;
    }


    public void addItemToBackpack(Item item) {
        backpack.push(item);
    }


    public void move(Division newDivision) {

        if (newDivision != null) {
            this.setDivision(newDivision);
        }

    }

    public void catchTarget(Target target) {
        target.secure();
        this.target = target;
    }

    public Target getTarget() {
        return this.target;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void resetOcasionalVariables() {
        this.usedItem = false;
    }

    public boolean hasUsedItem() {
        return usedItem;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void dressBulletProffVest(BulletProofVest item) {
        this.health += item.getPoints();
    }

    public void reset() {
        this.target = null;
        this.power = DEFAULT_POWER;
        this.division = null;
        this.backpack = new ArrayStack<>();
        this.health = DEFAULT_HEALTH;
    }

    public boolean isInTheBuilding() {
        return inTheBuilding;
    }

    public void setInTheBuilding(boolean inTheBuilding) {
        this.inTheBuilding = inTheBuilding;
    }
}
