package interfata;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bazaDate.ConectareBD;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FiltrareAlbume extends JFrame {

    private AlbumeManager albume;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private Connection connection;
    private JTextArea resultArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Asigură-te că inițializezi o conexiune validă
                Connection connection = ConectareBD.connect();
                FiltrareAlbume frame = new FiltrareAlbume(connection);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public FiltrareAlbume(Connection connection) {
        this.connection = connection;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 380);
        setResizable(false); // Dezactivăm redimensionarea ferestrei
        setLocationRelativeTo(null); // Centram fereastra pe ecran

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 183, 219));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        resultArea = new JTextArea(10, 30); // 10 rânduri, 30 coloane
        resultArea.setEditable(false); // Facem JTextArea doar pentru afișare
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(10, 21, 416, 137);
        contentPane.add(scrollPane);

        JLabel lblNewLabel = new JLabel("Introduceți criteriul de filtrare:");
        lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblNewLabel.setBounds(51, 169, 350, 14);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(90, 194, 254, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Alegeți criteriul de filtrare:");
        lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(67, 235, 306, 14);
        contentPane.add(lblNewLabel_1);

        JButton btnNewButton = new JButton("Artist");
        btnNewButton.addActionListener(e -> {
            String keyword = textField.getText();
            if (!keyword.isEmpty()) {
                String query = "SELECT * FROM albume WHERE nume_artist LIKE ?";
                displayResults(query, keyword);
            } else {
                JOptionPane.showMessageDialog(null, "Introduceți un cuvânt cheie pentru artist!");
            }
        });
        btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnNewButton.setBounds(10, 273, 125, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Gen");
        btnNewButton_1.addActionListener(e -> {
            String keyword = textField.getText();
            if (!keyword.isEmpty()) {
                String query = "SELECT * FROM albume WHERE gen LIKE ?";
                displayResults(query, keyword);
            } else {
                JOptionPane.showMessageDialog(null, "Introduceți un cuvânt cheie pentru gen!");
            }
        });

        btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnNewButton_1.setBounds(155, 273, 125, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Format");
        btnNewButton_2.addActionListener(e -> {
            String keyword = textField.getText();
            if (!keyword.isEmpty()) {
                String query = "SELECT * FROM albume WHERE format LIKE ?";
                displayResults(query, keyword);
            } else {
                JOptionPane.showMessageDialog(null, "Introduceți un cuvânt cheie pentru format!");
            }
        });
        btnNewButton_2.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnNewButton_2.setBounds(301, 273, 125, 23);
        contentPane.add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Go back");
        btnNewButton_3.addActionListener(e -> {
            if (albume == null) {
                albume = new AlbumeManager();
            }
            albume.setVisible(true); // Afișăm interfața Albume
            dispose(); // Închidem fereastra curentă
        });
        btnNewButton_3.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnNewButton_3.setBounds(90, 307, 104, 23);
        contentPane.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("Clear");
        btnNewButton_4.setFont(new Font("Monospaced", Font.PLAIN, 16));
        btnNewButton_4.addActionListener(e -> resultArea.setText("")); // Golește JTextArea
        btnNewButton_4.setBounds(240, 307, 104, 23);
        contentPane.add(btnNewButton_4);
    }

    private void displayResults(String query, String keyword) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            // Construim rezultatele
            StringBuilder results = new StringBuilder();
            while (resultSet.next()) {
                results.append("Album: ").append(resultSet.getString("nume_album"))
                        .append(", Artist: ").append(resultSet.getString("nume_artist"))
                        .append(", Gen: ").append(resultSet.getString("gen"))
                        .append(", Format: ").append(resultSet.getString("format"))
                        .append(", Preț: ").append(resultSet.getDouble("pret"))
                        .append("\n");
            }

            // Afișăm rezultatele în JTextArea
            resultArea.setText(results.length() > 0 ? results.toString() : "Nu s-au găsit rezultate.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Eroare la preluarea datelor!");
        }
    }
}
