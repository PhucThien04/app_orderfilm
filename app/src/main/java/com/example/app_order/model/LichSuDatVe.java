package com.example.app_order.model;

public class LichSuDatVe {
    public String maLS;
    public String maVe;
    public String idCustomer;
    public String ngayDat;

    public LichSuDatVe(){

    }

    public LichSuDatVe(String maLS, String maVe, String idCustomer, String ngayDat) {
        this.maLS = maLS;
        this.maVe = maVe;
        this.idCustomer = idCustomer;
        this.ngayDat = ngayDat;
    }

    public String getMaLS() {
        return maLS;
    }

    public void setMaLS(String maLS) {
        this.maLS = maLS;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }
}
