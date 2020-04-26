package json;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import soldier.SoldiersList;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Yael Nisanov
 * @since 04/03/2020
 */
public class JsonWriter{

    private String filePath;

    @Inject
   public JsonWriter(@Named("filePath") String filePath) {
        this.filePath = filePath;
    }

    public final void write(SoldiersList soldiersList) {
        Gson gson = new Gson();
        String json = gson.toJson(soldiersList);
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
