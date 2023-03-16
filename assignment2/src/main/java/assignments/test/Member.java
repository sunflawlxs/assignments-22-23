package assignments.assignment2;

public class Member {
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp, String id) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
        this.bonusCounter = 0;
    }

    // Fungsi mendapatkan nama
    public String getNama() {
        return this.nama;
    }

    // Fungsi mendapatkan id
    public String getId() {
        return this.id;
    }

    // Fungsi mendapatkan nomor telepon
    public String getTelp() {
        return this.noHp;
    }

    // Fungsi menambah bonusCounter tiap membuat Nota
    public boolean incrementBonus() {
        if (this.bonusCounter == 2) {
            // Jika hendak mencapat 3, maka reset jadi 0
            this.bonusCounter = 0;
            return true; // return true untuk dapat bonus
        }
        this.bonusCounter++;
        return false; // return false untuk belum mendapat bonus
    }
}
