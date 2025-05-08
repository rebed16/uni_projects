package interfata;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OptiuniClient extends JFrame {

	private AlbumeClient albumec;
	private InstrumenteClient instrumentec;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptiuniClient frame = new OptiuniClient();
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
	public OptiuniClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 183, 219));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrPentruAContinua = new JTextArea();
		txtrPentruAContinua.setEditable(false);
		txtrPentruAContinua.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtrPentruAContinua.setBackground(new Color(255, 183, 219));
		txtrPentruAContinua.setText("Pentru a continua alege una din cele \r\n           două categorii");
		txtrPentruAContinua.setBounds(35, 34, 364, 48);
		contentPane.add(txtrPentruAContinua);
		
		JButton btnNewButton = new JButton("Instrumente muzicale");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Dacă interfața nu a fost creată, o instanțiem
                if (instrumentec == null) {
                    instrumentec = new InstrumenteClient();
                }
                instrumentec.setVisible(true); // Afișăm interfața
                dispose();
			}
		});
		btnNewButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnNewButton.setBounds(103, 122, 227, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Albume muzicale");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		          
		                // Dacă interfața nu a fost creată, o instanțiem
		                if (albumec == null) {
		                    albumec = new AlbumeClient();
		                }
		                albumec.setVisible(true); // Afișăm interfața
		                dispose();
		            }
		        });
			
		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnNewButton_1.setBounds(103, 187, 227, 42);
		contentPane.add(btnNewButton_1);
	}
}
