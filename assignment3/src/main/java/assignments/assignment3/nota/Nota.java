package assignments.assignment3.nota;
import java.time.LocalDate;//import package yang dibutuhkan
import java.time.format.DateTimeFormatter;
import java.util.List;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.user.Member;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services;
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int id;
    private int idNota;
    private int kompensasi;
    private String tanggalMasuk;
    private boolean isDone;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggalMasuk) {
        this.member = member;
        this.baseHarga= getHargaPaketperKg(this.paket) *getBerat();//inisialisasi variabel yang dibutuhkan
        this.berat = berat;
        this.paket = paket;
        this.tanggalMasuk = tanggalMasuk;
        this.sisaHariPengerjaan = getSisaHariPengerjaan();
        this.idNota = totalNota;
        totalNota++;
        this.services = new LaundryService[] {new CuciService()};
    }
    
    private int getHargaPaketperKg(String paket) {
        int hargaPaketPerKg = 0;
        switch (paket) {//harga sesuai input yang dimasukkan 
            case "express":
                hargaPaketPerKg = 12000;
                break;
            case "fast":
                hargaPaketPerKg = 10000;
                break;
            default:
                hargaPaketPerKg = 7000;
                break;
        }
        
        return hargaPaketPerKg;
        
    }

    public int getIdNota(){
        return idNota;
    }

    public boolean equals (int idNota) {
        return idNota == this.idNota;
    }

    public boolean equals (Nota nota) {
        return equals(nota.getIdNota());
    }
    public int SisaHariPengerjaan() {
        switch (paket.toLowerCase()) {//sisa hari pengerjaan tergantung dengan paket yang dipilih
            case "express":
                sisaHariPengerjaan = 1;
                break;
            case "fast":
                sisaHariPengerjaan = 2;
                break;
            default:
                sisaHariPengerjaan = 3;
                break;
        }
        
        return sisaHariPengerjaan;
    }
    public void addService(LaundryService service){
        LaundryService[] newService = new LaundryService[services.length + 1];///newservice dibuat dgn ukuran yang lebih besar sebanyak satu karena ingin menambah layanan baru
        int i = 0;
        while (i < services.length) {//setiap elemen disalin dengan metode while loop
            newService[i] = services[i];
            i++;
        }
        newService[newService.length - 1] = service;//objek service ditambahkan ke array new service pada indeks terakhir
        services = newService;//inisialisasi
    }

    public String kerjakan(){
        String serviceText = "";//inisialisasi service teks
        boolean isServiceDone = false;//set boolean is false and sevice index to 0
        int serviceIndex = 0;

        while (!isServiceDone && serviceIndex < services.length) {//use while loop to retrieves the laundry service at index from the servicearray
            LaundryService service = services[serviceIndex];

            if (!service.isDone()) {//check the service is done or not
                serviceText = "Nota " + this.idNota + " : " + service.doWork();
                if (checkService()) {
                    this.isDone = true;
                }
                isServiceDone = true;
            }
            serviceIndex++;
}
            if (!isServiceDone && this.isDone) {//if its done
                serviceText = "Nota " + this.idNota + " : " + "Sudah selesai.";
            }
            return serviceText; 
    }

    public boolean checkService() {
        boolean checkAllLaundry = true;//set to true
        int i = 0;
        while (i < services.length && checkAllLaundry) {//use while loop to iterates the service array, check the service is done
            if (services[i].isDone() == false) {//encounters a service id not done
                checkAllLaundry = false;//checkalllaundry set to false
            }
        i++;
        }
        return checkAllLaundry;//after loop finish will be return
    }

    public void toNextDay() {
        while (sisaHariPengerjaan > 0) {
            sisaHariPengerjaan--;
        }
        if (getSisaHariPengerjaan() < 0 && checkService() == false) {
            kompensasi++;
        }
        
    }

    public long calculateHarga(){//to calculate harga
        int tempTotalHarga = 0;//set to zero
        long totalHarga = 0;
        
        if (services == null) {//if service is zero
            System.out.println("Tidak ada layanan laundry yang diberikan.");
        } else {//if not akan ditambahkan dengan harga tergantung beratnya
            int i = 0;
            while (i < services.length) {
                LaundryService service = services[i];
                tempTotalHarga += service.getHarga(getBerat());
                i++;
            }
            
            totalHarga = tempTotalHarga + this.baseHarga;//total harga penjumalahnnya
            if (kompensasi != 0 && kompensasi >= 1) {
                totalHarga = tempTotalHarga + this.baseHarga - (getKompensasi() * 2000);//dioperasikan dengan kompensasinya
            } else {
                totalHarga = tempTotalHarga + this.baseHarga;
            }
            
            if (totalHarga < 0) {
                totalHarga = 0;
            }
        }
        return totalHarga;
    }

    public String getNotaStatus(){//nota status if finish or not
        if (this.isDone != true) {
            return "Nota " + this.idNota + " : " + "Belum selesai.";
        } else {
            return "Nota " + this.idNota + " : " + "Sudah selesai.";
        }
    }

    @Override
    public String toString(){//tannggalnya untuk servicenya
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date= LocalDate.parse(tanggalMasuk, formatter);
        int i = 0;
        while (i < services.length) {//using while loop to service arrays and prints out element
            LaundryService service = services[i];
            System.out.println(service);
            i++;
        }

        LocalDate tanggalakhir;//tanggal akhir untuk paketnya
        switch (paket) {
            case "express":
                tanggalakhir = date.plusDays(1);
                break;
            case "fast":
                tanggalakhir = date.plusDays(2);
                break;
            default:
                tanggalakhir = date.plusDays(3);
                break;
        }
        
        String inversetanggal = tanggalakhir.format(formatter);
        String teks = "" ;

        teks += "[ID NOTA= " +getIdNota() +"]" + "\nID    : " + member.getId() +"\nPaket : " + getPaket() + "\nHarga :\n" + getBerat() + " kg x " + getHargaPaketperKg(paket) + " = " + this.baseHarga + "\ntanggal terima  : " + getTanggal() + "\ntanggal selesai : " + inversetanggal;
        if (kompensasi <= 0 ) {//if kompensasi below or = 0
            teks += "\nHarga Akhir: " + calculateHarga();
        } else {//if no
            teks += "\nHarga Akhir: " + calculateHarga() + " Ada kompensasi keterlambatan " + getKompensasi() + " * 2000 hari";
        }
        
        return teks;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    } 
    public int getKompensasi(){
        return kompensasi;
    }
}