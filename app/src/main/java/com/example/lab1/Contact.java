package com.example.lab1;

import android.widget.BaseAdapter;
import android.widget.Filter;

public class Contact {
    private int id;
    private String images;
    private String name;
    private String phone;

    public Contact() {

    }

    public Contact(int id, String images, String name, String phone) {
        this.id = id;
        this.images = images;
        this.phone = phone;
        this.name = name;
    }

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.images = "@drawable/img";
        this.phone = phone;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
