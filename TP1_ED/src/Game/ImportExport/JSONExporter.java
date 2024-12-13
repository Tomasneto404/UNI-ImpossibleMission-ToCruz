package Game.ImportExport;

import ClassesAulas.ArrayUnorderedList;
import Game.Mission.Division;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONExporter {

    private String filePath;

    public JSONExporter(String filePath) {
        this.filePath = filePath;
    }

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