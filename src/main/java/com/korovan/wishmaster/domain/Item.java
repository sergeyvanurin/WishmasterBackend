package com.korovan.wishmaster.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private Long item_id;

    private String name;
    private String url;
    private String img_url;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Item() {

    }

    public Item(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Item(String name, String url, String img_url) {
        this.name = name;
        this.url = url;
        this.img_url = img_url;
    }

    public Item(String name, String url, String img_url, User user) {
        this.name = name;
        this.url = url;
        this.img_url = img_url;
        this.user = user;
    }

    public Item(String name, String url, String img_url, Long price, User user) {
        this.name = name;
        this.url = url;
        this.img_url = img_url;
        this.price = price;
        this.user = user;
    }

    public Long getItemId() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImg_url() {
        return img_url;
    }

    public Long getPrice() {
        return price;
    }
}
