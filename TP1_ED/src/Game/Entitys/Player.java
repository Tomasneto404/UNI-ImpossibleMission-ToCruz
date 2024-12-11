package Game.Entitys;

import ClassesAulas.ArrayStack;
import ExceptionsAulas.EmptyCollectionException;
import ExceptionsAulas.InvalidElementException;
import Game.Mission.Division;
import Game.Interfaces.*;
import Game.Items.BulletProofVest;
import Game.Items.RecoveryItem;
import Game.Mission.Target;

public class Player implements Entity {

    private final int DEFAULT_HEALTH = 100;
    private final int DEFAULT_POWER = 10;
    private String name;
    private int power;
    private Division division;
    private ArrayStack<RecoveryItem> backpack;
    private int health;
    private Target target;
    private boolean usedItem;

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

    public Player(String name, int power, Division division) {
        this.name = name;
        this.power = power;
        this.division = division;
    }

    public ArrayStack<RecoveryItem> getBackpack() {
        return backpack;
    }

    public void setBackpack(ArrayStack<RecoveryItem> backpack) {
        this.backpack = backpack;
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


    public int increaseAttackPower(int value) throws InvalidElementException {
        if (value <= 0) {
            throw new InvalidElementException("The value is invalid");
        }
        this.power += value;
        return this.power;
    }

    public void useRecoveryItem() throws EmptyCollectionException {
        if (backpack.isEmpty()) {
            throw new EmptyCollectionException("There's no items in backpack.");
        }
        RecoveryItem item = backpack.pop();
        item.applyEffect(this);
        usedItem = true;
    }

    public void useBulletProofVest(){
        BulletProofVest item= new BulletProofVest();
        item.applyEffect(this);
    }


    public void addItem(IItem item) {
        if(item instanceof BulletProofVest){
            BulletProofVest item1= (BulletProofVest) item;
            useBulletProofVest();
        }

        RecoveryItem item1= (RecoveryItem) item;
        item1= backpack.peek();
    }

    public void move(Division newDivision) {

        if (newDivision != null) {
            this.setDivision(newDivision);
        }

    }

    public boolean interactWithTarget(Target target) {
        if (this.getDivision() != null && this.getDivision().equals(target.getDivision())) {
            return target.isSecured();
        }
        return false;
    }

    public boolean isMissionSuccessful() {

        return (target != null && target.isSecured());
    }

    public void catchTarget(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return this.target;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public void resetOcasionalVariables(){
        this.usedItem = false;
    }

    public boolean hasUsedItem() {
        return usedItem;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
    }
}
