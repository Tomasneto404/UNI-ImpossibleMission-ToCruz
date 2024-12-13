package Game.ImportExport;

import Game.Items.Item;
import Game.Items.BulletProofVest;
import Game.Items.RecoveryItem;
import Game.Mission.Division;
import Game.Entitys.Enemy;
import Game.Mission.Map;
import Game.Mission.Target;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import ClassesAulas.ArrayUnorderedList;

import Game.Exceptions.FileNotFoundException;
import Game.Exceptions.JSONFieldNotFoundException;
import Game.Exceptions.DivisionNotFoundException;

/**
 * Class responsible for importing and exporting data from JSON files.
 */
public class JSONImporter {

    private String filePath;

    /**
     * Constructor for the JSONImporter class.
     *
     * @param filePath Path to the JSON file to be read.
     */
    public JSONImporter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the mission code from the JSON file.
     *
     * @return The mission code as a String.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "cod-missao" field is missing or cannot be read.
     */
    public String getMissionCode() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject;

            try {

                jsonObject = (JSONObject) jsonParser.parse(reader);
                return jsonObject.get("cod-missao").toString();

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"cod-missao\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    /**
     * Retrieves the version from the JSON file.
     *
     * @return The version as an integer.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "versao" field is missing or cannot be read.
     */
    public int getVersion() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject;

            try {

                jsonObject = (JSONObject) jsonParser.parse(reader);
                return Integer.valueOf(jsonObject.get("versao").toString());

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"versao\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    /**
     * Retrieves a list of divisions from the JSON file.
     *
     * @return A list of divisions as an ArrayUnorderedList.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "edificio" field is missing or cannot be read.
     */
    public ArrayUnorderedList<Division> getDivisions() {

        ArrayUnorderedList<Division> listDivisions = new ArrayUnorderedList<>();
        ArrayUnorderedList<Division> ee = getEntrancesExits();
        Target target = getTarget();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject;

            try {

                jsonObject = (JSONObject) jsonParser.parse(reader);
                JSONArray edificioArray = (JSONArray) jsonObject.get("edificio");

                for (Object division : edificioArray) {


                    Division tmpDivision = new Division(division.toString());

                    if (ee.contains(tmpDivision)) {
                        tmpDivision.setEntranceExit(true);
                    }

                    if (tmpDivision.equals(target.getDivision())) {
                        tmpDivision.setHasTarget(true);
                    }

                    listDivisions.addToRear(tmpDivision);
                }

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"edificio\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return listDivisions;

    }

    /**
     * Retrieves a list of enemies from the JSON file.
     *
     * @return A list of enemies as an ArrayUnorderedList.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "inimigos" field is missing or cannot be read.
     */
    public ArrayUnorderedList<Enemy> getEnemies() {
        ArrayUnorderedList<Enemy> listEnemies = new ArrayUnorderedList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject;

            try {

                jsonObject = (JSONObject) jsonParser.parse(reader);
                JSONArray enemyArray = (JSONArray) jsonObject.get("inimigos");

                for (Object enemy : enemyArray) {

                    JSONObject enemyObj = (JSONObject) enemy;

                    String name = (String) enemyObj.get("nome");
                    int power = ((Long) enemyObj.get("poder")).intValue();
                    String division = (String) enemyObj.get("divisao");

                    listEnemies.addToRear(new Enemy(
                            name,
                            power,
                            new Division(division)
                    ));

                }

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"inimigos\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return listEnemies;
    }

    /**
     * Retrieves a list of divisions that are entrances or exits from the JSON file.
     *
     * @return A list of divisions as an ArrayUnorderedList.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "entradas-saidas" field is missing or cannot be read.
     */
    public ArrayUnorderedList<Division> getEntrancesExits() {

        ArrayUnorderedList<Division> listDivisions = new ArrayUnorderedList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject;

            try {

                jsonObject = (JSONObject) jsonParser.parse(reader);
                JSONArray edificioArray = (JSONArray) jsonObject.get("entradas-saidas");

                for (Object division : edificioArray) {
                    listDivisions.addToRear(new Division(division.toString()));
                }

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"entradas-saidas\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return listDivisions;

    }

    /**
     * Retrieves the target specified in the JSON file.
     *
     * @return A Target object representing the target.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "alvo" field is missing or cannot be read.
     */
    public Target getTarget() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONObject targetObj = (JSONObject) jsonObject.get("alvo");

            String division = (String) targetObj.get("divisao");
            String type = (String) targetObj.get("tipo");

            return new Target(new Division(division), type);

        } catch (ParseException ex) {
            throw new JSONFieldNotFoundException("Error reading \"entradas-saidas\": " + ex.getMessage());

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
    }

    /**
     * Retrieves a list of items from the JSON file.
     *
     * @return A list of items as an ArrayUnorderedList.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "itens" field is missing or cannot be read.
     */
    public ArrayUnorderedList<Item> getItems() {

        ArrayUnorderedList<Item> listItems = new ArrayUnorderedList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject;

            try {

                jsonObject = (JSONObject) jsonParser.parse(reader);
                JSONArray itemArray = (JSONArray) jsonObject.get("itens");

                for (Object item : itemArray) {

                    JSONObject itemObj = (JSONObject) item;

                    String division = (String) itemObj.get("divisao");
                    Division itemDivision = new Division(division);

                    String readType = (String) itemObj.get("tipo");

                    Item tmpItem;
                    int points;

                    switch (readType) {
                        case "kit de vida":
                            points = ((Long) itemObj.get("pontos-recuperados")).intValue();
                            tmpItem = new RecoveryItem(itemDivision, points);
                            listItems.addToRear(tmpItem);
                            break;

                        case "colete":
                            points = ((Long) itemObj.get("pontos-extra")).intValue();
                            tmpItem = new BulletProofVest(itemDivision, points);
                            listItems.addToRear(tmpItem);
                            break;

                        default:
                            break;
                    }

                }

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"itens\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return listItems;
    }

    /**
     * Generates a map of divisions and their connections based on the JSON file.
     *
     * @return A Map object representing the divisions and their connections.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "ligacoes" field is missing or cannot be read.
     * @throws DivisionNotFoundException If a specified division in the connections cannot be found.
     */
    public Map<Division> generateMap() {

        Map<Division> tmpMap = new Map<Division>();

        ArrayUnorderedList<Division> divisions = getDivisions();

        tmpMap.setTarget(getTarget());

        for (Division division : divisions) {
            tmpMap.addVertex(division);
        }

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray connections = (JSONArray) jsonObject.get("ligacoes");

            for (Object item : connections) {
                JSONArray connection = (JSONArray) item;

                String startVertexName = (String) connection.get(0);
                String endVertexName = (String) connection.get(1);

                Division startVertex = findDivisionByName(divisions, startVertexName);
                Division endVertex = findDivisionByName(divisions, endVertexName);

                if (startVertex != null && endVertex != null) {

                    startVertex.setItems(addItemsToDivision(startVertex));
                    startVertex.setEnemies(addEnemiesToDivision(startVertex));

                    endVertex.setItems(addItemsToDivision(endVertex));
                    endVertex.setEnemies(addEnemiesToDivision(endVertex));

                    tmpMap.addEdge(startVertex, endVertex);

                } else {
                    throw new DivisionNotFoundException("Division not found.");
                }
            }

        } catch (IOException e) {

            throw new FileNotFoundException("File not found: " + filePath);

        } catch (ParseException ex) {

            throw new JSONFieldNotFoundException("Error reading \"ligacoes\": " + ex.getMessage());

        }

        return tmpMap;
    }

    /**
     * Generates a map of divisions and their connections based on the JSON file.
     *
     * @return A Map object representing the divisions and their connections.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws JSONFieldNotFoundException If the "ligacoes" field is missing or cannot be read.
     * @throws DivisionNotFoundException If a specified division in the connections cannot be found.
     */
    private Division findDivisionByName(ArrayUnorderedList<Division> divisions, String name) {

        for (Division division : divisions) {

            if (division.getName().equals(name)) {
                return division;
            }

        }

        return null;

    }


    /**
     * Adds enemies to a specific division based on the JSON file.
     *
     * @param division The division to which the enemies should be added.
     * @return A list of enemies associated with the division.
     */
    private ArrayUnorderedList<Enemy> addEnemiesToDivision(Division division) {

        ArrayUnorderedList<Enemy> enemies = getEnemies();
        ArrayUnorderedList<Enemy> tmpEnemies = new ArrayUnorderedList<>();

        for (Enemy enemy : enemies) {
            if (enemy.getDivision().equals(division)) {
                tmpEnemies.addToRear(enemy);
            }
        }

        return tmpEnemies;
    }

    /**
     * Adds items to a specific division based on the JSON file.
     *
     * @param division The division to which the items should be added.
     * @return A list of items associated with the division.
     */
    private ArrayUnorderedList<Item> addItemsToDivision(Division division) {
        ArrayUnorderedList<Item> items = getItems();
        ArrayUnorderedList<Item> tmpItems = new ArrayUnorderedList<>();

        for (Item item : items) {
            if (item.getDivision().equals(division)) {
                tmpItems.addToRear(item);
            }
        }

        return tmpItems;
    }

}
