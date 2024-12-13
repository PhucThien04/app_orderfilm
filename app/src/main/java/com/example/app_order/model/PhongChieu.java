package com.example.app_order.model;

public class PhongChieu {
    private String maPhong;
    private int soPhong;
    private String maRap;

    public PhongChieu() {
    }

    public PhongChieu(String maPhong, int soPhong, String maRap) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.maRap = maRap;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getMaRap() {
        return maRap;
    }

    public void setMaRap(String maRap) {
        this.maRap = maRap;
    }
}

