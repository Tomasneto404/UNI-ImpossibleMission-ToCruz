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
 * Classe responsavel por importar e exportar dados dos ficheiros
 */
public class JSONImporter {

    private String filePath;

    /**
     * Construtor da classe JSONImporter.
     *
     * @param filePath Caminho do ficheiro JSON a ser lido.
     */
    public JSONImporter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retorna o código da missão presente no ficheiro JSON.
     *
     * @return O código da missão como uma String.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "cod-missao" não seja encontrado ou ocorra erro ao ler.
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
     * Retorna a versão do ficheiro JSON.
     *
     * @return A versão como um inteiro.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "versao" não seja encontrado ou ocorra erro ao ler.
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
     * Retorna uma lista de divisões presentes no ficheiro JSON.
     *
     * @return Uma lista de divisões do tipo ArrayUnorderedList.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "edificio" não seja encontrado ou ocorra erro ao ler.
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
     * Retorna uma lista de inimigos presentes no ficheiro JSON.
     *
     * @return Uma lista de inimigos do tipo ArrayUnorderedList.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "inimigos" não seja encontrado ou ocorra erro ao ler.
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
     * Retorna uma lista de divisões que são entradas ou saídas do ficheiro JSON.
     *
     * @return Uma lista de divisões do tipo ArrayUnorderedList.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "entradas-saidas" não seja encontrado ou ocorra erro ao ler.
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
     * Retorna o alvo definido no ficheiro JSON.
     *
     * @return Um objeto Target representando o alvo.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "alvo" não seja encontrado ou ocorra erro ao ler.
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
     * Retorna uma lista de itens presentes no ficheiro JSON.
     *
     * @return Uma lista de itens do tipo ArrayUnorderedList.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "itens" não seja encontrado ou ocorra erro ao ler.
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
     * Gera um mapa de divisões e suas conexões com base no ficheiro JSON.
     *
     * @return Um objeto Map representando o mapa das divisões.
     * @throws FileNotFoundException Caso o ficheiro não seja encontrado.
     * @throws JSONFieldNotFoundException Caso o campo "ligacoes" não seja encontrado ou ocorra erro ao ler.
     * @throws DivisionNotFoundException Caso uma divisão especificada na ligação não seja encontrada.
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
     * Encontra uma divisão pelo nome em uma lista de divisões.
     *
     * @param divisions Lista de divisões do tipo ArrayUnorderedList.
     * @param name Nome da divisão a ser encontrada.
     * @return A divisão correspondente ou null caso não seja encontrada.
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
     * Adiciona inimigos a uma divisão específica com base no ficheiro JSON.
     *
     * @param division A divisão para a qual os inimigos devem ser adicionados.
     * @return Uma lista de inimigos associados à divisão.
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
     * Adiciona itens a uma divisão específica com base no ficheiro JSON.
     *
     * @param division A divisão para a qual os itens devem ser adicionados.
     * @return Uma lista de itens associados à divisão.
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
