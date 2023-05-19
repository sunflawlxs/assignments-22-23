package assignments.assignment4.gui.member.employee;
//import package yang dibutuhkan
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {//untuk employee nya
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        return new JButton[]{
            new JButton("It's nyuci time"),
            new JButton("Display List Nota"),
        };
    };
    

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {//untuk display nota kalo nota nya ga kosong
        String containerStatus = "";
        if (NotaManager.notaList.length != 0) {
            for (Nota nota : NotaManager.notaList) {
                containerStatus += (nota.getNotaStatus()) + ("\n");
                
            }
            JOptionPane.showMessageDialog(this, containerStatus.toString(), "List Nota", JOptionPane.INFORMATION_MESSAGE);
            
        }else {
        JOptionPane.showMessageDialog(this, "Belum ada nota", "List Nota", JOptionPane.ERROR_MESSAGE);//kalo nota nya belom ada
        }
        
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {//method utk cuci2 kalo sudah pesan
        JOptionPane.showMessageDialog(this, "Stand back! " + loggedInMember.getNama() + " beginning to nyuci!", "Nyuci Time", JOptionPane.INFORMATION_MESSAGE);
    
        switch (NotaManager.notaList.length) {//cek ada yang harus dicuci atau ga kalo ada maka akan masuk ke nyuci result
            case 0:
                JOptionPane.showMessageDialog(this, "Nothing to cuci here", "Nyuci Results", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                StringBuilder containerNota = new StringBuilder();
                for (Nota nota : NotaManager.notaList) {
                    containerNota.append(nota.kerjakan()).append("\n");
                }
    
                JOptionPane.showMessageDialog(this, containerNota.toString(), "Nyuci Results", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        
        
    }
}
