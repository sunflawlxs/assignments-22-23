package assignments.assignment1;
//import 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);//untuk input
    public static void main(String[] args) {
        //inisiasi nama variabel
        int count = 0;
        String nama;
        String nomorHP;
        String tanggalTerima;
        String paket;
        
        while (true) {
            printMenu();//untuk print file 
            System.out.print("Pilihan:");//input untuk pilihannya
            String angka= input.next();

            if (angka.equals("1")){
                System.out.println("Masukkan nama Anda:");//kemudian stelah input minta input nama
                nama=input.next();
                input.nextLine();
                
                System.out.println("Masukkan nomor handphone Anda:");//input nomor telpon dan cek int atau bukan
                nomorHP = input.nextLine();
                boolean param = true;
                while (param ==true){
                    for (int i = 0; i<nomorHP.length();i++){
                        if (!isDigit(nomorHP.charAt(i))){
                            System.out.println("Gunakan digit saja!");
                            nomorHP = input.nextLine();
                            break;
                        }
                        param =  false;
                    }
                }
              System.out.println("ID anda: " + generateId(nama, nomorHP));//melakukan cek sum utk nama dan nomor hp
            }
            else if(angka.equals("2")) {
                System.out.println("Masukkan nama Anda:");//untuk pilihan 2 masukkan nama
                nama= input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");//masukkan nomor hp input
                nomorHP= input.nextLine();
                boolean paramTwo = true;
                while (paramTwo ==true){//cek klo yg dimasukin int atau bukan
                    for (int i = 0; i<nomorHP.length();i++){
                        if (!isDigit(nomorHP.charAt(i))){
                            System.out.println("Gunakan digit saja!");
                            nomorHP = input.nextLine();
                            break;
                        }
                        paramTwo =  false; 
                    }
                }
                System.out.println("Masukkan tanggal terima:");//meminta input tanggal
                tanggalTerima = input.next();
                input.nextLine();
                
                int hitung = 1;
                do {//melakukan cek input apa paket yang dimasukkin
                    System.out.println("Masukkan paket laundry:");
                    paket = input.nextLine();
                    
                    if (paket.equals("express") | paket.equals("fast") | paket.equals("reguler")) {
                        String id = generateId(nama, nomorHP);
                        System.out.println("Masukkan berat cucian Anda [kg]:");//input berat cucian
                        String berat = input.nextLine();
                        paramTwo = true;
                        while (paramTwo ==true){//mengecek yg diinput angka atau bukan
                        for (int i = 0; i<berat.length();i++){
                            if (!isDigit(berat.charAt(i))){
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            berat = input.nextLine();
                                    break;
                                }
                                paramTwo =  false; 
                            }
                        }

                        generateNota(paket, nama, nomorHP, id , Integer.parseInt(berat), tanggalTerima);
                        hitung = 0;
                    }else if (paket.equals("?")) {//klo yg diinput tanda tanya maka akan memunculkan paket
                        showPaket();
                    
                    }else {
                        System.out.println("Paket " + paket + " tidak diketahui\nketik ? untuk mencari tahu jenis paket]");
                        if (paket.equals("?")) {
                            showPaket();
                        }
                    }
                } while (hitung == 1);
                

            }else if (angka.equals("0")){//kalo yg diinput 0
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                break;

            
            }else{//sealain yang diminta maka error
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                count = 1;
            }
        }
        while (count == 1);
        
    }


    private static void printMenu() {//untuk print menu
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }


    private static void showPaket() {//print paket
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }


    public static String generateId(String nama, String nomorHP){

        String anggota = nama.toLowerCase() + "-" + nomorHP;//untuk check sum
        int mySum = 0;
        for (int i = 0; i < anggota.length(); i++) {
            char myChar = anggota.charAt(i);
            if (Character.isLetter(myChar)) {
                int charToInt = (int)myChar - (int)'a' + 1;
                mySum += charToInt;
            } else if (Character.isDigit(myChar)) {
                mySum += Character.getNumericValue(myChar);
            } else {
                mySum += 7;
            }
        }
        
        return nama.toUpperCase() + "-" + nomorHP + "-" + mySum;
    }
    

    public static String generateNota(String paket ,String nama, String nomorHP, String id, int berat, String tanggalTerima) {
        int hargaPaketPerKg = 0;//harga per paket
        if (paket.equals("express")) {
            hargaPaketPerKg = 12000;
        } else if (paket.equals("fast")) {
            hargaPaketPerKg = 10000;
        } else if (paket.equals("reguler")) {
            hargaPaketPerKg = 7000;
        } 
        
        LocalDate terima = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate selesai = terima.plusDays(paket.equals("express") ? 1 : (paket.equals("fast") ? 2 : 3));
        
        String anggota = nama.toLowerCase() + "-" + nomorHP;//checksum utk generate nota
        int mySum = 0;
        for (int i = 0; i < anggota.length(); i++) {
            char myChar = anggota.charAt(i);
            if (Character.isLetter(myChar)) {
                int charToInt = (int)myChar - (int)'a' + 1;
                mySum += charToInt;
            } else if (Character.isDigit(myChar)) {
                mySum += Character.getNumericValue(myChar);
            } else {
                mySum += 7;
            }
        }
        id = nama.toUpperCase() + "-" +nomorHP + "-" + mySum;
        
        
        //System.out.println("Masukkan berat cucian Anda [kg]: ");

        
        int beratMin = 2;
            if (berat < beratMin) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
        
        int totalHarga = berat * hargaPaketPerKg;//utk total harga
            //print biasa
        System.out.println("Nota Laundy");
        System.out.println("ID    : " + id);
        System.out.println("Paket : " + paket);
        System.out.println("Harga : ");
       // System.out.println("Masukkan berat cucian Anda [kg]:"+ berat);
        System.out.println(berat + " kg x " + hargaPaketPerKg + " = " + totalHarga);
        System.out.println("Tanggal Terima  : " + tanggalTerima);
        System.out.println("Tanggal Selesai : " + selesai.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println( "Harga : "+ totalHarga);

        return null;
    }
    public static boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

}