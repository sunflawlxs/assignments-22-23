package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

//
public class MemberSystem extends SystemCLI {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar calendar = Calendar.getInstance();
    private static Date currentDate = new Date();
    Date newDate = calendar.getTime();
    String formattedDate = fmt.format(newDate);
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> laundry();
            case 2 -> detailnota();
            case 3 -> logout = true;
        }
        return logout;
    }

    public void laundry(){
        System.out.println("Masukkan paket laundry:");
        
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
        String paket = in.nextLine();

        if (getHargaPaket(paket)< 0) {
            System.out.println("Paket tidak diktetahui");
        }
        System.out.println("Masukan berat cucian anda [Kg]: ");

        String beratInput = in.nextLine();
        int berat = Integer.parseInt(beratInput);

        if (berat < 2) {
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }

        String setrika;
        String loginday = fmt.format(NotaManager.cal.getTime());
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.println("[Ketik x untuk tidak mau]:");
        setrika= in.nextLine();
        boolean issetrika = false;
        if (setrika.equals("x")){
            issetrika = true;
        }
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan! \n Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.println("[Ketik x untuk tidak mau]:");
        boolean isantar = false;
        String antar= in.nextLine();
        if (antar.equals("x")){
            isantar = true;
        }
        Nota newNota = new Nota(loginMember, berat, paket, loginday );
        System.out.println("Nota berhasil dibuat");

    }
    public void detailnota(){
        
    }
    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        Member[] newMemberList = new Member[memberList.length + 1];
        for (int i = 0; i < memberList.length; i++) {
            newMemberList[i] = memberList[i];
        }
        newMemberList[newMemberList.length-1] = member;
        memberList= newMemberList;
    }
    public static boolean isNumeric(String str) {
        int strLen = str.length();
        int i = 0;
        while (i < strLen) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }
    private static long getHargaPaket(String paket) {
        paket = paket.toLowerCase();
        switch(paket) {
            case "express":
                return 12000;
            case "fast":
                return 10000;
            case "reguler":
                return 7000;
            default:
                return -1;
        }
    }
    
    

}   