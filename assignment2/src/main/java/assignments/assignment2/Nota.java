package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    private String member;
    private String paket;
    private String berat;
    private String tanggalMasuk;
    private String sisaHariPengerjaan;
    private String isReady;

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(Member member, String paket, String berat, String tanggalMasuk) {
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;

    }

    public int getBerat() {
        return Integer.parseInt(berat);
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}