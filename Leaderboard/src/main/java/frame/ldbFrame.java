package frame;

import helper.koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

import static javax.swing.JOptionPane.*;

public class ldbFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTF;
    private JButton searchBtn;
    private JTable tableView;
    private JButton INSERTButton;
    private JButton EDITButton;
    private JButton CLEARButton;
    private JButton BACKButton;
    private JButton PRINTButton;
    private JButton CLOSEButton;

    public ldbFrame() {
        EDITButton.addActionListener(e -> {
            int selectedRow = tableView.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null,
                        "Silahkan pilih data yang akan dihapus",
                        "Validasi data",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            TableModel tbm = tableView.getModel();
            int id = Integer.parseInt(tbm.getValueAt(selectedRow,0).toString());
            inputPlayerFrame inputFrame = new inputPlayerFrame();
            inputFrame.setId(id);
            inputFrame.isiKomponen();
            inputFrame.setVisible(true);
        });
        INSERTButton.addActionListener(e -> {
            inputPlayerFrame inputFrame = new inputPlayerFrame();
            inputFrame.setVisible(true);
        });
        CLEARButton.addActionListener(e -> {
            int selectedRow = tableView.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(null,
                        "Silahkan pilih data yang akan dihapus",
                        "Validasi data",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selection = JOptionPane.showConfirmDialog(null,
                    "Are you sure?",
                    "DELETE CONFIRMATION",
                    YES_NO_OPTION);
            if (selection == 0){
                TableModel tbm = tableView.getModel();
                String name = tbm.getValueAt(selectedRow, 0).toString();
                Connection con = koneksi.getConnection();
                String deleteSQL = "DELETE FROM player WHERE name = ?";
                try {
                    PreparedStatement pst = con.prepareStatement(deleteSQL);
                    pst.setString(1, name);
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
            JOptionPane.showMessageDialog(null, String.valueOf(selection));
        });
        searchBtn.addActionListener(e -> {

            String keyword = searchTF.getText();
            if(keyword.equals("")){
                JOptionPane.showMessageDialog(null,
                        "FILL THE BLANKS", "" + "VALIDASI TEKS KOSONG",
                        WARNING_MESSAGE);
                searchTF.requestFocus();
                return;
            }
            Connection con = koneksi.getConnection();
            keyword = "%" + searchTF.getText() + "%";
            String searchSQL = "SELECT * FROM player WHERE name like ?";

            try {
                PreparedStatement pst = con.prepareStatement(searchSQL);
                pst.setString(1, keyword);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel dtm = (DefaultTableModel) tableView.getModel();
                dtm.setRowCount(0);
                Object[] row = new Object[2];

                while (rs.next()){
                    row[0] = rs.getInt(1);
                    row[1] = rs.getString(2);
                    row[2] = rs.getString(3);
                    dtm.addRow(row);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        CLOSEButton.addActionListener(e -> {
            dispose();
        });
        BACKButton.addActionListener(e -> {
            isiTable();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                isiTable();
            }
        });
        isiTable();
        init();
    }

    public void init() {
        setContentPane(mainPanel);
        setTitle("LEADERBOARD");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    //method untuk display data
    public void isiTable() {
        Connection con = koneksi.getConnection();
        String selectSQL = "SELECT * FROM player";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL);

            String[] header = {"id","name", "result"};
            DefaultTableModel dtm = new DefaultTableModel(header, 0);
            tableView.setModel(dtm);

            Object[] row = new Object[3];
            while (rs.next()) {
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                dtm.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
