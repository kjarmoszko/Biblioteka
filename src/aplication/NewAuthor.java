package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Autor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewAuthor extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField nazwisko;
	private JTextField imie;
	private SessionFactory sessionFactory;
	
	Transaction tx;
	Session session;
	/**
	 * Create the frame.
	 */
	public NewAuthor(SessionFactory sF) {
		sessionFactory = sF;
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		setTitle("Dodaj autora");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 261, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(10, 11, 46, 14);
		contentPane.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 36, 74, 14);
		contentPane.add(lblNazwisko);
		
		nazwisko = new JTextField();
		nazwisko.setBounds(94, 33, 141, 20);
		contentPane.add(nazwisko);
		nazwisko.setColumns(10);
		
		imie = new JTextField();
		imie.setBounds(94, 8, 141, 20);
		contentPane.add(imie);
		imie.setColumns(10);
		
		JButton btnNewButton = new JButton("Cofnij");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 72, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dodaj");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(146, 72, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			tx.rollback();
			session.close();
			this.dispose();
		}
		else if (e.getActionCommand().equals("Dodaj")) {
			Autor autor = new Autor(imie.getText().toUpperCase(), nazwisko.getText().toUpperCase());
			session.save(autor);
			tx.commit();
			session.close();
			this.dispose();
		}
	}

}
