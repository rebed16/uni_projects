package interfata;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class InstrumenteClient extends JFrame {

	private ResultSet rs;
    private java.sql.Statement stmt;
	private OptiuniClient optiunic;
	private FiltrareInstrumenteClient filtrareinstrumentec;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private void populateFields(ResultSet rs) throws SQLException {
        // Exemplu de setare a valorilor câmpurilor de text cu datele din ResultSet
        textField.setText(rs.getString("instrument"));
        textField_1.setText(rs.getString("tip_instrument"));
        textField_2.setText(rs.getString("brand"));
        textField_3.setText(rs.getString("material"));
        textField_4.setText(rs.getString("culoare"));
        textField_5.setText(rs.getString("pret"));
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
					InstrumenteClient frame = new InstrumenteClient();
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
	public InstrumenteClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 183, 219));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Instrument:");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 22, 114, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tip instrument:");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 52, 150, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Brand:");
		lblNewLabel_2.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(20, 84, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Material:");
		lblNewLabel_3.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 112, 95, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Culoare:");
		lblNewLabel_4.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(20, 144, 79, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Preț:");
		lblNewLabel_5.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(20, 175, 49, 14);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(136, 21, 272, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 51, 228, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(86, 83, 322, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(118, 111, 290, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(109, 143, 299, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(79, 174, 329, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(e -> {
		    try (Connection conn = ConectareBD.connect()) {
		        String query = "SELECT * FROM instrumente";
		        java.sql.Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        ResultSet rs = stmt.executeQuery(query);
		        

		        if (rs.first()) {
		            // Populează câmpurile cu datele primului instrument
		        	textField.setText(rs.getString("instrument"));
		            textField_1.setText(rs.getString("tip_instrument"));
		            textField_2.setText(rs.getString("brand"));
		            textField_3.setText(rs.getString("material"));
		            textField_4.setText(rs.getString("culoare"));
		            textField_5.setText(String.valueOf(rs.getDouble("pret")));
		            
		        } else {
		            JOptionPane.showMessageDialog(null, "Nu există instrumente în baza de date.");
		        }
		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Eroare la încărcarea instrumentului: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
		    }
		});
		btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton.setBounds(20, 209, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Last");
		btnNewButton_1.addActionListener(e -> {
		    try (Connection conn = ConectareBD.connect()) {
		        String query = "SELECT * FROM instrumente";
		        java.sql.Statement stmt = conn.createStatement(
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY
		        );
		        ResultSet rs = stmt.executeQuery(query);

		        if (rs.last()) { // Mută cursorul la ultima înregistrare
		        	textField.setText(rs.getString("instrument"));
		            textField_1.setText(rs.getString("tip_instrument"));
		            textField_2.setText(rs.getString("brand"));
		            textField_3.setText(rs.getString("material"));
		            textField_4.setText(rs.getString("culoare"));
		            textField_5.setText(String.valueOf(rs.getDouble("pret")));
		        } else {
		            JOptionPane.showMessageDialog(null, "Nu există instrumente în baza de date.");
		        }
		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Eroare la încărcarea instrumente: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
		    }
		});
		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_1.setBounds(319, 209, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Previous");
		btnNewButton_2.addActionListener(e -> {
            try {
                // Verificăm dacă ResultSet-ul este deja creat, dacă nu, îl creăm
                if (rs == null) {
                    // Creăm o conexiune și statement pentru a obține albumele
                    Connection conn = ConectareBD.connect();
                    String query = "SELECT * FROM instrumente";
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
                    JOptionPane.showMessageDialog(null, "Nu există instrument anterior.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare la navigare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
		btnNewButton_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_2.setBounds(118, 209, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Next");
		btnNewButton_3.addActionListener(e -> {
            try {
                // Verificăm dacă ResultSet-ul este deja creat, dacă nu, îl creăm
                if (rs == null) {
                    // Creăm o conexiune și statement pentru a obține albumele
                    Connection conn = ConectareBD.connect();
                    String query = "SELECT * FROM instrumente";
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
                    JOptionPane.showMessageDialog(null, "Nu există instrument următor.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Eroare la navigare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });
    
		btnNewButton_3.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_3.setBounds(217, 209, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("Filter");
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 // Dacă interfața nu a fost creată, o instanțiem
                if (filtrareinstrumentec == null) {
                	Connection conn;
                	try {
						conn = ConectareBD.connect();
						filtrareinstrumentec = new FiltrareInstrumenteClient(conn);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                }
                filtrareinstrumentec.setVisible(true); // Afișăm interfața
                dispose();
			}
		});
		btnNewButton_6.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_6.setBounds(66, 243, 89, 23);
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
		btnNewButton_7.setBounds(269, 243, 89, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Clear");
		btnNewButton_8.addActionListener(e -> {
			textField.setText("");
	        textField_1.setText("");
	        textField_2.setText("");
	        textField_3.setText("");
	        textField_4.setText("");
	        textField_5.setText("");
		   
		});
		btnNewButton_8.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnNewButton_8.setBounds(165, 243, 89, 23);
		contentPane.add(btnNewButton_8);
	}
}
