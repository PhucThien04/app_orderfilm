package com.example.app_order.model;

public class Ghe {
    public String maGhe;
    public String dayGhe;
    public String trangThai;
    public String maVe;

    public Ghe(){

    }

    public Ghe(String maGhe, String dayGhe, String trangThai, String maVe) {
        this.maGhe = maGhe;
        this.dayGhe = dayGhe;
        this.trangThai = trangThai;
        this.maVe = maVe;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public String getDayGhe() {
        return dayGhe;
    }

    public void setDayGhe(String dayGhe) {
        this.dayGhe = dayGhe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }
}
