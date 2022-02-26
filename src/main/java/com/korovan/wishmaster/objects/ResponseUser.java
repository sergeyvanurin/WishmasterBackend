package com.korovan.wishmaster.objects;

import com.korovan.wishmaster.domain.Item;
import com.korovan.wishmaster.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ResponseUser {
    public Long id;
    public String name;
    public List<ResponseItem> items;

    public ResponseUser() {

    }

    public ResponseUser(User user, List<Item> items) {
        this.id = user.getUser_id();
        this.name = user.getName();
        this.items = new ArrayList<ResponseItem>();

        for (Item item: items) {
            this.items.add(new ResponseItem(item));
        }
    }
}
