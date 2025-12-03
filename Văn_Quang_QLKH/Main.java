package javaapplication31;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyKhoaHoc ql = new QuanLyKhoaHoc();
        ql.readData(); // đọc từ file nếu có

        // Tạo dữ liệu mẫu nếu danh sách rỗng
        if (ql.getDanhSach().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                // Khóa Online
                KhoaHocOnline onl1 = new KhoaHocOnline();
                onl1.maKH = "KH0001";
                onl1.tenKH = "Lập trình Java Fullstack Online";
                onl1.giangVien = "Nguyễn Văn An";
                onl1.soBuoi = 60;
                onl1.hocPhi = 8500000;
                onl1.ngayMo = sdf.parse("10/03/2025");
                onl1.moTa = "Học Java từ cơ bản đến nâng cao";
                onl1.soGioVideo = 120;
                onl1.danhGiaTB = 9.2;

                // Khóa Offline
                KhoaHocOffline off1 = new KhoaHocOffline();
                off1.maKH = "KH0002";
                off1.tenKH = "Excel nâng cao cho kế toán";
                off1.giangVien = "Trần Thị Lan";
                off1.soBuoi = 20;
                off1.hocPhi = 4500000;
                off1.ngayMo = sdf.parse("15/04/2025");
                off1.moTa = "Thực hành Excel thực tế";
                off1.soLuongHocVien = 45;
                off1.phongHoc = "P305";
                off1.danhGiaTB = 8.8;

                // Khóa Chuyên đề
                KhoaHocChuyenDe cd1 = new KhoaHocChuyenDe();
                cd1.maKH = "KH0003";
                cd1.tenKH = "AI & Machine Learning cơ bản";
                cd1.giangVien = "Phạm Minh Đức";
                cd1.soBuoi = 40;
                cd1.hocPhi = 12000000;
                cd1.ngayMo = sdf.parse("20/05/2025");
                cd1.moTa = "Giới thiệu AI hiện đại";
                cd1.chuDe = "Trí tuệ nhân tạo";
                cd1.doKho = 4;
                cd1.danhGiaTB = 9.5;

                ql.them(onl1);
                ql.them(off1);
                ql.them(cd1);

                System.out.println("Đã tạo dữ liệu mẫu (3 khóa học)!");
            } catch (Exception e) {
                System.out.println("Lỗi tạo dữ liệu mẫu!");
            }
        }

        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n" + "=".repeat(70));
            System.out.println("        QUẢN LÝ KHÓA HỌC TRỰC TUYẾN - LMS SYSTEM");
            System.out.println("=".repeat(70));
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Thêm khóa học mới");
            System.out.println("3. Tìm khóa học theo mã");
            System.out.println("4. Xóa khóa học");
System.out.println("5. Sắp xếp theo học phí");
            System.out.println("6. Tìm khóa học theo giảng viên");
            System.out.println("7. Thống kê doanh thu theo giảng viên");
            System.out.println("8. Thống kê số khóa theo tháng");
            System.out.println("9. Tổng doanh thu toàn hệ thống");
            System.out.println("10. Xuất danh sách ra CSV");
            System.out.println("11. Lưu dữ liệu vào file");
            System.out.println("0. Thoát chương trình");
            System.out.println("-".repeat(70));
            System.out.print("Nhập lựa chọn của bạn: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> ql.output();
                case 2 -> ql.input();
                case 3 -> {
                    System.out.print("Nhập mã KH cần tìm: ");
                    String ma = sc.nextLine();
                    KhoaHoc kh = ql.tim(ma);
                    if (kh != null) kh.output();
                    else System.out.println("Không tìm thấy!");
                }
                case 4 -> {
                    System.out.print("Nhập mã KH cần xóa: ");
                    ql.xoa(sc.nextLine());
                }
                case 5 -> ql.sapXepTheoHocPhi();
                case 6 -> {
                    System.out.print("Nhập tên giảng viên: ");
                    String gv = sc.nextLine();
                    ql.timTheoGiangVien(gv).forEach(KhoaHoc::output);
                }
                case 7 -> {
                    System.out.print("Nhập tên giảng viên: ");
                    String gv = sc.nextLine();
                    System.out.printf("Doanh thu của GV %s: %,15.0f đ\n", gv, ql.thongKeTheoGV(gv));
                }
                case 8 -> {
                    System.out.print("Nhập tháng (1-12): ");
                    int thang = sc.nextInt();
                    System.out.println("Số khóa học mở trong tháng " + thang + ": " + ql.thongKeTheoThang(thang) + " khóa");
                }
                case 9 -> System.out.printf("TỔNG DOANH THU: %,20.0f đ\n", ql.tinhTongDoanhThu());
                case 10 -> ql.exportCSV();
                case 11 -> ql.writeData();
                case 0 -> {
                    ql.writeData(); // tự động lưu khi thoát
                    System.out.println("Cảm ơn bạn đã sử dụng hệ thống! Tạm biệt!");
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }
}
