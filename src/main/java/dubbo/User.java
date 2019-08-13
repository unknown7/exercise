package dubbo;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: ").append(id).append(", ")
                .append("name: ").append(name).append(", ")
                .append("gender: ").append(gender);
        return builder.toString();
    }
}
