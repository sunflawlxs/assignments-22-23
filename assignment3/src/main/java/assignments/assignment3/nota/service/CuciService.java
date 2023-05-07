package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    private boolean checkDone = false;
    @Override
    public String doWork() {
        checkDone =  true;
        return "Sedang mencuci...";//bila masi dikerjakan akan keluar sedang mencuci
    }

    @Override
    public boolean isDone() {
        return checkDone;
    }

    @Override
    public long getHarga(int berat) {//itungan harga beratnya
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";//return cuci 
    }
}
