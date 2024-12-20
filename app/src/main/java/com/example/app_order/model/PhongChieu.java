package com.example.app_order.model;

public class PhongChieu {

    private String TenPC;
    private String gioChieu;

    public PhongChieu() {
    }

    public PhongChieu(String TenPC, String gioChieu) {
        this.TenPC = TenPC;
        this.gioChieu = gioChieu;
    }

    public String getTenPC() {
        return TenPC;
    }

    public String getGioChieu() {
        return gioChieu;
    }
}