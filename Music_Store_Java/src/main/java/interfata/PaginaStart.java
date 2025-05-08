package interfata;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Client;

import magazinMuzical.Manager;
import magazinMuzical.ClientMagazin;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class PaginaStart extends JFrame {

	private OptiuniManager optiuni;
	private OptiuniClient optiunic;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaginaStart frame = new PaginaStart();
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
	public PaginaStart() {
		setTitle("GESTIONARE MAGAZIN MUZICA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 183, 219));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrAlegeiCeDorii = new JTextArea();
		txtrAlegeiCeDorii.setEditable(false);
		txtrAlegeiCeDorii.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrAlegeiCeDorii.setBackground(new Color(255, 183, 219));
		txtrAlegeiCeDorii.setText("Introduceți username-ul și parola,\r\napoi faceți clic pe butonul potrivit \r\nrolului dvs. pentru a vă autentifica");
		txtrAlegeiCeDorii.setBounds(32, 74, 374, 65);
		contentPane.add(txtrAlegeiCeDorii);
		
		JTextArea txtrBineAiVenit = new JTextArea();
		txtrBineAiVenit.setEditable(false);
		txtrBineAiVenit.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrBineAiVenit.setBackground(new Color(255, 183, 219));
		txtrBineAiVenit.setText("Bine ați venit la magazinul nostru\r\n              de muzică!");
		txtrBineAiVenit.setBounds(42, 22, 347, 54);
		txtrBineAiVenit.setEditable(false);
		contentPane.add(txtrBineAiVenit);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel.setBounds(42, 163, 101, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Parola:");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(42, 202, 84, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(161, 162, 228, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Manager log-in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
                String parola = new String(passwordField.getPassword());

                // Verifică autentificarea
                if (Manager.autentificare(username, parola)) {
                	if (optiuni == null) {
                        optiuni = new OptiuniManager();
                    }
                    optiuni.setVisible(true); 
                    dispose(); // Închide fereastra de log in
                } else {
                	JOptionPane.showMessageDialog(PaginaStart.this, "Username sau parolă incorectă!", "Eroare", JOptionPane.ERROR_MESSAGE);

                }
			}
		});
		btnNewButton.setBounds(64, 229, 121, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 201, 243, 20);
		contentPane.add(passwordField);
		
		JButton btnClientLogin = new JButton("Client log-in");
		btnClientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
                String parola = new String(passwordField.getPassword());

                if (ClientMagazin.autentific(username, parola)) {
                	if (optiunic == null) {
                        optiunic = new OptiuniClient();
                    }
                    optiunic.setVisible(true); 
                    dispose(); // Inchide fereastra de log in
                } else {
                	JOptionPane.showMessageDialog(PaginaStart.this, "Username sau parolă incorectă!", "Eroare", JOptionPane.ERROR_MESSAGE);

                }
			}
		});
		btnClientLogin.setBounds(238, 229, 121, 23);
		contentPane.add(btnClientLogin);
	}
}
