package com.example.app_order.model;

public class LichChieu {
    private String IDLichchieu;
    private String IDPhim;
    private String IDPhongchieu;
    private String Thoigian;

    public LichChieu() {
    }

    public LichChieu(String IDLichchieu, String IDPhim, String IDPhongchieu, String Thoigian) {
        this.IDLichchieu = IDLichchieu;
        this.IDPhim = IDPhim;
        this.IDPhongchieu = IDPhongchieu;
        this.Thoigian = Thoigian;
    }

    public String getIDLichchieu() {
        return IDLichchieu;
    }

    public String getIDPhim() {
        return IDPhim;
    }

    public String getIDPhongchieu() {
        return IDPhongchieu;
    }

    public String getThoigian() {
        return Thoigian;
    }
}

