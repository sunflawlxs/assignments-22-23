package assignments.assignment4.gui;
//import package yang dibutuhkan
import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {//layout register
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
    private void initGUI() {
        GridBagConstraints constraints = new GridBagConstraints();//peletakkan tombol2nya 

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;

        nameLabel = new JLabel("Silahkan masukkan nama Anda: ");
        mainPanel.add(nameLabel, constraints);
        
        constraints.gridy = 1;
        nameTextField = new JTextField();
        constraints.weighty += 0;
        mainPanel.add(nameTextField, constraints);

        constraints.gridy = 2;
        phoneLabel = new JLabel("Silahkan masukkan nomor handphone Anda: ");
        constraints.weighty += 0;
        mainPanel.add(phoneLabel, constraints);

        constraints.gridy = 3;
        phoneTextField = new JTextField();
        constraints.weighty +=0;
        mainPanel.add(phoneTextField, constraints);

        constraints.gridy = 4;
        passwordLabel = new JLabel("Silahkan masukkan password Anda: ");
        constraints.weighty += 0;
        mainPanel.add(passwordLabel, constraints);

        constraints.gridy= 5;
        passwordField = new JPasswordField();
        constraints.weighty +=0;
        mainPanel.add(passwordField, constraints);

        constraints.gridy = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        registerButton= new JButton("Register");
        mainPanel.add(registerButton, constraints);

        constraints.gridy = 7;
        backButton = new JButton("Kembali");
        mainPanel.add(backButton, constraints);

        backButton.addActionListener(e -> handleBack());

        registerButton.addActionListener(e -> handleRegister());

       





    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame.getInstance().navigateTo(HomeGUI.KEY);
        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {//handle error untuk registernya
        String password = new String (passwordField.getPassword());
        if (nameTextField.getText().equals("") || phoneTextField.getText().equals("") ||password.equals("")) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi","Info", JOptionPane.INFORMATION_MESSAGE);
        } else if (password.length() < 8) {
            JOptionPane.showMessageDialog(this, "Password tidak boleh kurang dari 8 karakter", "Registrasi gagal", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!isNumeric(phoneTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Nomor hp hanya menerima digit", "Error", JOptionPane.ERROR_MESSAGE);
            phoneTextField.setText("");
        }
        else {

            Member registMember =  loginManager.register(nameTextField.getText(), phoneTextField.getText(), password);
    
    
            if (registMember != null) {//kalo diisi lengkap maka akan sukses
                JOptionPane.showMessageDialog(this, "berhasil membuat ID" + registMember.getId(), "Registrasi sukses", JOptionPane.INFORMATION_MESSAGE);
                nameTextField.setText("");
                phoneTextField.setText("");
                passwordField.setText("");
                MainFrame.getInstance().navigateTo(HomeGUI.KEY);
                // Set text later
                // Set text later
            } else {//kalau isi yang sudah ada maka akan error
                JOptionPane.showMessageDialog(this, "Nama" + nameTextField.getText() + " dan nomor hp " + phoneTextField.getText() + " sudah ada!", "Registrasi gagal", JOptionPane.ERROR_MESSAGE);
                nameTextField.setText("");
                phoneTextField.setText("");
                passwordField.setText("");
                MainFrame.getInstance().navigateTo(HomeGUI.KEY);
            }
        }

        
        
    }
    //fungsi is numeric
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}
