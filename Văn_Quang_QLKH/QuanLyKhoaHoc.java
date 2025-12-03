/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication31;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class QuanLyKhoaHoc implements IReadWrite, IThongKe {

    private ArrayList<KhoaHoc> danhSach = new ArrayList<>();

    // ====================== CÁC PHƯƠNG THỨC CƠ BẢN ======================
    public void input() {
        System.out.println("1. Online | 2. Offline | 3. Chuyên đề");
        System.out.print("Chọn loại khóa học: ");
        Scanner sc = new Scanner(System.in);
        int loai = sc.nextInt();
        sc.nextLine();

        KhoaHoc kh = switch (loai) {
            case 1 -> new KhoaHocOnline();
            case 2 -> new KhoaHocOffline();
            case 3 -> new KhoaHocChuyenDe();
            default -> {
                System.out.println("Loại không hợp lệ!");
                yield null;
            }
        };
        if (kh != null) {
            kh.maKH = autoID();           // tự động sinh mã
            kh.input();                   // nhập thông tin
            them(kh);
            System.out.println("→ Thêm thành công! Mã KH: " + kh.maKH);
        }
    }

    public void output() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }
        System.out.println("\n" + "=".repeat(160));
        System.out.println("                               DANH SÁCH KHÓA HỌC");
        System.out.println("=".repeat(160));
        for (KhoaHoc kh : danhSach) {
            kh.output();
        }
        System.out.println("=".repeat(160));
        System.out.printf("Tổng doanh thu toàn bộ: %,20.0f đ\n\n", tinhTongDoanhThu());
    }

    public void them(KhoaHoc kh) {
        danhSach.add(kh);
    }

    public KhoaHoc tim(String maKH) {
        return danhSach.stream()
                .filter(kh -> kh.maKH.equalsIgnoreCase(maKH))
                .findFirst()
                .orElse(null);
    }

    public boolean xoa(String maKH) {
        KhoaHoc kh = tim(maKH);
        if (kh != null) {
            danhSach.remove(kh);
            System.out.println("Đã xóa khóa học: " + maKH);
            return true;
        }
        System.out.println("Không tìm thấy mã KH: " + maKH);
        return false;
    }

   public void sapXepTheoHocPhi() {
    if (danhSach.isEmpty()) {
        System.out.println("Danh sách trống! Không thể sắp xếp.");
        return;
    }

    danhSach.sort(Comparator.comparingDouble(kh -> kh.hocPhi));

    System.out.println("Đã sắp xếp theo học phí tăng dần!");
}


    // ====================== IMPLEMENT IThongKe ======================
    @Override
    public double thongKeTheoGV(String gv) {
        return danhSach.stream()
                .filter(kh -> kh.giangVien.equalsIgnoreCase(gv))
                .mapToDouble(KhoaHoc::tinhDoanhThu)
                .sum();
    }

    @Override
    public int thongKeTheoThang(int thang) {
        return (int) danhSach.stream()
                .filter(kh -> {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(kh.ngayMo);
                    return cal.get(Calendar.MONTH) + 1 == thang;
                })
                .count();
    }

    // ====================== TỰ ĐỘNG SINH MÃ ======================
    public String autoID() {
        int n = danhSach.size() + 1;
        return String.format("KH%04d", n); // KH0001, KH0002,...
    }

    // ====================== IMPLEMENT IReadWrite ======================
    @Override
    public void readData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(IReadWrite.fileName))) {
            danhSach = (ArrayList<KhoaHoc>) ois.readObject();
            System.out.println("Đọc dữ liệu thành công từ " + IReadWrite.fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File chưa tồn tại → sẽ tạo mới khi lưu.");
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
    }

    @Override
    public void writeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(IReadWrite.fileName))) {
            oos.writeObject(danhSach);
            System.out.println("Lưu dữ liệu thành công vào " + IReadWrite.fileName);
        } catch (Exception e) {
            System.out.println("Lỗi ghi file!");
        }
    }

    @Override
    public void exportCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("KhoaHoc_Export.csv"))) {
            pw.println("Mã KH,Tên KH,Giảng viên,Số buổi,Học phí,Ngày mở,Mô tả,Đánh giá,Doanh thu,Thành tiền,Loại");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (KhoaHoc kh : danhSach) {
                String loai = kh instanceof KhoaHocOnline ? "Online" :
                              kh instanceof KhoaHocOffline ? "Offline" : "Chuyên đề";
                pw.printf("%s,%s,%s,%d,%.0f,%s,%s,%.1f,%.0f,%.0f,%s%n",
                        kh.maKH, kh.tenKH, kh.giangVien, kh.soBuoi, kh.hocPhi,
                        sdf.format(kh.ngayMo), kh.moTa, kh.danhGiaTB,
                        kh.tinhDoanhThu(), kh.tinhThanhTien(), loai);
            }
            System.out.println("Xuất CSV thành công → KhoaHoc_Export.csv");
        } catch (Exception e) {
            System.out.println("Lỗi xuất CSV!");
        }
    }

    // Getter (dùng trong main nếu cần)
    public ArrayList<KhoaHoc> getDanhSach() {
        return danhSach;
    }

    public double tinhTongDoanhThu() {
    return danhSach.stream()
            .mapToDouble(KhoaHoc::tinhDoanhThu)
            .sum();
    }

    Object timTheoGiangVien(String gv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}