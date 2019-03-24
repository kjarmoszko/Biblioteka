package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Czytelnik;
import database.Egzemplarz;
import database.Pracownik;
import database.SlowoKluczowe;
import database.Wypozyczenie;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SetBorrow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField egzemplarz;
	private JTextField czytelnik;
	private JTextField pracownik;

	private SessionFactory sessionFactory;
	
	/**
	 * Create the frame.
	 */
	public SetBorrow(SessionFactory sF) {
		sessionFactory = sF;
		setTitle("Wypo\u017Cyczenie/zwrot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 265, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdEgzemplarza = new JLabel("Id egzemplarza");
		lblIdEgzemplarza.setBounds(10, 11, 110, 14);
		contentPane.add(lblIdEgzemplarza);
		
		JLabel lblIdCzytelnika = new JLabel("Id czytelnika");
		lblIdCzytelnika.setBounds(10, 36, 110, 14);
		contentPane.add(lblIdCzytelnika);
		
		egzemplarz = new JTextField();
		egzemplarz.setBounds(130, 8, 100, 20);
		contentPane.add(egzemplarz);
		egzemplarz.setColumns(10);
		
		czytelnik = new JTextField();
		czytelnik.setBounds(130, 33, 100, 20);
		contentPane.add(czytelnik);
		czytelnik.setColumns(10);
		
		JLabel lblIdPracownika = new JLabel("Id pracownika");
		lblIdPracownika.setBounds(10, 61, 110, 14);
		contentPane.add(lblIdPracownika);
		
		pracownik = new JTextField();
		pracownik.setBounds(130, 58, 100, 20);
		contentPane.add(pracownik);
		pracownik.setColumns(10);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 97, 100, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);
		
		JButton btnZatwierd = new JButton("Zatwierd\u017A");
		btnZatwierd.setBounds(130, 97, 100, 23);
		btnZatwierd.addActionListener(this);
		contentPane.add(btnZatwierd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			this.dispose();
		}
		else if (e.getActionCommand().equals("Zatwierd\u017A")) {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			String idEgz = egzemplarz.getText();
			String idCzy = czytelnik.getText();
			String idPra = pracownik.getText();
			List<Egzemplarz> egz = session.createQuery("from Egzemplarz where idEgzemplarza like "+idEgz).getResultList();
			List<Pracownik> prac = session.createQuery("from Pracownik where idPracownika like "+idPra).getResultList();
			List<Czytelnik> czyt = session.createQuery("from Czytelnik where idCzytelnika like "+idCzy).getResultList();
			
			Date date = new Date();

			Wypozyczenie wypozyczenie = new Wypozyczenie(egz.get(0),prac.get(0),czyt.get(0),date);
	        
			session.save(wypozyczenie);
			
	        tx.commit();
	        session.close();
	        this.dispose();
		}
		
	}

}
