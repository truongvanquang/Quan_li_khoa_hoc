package javaapplication31;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class KhoaHocChuyenDe extends KhoaHoc {
    String chuDe;
    int doKho;   // 1-5

    public KhoaHocChuyenDe() {
        super();
    }

    public KhoaHocChuyenDe(String maKH, String tenKH, String giangVien, int soBuoi,
                           double hocPhi, Date ngayMo, String moTa,
                           String chuDe, int doKho) {
        super(maKH, tenKH, giangVien, soBuoi, hocPhi, ngayMo, moTa);
        this.chuDe = chuDe;
        this.doKho = doKho;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // ======== NHẬP THÔNG TIN CHUNG =========
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
        } catch (Exception e) {
            System.out.println("Ngày sai → tự động lấy ngày hiện tại!");
            ngayMo = new Date();
        }

        System.out.print("Nhập mô tả: ");
        moTa = sc.nextLine();

        // ======== THUỘC TÍNH RIÊNG =========
        System.out.print("Nhập chủ đề chuyên đề (VD: AI, IELTS Speaking...): ");
        chuDe = sc.nextLine();

        System.out.print("Nhập độ khó (1 - Dễ → 5 - Rất khó): ");
        doKho = Integer.parseInt(sc.nextLine());

        // ======== TỰ ĐỘNG TÍNH ĐÁNH GIÁ =========
        danhGiaTB = Math.min(10.0, 6.0 + doKho * 0.8);
    }

    @Override
    public void output() {
        System.out.print("[CHUYÊN ĐỀ] ");
        super.output();
        System.out.printf(" | Chủ đề: %-20s | Độ khó: %d | Doanh thu: %,12.0fđ | Thành tiền: %,10.0fđ\n",
                chuDe, doKho, tinhDoanhThu(), tinhThanhTien());
    }

    @Override
    public double tinhDoanhThu() {
        // học phí × số buổi × 15 học viên
        return hocPhi * soBuoi * 15;
    }

    @Override
    public double tinhThanhTien() {
        // độ khó cao → tăng giá nhẹ
        double heSo = 1 + (doKho * 0.03);
        return hocPhi * heSo;
    }
}

