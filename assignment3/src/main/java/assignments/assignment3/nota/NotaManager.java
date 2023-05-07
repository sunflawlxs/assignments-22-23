package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];
    

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){//untuk menggeser tanggal saat ini ke esok hari 
        cal.add(Calendar.DAY_OF_YEAR, 1);
        fmt.format(cal.getTime());

        int index = 0;
        while (index < notaList.length) {// fungsi ini juga melakukan pemanggilan method toNextDay() pada setiap objek Nota yang ada di dalam notaList
            Nota checkIndex = notaList[index];
            checkIndex.toNextDay();
            index++;
            
        }

    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    public static void addNota(Nota nota){//membuat array baru kemudian diiterasi dan ditambahkan ke array tsb
        Nota[] newNotaList = new Nota[notaList.length + 1];
        int i = 0;
        while (i < notaList.length) {
            newNotaList[i] = notaList[i];
            i++;
        }
        newNotaList[newNotaList.length - 1] = nota;//menambahkan objek Nota ke dalam array notaList
        notaList = newNotaList;//menambahkan objek nota ke dalam indeks terakhir dari array newNotaList.
//array notaList diinisialisasi ulang dengan referensi ke array newNotaList yang sudah berisi objek nota yang baru ditambahkan
    }
}
