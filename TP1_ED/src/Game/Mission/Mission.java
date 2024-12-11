package Game.Mission;

import ClassesAulas.ArrayUnorderedList;
import Game.*;
import Game.Entitys.Enemy;
import Game.Exceptions.FileNotFoundException;
import Game.ImportExport.JSONImporter;

/**
 * Classe responsavel por representar a versao da missao de To Cruz
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

    public Mission(String missionFile){

        this.fileToImport = missionFile;
        this.jsonImporter = new JSONImporter(fileToImport);
        this.enemies = new ArrayUnorderedList<>();
        this.divisions = new ArrayUnorderedList<>();
        this.entrancesExites = new ArrayUnorderedList<>();
        this.items = new ArrayUnorderedList<>();
        this.map = new Map<>();

        try {

            this.versionid = jsonImporter.getVersion();
            this.missionCode = jsonImporter.getMissionCode();
            this.enemies =  jsonImporter.getEnemies();
            this.divisions = jsonImporter.getDivisions();
            this.entrancesExites = jsonImporter.getEntrancesExits();
            this.target = jsonImporter.getTarget();
            this.items = jsonImporter.getItems();
            this.map = jsonImporter.generateMap();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getFileToImport() {
        return fileToImport;
    }

    public void setFileToImport(String fileToImport) {
        this.fileToImport = fileToImport;
    }

    public JSONImporter getJsonImporter() {
        return jsonImporter;
    }

    public void setJsonImporter(JSONImporter jsonImporter) {
        this.jsonImporter = jsonImporter;
    }

    public String getMissionCode() {
        return missionCode;
    }

    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    public int getVersionid() {
        return versionid;
    }

    public void setVersionid(int versionid) {
        this.versionid = versionid;
    }

    public ArrayUnorderedList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayUnorderedList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayUnorderedList<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(ArrayUnorderedList<Division> divisions) {
        this.divisions = divisions;
    }

    public ArrayUnorderedList<Division> getEntrancesExites() {
        return entrancesExites;
    }

    public void setEntrancesExites(ArrayUnorderedList<Division> entrancesExites) {
        this.entrancesExites = entrancesExites;
    }

    public ArrayUnorderedList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayUnorderedList<Item> items) {
        this.items = items;
    }

    public Map<Division> getMap() {
        return map;
    }

    public void setMap(Map<Division> map) {
        this.map = map;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
