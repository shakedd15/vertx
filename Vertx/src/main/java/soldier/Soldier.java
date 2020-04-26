package soldier;

import com.google.gson.Gson;

/**
 * @author Yael Nisanov
 * @since 04/03/2020
 */
public class Soldier {
    private String id;
    private String firstName;
    private String lastName;

    public Soldier(String id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String toJsonString(){
        return new Gson().toJson(this);
    }

}
