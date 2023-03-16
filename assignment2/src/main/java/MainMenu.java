import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * Kelas Nota merepresentasikan objek nota yang berisi informasi tentang pesanan
 * pelanggan.
 */
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

public class MainMenu {
    // Objek untuk mengformat tanggal
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    // Objek untuk menyimpan tanggal saat ini
    private static Date currentDate = new Date();
    // Objek untuk menyimpan daftar member
    private static ArrayList<Member> members = new ArrayList<>();
    // Objek untuk menyimpan daftar nota
    private static ArrayList<Nota> notas = new ArrayList<>();
    // Counter untuk id nota
    private static int notaCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Tampilkan menu
            System.out.println("\nSelamat datang di CuciCuci!");
            System.out.println("Sekarang Tanggal: " + DATE_FORMAT.format(currentDate));
            System.out.println("==============Menu==============");
            System.out.println("[1] Generate Member");
            System.out.println("[2] Generate Nota");
            System.out.println("[3] List Nota");
            System.out.println("[4] List Member");
            System.out.println("[5] Ambil Cucian");
            System.out.println("[6] Next Day");
            System.out.println("[0] Exit");
            System.out.print("Pilihan : ");

            // Baca input dari user
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Lakukan aksi sesuai pilihan yang dipilih
            switch (choice) {
                case 1:
                    generateMember(scanner);
                    break;
                case 2:
                    generateNota(scanner);
                    break;
                case 3:
                    listNotas();
                    break;
                case 4:
                    listMembers();
                    break;
                case 5:
                    ambilCucian(scanner);
                    break;
                case 6:
                    nextDay();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                    System.exit(0);
                default:
                    System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
    }

    /**
     * Method untuk menambahkan member baru ke dalam daftar member
     *
     * @param scanner Objek Scanner untuk membaca input dari user
     */
    private static void generateMember(Scanner scanner) {
        System.out.println("Masukan nama Anda: ");
        String nama = scanner.nextLine();
        System.out.println("Masukan nomor handphone Anda: ");

        // Validasi input nomor handphone
        String noHp;
        while (true) {
            noHp = scanner.nextLine();

            if (!noHp.matches("\\d+")) {
                System.out.println("Field nomor hp hanya menerima digit.");

            } else {
                break;
            }
        }

        // Cek apakah member dengan nama dan nomor handphone yang sama sudah ada
        for (Member member : members) {
            String arr[] = nama.split(" ", 2);
            if (member.getId().startsWith(arr[0].toUpperCase()) && member.toString().contains(noHp)) {
                System.out.println("Member dengan nama " + nama + " dan nomor hp " + noHp + " sudah ada!");
                return;
            }
        }

        // Generate ID member baru
        String id = generateMemberId(nama, noHp);
        /// Buat objek Member baru dengan parameter nama, nomor handphone, dan id
        Member newMember = new Member(nama, noHp, id);

        // Tambahkan objek Member baru ke dalam ArrayList members
        members.add(newMember);

        // Cetak pesan berhasil membuat member dengan ID baru
        System.out.println("Berhasil membuat member dengan ID " + id + "!");
    }// Method untuk menghasilkan ID member baru

    public static String generateMemberId(String nama, String noHp) {
        String id = "";

        // Ambil huruf pertama dari nama dan ubah menjadi huruf kapital
        id += (nama.split(" ")[0] + "-").toUpperCase();

        // Tambahkan nomor handphone ke dalam id
        id += noHp;

        // Hitung checksum
        int checksum = 0;
        for (char c : id.toCharArray()) {
            if (Character.isDigit(c))
                checksum += c - '0';
            else if (Character.isLetter(c))
                checksum += (c - 'A') + 1;
            else
                checksum += 7;
        }

        // Tambahkan nilai checksum ke dalam id dengan format 2 digit
        id += String.format("-%02d", checksum % 100);

        // Kembalikan nilai id
        return id;
    }

    // Method untuk membuat nota baru
    private static void generateNota(Scanner scanner) {
        System.out.println("Masukkan ID Member: ");
        String memberId = scanner.nextLine();
        Member member = null;

        // Cari objek Member dengan ID yang sesuai dalam ArrayList members
        for (Member m : members) {
            if (m.getId().equals(memberId)) {
                member = m;
                break;
            }
        }

        // Jika objek Member tidak ditemukan, cetak pesan error dan keluar dari method
        if (member == null) {
            System.out.println("Member dengan ID " + memberId + " tidak ditemukan!");
            return;
        }

        String paket;
        while (true) {
            System.out.println("Masukan paket laundry: ");
            paket = scanner.nextLine().toLowerCase();

            // Jika input pengguna adalah tanda tanya, tampilkan opsi paket
            if (paket.equals("?")) {
                System.out.println("+-------------Paket-------------+");
                System.out.println("| Express | 1 Hari | 12000 / Kg |");
                System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
                System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
                System.out.println("+-------------------------------+");
            } else {
                break;
            }
        }

        // Inisialisasi variabel sisaHariPengerjaan dan harga sesuai dengan paket yang
        // dipilih
        int sisaHariPengerjaan;
        int harga;
        switch (paket) {
            case "reguler":
                sisaHariPengerjaan = 3;
                harga = 7000;
                break;
            case "fast":
                sisaHariPengerjaan = 2;
                harga = 10000;
                break;
            case "express":
                sisaHariPengerjaan = 1;
                harga = 12000;
                break;
            default:
                System.out.println("Paket tidak valid.");
                return;
        }

        // Mintai input dari pengguna mengenai berat cucian
        // Mintai input dari pengguna mengenai berat cucian
        System.out.print("Masukkan berat cucian Anda [Kg]: ");
        int berat = scanner.nextInt();
        scanner.nextLine();

        // Jika berat kurang dari 2 kg, set berat menjadi 2 kg
        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }

