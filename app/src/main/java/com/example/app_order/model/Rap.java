package com.example.app_order.model;

import java.io.Serializable;

public class Rap implements Serializable {
    private String tenRap;
    private String diaChi;
    private String noiDoXe;
    private String tienIch;
    private int idRap;

    public Rap(String tenRap, String diaChi, String noiDoXe, String tienIch, int idRap) {
        this.tenRap = tenRap;
        this.diaChi = diaChi;
        this.noiDoXe = noiDoXe;
        this.tienIch = tienIch;
        this.idRap = idRap;
    }

    public String getTenRap() {
        return tenRap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getNoiDoXe() {
        return noiDoXe;
    }

    public String getTienIch() {
        return tienIch;
    }

    public int getIdRap() {
        return idRap;
    }
}