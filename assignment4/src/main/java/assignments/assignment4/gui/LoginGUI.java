//import package yang dibutuhkan
package assignments.assignment4.gui;
import assignments.assignment3.LoginManager;
import assignments.assignment4.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {//tampilan utk login nya
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

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
    private void initGUI() {//set up layout dan peletakkan tombolnya
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx =0;
        constraints.gridy = 0;
        constraints.weighty= 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;

        idLabel = new JLabel("Silahkan masukkan ID Anda: ");
        mainPanel.add(idLabel, constraints);

        constraints.gridy =1;
        idTextField = new JTextField();
        constraints.weighty+=0;
        mainPanel.add(idTextField, constraints);

        constraints.gridy =2;
        passwordLabel = new JLabel("Silahkan masukkan password Anda");
        constraints.weighty += 0;
        mainPanel.add(passwordLabel, constraints);

        constraints.gridy = 3;
        passwordField = new JPasswordField();
        constraints.weighty +=0;
        mainPanel.add(passwordField, constraints);

        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        loginButton = new JButton("Login");
        mainPanel.add(loginButton, constraints);

        constraints.gridy = 5;
        backButton = new JButton("Kembali");
        mainPanel.add(backButton, constraints);

    

        backButton.addActionListener(e -> handleBack());

        loginButton.addActionListener(e -> handleLogin());
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        idTextField.setText("");
        passwordField.setText("");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        String password = new String (passwordField.getPassword());
        boolean login = MainFrame.getInstance().login(idTextField.getText(), password);
        idTextField.setText("");
        passwordField.setText("");
        if (!login) {
            JOptionPane.showMessageDialog(this, "ID atau password invalid.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            idTextField.setText("");
            passwordField.setText("");
        } else {
            return;
        }
    }
}