        // Buat objek Nota baru
        Nota newNota = new Nota(notaCounter, paket, member, berat, DATE_FORMAT.format(currentDate), sisaHariPengerjaan,
                harga);

        // Tambahkan objek Nota ke dalam array list notas
        notas.add(newNota);

        // Tambahkan bonus counter pada member yang melakukan transaksi
        member.incrementBonusCounter();

        // Tampilkan informasi nota yang baru dibuat
        System.out.println("Berhasil menambahkan nota!");
        System.out.println("[ID Nota = " + notaCounter + "]");
        System.out.println("ID : " + memberId);
        System.out.println("Paket : " + paket);
        System.out.println("Harga :");
        System.out.print(berat + " kg x " + harga + " = " + (berat * harga));

        // Jika member memiliki bonus yang cukup untuk mendapatkan diskon 50%, tampilkan
        // informasi diskon
        if (member.isEligibleForDiscount()) {
            System.out.println(" = " + ((berat * harga) / 2) + "(Discount member 50%!!!)");
            member.resetBonusCounter();
        } else {
            System.out.println();
        }

        // Tampilkan informasi tanggal terima dan tanggal selesai pengerjaan
        System.out.println("Tanggal Terima : " + DATE_FORMAT.format(currentDate));

        // Buat objek Calendar baru
        Calendar calendar = Calendar.getInstance();

        // Set waktu pada objek Calendar menjadi tanggal saat ini
        calendar.setTime(currentDate);

        // Tambahkan sisa hari pengerjaan ke tanggal pada objek Calendar
        calendar.add(Calendar.DATE, sisaHariPengerjaan);

        // Dapatkan tanggal baru setelah penambahan sisa hari pengerjaan
        Date newDate = calendar.getTime();

        // Format tanggal baru menggunakan SimpleDateFormat
        String formattedDate = DATE_FORMAT.format(newDate);

        // Tampilkan informasi tanggal selesai pengerjaan
        System.out.println("Tanggal Selesai : " + formattedDate);

        // Tampilkan informasi status nota
        System.out.println("Status : Belum bisa diambil :(");

        

        // Cek apakah member memenuhi syarat diskon 50%
        if (member.isEligibleForDiscount()) {
            System.out.println("Bonus : Mendapatkan diskon 50%!");
            member.resetBonusCounter();
        }

        notaCounter++;
    }

    /**
     * 
     * Method untuk menampilkan list nota yang sudah terdaftar
     */
    private static void listNotas() {
        if (notas.size() == 0) {
            System.out.println("Terdaftar 0 nota dalam sistem.");
            return;
        }

        System.out.println("Terdaftar " + notas.size() + " nota dalam sistem.");
        int i = 0;
        for (Nota nota : notas) {
            System.out.println("- [" + i + "] Status : " + nota);
            i++;
        }
    }

    /**
     * 
     * Method untuk menampilkan list member yang sudah terdaftar
     */
    private static void listMembers() {
        if (members.size() == 0) {
            System.out.println("Terdaftar 0 member dalam sistem.");
            return;
        }

        System.out.println("Terdaftar " + members.size() + " member dalam sistem.");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    /**
     * 
     * Method untuk mengambil cucian dan menampilkan informasi terkait
     */
    private static void ambilCucian(Scanner scanner) {
        String idNotaStr;
        int idNota;
        System.out.print("Masukkan ID nota yang akan diambil: ");
        while (true) {
            idNotaStr = scanner.nextLine();

            try {
                idNota = Integer.parseInt(idNotaStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID Nota harus berupa angka.");
            }
        }

        Nota nota = null;
        for (Nota n : notas) {
            if (n.getIdNota() == idNota) {
                nota = n;
                break;
            }
        }

        if (nota == null) {
            System.out.println("Nota dengan ID " + idNota + " tidak ditemukan!");
            return;
        }

        if (!nota.isReady()) {
            System.out.println("Nota dengan ID " + idNota + " gagal diambil!");
            return;
        }

        Member member = nota.getMember();

        System.out.println("Nota dengan ID " + idNota + " berhasil diambil!");

        notas.remove(nota);
    }

    /**
     * 
     * Method untuk menambahkan 1 hari ke current date dan menampilkan informasi
     * terkait nota
     */
    private static void nextDay() {

        System.out.println("Dek Depe tidur hari ini... zzz...");
        currentDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));
        for (Nota nota : notas) {
            nota.nextDay();
            if (nota.isReady() == true) {
                System.out.println("Laundry dengan nota ID " + nota.getIdNota() + " sudah dapat diambil!");
            }
        }
        System.out.println("Selamat pagi dunia!\nDek Depe: It's CuciCuci Time.");
    }
}