package com.example.app_order.model;

import com.example.app_order.model.PhongChieu;

import java.util.List;

public class Phimchieu {
    private int doTuoi;
    private String tenPhim;
    private List<PhongChieu> rooms;

    public Phimchieu(int doTuoi, String tenPhim, List<PhongChieu> rooms) {
        this.doTuoi = doTuoi;
        this.tenPhim = tenPhim;
        this.rooms = rooms;
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public List<PhongChieu> getRooms() {
        return rooms;
    }
}



