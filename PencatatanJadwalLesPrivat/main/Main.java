package main;

import java.util.Scanner;
import service.JadwalService;
import util.InputValidator;
import auth.LoginService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginService loginService = new LoginService();

        System.out.println("=== LOGIN ===");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (!loginService.login(username, password)) {
            System.out.println("Login gagal. Username atau password salah.");
            sc.close();
            return;
        }

        System.out.println("Login berhasil. Selamat datang, " + username + "!");

        JadwalService service = new JadwalService();

        while (true) {
            System.out.println("\n=== MENU JADWAL LES PRIVAT ===");
            System.out.println("1. Tambah Jadwal");
            System.out.println("2. Tampilkan Semua Jadwal");
            System.out.println("3. Hapus Jadwal Terdepan");
            System.out.println("4. Reset Semua Jadwal");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan;
            try {
                pilihan = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            switch (pilihan) {
                case 1:
                	System.out.print("Nama: ");
                	String nama = sc.nextLine().trim();

                	if (nama.isEmpty()) {
                	    System.out.println("Nama tidak boleh kosong.");
                	    break; 
                	}
                    System.out.println("Pilih Mata Pelajaran:");
                    System.out.println("1. Bahasa Inggris");
                    System.out.println("2. Matematika");
                    System.out.print("Masukkan pilihan: ");

                    int pelajaran;
                    try {
                        pelajaran = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid.");
                        break;
                    }

                    if (!InputValidator.isValidPelajaran(pelajaran)) {
                        System.out.println("Pilihan pelajaran tidak valid.");
                        break;
                    }

                    String namaPelajaran = InputValidator.mapPelajaran(pelajaran);

                    service.tampilkanSlot();
                    System.out.print("Pilih slot (1-4): ");

                    int slotPilihan;
                    try {
                        slotPilihan = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Input slot tidak valid.");
                        break;
                    }

                    service.tambahJadwalManual(nama, namaPelajaran, slotPilihan);
                    break;

                case 2:
                    service.tampilkanJadwal();
                    break;

                case 3:
                    service.hapusJadwal();
                    break;

                case 4:
                    service.resetJadwal();
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan aplikasi jadwal les privat.");
                    sc.close();
                    return;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
    }
}
