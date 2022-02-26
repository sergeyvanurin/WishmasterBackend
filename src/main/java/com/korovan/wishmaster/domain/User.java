package com.korovan.wishmaster.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_data")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long user_id;

    @Column(unique=true)
    private String name;

    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Item> items = new ArrayList<>();

    public Long getUser_id() {
        return user_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
