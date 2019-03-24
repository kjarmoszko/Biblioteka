package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import database.Autor;
import database.Czytelnik;
import database.Egzemplarz;
import database.Ksiazka;
import database.Pracownik;
import database.SlowoKluczowe;

public class SearchBook extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField textField;
	private String choose;
	private JList list;
	//Transaction tx;
	private int selected;
	private boolean addCopy;

	//private Session session;
	private SessionFactory sessionFactory;

	/**
	 * Create the frame.
	 */
	public SearchBook(SessionFactory sF, boolean addCopy) {
		setTitle("Szukaj ksi\u0105\u017Cki");
		this.addCopy = addCopy;
		sessionFactory = sF;
		//session = sessionFactory.getCurrentSession();
		//tx = session.beginTransaction();
		
		choose = "tytul";
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.addActionListener(this);
		btnCofnij.setBounds(10, 227, 89, 23);
		contentPane.add(btnCofnij);
		
		if(addCopy) {
			JButton btnSzukaj = new JButton("Dodaj");
			btnSzukaj.addActionListener(this);
			btnSzukaj.setBounds(435, 227, 89, 23);
			contentPane.add(btnSzukaj);
		}
		else {
			JButton btnSzukaj = new JButton("Wybierz");
			btnSzukaj.addActionListener(this);
			btnSzukaj.setBounds(435, 227, 89, 23);
			contentPane.add(btnSzukaj);
		}
		
		JLabel lblSzukaj = new JLabel("Szukaj:");
		lblSzukaj.setBounds(10, 11, 46, 14);
		contentPane.add(lblSzukaj);
		
		textField = new JTextField();
		textField.setBounds(66, 8, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		//JList list = new JList();
		
		
		
		list = new JList();
		list.setVisibleRowCount(9);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 39, 514, 161);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = list.getSelectedIndex();
			}
		});	
		contentPane.add(list);

		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"tytul", "autor", "ISBN", "s\u0142owo kluczowe"}));
		comboBox.addItemListener(this);
		comboBox.setBounds(196, 8, 120, 20);
		contentPane.add(comboBox);
		
		JButton btnSzukaj_1 = new JButton("Szukaj");
		btnSzukaj_1.setBounds(326, 7, 89, 23);
		btnSzukaj_1.addActionListener(this);
		contentPane.add(btnSzukaj_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Transaction tx = session.beginTransaction();
		if (e.getActionCommand().equals("Szukaj")) {
			String szukaj;
			if(choose.equals("tytul")) {
				Session session = sessionFactory.getCurrentSession();
				Transaction tx = session.beginTransaction();
				List <Ksiazka> ksiazki;
				szukaj = textField.getText();
				if(szukaj.equals("")) {
					ksiazki = session.createQuery("from Ksiazka ").getResultList();
				}
				else {
				ksiazki = session.createQuery("from Ksiazka where tytul like upper('%"+szukaj+"%')").getResultList();
				}
				
				list.setModel(new DefaultComboBoxModel(ksiazki.toArray()));
				
				tx.commit();
				session.close();
			}
			else if(choose.equals("ISBN")) {
				//Transaction tx = session.beginTransaction();
				Session session = sessionFactory.getCurrentSession();
				Transaction tx = session.beginTransaction();
				List <Ksiazka> ksiazki;
				szukaj = textField.getText();
				ksiazki = session.createQuery("from Ksiazka where isbn like "+szukaj).getResultList();
				
				list.setModel(new DefaultComboBoxModel(ksiazki.toArray()));
				
				tx.commit();
				session.close();
				

				//tx.commit();
			}
			else if(choose.equals("s\u0142owo kluczowe")) {
				//Transaction tx = session.beginTransaction();
				Session session = sessionFactory.getCurrentSession();
				Transaction tx = session.beginTransaction();
				
				szukaj = textField.getText();
				List <Ksiazka> ksiazki = new ArrayList<Ksiazka>();
				List <Egzemplarz> egzemplarze = new ArrayList<Egzemplarz>();
				List<SlowoKluczowe> slowaKluczowe = session.createQuery("from SlowoKluczowe where slowo like upper('%"+szukaj+"%')").getResultList();
				SlowoKluczowe id;
				Query query;
				for(SlowoKluczowe s: slowaKluczowe) {
					id = s;
					query = session.createQuery("SELECT e FROM Egzemplarz e JOIN e.slowaKluczowe s WHERE s.idSlowa = :idSlo");
					query.setParameter("idSlo", id.getIdSlowa());
					egzemplarze.addAll(query.getResultList());
				}
				Egzemplarz id2;
				for(Egzemplarz egz: egzemplarze){
					id2 = egz;
					query = session.createQuery("SELECT k FROM Ksiazka k JOIN k.egzemplarze e WHERE e.idEgzemplarza = :idEgz");
					query.setParameter("idEgz", id2.getIdEgzemplarza());
					ksiazki.addAll(query.getResultList());
				}
				
				Set<Ksiazka> set = new HashSet<>(ksiazki);
				ksiazki.clear();
				ksiazki.addAll(set);
				
				list.setModel(new DefaultComboBoxModel(ksiazki.toArray()));
				
				tx.commit();
				session.close();
				

				//tx.commit();
			}
			else {
				
				Session session = sessionFactory.getCurrentSession();
				Transaction tx = session.beginTransaction();
				List <Ksiazka> ksiazki = new ArrayList<Ksiazka>();
				szukaj = textField.getText();
				
				List<Autor> autorzy = session.createQuery("from Autor where nazwisko like upper('%"+szukaj+"%')").getResultList();
				Autor id;
				Query query;
				for(Autor a: autorzy) {
					id = a;
					query = session.createQuery("SELECT k FROM Ksiazka k JOIN k.autorzy a WHERE a.idAutora = :idAut");
					query.setParameter("idAut", id.getIdAutora());
					ksiazki.addAll(query.getResultList());
				}
					
				list.setModel(new DefaultComboBoxModel(ksiazki.toArray()));
				
				tx.commit();
				session.close();
				//tx.commit();
			}
			
			
		}
		else if (e.getActionCommand().equals("Cofnij")) {
			//tx.rollback();
			//session.close();
			this.dispose();
		}
		else if (e.getActionCommand().equals("Dodaj")) {
			NewBookCopy frame = new NewBookCopy(sessionFactory,(Ksiazka)list.getModel().getElementAt(selected));
			frame.setVisible(true);
			//tx.rollback();
			//session.close();
			//this.dispose();
		}
		else if (e.getActionCommand().equals("Wybierz")) {
			ValidCopyFrame frame = new ValidCopyFrame(sessionFactory,(Ksiazka)list.getModel().getElementAt(selected));
			frame.setVisible(true);
			//tx.rollback();
			//session.close();
			//this.dispose();
		}
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		choose = arg0.getItem().toString();
	}
}
