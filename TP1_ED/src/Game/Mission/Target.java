package Game.Mission;

/**
 * Represents a game target associated with a division.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Target {

    private Division division;
    private String type; // preciso de alterar isto tomas
    private boolean secured;

    /**
     * Constructs a new 'Target' object with a specified division and target type.
     *
     * @param division The `Division` object where the target is located.
     * @param type     The type of the target (e.g., enemy stronghold, checkpoint).
     */
    public Target(Division division, String type) {
        this.division = division;
        this.type = type;
    }

    /**
     * Returns the division where the target is located.
     *
     * @return The division object representing the location of the target.
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Sets the division where the target is located.
     *
     * @param division The division object to associate with the target.
     */
    public void setDivision(Division division) {
        this.division = division;
    }


    /**
     * Returns the type of the target.
     *
     * @return A 'String' representing the type of the target.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the target.
     *
     * @param type A 'String' representing the new type of the target.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Checks if the target has been secured.
     *
     * @return true if the target is secured, otherwise false.
     */
    public boolean isSecured() {
        return secured;
    }

    /**
     * Marks the target as secured.
     * Sets the secured attribute to true.
     */
    public void secure() {
        this.secured = true;
    }

    /**
     * Returns a string representation of the target's attributes.
     *
     * @return A formatted string containing the division, target type, and secured status.
     */
    @Override
    public String toString() {
        String str = "";

        str += "Division: " + division.toString();
        str += "\nType: " + type.toString();
        str += "\nSecured: " + secured;

        return str;
    }
}
