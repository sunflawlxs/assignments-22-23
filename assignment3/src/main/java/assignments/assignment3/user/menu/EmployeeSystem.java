package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

import java.util.Arrays;

public class EmployeeSystem extends SystemCLI {
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }
    public void addEmployee(Employee[] employees) {
        Member[] result = new Member[employees.length + memberList.length];
     
     
        System.arraycopy(memberList, 0, result, 0, memberList.length);
        System.arraycopy(employees, 0, result, memberList.length, employees.length);
     
        memberList = result;
     }
     
    
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        switch (choice) {
            case 1 -> cuci();
            case 2 -> displayNota();
            case 3 -> logout = true;
            default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
        }
        return logout;
    }

    private void displayNota() {
        for (Nota nota:
             notaList) {
            System.out.println(nota.getNotaStatus());
        }
    }

    public void cuci() {
        System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());
        for (Nota nota:
             notaList) {
            System.out.println(nota.kerjakan());
        }
    }

    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}
