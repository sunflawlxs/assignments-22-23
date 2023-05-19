//import file tp sebelumnya dan package yang dibutuhkan
package assignments.assignment4.gui;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {//untuk label dan tombol
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");//untuk tanggal skrg
    private static Calendar cal = Calendar.getInstance();
    
    public HomeGUI(){
        super(new BorderLayout()); // Setup layout, Feel free to make any changes

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {//set up tombol dan peletakan home gui
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 3;
       
        titleLabel = new JLabel("Selamat datang di Cuci Cuci System");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 30));
        mainPanel.add(titleLabel, constraints);

        constraints.gridy =1;
        loginButton = new JButton("Login");
        constraints.weighty +=0;
        mainPanel.add(loginButton, constraints);

        constraints.gridy =2;
        registerButton = new JButton("Register");
        constraints.weighty+=0;
        mainPanel.add(registerButton, constraints);

        constraints.gridy = 3;
        toNextDayButton = new JButton("Next Day");
        constraints.weighty +=0;
        mainPanel.add(toNextDayButton, constraints);

        constraints.gridy = 4;
        dateLabel = new JLabel("Hari ini: " + dateFormat.format(NotaManager.cal.getTime()));
        constraints.weighty +=0;
        mainPanel.add(dateLabel, constraints);

        loginButton.addActionListener(e -> handleToLogin());

        

        // Add ActionListener pada registerButton
        registerButton.addActionListener(e -> handleToRegister());


        // Add ActionListener pada toNextDayButton
        toNextDayButton.addActionListener(e -> handleNextDay());
        
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    private static void handleToRegister() {
        MainFrame.getInstance().navigateTo(RegisterGUI.KEY);
        
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private static void handleToLogin() {
        MainFrame.getInstance().navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    private void handleNextDay() {

        NotaManager.toNextDay();
        dateLabel.setText("Hari ini" + dateFormat.format(NotaManager.cal.getTime()));

        JOptionPane.showMessageDialog(this, "Kamu tidur hari ini... zzz...", "Info Nextday", JOptionPane.INFORMATION_MESSAGE);
    }
}
