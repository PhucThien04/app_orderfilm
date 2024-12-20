package com.example.app_order.model;

import java.util.Date;

public class LichChieu {
   private String maLichChieu;
   private Date ngayChieu;
   private String Thu;
   private String thoiGian;
   private Rap idRap;
   private Phim maPhim;

   public LichChieu(){

   }
    public LichChieu(String maLichChieu, Date ngayChieu, String thu, String thoiGian, Rap idRap, Phim maPhim) {
        this.maLichChieu = maLichChieu;
        this.ngayChieu = ngayChieu;
        this.Thu = thu;
        this.thoiGian = thoiGian;
        this.idRap = idRap;
        this.maPhim = maPhim;
    }

    public String getMaLichChieu() {
        return maLichChieu;
    }

    public void setMaLichChieu(String maLichChieu) {
        this.maLichChieu = maLichChieu;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Rap getMaRap() {
        return idRap;
    }

    public void setMaRap(Rap maRap) {
        this.idRap = maRap;
    }

    public Phim getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(Phim maPhim) {
        this.maPhim = maPhim;
    }
}

