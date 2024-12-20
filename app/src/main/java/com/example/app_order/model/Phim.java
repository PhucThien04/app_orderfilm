package com.example.app_order.model;

import java.util.Date;

public class Phim {
    public String maPhim;
    private String TenPhim;
    private String thoiLuong;
    private Date ngayKhoiChieu;
    private String danhGia;
    private String moTa;
    private String anhPhim;
    private String tinhTrang;
    public int doTuoi;
    private String maLoaiPhim;

    public Phim(){

    }
    public Phim(String maPhim, String tenPhim, String thoiLuong, Date ngayKhoiChieu, String danhGia, String moTa, String anhPhim, String tinhTrang ,int doTuoi, String maLoaiPhim) {
        this.maPhim = maPhim;
        TenPhim = tenPhim;
        this.thoiLuong = thoiLuong;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.danhGia = danhGia;
        this.moTa = moTa;
        this.anhPhim = anhPhim;
        this.tinhTrang = tinhTrang;
        this.doTuoi = doTuoi;
        this.maLoaiPhim = maLoaiPhim;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public String getTenPhim() {
        return TenPhim;
    }

    public void setTenPhim(String tenPhim) {
        TenPhim = tenPhim;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public Date getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(Date ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getAnhPhim() {
        return anhPhim;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }

    public String getMaLoaiPhim() {
        return maLoaiPhim;
    }

    public void setMaLoaiPhim(String maLoaiPhim) {
        this.maLoaiPhim = maLoaiPhim;
    }
}

