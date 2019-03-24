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

import database.Autor;
import database.SlowoKluczowe;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewKeyWord extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField slowo;

	private SessionFactory sessionFactory;
	
	/**
	 * Create the frame.
	 */
	public NewKeyWord(SessionFactory sF) {
		sessionFactory = sF;
		setTitle("Dodaj s\u0142owo kluczowe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSowoKluczowe = new JLabel("S\u0142owo kluczowe");
		lblSowoKluczowe.setBounds(10, 11, 100, 14);
		contentPane.add(lblSowoKluczowe);
		
		slowo = new JTextField();
		slowo.setBounds(120, 11, 150, 20);
		contentPane.add(slowo);
		slowo.setColumns(10);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 47, 89, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(181, 47, 89, 23);
		btnDodaj.addActionListener(this);
		contentPane.add(btnDodaj);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			//tx.rollback();
			//session.close();
			this.dispose();
		}
		else if (e.getActionCommand().equals("Dodaj")) {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			SlowoKluczowe slowoKluczowe = new SlowoKluczowe(slowo.getText().toUpperCase());
			session.save(slowoKluczowe);
			tx.commit();
			session.close();
			this.dispose();
		}
		
	}

}
