package assignments.assignment2;

class Nota {
    private int idNota; // ID nota
    private String paket; // Jenis paket (Express/Fast/Reguler)
    private Member member; // Objek member pelanggan
    private int berat; // Berat cucian
    private String tanggalMasuk; // Tanggal masuk cucian
    private int sisaHariPengerjaan; // Sisa hari pengerjaan cucian
    private boolean isReady; // Status apakah cucian sudah siap diambil atau belum
    private int harga; // Harga total cucian

    /**
     * 
     * Constructor untuk kelas Nota.
     * 
     * @param idNota             ID nota
     * @param paket              Jenis paket
     * @param member             Objek member pelanggan
     * @param berat              Berat cucian
     * @param tanggalMasuk       Tanggal masuk cucian
     * @param sisaHariPengerjaan Sisa hari pengerjaan cucian
     * @param harga              Harga total cucian
     */
    public Nota(int idNota, String paket, Member member, int berat, String tanggalMasuk, int sisaHariPengerjaan,
            int harga) {
        this.idNota = idNota;
        this.paket = paket;
        this.member = member;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = sisaHariPengerjaan;
        this.isReady = false;
        this.harga = harga;
    }
    /**
     * 
     * Method untuk mengurangi sisa hari pengerjaan setiap hari.
     * Jika sisa hari pengerjaan sudah habis, maka cucian dianggap sudah siap
     * diambil.
     */
    public void nextDay() {
        sisaHariPengerjaan--;
        if (sisaHariPengerjaan == 0) {
            isReady = true;
        }
    }

    /**
     * 
     * Method untuk mendapatkan status apakah cucian sudah siap diambil atau belum.
     * 
     * @return True jika sudah siap diambil, false jika belum siap diambil
     */
    public boolean isReady() {
        return isReady;
    }

    /**
     * 
     * Method untuk mendapatkan ID nota.
     * 
     * @return ID nota
     */
    public int getIdNota() {
        return idNota;
    }

    /**
     * 
     * Method untuk mendapatkan objek member pelanggan.
     * 
     * @return Objek member pelanggan
     */
    public Member getMember() {
        return member;
    }

    /**
     * 
     * Method untuk mendapatkan string representasi dari objek Nota.
     * 
     * @return String representasi dari objek Nota
     */
    @Override
    public String toString() {
        return isReady ? "Sudah dapat diambil!" : "Belum bisa diambil :(";
    }
}
