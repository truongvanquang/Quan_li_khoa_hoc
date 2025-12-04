package quanlykhoahoc.java;

import java.util.Date;
import java.util.Scanner;

public class KhoaHocOffline extends KhoaHoc {
    int soLuongHocVien; 
    String phongHoc;    

    public KhoaHocOffline() {
        super();
    }

    public KhoaHocOffline(String maKH, String tenKH, String giangVien, int soBuoi,
                          double hocPhi, Date ngayMo, String moTa,
                          int soLuongHocVien, String phongHoc) {
        super(maKH, tenKH, giangVien, soBuoi, hocPhi, ngayMo, moTa);
        this.soLuongHocVien = soLuongHocVien;
        this.phongHoc = phongHoc;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        super.input();

        System.out.print("Nhập sĩ số học viên: ");
        soLuongHocVien = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập phòng học: ");
        phongHoc = sc.nextLine();

        // tự tính đánh giá
        danhGiaTB = Math.min(10.0, 6.0 + soLuongHocVien / 10.0);
    }

    @Override
    public void output() {
        System.out.print("[OFFLINE] ");
        super.output();
        System.out.printf(" | Sĩ số: %2d | Phòng: %-6s | Doanh thu: %,12.0fđ | Thành tiền: %,10.0fđ\n",
                soLuongHocVien, phongHoc, tinhDoanhThu(), tinhThanhTien());
    }

    @Override
    public double tinhDoanhThu() {
        return hocPhi * soBuoi * soLuongHocVien;
    }

    @Override
    public double tinhThanhTien() {
        double giamGia = 1.0;

        if (soLuongHocVien >= 50) giamGia = 0.8;
        else if (soLuongHocVien >= 30) giamGia = 0.9;

        return hocPhi * giamGia;
    }
}
