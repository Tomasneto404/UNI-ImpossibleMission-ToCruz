package Game;

import ClassesAulas.ArrayUnorderedList;

public class Division {

    private String name;
    private ArrayUnorderedList<Enemy> enemies;
    private boolean hasTarget;
    private boolean hasNewEnemies;
    private boolean isEntranceExit;

    {
        this.hasNewEnemies = false;
        this.hasTarget = false;
        this.isEntranceExit = false;
    }

    public Division() {
    }

    public Division(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public ArrayUnorderedList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayUnorderedList<Enemy> enemies) {

        /*
        if (this.enemies != null){
            hasNewEnemies = true;
        }
         */

        this.enemies = enemies;
    }

    public void setNewEnemies(ArrayUnorderedList<Enemy> enemies) {
        this.hasNewEnemies = true;
        this.enemies = enemies;
    }

    public boolean hasNewEnemies() {
        return hasNewEnemies;
    }

    public boolean hasEnemies() {
        return enemies != null && !enemies.isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Division) {
            Division division = (Division) obj;
            return name.equals(division.getName());
        }
        return false;
    }

    public String dataToString() {
        String result = "[" + name + "] \n";

        if (enemies != null && !enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                result += "- " + enemy.getName() + "\n";
            }
        }

        return result;
    }

    public boolean isHasTarget() {
        return hasTarget;
    }

    public boolean isHasNewEnemies() {
        return hasNewEnemies;
    }

    public void setHasNewEnemies(boolean hasNewEnemies) {
        this.hasNewEnemies = hasNewEnemies;
    }

    public boolean isEntranceExit() {
        return isEntranceExit;
    }

    public void setEntranceExit(boolean entranceExit) {
        isEntranceExit = entranceExit;
    }
}
