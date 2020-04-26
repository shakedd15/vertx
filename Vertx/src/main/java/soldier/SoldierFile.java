package soldier;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.vertx.core.json.JsonObject;
import json.JsonReader;
import json.JsonWriter;

import java.util.Optional;

/**
 * @author Yael Nisanov
 * @since 04/03/2020
 */

public class SoldierFile {

    private JsonWriter writer;

    private SoldiersList soldiers;

    @Inject
    public SoldierFile(JsonWriter writer, JsonReader reader) {
        this.writer = writer;
        soldiers = reader.read();
    }

    public Optional<Soldier> getSoldier(String id) {
        return soldiers.getSoldierById(id);
    }

    public SoldiersList getAllSoldiers() {
        return soldiers;
    }

    public boolean addSoldier(JsonObject jsonObject) {
        Soldier soldier = jsonToSoldier(jsonObject);
        boolean isAdded = soldiers.addSoldier(soldier);
        writer.write(soldiers);
        return isAdded;
    }

    public boolean removeSoldier(String id) {
        boolean isRemoved = soldiers.removeSoldierById(id);
        writer.write(soldiers);
        return isRemoved;
    }

    public boolean updateSoldier(JsonObject jsonObject) {
        Soldier soldier = jsonToSoldier(jsonObject);
        boolean isUpdated = soldiers.updateSoldier(soldier);
        writer.write(soldiers);
        return isUpdated;
    }

    public Soldier jsonToSoldier(JsonObject jsonObject) {
        String id = jsonObject.getString("id");
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        return new Soldier(id, firstName, lastName);
    }

}

