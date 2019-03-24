package aplication;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminWindow {
	
	SessionFactory sessionFactory = null;
	//Session session;
	//Transaction tx;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		sessionFactory = HibernateUtil.getSessionFactory();
		//session = sessionFactory.getCurrentSession();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 365, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnWypoycz = new JButton("Wypo\u017Cyczenie");
		btnWypoycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SetBorrow frame = new SetBorrow(sessionFactory);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnWypoycz.setBounds(10, 193, 160, 23);
		frame.getContentPane().add(btnWypoycz);
		
		JButton btnDodajCzytelnika = new JButton("Dodaj czytelnika");
		btnDodajCzytelnika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewUser frame = new NewUser(sessionFactory);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnDodajCzytelnika.setBounds(10, 76, 160, 23);
		frame.getContentPane().add(btnDodajCzytelnika);
		
		JButton btnDodajPracownika = new JButton("Dodaj pracownika");
		btnDodajPracownika.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewEmployee frame = new NewEmployee(sessionFactory);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnDodajPracownika.setBounds(180, 76, 160, 23);
		frame.getContentPane().add(btnDodajPracownika);
		
		JButton btnWyszukaj = new JButton("Wyszukaj");
		btnWyszukaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchBook frame = new SearchBook(sessionFactory, false);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnWyszukaj.setBounds(10, 11, 330, 23);
		frame.getContentPane().add(btnWyszukaj);
		
		JButton btnEdytujBibliotek = new JButton("Dodaj zas\u00F3b");
		btnEdytujBibliotek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditBookstore frame = new EditBookstore(sessionFactory);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnEdytujBibliotek.setBounds(10, 130, 160, 23);
		frame.getContentPane().add(btnEdytujBibliotek);
		
		JButton btnEdytujUytkownikw = new JButton("Edycja u\u017Cytkownik\u00F3w");
		btnEdytujUytkownikw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchUsers frame = new SearchUsers(sessionFactory);
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnEdytujUytkownikw.setBounds(180, 130, 160, 23);
		frame.getContentPane().add(btnEdytujUytkownikw);
		
		JButton btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sessionFactory.close();
				System.exit(0);
			}
		});
		btnWyloguj.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnWyloguj);
		
		JButton btnNewButton = new JButton("Zwrot");
		btnNewButton.setBounds(180, 193, 160, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
