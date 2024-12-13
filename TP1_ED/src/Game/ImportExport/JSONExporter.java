package Game.ImportExport;

import ClassesAulas.ArrayUnorderedList;
import Game.Mission.Division;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por exportar dados para ficheiros JSON.
 */
public class JSONExporter {

    private String filePath;

    /**
     * Construtor da classe JSONExporter.
     *
     * @param filePath Caminho do ficheiro JSON onde os dados serão exportados.
     */
    public JSONExporter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Exporta o caminho de uma missão, representado por uma lista de divisões, para um ficheiro JSON.
     *
     * @param divisions Lista de divisões representando o caminho da missão.
     *                  Cada divisão é convertida para seu nome.
     * @throws IOException Caso ocorra um erro ao escrever no ficheiro.
     */
    public void missionPath(ArrayUnorderedList<Division> divisions) {

        JSONObject jsonObject = new JSONObject();
        JSONArray divisionList = new JSONArray();

        for (Division division : divisions) {
            divisionList.add(division.getName());
        }

        jsonObject.put("missionPath", divisionList);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}