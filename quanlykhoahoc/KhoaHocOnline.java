package quanlykhoahoc.java;

import java.util.Date;
import java.util.Scanner;

public class KhoaHocOnline extends KhoaHoc {
    int soGioVideo;

    public KhoaHocOnline() {
        super();
    }

    public KhoaHocOnline(String maKH, String tenKH, String giangVien, int soBuoi,
                         double hocPhi, Date ngayMo, String moTa, int soGioVideo) {
        super(maKH, tenKH, giangVien, soBuoi, hocPhi, ngayMo, moTa);
        this.soGioVideo = soGioVideo;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        super.input();   // Nhập thông tin chung

        System.out.print("Nhập số giờ video của khóa học: ");
        soGioVideo = Integer.parseInt(sc.nextLine());

        // Tự tính đánh giá (càng nhiều giờ video càng tốt)
        danhGiaTB = Math.min(10.0, 5.0 + soGioVideo * 0.05);
    }

    @Override
    public void output() {
        System.out.print("[ONLINE] ");
        super.output();
        System.out.printf(" | Giờ video: %3d giờ | Doanh thu: %,12.0fđ | Thành tiền: %,10.0fđ\n",
                soGioVideo, tinhDoanhThu(), tinhThanhTien());
    }

    @Override
    public double tinhDoanhThu() {
        // học phí × số buổi × 20 học viên + tiền video
        return hocPhi * soBuoi * 20 + soGioVideo * 500000;
    }

    @Override
    public double tinhThanhTien() {
        double giamGia = (soGioVideo > 50) ? 0.85 : 1.0;
        return hocPhi * giamGia;
    }

    public int getSoGioVideo() {
        return soGioVideo;
    }
}
