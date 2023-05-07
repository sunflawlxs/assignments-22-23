package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean checkDone =  false;
    @Override
    public String doWork() {
        checkDone = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        return false;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long price =  berat * 1000;
        return price;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
