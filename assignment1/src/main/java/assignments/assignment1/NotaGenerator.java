package assignments.assignment1;
//import
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);//untuk input
    public static void main(String[] args) {
        int count = 0;
        //initiate variabel
        String nama;
        String nomorHP;
        String tanggalTerima;
        String paket;
        
        while (true) {//untuk mengecek pilihan berapa yang dipilih
            printMenu();//print menu nya
            System.out.print("Pilihan:");//input pilihan
            String angka= input.next();

            if (angka.equals("1")){//klo pilih 1
                System.out.println("Masukkan nama Anda:");//input nama
                input.nextLine();
                nama=input.next();
                
                System.out.println("Masukkan nomor handphone Anda:");//klo input nomor hp
                input.nextLine();
                nomorHP = input.next();
                boolean param = true;
                while (param ==true){
                    for (int i = 0; i<nomorHP.length();i++){//cek no hp itu int
                        if (!isDigit(nomorHP.charAt(i))){
                            System.out.println("Gunakan digit saja!");
                            nomorHP = input.nextLine();
                            break;
                        }
                        param =  false;
                    }
                }
              System.out.println("ID anda: " + generateId(nama, nomorHP));
            }
            else if(angka.equals("2")) {//klo pilih 2
                System.out.println("Masukkan nama Anda:");//input nama
                nama= input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");//input nomor hp
                nomorHP= input.nextLine();
                System.out.println("Masukkan tanggal terima:");//input tanggal terima
                tanggalTerima = input.next();
                input.nextLine();
                
                int hitung = 1;
                do {
                    System.out.println("Masukkan paket laundry:");//input paket laundry
                    paket = input.nextLine();
                    //cek paket apa yang diinput
                    if (paket.equals("express") | paket.equals("fast") | paket.equals("reguler")) {
                        String id = generateId(nama, nomorHP);
                        System.out.println("Masukkan berat cucian Anda [kg]:");//input berat cucian
                        String berat = input.nextLine();
                        boolean paramTwo = true;
                        while (paramTwo ==true){
                        for (int i = 0; i<berat.length();i++){
                            if (!isDigit(berat.charAt(i))){//cek berat int 
                            System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                            berat = input.nextLine();
                                    break;
                                }
                                paramTwo =  false; 
                            }
                        }

                        System.out.println(generateNota(id, paket, hitung, tanggalTerima));
                        hitung = 0;
                    }else if (paket.equals("?")) {//klo input tanda tanya muncul paket yg ada
                        showPaket();
                    
                    }else {//selain itu error
                        System.out.println("Paket " + paket + " tidak diketahui\nketik ? untuk mencari tahu jenis paket]");
                        if (paket.equals("?")) {
                            showPaket();
                        }
                    }
                } while (hitung == 1);
                

            }else if (angka.equals("0")){//klo 0 exit
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                break;

            
            }else{//klo ngasal error
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
                count = 1;
            }
        }
        while (count == 1);
        
    }


    private static void printMenu() {//print menu
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
        String namaPertama = "";//ambil kata pertama
        for (int i = 0; i < nama.length(); i++) {
            char c = nama.charAt(i);
            if (c != ' ') {
                namaPertama += c; // add each character to the firstWord string
            } else {
                break; // exit the loop when a space is found
            }
        }

        nama = namaPertama;

        String anggota = nama.toLowerCase() + "-" + nomorHP;
        int mySum = 0;
        for (int i = 0; i < anggota.length(); i++) {//checksumm
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
        String id = nama.toUpperCase() + "-" + nomorHP + "-" + mySum;
        
        return id;
    }
    

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {
        int hargaPaketPerKg = 0;
        //harga per paketnya
        if (paket.equals("express")) {
            hargaPaketPerKg = 12000;
        } else if (paket.equals("fast")) {
            hargaPaketPerKg = 10000;
        } else if (paket.equals("reguler")) {
            hargaPaketPerKg = 7000;
        } 
        
        LocalDate terima = LocalDate.parse(tanggalTerima, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate selesai = terima.plusDays(paket.equals("express") ? 1 : (paket.equals("fast") ? 2 : 3));
        
        
        //System.out.println("Masukkan berat cucian Anda [kg]: ");

        
        int beratMin = 2;
            if (berat < beratMin) {//klo berat kurg 2 maka akan jd 2
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
        
        int totalHarga = berat * hargaPaketPerKg;

        String nota = "";
        System.out.println("Nota Laundy");//ending nota 
        nota += ("ID    : " + id + "\n");
        nota += ("Paket : " + paket + "\n");
        nota += ("Harga :\n");
        nota += (berat + " kg x " + hargaPaketPerKg + " = " + totalHarga + "\n");
        nota += ("Tanggal Terima  : " + tanggalTerima + "\n");
        nota += ("Tanggal Selesai : " + selesai.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return nota;
    }

    public static boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

}

//collaborator rifda aulia, vina mrynauli, hanan adipratama