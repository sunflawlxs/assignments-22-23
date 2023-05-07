package assignments.assignment3;

import assignments.assignment1.NotaGenerator;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.EmployeeSystem;
import assignments.assignment3.user.menu.MemberSystem;
import assignments.assignment3.user.menu.SystemCLI;

public class LoginManager {//kelola sistem login dgn 2 atribut
    private final EmployeeSystem employeeSystem;
    private final MemberSystem memberSystem;

    public LoginManager(EmployeeSystem employeeSystem, MemberSystem memberSystem) {
        this.employeeSystem = employeeSystem;
        this.memberSystem = memberSystem;
    }

    /**
     * Method mapping dari ke SystemCLI yang sesuai.
     *
     * @param id -> ID dari user yang akan menggunakan SystemCLI
     * @return SystemCLI object yang sesuai dengan ID, null if  ID tidak ditemukan.
     */
    public SystemCLI getSystem(String id){//memeriksa apakah ID yang diberikan sesuai dengan ID member atau employee di dalam sistem
        if(memberSystem.isMemberExist(id)){
            return memberSystem;
        }
        if(employeeSystem.isMemberExist(id)){
            
            return employeeSystem;
        }
        return null;
    }

    /**
     * Mendaftarkan member baru dengan informasi yang diberikan.
     *
     * @param nama -> Nama member.
     * @param noHp -> Nomor handphone member.
     * @param password -> Password akun member.
     * @return Member object yang berhasil mendaftar, return null jika gagal mendaftar.
     */
    public Member register(String nama, String noHp, String password) {
        String id = "";//mendaftarkan seorang member baru dengan memasukkan data-data seperti nama, nomor telepon, dan password.
        id += (nama.split(" ")[0] + "-").toUpperCase();
        id += noHp;
        
        int checksum = 0;
        for (char c : id.toCharArray()) {
            if (Character.isDigit(c))
            checksum += c - '0';
            else if (Character.isLetter(c))
                checksum += (c - 'A') + 1;
            else
                checksum += 7;
        }
        id += String.format("-%02d", checksum % 100);
        Member member = new Member(nama, id, password);
        memberSystem.addMember(member);//objek Member baru akan dibuat dengan menggunakan data-data yang telah dimasukkan dan objek tersebut akan ditambahkan ke dalam sistem melalui method addMember yang terdapat pada kelas MemberSystem.
        return member;
    }
}