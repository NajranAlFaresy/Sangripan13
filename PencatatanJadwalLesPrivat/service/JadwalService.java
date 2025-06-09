package service;

import entity.JadwalNode;
import java.util.HashSet;
import java.util.Set;

public class JadwalService {
    private JadwalNode head, tail;
    private final String[] slotJadwal = {
        "Senin 13:00", "Senin 15:00", "Kamis 13:00", "Kamis 15:00"
    };

    // Simpan minggu yang sudah pernah dipakai (terisi)
    private Set<Integer> mingguTerpakai = new HashSet<>();

    public void tambahJadwalManual(String nama, String pelajaran, int slotIndex) {
        if (slotIndex < 1 || slotIndex > slotJadwal.length) {
            System.out.println("Pilihan slot tidak valid.");
            return;
        }
        String slot = slotJadwal[slotIndex - 1];
        String[] parts = slot.split(" ");
        String hari = parts[0];
        String jam = parts[1];

        // Cari minggu terkecil yang belum pernah dipakai
        int mingguTerisi = 0;
        for (int minggu = 1; minggu <= 4; minggu++) {
            if (!mingguTerpakai.contains(minggu)) {
                mingguTerisi = minggu;
                break;
            }
        }

        if (mingguTerisi == 0) {
            System.out.println("Semua minggu sudah penuh dengan jadwal, tidak bisa menambah jadwal baru.");
            return;
        }

        // Tandai minggu sebagai terpakai
        mingguTerpakai.add(mingguTerisi);

        JadwalNode newNode = new JadwalNode(nama, pelajaran, hari, jam, mingguTerisi);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        System.out.println("Jadwal berhasil ditambahkan untuk " + nama +
                           " pada " + hari + " " + jam +
                           " minggu ke-" + mingguTerisi);
    }

    public void hapusJadwal() {
        if (head == null) {
            System.out.println("Tidak ada jadwal untuk dihapus.");
            return;
        }

        System.out.println("Menghapus jadwal: " + head.nama + " (" + head.pelajaran + "), minggu ke-" + head.minggu);
        head = head.next;
        if (head == null) {
            tail = null;
        }
        // **Tidak menghapus minggu dari mingguTerpakai supaya minggu tetap dianggap sudah terpakai**
    }

    public void tampilkanJadwal() {
        if (head == null) {
            System.out.println("Belum ada jadwal.");
            return;
        }

        JadwalNode current = head;
        System.out.println("Daftar Jadwal Les:");
        while (current != null) {
            System.out.println("Nama: " + current.nama +
                               ", Pelajaran: " + current.pelajaran +
                               ", Hari: " + current.hari +
                               ", Jam: " + current.jam +
                               ", Minggu ke-" + current.minggu);
            current = current.next;
        }
    }

    public void tampilkanSlot() {
        System.out.println("Pilih slot jadwal:");
        for (int i = 0; i < slotJadwal.length; i++) {
            System.out.println((i + 1) + ". " + slotJadwal[i]);
        }
    }

    public void resetJadwal() {
        head = tail = null;
        mingguTerpakai.clear();
        System.out.println("Semua jadwal telah dihapus dan minggu terpakai direset.");
    }
}
