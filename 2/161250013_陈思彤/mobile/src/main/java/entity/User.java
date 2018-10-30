package entity;

import lombok.Data;

@Data
public class User {
    private Integer id;

    private String name;

    private String telephone;

    public User(Integer id, String name, String telephone) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
    }
}
