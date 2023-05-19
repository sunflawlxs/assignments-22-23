package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        return new JButton[]{
            new JButton("Saya ingin laundry"),
            new JButton("Lihat detail nota saya"),

        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        Member loggedInMember = getLoggedInMember();
        JTextArea label = new JTextArea();

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(label);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        try {
            StringBuilder message = new StringBuilder();
            for (Nota nota : NotaManager.notaList) {
                if (loggedInMember.equals(nota.getMember())) {
                    message.append(nota.toString()).append("\n");
                }
            }

            if (message.toString().isEmpty()) {
                label.setText("You haven't done any laundry at Cucicuci yet. :(");
            } else {
                label.setText(message.toString());
            }

            JOptionPane.showMessageDialog(this, scrollPane, "Nota Details", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to show nota details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

            


    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        // TODO
        MainFrame.getInstance().navigateTo(CreateNotaGUI.KEY);
    }

}