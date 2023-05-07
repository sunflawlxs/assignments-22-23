package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean checkDone =  false;
    @Override
    public String doWork() {
        checkDone = true;
        return "Sedang mengantar...";//menandai bahwa proses pengantaran sedang berlangsung
    }

    @Override
    public boolean isDone() {
        return checkDone;
    }

    @Override
    public long getHarga(int berat) {//menghitung harga layanan 
        long price = berat * 500; //hitungan per kg
        if (price < 2000){//jika kurang dari 2000 maka akan jadi 2000
            price =2000;
        } 
        return price;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
