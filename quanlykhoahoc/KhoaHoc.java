package quanlykhoahoc.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class KhoaHoc {
    protected String maKH;
    protected String tenKH;
    protected String giangVien;
    protected int soBuoi;
    protected double hocPhi;
    protected Date ngayMo;
    protected String moTa;
    protected double danhGiaTB;

    // Constructor rỗng
    public KhoaHoc() {
        this.danhGiaTB = 0.0;
    }

    // Constructor đầy đủ
    public KhoaHoc(String maKH, String tenKH, String giangVien, int soBuoi,
                   double hocPhi, Date ngayMo, String moTa) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.giangVien = giangVien;
        this.soBuoi = soBuoi;
        this.hocPhi = hocPhi;
        this.ngayMo = ngayMo;
        this.moTa = moTa;
        this.danhGiaTB = 0.0;
    }

    // NHẬP THÔNG TIN CHUNG CHO KHÓA HỌC
    public void input() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Nhập mã khóa học: ");
        maKH = sc.nextLine();

        System.out.print("Nhập tên khóa học: ");
        tenKH = sc.nextLine();

        System.out.print("Nhập tên giảng viên: ");
        giangVien = sc.nextLine();

        System.out.print("Nhập số buổi: ");
        soBuoi = Integer.parseInt(sc.nextLine());

        System.out.print("Nhập học phí: ");
        hocPhi = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập ngày mở (dd/MM/yyyy): ");
        try {
            ngayMo = sdf.parse(sc.nextLine());
        } catch (ParseException e) {
            ngayMo = new Date(); // ngày hiện tại
            System.out.println("Ngày sai -> tự động lấy ngày hiện tại!");
        }

        System.out.print("Nhập mô tả: ");
        moTa = sc.nextLine();
    }

    // Xuất thông tin
    public void output() {
        System.out.printf("Mã KH: %-6s | Tên: %-25s | GV: %-15s | Buổi: %2d | Học phí: %,10.0fđ | Ngày mở: %s | Đánh giá: %.1f",
                maKH, tenKH, giangVien, soBuoi, hocPhi, ngayMo, danhGiaTB);
    }

    // 2 phương thức abstract
    public abstract double tinhDoanhThu();
    public abstract double tinhThanhTien();

    public double tinhDanhGia() {
        return danhGiaTB;
    }
}
