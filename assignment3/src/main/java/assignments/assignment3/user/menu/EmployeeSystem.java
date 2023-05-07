package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1:
                logout = false;
                if (loginMember instanceof Employee) {
                    System.out.println("Stand back! " + loginMember.getNama() + " beginning to nyuci!");
        
                    for (Nota nota : notaList) {
                        System.out.println(nota.kerjakan());
                    }
                }
                break;
            case 2:
                logout = false;
                for (Nota nota : notaList) {
                    System.out.println(nota.getNotaStatus());
                }
                break;
            case 3:
                logout = true;
                break;
            default:
                System.out.println("Pilihan tidak diketahui.");
                break;
        }
        
        return logout;
        
        
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}