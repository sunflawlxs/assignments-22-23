package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        GridBagConstraints constraints = new GridBagConstraints();

        setLayout((new GridBagLayout()));
        constraints.gridx = 0;
        constraints.gridy =0;
        constraints.anchor = GridBagConstraints.WEST;

        paketLabel =  new JLabel("Paket Laundry: ");
        add(paketLabel, constraints);

        constraints.anchor = GridBagConstraints.EAST;
        String[] namaPaket = {"Reguler", "Fast", "Express"};
        paketComboBox = new JComboBox<String>(namaPaket);
        constraints.gridx = 1;
        add(paketComboBox, constraints);

        showPaketButton = new JButton("Show Paket");
        constraints.gridx =2 ;
        add(showPaketButton, constraints);

        // Pembuatan Label Berat dan Peletakkannya
        constraints.anchor = GridBagConstraints.WEST;
        beratLabel = new JLabel("Berat Cucian (Kg):");
        constraints.gridy =3;
        constraints.gridx = 0;
        add(beratLabel, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.EAST;
        beratTextField = new JTextField();
        constraints.gridx = 1;
        add(beratTextField, constraints);

        // Pembuatan CheckBox Setrika dan Peletakkannya
        constraints.anchor = GridBagConstraints.WEST;
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        constraints.gridx = 0;
        constraints.gridy =4;
        add(setrikaCheckBox, constraints);

        // Pembuatan CheckBox Antar dan Peletakkannya
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama, kemudian 500/kg)");
        constraints.gridy  =5;
        add(antarCheckBox, constraints);

        // Pembuatan Button createNota dan Peletakkannya
        createNotaButton = new JButton("Buat Nota");
        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.gridwidth = 4;
        constraints.gridy =6;
        add(createNotaButton, constraints);

        // Pembuatan Button back dan peletakkannya
        backButton = new JButton("Kembali");
        constraints.gridy =7;
        add(backButton, constraints);

        showPaketButton.addActionListener(e -> showPaket());
        backButton.addActionListener(e -> handleBack());
        createNotaButton.addActionListener(e -> createNota());

    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;
        //gatau ini masi sama kynya
        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        int berat = 0;

        if (beratTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Input harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
        } else {
            try {
                berat = Integer.parseInt(beratTextField.getText());

                if (berat < 1) {
                    JOptionPane.showMessageDialog(null, "Hanya menerima input positif", "Error", JOptionPane.ERROR_MESSAGE);
                    beratTextField.setText("");
                } else {

                    if (berat == 1){
                        JOptionPane.showMessageDialog(null, "Jika berat kurang dari 2kg, maka akan dianggap 2 kg", "Information", JOptionPane.INFORMATION_MESSAGE);
                        berat = 2;
                    }
    
                    String selectedPaket = (String) paketComboBox.getSelectedItem();
                    String formattedTime = fmt.format(cal.getTime());
                        
                    // membuat object nota baru
                    Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, selectedPaket, formattedTime);
            
                    // apabila memilih setrika
                    if (setrikaCheckBox.isSelected()) {
                        nota.addService(new SetrikaService());
                    } 
            
                    // apabila memilih antar
                    if (antarCheckBox.isSelected()) {
                        nota.addService(new AntarService());
                    }
            
                    NotaManager.addNota(nota);
                    Member member = memberSystemGUI.getLoggedInMember();
                    member.addNota(nota);
            
                    JOptionPane.showMessageDialog(this, "Nota berhasil dibuat!");
                }
                
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Input harus berisi angka", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

        reset();

    }
    

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        reset();
        MainFrame.getInstance().navigateTo(MemberSystemGUI.KEY);
    }

    private void reset() {
        paketComboBox.setSelectedItem("Express");
        beratTextField.setText("");
        antarCheckBox.setSelected(false);
        setrikaCheckBox.setSelected(false);
    }
}    
