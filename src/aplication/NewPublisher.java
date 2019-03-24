package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Adres;
import database.Autor;
import database.Wydawnictwo;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewPublisher extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField ulica;
	private JTextField nazwa;
	private JTextField panstwo;
	private JTextField kod;
	private JTextField miasto;
	
	private SessionFactory sessionFactory;	
	private Transaction tx;
	private Session session;


	/**
	 * Create the frame.
	 */
	public NewPublisher(SessionFactory sF) {
		sessionFactory = sF;
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		setTitle("Dodaj wydawnictwo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(10, 11, 46, 14);
		contentPane.add(lblNazwa);
		
		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setBounds(10, 36, 85, 14);
		contentPane.add(lblUlica);
		
		ulica = new JTextField();
		ulica.setBounds(105, 33, 86, 20);
		contentPane.add(ulica);
		ulica.setColumns(10);
		
		nazwa = new JTextField();
		nazwa.setBounds(105, 8, 273, 20);
		contentPane.add(nazwa);
		nazwa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Miasto");
		lblNewLabel.setBounds(212, 36, 70, 14);
		contentPane.add(lblNewLabel);
		
		panstwo = new JTextField();
		panstwo.setBounds(292, 58, 86, 20);
		contentPane.add(panstwo);
		panstwo.setColumns(10);
		
		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		lblKodPocztowy.setBounds(10, 61, 85, 14);
		contentPane.add(lblKodPocztowy);
		
		kod = new JTextField();
		kod.setBounds(105, 58, 86, 20);
		contentPane.add(kod);
		kod.setColumns(10);
		
		JLabel lblPastwo = new JLabel("Pa\u0144stwo");
		lblPastwo.setBounds(212, 61, 70, 14);
		contentPane.add(lblPastwo);
		
		miasto = new JTextField();
		miasto.setBounds(292, 33, 86, 20);
		contentPane.add(miasto);
		miasto.setColumns(10);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 97, 89, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(289, 97, 89, 23);
		btnDodaj.addActionListener(this);
		contentPane.add(btnDodaj);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			tx.rollback();
			session.close();
			this.dispose();
		}
		else if (e.getActionCommand().equals("Dodaj")) {
			Adres adres = new Adres(ulica.getText().toUpperCase(),miasto.getText().toUpperCase(),kod.getText().toUpperCase(),panstwo.getText().toUpperCase());
			Wydawnictwo wydawnictwo = new Wydawnictwo(nazwa.getText().toUpperCase(),adres);
			
			Set<Wydawnictwo> itemSet = new HashSet<Wydawnictwo>();
			itemSet.add(wydawnictwo);
			
			adres.setWydawnictwa(itemSet);
			
			session.save(adres);
			session.save(wydawnictwo);
			tx.commit();
			session.close();
			this.dispose();
		}
		
	}

}
