package Game.Mission;

//para apagar
public class Target {

    private Division division;
    private String type; // preciso de alterar isto tomas
    private boolean secured;

    public Target(Division division, String type) {
        this.division = division;
        this.type = type;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSecured() {
        return secured;
    }

    public void secure(){
        this.secured = true;
    }

    @Override
    public String toString() {
        String str = "";

        str += "Division: " + division.toString();
        str += "\nType: " + type.toString();
        str += "\nSecured: " + secured;

        return str;
    }
}
