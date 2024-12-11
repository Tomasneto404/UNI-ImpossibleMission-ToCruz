package Game.ImportExport;

import Game.Mission.Division;
import Game.Entitys.Enemy;
import Game.Enums.ItemType;
import Game.Item;
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
 * Classe responsavel por importar e exportar dados dos ficheiros
 */
public class JSONImporter {

    private String filePath;

    /**
     * Metodo construtor
     */
    public JSONImporter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Metodo para retornar o codigo da missao
     *
     * @return
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
     * Metodo para retornar a Versao no ficheiro json
     *
     * @return
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
     * Metodo para retornar Divisoes do ficheiro
     *
     * @return
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
     * Metodo para retornar inimigos do ficheiro .json
     *
     * @return listEnemies List of enemies
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
     * Metodo para retornar Divisoes que sao entradas ou saidas do ficheiro
     *
     * @return listDivisions
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
     * Metodo responsavel por ler e retornar informação do Alvo
     *
     * @return
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
     * Metodo responsavel por ler e retornar informação dos items
     *
     * @return
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
                    String readType = (String) itemObj.get("tipo");

                    ItemType type;

                    switch (readType) {
                        case "kit de vida":
                            type = ItemType.HEALTH_KIT;
                            break;
                        case "colete":
                            type = ItemType.BULLET_PROOF_VEST;
                            break;
                        default:
                            type = ItemType.WEAPON;
                            break;
                    }

                    int points;
                    Item tmpItem;

                    if (itemObj.containsKey("pontos-recuperados")) {
                        points = ((Long) itemObj.get("pontos-recuperados")).intValue();

                        tmpItem = new Item(new Division(division), points, type);

                    } else {
                        points = ((Long) itemObj.get("pontos-extra")).intValue();

                        tmpItem = new Item(new Division(division), type, points);
                    }

                    listItems.addToRear(tmpItem);

                }

            } catch (ParseException ex) {
                throw new JSONFieldNotFoundException("Error reading \"itens\": " + ex.getMessage());
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        return listItems;
    }

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

                    Division startVertexWithEnemies = addEnemiesToDivision(startVertex);
                    Division endVertexWithEnemies = addEnemiesToDivision(endVertex);

                    tmpMap.addEdge(startVertexWithEnemies, endVertexWithEnemies);

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

    private Division findDivisionByName(ArrayUnorderedList<Division> divisions, String name) {

        for (Division division : divisions) {

            if (division.getName().equals(name)) {
                return division;
            }

        }

        return null;

    }

    private Division addEnemiesToDivision(Division division) {

        ArrayUnorderedList<Enemy> enemies = getEnemies();
        ArrayUnorderedList<Enemy> tmpEnemies = new ArrayUnorderedList<>();

        for (Enemy enemy : enemies) {
            if (enemy.getDivision().equals(division)) {
                tmpEnemies.addToRear(enemy);
            }
        }

        division.setEnemies(tmpEnemies);

        return division;
    }

}
