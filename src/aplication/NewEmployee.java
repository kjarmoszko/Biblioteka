package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import database.Adres;
import database.Czytelnik;
import database.Pracownik;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;

public class NewEmployee extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField imie;
	private JTextField nazwisko;
	private JTextField pesel;
	private JTextField telefon;
	private JTextField email;
	private JTextField kodZameldowania;
	private JTextField miastoZameldowania;
	private JTextField ulicaZameldowania;
	private JTextField panstwoZameldowania;
	private JTextField kodKorespondencyjny;
	private JTextField miastoKorespondencyjne;
	private JTextField ulicaKorespondencyjna;
	private JTextField panstwoKorespondencyjne;

	// SessionFactory sessionFactory;

	Transaction tx;
	Session session;
	private JTextField stanowisko;
	private int idPrac;
	private int idZaml;
	private int idKores;

	/**
	 * Create the frame.
	 */
	public NewEmployee(SessionFactory sF) {
		this(sF, null);
		setTitle("Dodaj pracownika");
	}

	public NewEmployee(SessionFactory sF, Pracownik pracownik) {
		idPrac = idZaml = idKores = 0;
		SessionFactory sessionFactory = sF;
		// sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();

		setTitle("Edytuj pracownika");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 434, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 418, 119);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblDaneObowizkowe = new JLabel("Dane obowi\u0105zkowe:");
		lblDaneObowizkowe.setBounds(10, 0, 186, 14);
		panel.add(lblDaneObowizkowe);

		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(10, 21, 46, 14);
		panel.add(lblImie);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 46, 67, 14);
		panel.add(lblNazwisko);

		JLabel lblPesel = new JLabel("Pesel");
		lblPesel.setBounds(10, 71, 46, 14);
		panel.add(lblPesel);

		imie = new JTextField();
		imie.setBounds(110, 18, 86, 20);
		if (pracownik != null) {
			imie.setText(pracownik.getImie());
			idPrac = pracownik.getIdPracownika();
		}
		panel.add(imie);
		imie.setColumns(10);

		nazwisko = new JTextField();
		nazwisko.setBounds(110, 43, 86, 20);
		if (pracownik != null)
			nazwisko.setText(pracownik.getNazwisko());
		panel.add(nazwisko);
		nazwisko.setColumns(10);

		pesel = new JTextField();
		pesel.setBounds(110, 68, 86, 20);
		if (pracownik != null)
			pesel.setText(pracownik.getPesel());
		panel.add(pesel);
		pesel.setColumns(10);

		JLabel lblDaneOpcjonalne = new JLabel("Dane opcjonalne:");
		lblDaneOpcjonalne.setBounds(224, 0, 142, 14);
		panel.add(lblDaneOpcjonalne);

		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(224, 21, 46, 14);
		panel.add(lblTelefon);

		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setBounds(224, 46, 46, 14);
		panel.add(lblEmail);

		telefon = new JTextField();
		telefon.setBounds(322, 18, 86, 20);
		panel.add(telefon);
		telefon.setColumns(10);

		email = new JTextField();
		email.setBounds(322, 43, 86, 20);
		panel.add(email);
		email.setColumns(10);

		JLabel lblStanowisko = new JLabel("Stanowisko");
		lblStanowisko.setBounds(10, 96, 92, 14);
		panel.add(lblStanowisko);

		stanowisko = new JTextField();
		stanowisko.setBounds(110, 93, 86, 20);
		if (pracownik != null)
			stanowisko.setText(pracownik.getStanowisko());
		panel.add(stanowisko);
		stanowisko.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(0, 119, 418, 100);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		Adres adres = null;
		if (pracownik != null) {
			adres = searchAdres(pracownik.getIdAdresuZameldowania());
			idZaml = adres.getIdAdresu();
		}

		JLabel lblAdresZameldowania = new JLabel("Adres zameldowania:");
		lblAdresZameldowania.setBounds(10, 0, 138, 14);
		panel_1.add(lblAdresZameldowania);

		JLabel lblUlica = new JLabel("Ulica");
		lblUlica.setBounds(10, 21, 46, 14);
		panel_1.add(lblUlica);

		JLabel lblMiasto = new JLabel("Miasto");
		lblMiasto.setBounds(10, 46, 46, 14);
		panel_1.add(lblMiasto);

		JLabel lblKodPocztowy = new JLabel("Kod pocztowy");
		lblKodPocztowy.setBounds(10, 71, 90, 14);
		panel_1.add(lblKodPocztowy);

		kodZameldowania = new JTextField();
		kodZameldowania.setBounds(110, 68, 86, 20);
		if (pracownik != null)
			kodZameldowania.setText(adres.getKod());
		panel_1.add(kodZameldowania);
		kodZameldowania.setColumns(10);

		miastoZameldowania = new JTextField();
		miastoZameldowania.setBounds(110, 43, 86, 20);
		if (pracownik != null)
			miastoZameldowania.setText(adres.getMiasto());
		panel_1.add(miastoZameldowania);
		miastoZameldowania.setColumns(10);

		ulicaZameldowania = new JTextField();
		ulicaZameldowania.setBounds(110, 18, 86, 20);
		if (pracownik != null)
			ulicaZameldowania.setText(adres.getUlica());
		panel_1.add(ulicaZameldowania);
		ulicaZameldowania.setColumns(10);

		JLabel lblPastwo = new JLabel("Pa\u0144stwo");
		lblPastwo.setBounds(224, 21, 57, 14);
		panel_1.add(lblPastwo);

		panstwoZameldowania = new JTextField();
		panstwoZameldowania.setBounds(322, 18, 86, 20);
		if (pracownik != null)
			panstwoZameldowania.setText(adres.getPanstwo());
		panel_1.add(panstwoZameldowania);
		panstwoZameldowania.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(0, 219, 418, 100);
		contentPane.add(panel_2);

		JLabel lblAdresKorespondencyjnyjeeli = new JLabel(
				"Adres korespondencyjny (je\u017Celi inny ni\u017C zamieszkania):");
		lblAdresKorespondencyjnyjeeli.setBounds(10, 0, 356, 14);
		panel_2.add(lblAdresKorespondencyjnyjeeli);

		if (pracownik != null) {
			adres = searchAdres(pracownik.getIdAdresuKorespondencyjnego());
			idKores = adres.getIdAdresu();
		}

		JLabel label_1 = new JLabel("Ulica");
		label_1.setBounds(10, 21, 46, 14);
		panel_2.add(label_1);

		JLabel label_2 = new JLabel("Miasto");
		label_2.setBounds(10, 46, 46, 14);
		panel_2.add(label_2);

		JLabel label_3 = new JLabel("Kod pocztowy");
		label_3.setBounds(10, 71, 90, 14);
		panel_2.add(label_3);

		kodKorespondencyjny = new JTextField();
		kodKorespondencyjny.setColumns(10);
		kodKorespondencyjny.setBounds(110, 68, 86, 20);
		if (pracownik != null)
			kodKorespondencyjny.setText(adres.getKod());
		panel_2.add(kodKorespondencyjny);

		miastoKorespondencyjne = new JTextField();
		miastoKorespondencyjne.setColumns(10);
		miastoKorespondencyjne.setBounds(110, 43, 86, 20);
		if (pracownik != null)
			miastoKorespondencyjne.setText(adres.getMiasto());
		panel_2.add(miastoKorespondencyjne);

		ulicaKorespondencyjna = new JTextField();
		ulicaKorespondencyjna.setColumns(10);
		ulicaKorespondencyjna.setBounds(110, 18, 86, 20);
		if (pracownik != null)
			ulicaKorespondencyjna.setText(adres.getUlica());
		panel_2.add(ulicaKorespondencyjna);

		JLabel label_4 = new JLabel("Pa\u0144stwo");
		label_4.setBounds(224, 21, 59, 14);
		panel_2.add(label_4);

		panstwoKorespondencyjne = new JTextField();
		panstwoKorespondencyjne.setColumns(10);
		panstwoKorespondencyjne.setBounds(322, 18, 86, 20);
		if (pracownik != null)
			panstwoKorespondencyjne.setText(adres.getPanstwo());
		panel_2.add(panstwoKorespondencyjne);

		JButton btnAnuluj = new JButton("Cofnij");
		btnAnuluj.addActionListener(this);
		btnAnuluj.setBounds(10, 330, 89, 23);
		contentPane.add(btnAnuluj);

		JButton btnDodaj = new JButton("Zapisz");
		btnDodaj.addActionListener(this);

		btnDodaj.setBounds(319, 330, 89, 23);
		contentPane.add(btnDodaj);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Zapisz")) {
			if (idPrac == 0) {
			Adres adres, adres2;
			Pracownik pracownik;
			adres = new Adres(ulicaZameldowania.getText().toUpperCase(), miastoZameldowania.getText().toUpperCase(),
					kodZameldowania.getText().toUpperCase(), panstwoZameldowania.getText().toUpperCase());
			if (ulicaKorespondencyjna.getText().equals("") && miastoKorespondencyjne.getText().equals("")
					&& kodKorespondencyjny.getText().equals("") && panstwoKorespondencyjne.getText().equals("")) {
				adres2 = adres;
			} else {
				adres2 = new Adres(ulicaKorespondencyjna.getText().toUpperCase(),
						miastoKorespondencyjne.getText().toUpperCase(), kodKorespondencyjny.getText().toUpperCase(),
						panstwoKorespondencyjne.getText().toUpperCase());
			}

			pracownik = new Pracownik(adres, adres2, pesel.getText().toUpperCase(), imie.getText().toUpperCase(),
					nazwisko.getText().toUpperCase(), stanowisko.getText().toUpperCase());

			/*
			 * if(!telefon.getText().equals(""))
			 * pracownik.setTelefon(Integer.parseInt(telefon.getText()));
			 * if(!email.getText().equals(""))
			 * pracownik.setEmail(email.getText());
			 */

			Set<Pracownik> itemsSet = new HashSet<Pracownik>();
			itemsSet.add(pracownik);

			adres.setPracownicyZameldowania(itemsSet);
			adres.setPracownicyKorespondencja(itemsSet);

			if (!adres.equals(adres2)) {
				//adres2.setPracownicyZameldowania(itemsSet);
				adres2.setPracownicyKorespondencja(itemsSet);
			}
			//Transaction tx = session.beginTransaction();

			session.save(adres);
			if (!adres.equals(adres2))
				session.save(adres2);
			session.save(pracownik);
			tx.commit();
			// sessionFactory.close();
			this.dispose();
			}
			else {
				//Transaction tx = session.beginTransaction();
				String hql = "UPDATE Pracownik set imie = :noweImie, nazwisko = :noweNazwisko, pesel = :nowyPesel, stanowisko = :noweStanowisko " +
								"WHERE id_pracownika is " + idPrac;
				Query query = session.createQuery(hql);
				query.setParameter("noweImie", imie.getText().toUpperCase());
				query.setParameter("noweNazwisko", nazwisko.getText().toUpperCase());
				query.setParameter("nowyPesel", pesel.getText().toUpperCase());
				query.setParameter("noweStanowisko", stanowisko.getText().toUpperCase());
				int result = query.executeUpdate();
				if(idKores == idZaml) {
					if(ulicaZameldowania.getText().equals(ulicaKorespondencyjna.getText()) && miastoZameldowania.getText().equals(miastoKorespondencyjne.getText()) && 
							kodZameldowania.getText().equals(kodKorespondencyjny.getText()) && panstwoZameldowania.getText().equals(panstwoKorespondencyjne.getText())) {
						hql = "UPDATE Adres set ulica = :nowaUlica, miasto = :noweMiasto, kod = :nowyKod, panstwo = :nowePanstwo " + 
							"WHERE id_adresu is "+ idZaml;
						query = session.createQuery(hql);
						query.setParameter("nowaUlica", ulicaZameldowania.getText().toUpperCase());
						query.setParameter("noweMiasto", miastoZameldowania.getText().toUpperCase());
						query.setParameter("nowyKod", kodZameldowania.getText().toUpperCase());
						query.setParameter("nowePanstwo", panstwoZameldowania.getText().toUpperCase());
						result = query.executeUpdate();
						tx.commit();
					}
					else {
						Adres adres = new Adres(ulicaKorespondencyjna.getText().toUpperCase(), miastoKorespondencyjne.getText().toUpperCase(),
								kodKorespondencyjny.getText().toUpperCase(), panstwoKorespondencyjne.getText().toUpperCase());
						hql = "UPDATE Adres set ulica = :nowaUlica, miasto = :noweMiasto, kod = :nowyKod, panstwo = :nowePanstwo " + 
								"WHERE id_adresu is "+ idZaml;
							query = session.createQuery(hql);
							query.setParameter("nowaUlica", ulicaZameldowania.getText().toUpperCase());
							query.setParameter("noweMiasto", miastoZameldowania.getText().toUpperCase());
							query.setParameter("nowyKod", kodZameldowania.getText().toUpperCase());
							query.setParameter("nowePanstwo", panstwoZameldowania.getText().toUpperCase());
							
							Set<Pracownik> itemsSet = new HashSet<Pracownik>();
							itemsSet.add(searchPracownik(idPrac));
							
							adres.setPracownicyKorespondencja(itemsSet);
							
							session.save(adres);
							
							
							
							result = query.executeUpdate();
							hql = "UPDATE Pracownik set id_adresu_korespondencyjnego = :id_adresu " + 
									"WHERE id_pracownika is "+ idPrac;
								query = session.createQuery(hql);
								query.setParameter("id_adresu", adres.getIdAdresu());
								result = query.executeUpdate();
							tx.commit();
					}
				}
				//tx.commit();
				session.close();
				this.dispose();
			}
		} else if (e.getActionCommand().equals("Cofnij")) {
			tx.rollback();
			session.close();
			this.dispose();
		}
	}

	private Adres searchAdres(Adres id) {
		List<Adres> adres = session.createQuery("from Adres where id_adresu like upper(" + id.getIdAdresu() + ")")
				.getResultList();
		return adres.get(0);
	}

	private Pracownik searchPracownik(int id) {
		List<Pracownik> pracownik = session.createQuery("from Pracownik where id_pracownika like upper(" + id + ")")
				.getResultList();
		return pracownik.get(0);
	}

}
