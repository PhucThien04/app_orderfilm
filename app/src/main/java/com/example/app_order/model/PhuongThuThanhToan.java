package com.example.app_order.model;

public class PhuongThuThanhToan {
    private String maPTT;
    private String tenPTT;

    public PhuongThuThanhToan(){

    }

    public PhuongThuThanhToan(String maPTT, String tenPTT) {
        this.maPTT = maPTT;
        this.tenPTT = tenPTT;
    }

    public String getMaPTT() {
        return maPTT;
    }

    public void setMaPTT(String maPTT) {
        this.maPTT = maPTT;
    }

    public String getTenPTT() {
        return tenPTT;
    }

    public void setTenPTT(String tenPTT) {
        this.tenPTT = tenPTT;
    }

}
