package assignments.assignment2;

class Member {
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    /**
     * Konstruktor untuk membuat objek Member dengan parameter nama, nomor hp, dan
     * ID.
     * Member awalnya memiliki bonusCounter sebesar 0.
     *
     * @param nama Nama member
     * @param noHp Nomor hp member
     * @param id   ID member
     */
    public Member(String nama, String noHp, String id) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = id;
        this.bonusCounter = 0;
    }

    /**
     * Increment bonusCounter sebanyak 1.
     */
    public void incrementBonusCounter() {
        bonusCounter++;
    }

    /**
     * Cek apakah member eligible untuk mendapatkan diskon. Member eligible jika
     * bonusCounter nya mencapai 3.
     *
     * @return True jika eligible untuk mendapatkan diskon, false jika tidak
     *         eligible.
     */
    public boolean isEligibleForDiscount() {
        return bonusCounter == 3;
    }

    /**
     * Reset bonusCounter menjadi 0.
     */
    public void resetBonusCounter() {
        bonusCounter = 0;
    }

    /**
     * Getter untuk ID member.
     *
     * @return ID member.
     */
    public String getId() {
        return id;
    }

    /**
     * Mengembalikan string yang merepresentasikan objek Member dalam format
     * "- [ID Member] : [Nama Member]".
     *
     * @return String representasi objek Member.
     */
    @Override
    public String toString() {
        return "- " + id + " : " + nama;
    }

}