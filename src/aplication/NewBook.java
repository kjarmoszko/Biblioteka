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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Autor;
import database.Ksiazka;
import database.Tematyka;
import database.Wydawnictwo;

import java.awt.Color;
import javax.swing.JButton;

public class NewBook extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tytul;
	private JTextField opis;
	private JTextField isbn;

	private SessionFactory sessionFactory;	
	//private Transaction tx;
	//private Session session;
	
	private SetAuthorFrame setAuthorFrame;
	private SetPublisherFrame setPublisherFrame;
	private SetSubjectFrame setSubjectFrame;

	/**
	 * Create the frame.
	 */
	public NewBook(SessionFactory sF) {
		sessionFactory = sF;
		setAuthorFrame = new SetAuthorFrame(sF);
		setPublisherFrame = new SetPublisherFrame(sF);
		setSubjectFrame = new SetSubjectFrame(sF);
		//session = sessionFactory.getCurrentSession();
		//tx = session.beginTransaction();
		setTitle("Dodaj ksi\u0105\u017Ck\u0119");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 584, 70);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTytu = new JLabel("Tytu\u0142");
		lblTytu.setBounds(10, 11, 46, 14);
		panel.add(lblTytu);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(10, 36, 46, 14);
		panel.add(lblOpis);
		
		tytul = new JTextField();
		tytul.setBounds(66, 8, 170, 20);
		panel.add(tytul);
		tytul.setColumns(10);
		
		opis = new JTextField();
		opis.setBounds(66, 33, 508, 20);
		panel.add(opis);
		opis.setColumns(10);
		
		isbn = new JTextField();
		isbn.setBounds(404, 8, 170, 20);
		panel.add(isbn);
		isbn.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(348, 11, 46, 14);
		panel.add(lblIsbn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 69, 584, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnDodajAutorw = new JButton("Autorzy");
		btnDodajAutorw.setBounds(10, 11, 180, 23);
		btnDodajAutorw.addActionListener(this);
		panel_1.add(btnDodajAutorw);
		
		JButton btnDodajWydawnictwo = new JButton("Wydawnictwo");
		btnDodajWydawnictwo.setBounds(200, 11, 180, 23);
		btnDodajWydawnictwo.addActionListener(this);
		panel_1.add(btnDodajWydawnictwo);
		
		JButton btnTematyka = new JButton("Tematyka");
		btnTematyka.setBounds(390, 11, 184, 23);
		btnTematyka.addActionListener(this);
		panel_1.add(btnTematyka);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 124, 584, 47);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 13, 89, 23);
		btnCofnij.addActionListener(this);
		panel_2.add(btnCofnij);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(485, 13, 89, 23);
		btnDodaj.addActionListener(this);
		panel_2.add(btnDodaj);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			//tx.rollback();
			//session.close();
			setAuthorFrame.dispose();
			this.dispose();
		}
		else if (e.getActionCommand().equals("Autorzy")) {
			//tx.rollback();
			//session.close();
			
			//SetAuthorFrame frame = new SetAuthorFrame(sessionFactory);
			//frame.setVisible(true);
			//setAuthorFrame.saveState();
			setAuthorFrame.setVisible(true);
		}
		else if (e.getActionCommand().equals("Wydawnictwo")) {
			setPublisherFrame.setVisible(true);
		}
		else if (e.getActionCommand().equals("Tematyka")) {
			setSubjectFrame.setVisible(true);
		}
		else if (e.getActionCommand().equals("Dodaj")) {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			Wydawnictwo wydawnictwo = (Wydawnictwo)setPublisherFrame.getPublishers()[0];
			Ksiazka ksiazka = new Ksiazka(Integer.parseInt(isbn.getText()), tytul.getText().toUpperCase(), wydawnictwo);
			if(!opis.getText().equals(""))
				ksiazka.setOpis(opis.getText());
			Set<Tematyka> tematykaSet = new HashSet<Tematyka>();
			Set<Autor> autorSet = new HashSet<Autor>();
			for(Object t: setSubjectFrame.getSubjects()){
				tematykaSet.add((Tematyka)t);
			}
			for(Object a: setAuthorFrame.getAuthors()) {
				autorSet.add((Autor)a);
			}
			
			ksiazka.setAutorzy(autorSet);
			ksiazka.setTematyki(tematykaSet);
			//wydawnictwo.addKsiazka(ksiazka);
			
			session.save(ksiazka);
			tx.commit();
			session.close();
			this.dispose();
		}
	}

}
