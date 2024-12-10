package com.example.app_order.model;

public class Phim {
    private String IDPhim;
    private String TenPhim;
    private int DoTuoi;

    public Phim() {
    }

    public Phim(String IDPhim, String TenPhim) {
        this.IDPhim = IDPhim;
        this.TenPhim = TenPhim;
        this.DoTuoi = DoTuoi;
    }

    public String getIDPhim() {
        return IDPhim;
    }

    public void setIDPhim(String IDPhim) {
        this.IDPhim = IDPhim;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String TenPhim) {
        this.TenPhim = TenPhim;
    }

    public int getDoTuoi() {
        return DoTuoi;
    }

    public void setDoTuoi(int DoTuoi) {
        this.DoTuoi = DoTuoi;
    }
}

