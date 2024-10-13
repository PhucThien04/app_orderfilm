package com.example.app_order;

import java.util.List;

public class Phimchieu {
    private String title;
    private String type;
    private List<Phongchieu> phongchieus;

    public Phimchieu(String title, String type, List<Phongchieu> phongchieus) {
        this.title = title;
        this.type = type;
        this.phongchieus = phongchieus;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public List<Phongchieu> getRooms() {
        return phongchieus;
    }
}


