package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import Game.*;
import Game.Entitys.Enemy;
import Game.Exceptions.FileNotFoundException;
import Game.ImportExport.JSONImporter;
import Game.Items.Item;

/**
 * The 'Mission' class is responsible for representing the version of a mission in the game.
 *
 * @author Tânia Morais
 * @author Tomás Neto
 */
public class Mission {

    private String fileToImport;
    private JSONImporter jsonImporter;

    private String missionCode;
    private int versionid;
    private ArrayUnorderedList<Enemy> enemies;
    private ArrayUnorderedList<Division> divisions;
    private ArrayUnorderedList<Division> entrancesExites;
    private ArrayUnorderedList<Item> items;
    private Map<Division> map;
    private Target target;

    /**
     * Constructs a 'Mission' object by loading mission data from the specified mission file.
     *
     * @param missionFile The path to the JSON file containing the mission data.
     */
    public Mission(String missionFile) {

        this.fileToImport = missionFile;
        this.jsonImporter = new JSONImporter(fileToImport);
        this.enemies = new ArrayUnorderedList<>();
        this.divisions = new ArrayUnorderedList<>();
        this.entrancesExites = new ArrayUnorderedList<>();
        this.items = new ArrayUnorderedList<Item>();
        this.map = new Map<>();

        try {

            this.versionid = jsonImporter.getVersion();
            this.missionCode = jsonImporter.getMissionCode();
            this.enemies = jsonImporter.getEnemies();
            this.divisions = jsonImporter.getDivisions();
            this.entrancesExites = jsonImporter.getEntrancesExits();
            this.target = jsonImporter.getTarget();
            this.items = jsonImporter.getItems();
            this.map = jsonImporter.generateMap();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns the path of the mission file to import.
     *
     * @return The mission file path as a String.
     */
    public String getFileToImport() {
        return fileToImport;
    }

    /**
     * Sets the path of the mission file to import.
     *
     * @param fileToImport The path to the mission file.
     */
    public void setFileToImport(String fileToImport) {
        this.fileToImport = fileToImport;
    }

    /**
     * Returns the `JSONImporter` instance responsible for loading mission data.
     *
     * @return The JSONImporter instance.
     */
    public JSONImporter getJsonImporter() {
        return jsonImporter;
    }

    /**
     * Sets the `JSONImporter` instance for loading mission data.
     *
     * @param jsonImporter The JSONImporter to be set.
     */
    public void setJsonImporter(JSONImporter jsonImporter) {
        this.jsonImporter = jsonImporter;
    }

    /**
     * Returns the mission code representing the mission's unique identifier.
     *
     * @return The mission code as a String.
     */
    public String getMissionCode() {
        return missionCode;
    }

    /**
     * Sets the mission code representing the mission's unique identifier.
     *
     * @param missionCode The mission code to be set.
     */
    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    /**
     * Returns the version ID associated with the mission data.
     *
     * @return The version ID as an integer.
     */
    public int getVersionid() {
        return versionid;
    }

    /**
     * Sets the version ID for the mission data.
     *
     * @param versionid The version ID to be set.
     */
    public void setVersionid(int versionid) {
        this.versionid = versionid;
    }

    /**
     * Returns a list of enemies present in the mission.
     *
     * @return An `ArrayUnorderedList` containing `Enemy` objects.
     */
    public ArrayUnorderedList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Sets the list of enemies present in the mission.
     *
     * @param enemies An `ArrayUnorderedList` containing `Enemy` objects.
     */
    public void setEnemies(ArrayUnorderedList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Returns a list of divisions present in the mission.
     *
     * @return An `ArrayUnorderedList` containing `Division` objects.
     */
    public ArrayUnorderedList<Division> getDivisions() {
        return divisions;
    }

    /**
     * Sets the list of divisions present in the mission.
     *
     * @param divisions An `ArrayUnorderedList` containing `Division` objects.
     */
    public void setDivisions(ArrayUnorderedList<Division> divisions) {
        this.divisions = divisions;
    }

    /**
     * Returns a list of divisions representing entrances and exits.
     *
     * @return An `ArrayUnorderedList` of divisions that act as entrances and exits.
     */
    public ArrayUnorderedList<Division> getEntrancesExites() {
        return entrancesExites;
    }

    /**
     * Sets the divisions representing entrances and exits for the mission map.
     *
     * @param entrancesExites An `ArrayUnorderedList` of `Division` representing entrances and exits.
     */
    public void setEntrancesExites(ArrayUnorderedList<Division> entrancesExites) {
        this.entrancesExites = entrancesExites;
    }

    /**
     * Returns a list of items available in the mission.
     *
     * @return An `ArrayUnorderedList` containing `Item` objects.
     */
    public ArrayUnorderedList<Item> getItems() {
        return items;
    }

    /**
     * Sets the list of items present in the mission.
     *
     * @param items An `ArrayUnorderedList` containing `Item` objects.
     */
    public void setItems(ArrayUnorderedList<Item> items) {
        this.items = items;
    }

    /**
     * Returns the map representing the divisions and their connections in the mission.
     *
     * @return A `Map` object representing the mission's map.
     */
    public Map<Division> getMap() {
        return map;
    }

    /**
     * Sets the map representing the divisions and their connections in the mission.
     *
     * @param map The `Map` object to be set.
     */
    public void setMap(Map<Division> map) {
        this.map = map;
    }

    /**
     * Returns the target division in the mission.
     *
     * @return The target division represented as a `Target` object.
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Sets the target division in the mission.
     *
     * @param target The target division to be set.
     */
    public void setTarget(Target target) {
        this.target = target;
    }
}
