package com.example.app_order.model;

import java.util.Date;

public class HoanVe {
    public String maHV;
    public String idCustomer;
    public String maVe;
    public String soTienDaHoan;
    public Date ngayHoan;

    public HoanVe(){

    }

    public HoanVe(String maHV, String idCustomer, String maVe, String soTienDaHoan, Date ngayHoan) {
        this.maHV = maHV;
        this.idCustomer = idCustomer;
        this.maVe = maVe;
        this.soTienDaHoan = soTienDaHoan;
        this.ngayHoan = ngayHoan;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getSoTienDaHoan() {
        return soTienDaHoan;
    }

    public void setSoTienDaHoan(String soTienDaHoan) {
        this.soTienDaHoan = soTienDaHoan;
    }

    public Date getNgayHoan() {
        return ngayHoan;
    }

    public void setNgayHoan(Date ngayHoan) {
        this.ngayHoan = ngayHoan;
    }
}
