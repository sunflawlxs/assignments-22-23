package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private static String nama;
    private static String noHp;
    private static String id;

    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getnoHp() {
        return noHp;
    }

    public void setnoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getId() {
        return NotaGenerator.generateId(nama, noHp);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gennewId() {
        return id;
    }
}