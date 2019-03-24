package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Tematyka;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewSubject extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	
	private SessionFactory sessionFactory;	
	private Transaction tx;
	private Session session;

	/**
	 * Create the frame.
	 */
	public NewSubject(SessionFactory sF) {
		sessionFactory = sF;
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		setTitle("Nowa tematyka");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 244, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNazwa = new JLabel("Nazwa");
		lblNazwa.setBounds(10, 11, 70, 14);
		contentPane.add(lblNazwa);
		
		textField = new JTextField();
		textField.setBounds(90, 8, 128, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 61, 89, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(129, 61, 89, 23);
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
			Tematyka tematyka = new Tematyka(textField.getText().toUpperCase());
			session.save(tematyka);
			
			tx.commit();
			session.close();
			this.dispose();
		}
	}

}
