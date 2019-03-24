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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Autor;
import database.Egzemplarz;
import database.Ksiazka;
import database.SlowoKluczowe;
import database.Tematyka;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewBookCopy extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField rok;
	private JTextField ilosc;
	
	private SessionFactory sessionFactory;
	
	private Ksiazka ksiazka;
	private SetKeyWordFrame setKeyWordFrame;


	/**
	 * Create the frame.
	 */
	public NewBookCopy(SessionFactory sF, Ksiazka ksiazka) {
		sessionFactory = sF;
		this.ksiazka = ksiazka;
		setKeyWordFrame = new SetKeyWordFrame(sF);
		setTitle("Dodaj egzemplarze ksi\u0105\u017Cki");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTyru = new JLabel("Tytu\u0142");
		lblTyru.setBounds(10, 11, 90, 14);
		contentPane.add(lblTyru);
		
		JLabel tytul = new JLabel("New label");
		tytul.setBounds(140, 11, 284, 14);
		tytul.setText(ksiazka.getTytul());
		contentPane.add(tytul);
		
		JLabel lblNewLabel = new JLabel("Autorzy");
		lblNewLabel.setBounds(10, 36, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel autorzy = new JLabel("New label");
		autorzy.setBounds(140, 36, 284, 14);
		autorzy.setText(ksiazka.getAutorzy().toString());
		contentPane.add(autorzy);
		
		JLabel lblWydawnictwo = new JLabel("Wydawnictwo");
		lblWydawnictwo.setBounds(10, 61, 90, 14);
		contentPane.add(lblWydawnictwo);
		
		JLabel wydawnictwo = new JLabel("New label");
		wydawnictwo.setBounds(140, 61, 284, 14);
		wydawnictwo.setText(ksiazka.getIdWydawnictwa().toString());
		contentPane.add(wydawnictwo);
		
		JLabel lblTematyki = new JLabel("Tematyka");
		lblTematyki.setBounds(10, 86, 90, 14);
		contentPane.add(lblTematyki);
		
		JLabel tematyka = new JLabel("New label");
		tematyka.setBounds(140, 86, 284, 14);
		tematyka.setText(ksiazka.getTematyki().toString());
		contentPane.add(tematyka);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setBounds(10, 111, 90, 14);
		contentPane.add(lblOpis);
		
		JLabel opis = new JLabel("New label");
		opis.setBounds(140, 111, 284, 14);
		if(ksiazka.getOpis() != null)
			opis.setText(ksiazka.getOpis());
		else
			opis.setText("");
		contentPane.add(opis);
		
		JLabel lblRokWydania = new JLabel("Rok wydania");
		lblRokWydania.setBounds(10, 136, 90, 14);
		contentPane.add(lblRokWydania);
		
		rok = new JTextField();
		rok.setBounds(140, 133, 110, 20);
		contentPane.add(rok);
		rok.setColumns(10);
		
		JLabel lblIloEgzemplarzy = new JLabel("Ilo\u015B\u0107 egzemplarzy");
		lblIloEgzemplarzy.setBounds(10, 161, 120, 14);
		contentPane.add(lblIloEgzemplarzy);
		
		ilosc = new JTextField();
		ilosc.setBounds(140, 158, 110, 20);
		contentPane.add(ilosc);
		ilosc.setColumns(10);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 227, 89, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(335, 227, 89, 23);
		btnDodaj.addActionListener(this);
		contentPane.add(btnDodaj);
		
		JButton btnDodajSlowaKluczowe = new JButton("Dodaj slowa kluczowe");
		btnDodajSlowaKluczowe.addActionListener(this);
		btnDodajSlowaKluczowe.setBounds(10, 193, 414, 23);
		contentPane.add(btnDodajSlowaKluczowe);
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
			Egzemplarz egz;
			Set<SlowoKluczowe> slowoSet = new HashSet<SlowoKluczowe>();
			for(Object s: setKeyWordFrame.getKeyWords()){
				slowoSet.add((SlowoKluczowe)s);
			}
			
			for(int i = 0; i < Integer.parseInt(ilosc.getText()); i++) {
				egz = new Egzemplarz(ksiazka, Integer.parseInt(rok.getText()));
				egz.setSlowaKluczowe(slowoSet);
				session.save(egz);
			}
			tx.commit();
			session.close();
			this.dispose();
		}
		else if (e.getActionCommand().equals("Dodaj slowa kluczowe")) {
			setKeyWordFrame.setVisible(true);
		}
		
	}

}
