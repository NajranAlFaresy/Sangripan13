package entity;

public class JadwalNode {
    public String nama;
    public String pelajaran;
    public String hari;
    public String jam;
    public int minggu;
    public JadwalNode next;

    public JadwalNode(String nama, String pelajaran, String hari, String jam, int minggu) {
        this.nama = nama;
        this.pelajaran = pelajaran;
        this.hari = hari;
        this.jam = jam;
        this.minggu = minggu;
        this.next = null;
    }
}
