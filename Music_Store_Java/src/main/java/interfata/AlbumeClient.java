package interfata;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Statement;

import bazaDate.ConectareBD;

import java.awt.Color;
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

public class AlbumeClient extends JFrame {

	private ResultSet rs;
    private java.sql.Statement stmt;
	private OptiuniClient optiunic;
	private FiltrareAlbumeClient filtrarealbumec;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHj;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private void populateFields(ResultSet rs) throws SQLException {
        // Exemplu de setare a valorilor câmpurilor de text cu datele din ResultSet
        txtHj.setText(rs.getString("nume_album"));
        textField.setText(rs.getString("nume_artist"));
        textField_1.setText(rs.getString("gen"));
        textField_2.setText(rs.getString("format"));
        textField_3.setText(rs.getString("pret"));
    }

    // Cod pentru închidere manuală a resurselor
    public void closeResources() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlbumeClient frame = new AlbumeClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlbumeClient() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 183, 219));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nume album:");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel.setBounds(21, 21, 118, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nume artist:");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(21, 55, 118, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gen muzical:");
		lblNewLabel_2.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(21, 88, 123, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Format album:");
		lblNewLabel_3.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(21, 122, 133, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Preț:");
		lblNewLabel_4.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(21, 156, 49, 14);
		contentPane.add(lblNewLabel_4);
		
		txtHj = new JTextField();
		txtHj.setToolTipText("");
		txtHj.setBounds(141, 20, 269, 20);
		contentPane.add(txtHj);
		txtHj.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(151, 54, 259, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(154, 87, 256, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(164, 121, 246, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(79, 155, 331, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(e -> {
		    try (Connection conn = ConectareBD.connect()) {
		        String query = "SELECT * FROM albume";
		        java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        ResultSet rs = stmt.executeQuery(query);
		        

		        if (rs.first()) {
		            // Populează câmpurile cu datele primului album
		            txtHj.setText(rs.getString("nume_album"));
		            textField.setText(rs.getString("nume_artist"));
		            textField_1.setText(rs.getString("gen"));
		            textField_2.setText(rs.getString("format"));
		            textField_3.setText(String.valueOf(rs.getDouble("pret")));
		        } else {
		            JOptionPane.showMessageDialog(null, "Nu există albume în baza de date.");
		        }
		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Eroare la încărcarea albumului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
		    }
		});


		btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton.setBounds(21, 195, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Last");
		btnNewButton_1.addActionListener(e -> {
		    try (Connection conn = ConectareBD.connect()) {
		        String query = "SELECT * FROM albume";
		        java.sql.Statement stmt = conn.createStatement(
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY
		        );
		        ResultSet rs = stmt.executeQuery(query);

		        if (rs.last()) { // Mută cursorul la ultima înregistrare
		            txtHj.setText(rs.getString("nume_album"));
		            textField.setText(rs.getString("nume_artist"));
		            textField_1.setText(rs.getString("gen"));
		            textField_2.setText(rs.getString("format"));
		            textField_3.setText(String.valueOf(rs.getDouble("pret")));
		        } else {
		            JOptionPane.showMessageDialog(null, "Nu există albume în baza de date.");
		        }
		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Eroare la încărcarea albumului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
		    }
		});

		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_1.setBounds(321, 195, 89, 23);
		contentPane.add(btnNewButton_1);
		
		

		JButton btnNewButton_2 = new JButton("Previous");
		btnNewButton_2.addActionListener(e -> {
            try {
                // Verificăm dacă ResultSet-ul este deja creat, dacă nu, îl creăm
                if (rs == null) {
                    // Creăm o conexiune și statement pentru a obține albumele
                    Connection conn = ConectareBD.connect();
                    String query = "SELECT * FROM albume";
                    stmt = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Permite navigarea
                            ResultSet.CONCUR_READ_ONLY
                    );
                    rs = stmt.executeQuery(query); // Obținem ResultSet doar o dată
                }

                // Navigăm înapoi (previous)
                if (rs.previous()) { // Verificăm dacă există o înregistrare anterioară
                    populateFields(rs); // Populăm câmpurile cu datele din ResultSet
                } else {
                    JOptionPane.showMessageDialog(null, "Nu există album anterior.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare la navigare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });


		btnNewButton_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_2.setBounds(120, 195, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Next");
		
		btnNewButton_3.addActionListener(e -> {
            try {
                // Verificăm dacă ResultSet-ul este deja creat, dacă nu, îl creăm
                if (rs == null) {
                    // Creăm o conexiune și statement pentru a obține albumele
                    Connection conn = ConectareBD.connect();
                    String query = "SELECT * FROM albume";
                    stmt = conn.createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Permite navigarea
                            ResultSet.CONCUR_READ_ONLY
                    );
                    rs = stmt.executeQuery(query); // Obținem ResultSet doar o dată
                }

                // Navigăm înainte (next)
                if (rs.next()) { // Verificăm dacă există o înregistrare următoare
                    populateFields(rs); // Populăm câmpurile cu datele din ResultSet
                } else {
                    JOptionPane.showMessageDialog(null, "Nu există album următor.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare la navigare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
    

		btnNewButton_3.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_3.setBounds(222, 195, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("Filter");
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 // Dacă interfața nu a fost creată, o instanțiem
                if (filtrarealbumec == null) {
                	Connection conn;
					try {
						conn = ConectareBD.connect();
						filtrarealbumec = new FiltrareAlbumeClient(conn);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                }
                filtrarealbumec.setVisible(true); // Afișăm interfața
                dispose();
			}
		});
		btnNewButton_6.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_6.setBounds(79, 229, 89, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Go back");
		btnNewButton_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 // Dacă interfața nu a fost creată, o instanțiem
                if (optiunic == null) {
                    optiunic = new OptiuniClient();
                }
                optiunic.setVisible(true); // Afișăm interfața
                dispose();
			}
		});
		btnNewButton_7.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_7.setBounds(277, 229, 89, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Clear");
		btnNewButton_8.addActionListener(e -> {
		    txtHj.setText("");
		    textField.setText("");
		    textField_1.setText("");
		    textField_2.setText("");
		    textField_3.setText("");
		});

		btnNewButton_8.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_8.setBounds(178, 229, 89, 23);
		contentPane.add(btnNewButton_8);
	}
}
