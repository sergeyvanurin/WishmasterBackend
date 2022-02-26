package com.korovan.wishmaster.objects;

import com.korovan.wishmaster.domain.Item;

public class ResponseItem {
    public String name;
    public String url;
    public String img_url;
    public Long price;

    public ResponseItem() {

    }

    public ResponseItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public ResponseItem(String name, String url, String img_url) {
        this.name = name;
        this.url = url;
        this.img_url = img_url;
    }

    public ResponseItem(String name, String url, String img_url, Long price) {
        this.name = name;
        this.url = url;
        this.img_url = img_url;
        this.price = price;
    }

    public ResponseItem(Item item) {
        this.name = item.getName();
        this.url = item.getUrl();
        this.img_url = item.getImg_url();
        this.price = item.getPrice();
    }
}
