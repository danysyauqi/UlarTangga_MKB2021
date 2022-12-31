package frame;

import helper.koneksi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class inputPlayerFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField nameTF;
    private JTextField resultTF;
    private JButton SAVEButton;
    private JButton BACKButton;
    private JTextField idTF;

    int id;

    public void setId(int id) {
        this.id = id;
    }

    public inputPlayerFrame() {
        SAVEButton.addActionListener(e -> {

            String name = nameTF.getText();
            if(name.equals("")){
                JOptionPane.showMessageDialog(null,
                        "FILL THE BLANKS", "" + "VALIDASI TEKS KOSONG",
                        WARNING_MESSAGE);
                nameTF.requestFocus();
                return;
            }

            Connection con = koneksi.getConnection();
            PreparedStatement pst;

            try {
                if (id == 0) {
                    String cekDoubleSQL = "SELECT * FROM player WHERE name = ?";
                    pst = con.prepareStatement(cekDoubleSQL);
                    pst.setString(1, name);
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "NAME ALREADY EXIST",
                                "VALIDASI DATA YANG SAMA",
                                WARNING_MESSAGE);
                    } else {
                        String insertSQL = "INSERT INTO player SET name = ?";
                        pst = con.prepareStatement(insertSQL);
                        pst.setString(1, name);
                        pst.executeUpdate();
                        dispose();
                    }
                } else {
                    String updateSQL = "UPDATE player SET name = ? WHERE id = ?";
                    pst = con.prepareStatement(updateSQL);
                    pst.setString(1, name);
                    pst.setInt(2, id);
                    pst.executeUpdate();
                    dispose();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
                }
            });
            BACKButton.addActionListener(e -> {
                dispose();
            });
            init();
        }
        public void init () {
            setContentPane(mainPanel);
            setTitle("INPUT PLAYER DATA");
            pack();
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

        }

        public void isiKomponen(){
            Connection con = koneksi.getConnection();
            String findSQL = "SELECT * FROM player WHERE id = ?";
            PreparedStatement pst;

            try {
                pst = con.prepareStatement(findSQL);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    idTF.setText(String.valueOf(rs.getInt("id")));
                    nameTF.setText(rs.getString("name"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
