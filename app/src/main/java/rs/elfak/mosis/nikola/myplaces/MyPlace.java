package rs.elfak.mosis.nikola.myplaces;

/**
 * Created by Nikola on 2017-03-26.
 */

public class MyPlace {
    String name;
    String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MyPlace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public MyPlace(String name) {
        this(name, "");
    }
}
