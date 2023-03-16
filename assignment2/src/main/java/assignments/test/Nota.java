package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    private static int lastIdNota = 0;

    private int idNota;
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;

    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        this.idNota = lastIdNota++; // increment idNota untuk nota baru
        this.member = member;
        this.paket = paket;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = this.getSisaHariPengerjaan(paket.toLowerCase());
        this.isReady = false;

        if (berat < 2) {
            // Jika berat kurang dari 2, maka bulatkan ke 2
            berat = 2;
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
        }

        this.berat = berat;
        boolean isBonus = member.incrementBonus(); // increment bonusCounter untuk melacak jumlah nota yang dibuat

        System.out.println("Berhasil menambahkan nota!");
        System.out.println("[ID Nota = " + this.idNota + "]");
        System.out.println(NotaGenerator.generateNota(member.getId(), isBonus, paket, berat, tanggalMasuk));
        System.out.println("Status\t\t: Belum bisa diambil :(");
    }

    // Fungsi untuk mendapatkan lama waktu pengerjaan berdasarkan paket
    private int getSisaHariPengerjaan(String paket) {
        switch (paket) {
            case "express":
                return 1;
            case "fast":
                return 2;
            case "reguler":
                return 3;
            default:
                return 0;
        }
    }

    // Fungsi untuk mendapatkan id nota
    public int getIdNota() {
        return this.idNota;
    }

    // Fungsi untuk mendapatkan status paket
    public boolean getIsReady() {
        return this.isReady;
    }

    // Fungsi yang dijalankan tiap terjadi pergantian hari
    public void handleNextDay() {
        this.sisaHariPengerjaan--;
        if (this.sisaHariPengerjaan == 0) {
            this.isReady = true; // Jika sisa hari sudah 0, ubah isReady menjadi true
        }
    }
}
