package interfata;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bazaDate.ConectareBD;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FiltrareInstrumenteClient extends JFrame {

    private InstrumenteClient instrumentec;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea resultArea;
    private Connection connection;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Asigură-te că inițializezi o conexiune validă
                Connection connection = ConectareBD.connect();
                FiltrareInstrumenteClient frame = new FiltrareInstrumenteClient(connection);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public FiltrareInstrumenteClient(Connection connection) {
        this.connection = connection;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 183, 219));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(10, 21, 416, 137);
        contentPane.add(scrollPane);

        JLabel lblNewLabel = new JLabel("Introduceți criteriul de filtrare:");
        lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblNewLabel.setBounds(48, 169, 346, 14);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(107, 194, 217, 23);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Alege criteriul de filtrare:");
        lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(80, 228, 284, 14);
        contentPane.add(lblNewLabel_1);

        JButton btnInstrument = new JButton("Instrument");
        btnInstrument.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnInstrument.setBounds(10, 253, 137, 23);
        btnInstrument.addActionListener(e -> filterData("instrument", textField.getText()));
        contentPane.add(btnInstrument);

        JButton btnTip = new JButton("Tip");
        btnTip.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnTip.setBounds(177, 253, 89, 23);
        btnTip.addActionListener(e -> filterData("tip_instrument", textField.getText()));
        contentPane.add(btnTip);

        JButton btnBrand = new JButton("Brand");
        btnBrand.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnBrand.setBounds(289, 253, 137, 23);
        btnBrand.addActionListener(e -> filterData("brand", textField.getText()));
        contentPane.add(btnBrand);

        JButton btnMaterial = new JButton("Material");
        btnMaterial.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnMaterial.setBounds(289, 287, 137, 23);
        btnMaterial.addActionListener(e -> filterData("material", textField.getText()));
        contentPane.add(btnMaterial);

        JButton btnCuloare = new JButton("Culoare");
        btnCuloare.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnCuloare.setBounds(10, 287, 137, 23);
        btnCuloare.addActionListener(e -> filterData("culoare", textField.getText()));
        contentPane.add(btnCuloare);

        JButton btnPret = new JButton("Preț");
        btnPret.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnPret.setBounds(177, 287, 89, 23);
        btnPret.addActionListener(e -> filterData("pret", textField.getText()));
        contentPane.add(btnPret);

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnClear.setBounds(239, 321, 105, 23);
        btnClear.addActionListener(e -> resultArea.setText(""));
        contentPane.add(btnClear);

        JButton btnGoBack = new JButton("Go back");
        btnGoBack.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnGoBack.setBounds(107, 321, 105, 23);
        btnGoBack.addActionListener(e -> {
            if (instrumentec == null) {
                instrumentec = new InstrumenteClient();
            }
            instrumentec.setVisible(true);
            dispose();
        });
        contentPane.add(btnGoBack);
    }

    /**
     * Filter data based on the column and keyword.
     */
    private void filterData(String column, String keyword) {
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduceți un cuvânt cheie pentru " + column + "!");
            return;
        }

        String query = "SELECT * FROM instrumente WHERE " + column + " LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            StringBuilder results = new StringBuilder();
            while (resultSet.next()) {
                results.append("Instrument: ").append(resultSet.getString("instrument"))
                        .append(", Tip: ").append(resultSet.getString("tip_instrument"))
                        .append(", Brand: ").append(resultSet.getString("brand"))
                        .append(", Material: ").append(resultSet.getString("material"))
                        .append(", Culoare: ").append(resultSet.getString("culoare"))
                        .append(", Preț: ").append(resultSet.getDouble("pret"))
                        .append("\n");
            }

            resultArea.setText(results.length() > 0 ? results.toString() : "Nu s-au găsit rezultate.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la preluarea datelor!");
        }
    }
}
