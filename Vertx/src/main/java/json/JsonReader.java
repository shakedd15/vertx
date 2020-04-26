package json;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import soldier.SoldiersList;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author Yael Nisanov
 * @since 04/03/2020
 */
public class JsonReader{

    private String filePath;

    @Inject
    public JsonReader(@Named("filePath") String filePath){
        this.filePath = filePath;
    }

    public final SoldiersList read() {
        Gson gson = new Gson();
        SoldiersList soldiersList = null;
        try {
            soldiersList = gson.fromJson(new FileReader(filePath),SoldiersList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soldiersList;
    }
}
