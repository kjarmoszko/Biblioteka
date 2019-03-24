package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Autor;
import database.Czytelnik;
import database.Pracownik;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class SetAuthorFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	private JList<Autor> list_1;
	private int selected;
	private int selected2;
	private DefaultListModel<Autor> model;
	private Object[] authors;
	//private DefaultListModel<Autor> savedModel;

	private SessionFactory sessionFactory;
	//private Transaction tx;
	//private Session session;

	/**
	 * Create the frame.
	 */
	public SetAuthorFrame(SessionFactory sF) {
		sessionFactory = sF;
		//session = sessionFactory.getCurrentSession();
		//tx = session.beginTransaction();
		setTitle("Dodaj autor\u00F3w ksi\u0105\u017Cki");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 11, 60, 14);
		contentPane.add(lblNazwisko);

		textField = new JTextField();
		textField.setBounds(80, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.setBounds(176, 7, 89, 23);
		btnSzukaj.addActionListener(this);
		contentPane.add(btnSzukaj);

		list = new JList();
		list.setBounds(10, 36, 207, 132);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = list.getSelectedIndex();
			}
		});
		contentPane.add(list);

		JButton button = new JButton(">");
		button.setBounds(227, 48, 49, 23);
		button.addActionListener(this);
		contentPane.add(button);

		JButton button_1 = new JButton("<");
		button_1.setBounds(227, 82, 49, 23);
		button_1.addActionListener(this);
		contentPane.add(button_1);

		model = new DefaultListModel<>();
		list_1 = new JList<>(model);
		list_1.setBounds(286, 36, 207, 132);
		list_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected2 = list_1.getSelectedIndex();
			}
		});
		contentPane.add(list_1);

		JLabel lblAutorzyKsiki = new JLabel("Autorzy ksi\u0105\u017Cki:");
		lblAutorzyKsiki.setBounds(286, 11, 120, 14);
		contentPane.add(lblAutorzyKsiki);

		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 179, 100, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);

		JButton btnZatwierd = new JButton("Zatwierd\u017A");
		btnZatwierd.setBounds(393, 179, 100, 23);
		btnZatwierd.addActionListener(this);
		contentPane.add(btnZatwierd);

		JButton btnNowyAutor = new JButton("Nowy autor");
		btnNowyAutor.setBounds(194, 179, 120, 23);
		btnNowyAutor.addActionListener(this);
		contentPane.add(btnNowyAutor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			//tx.rollback();
			//session.close();
			if(authors != null)
				loadState();
			this.dispose();
		} 
		else if (e.getActionCommand().equals("Szukaj")) {
			Session session = sessionFactory.getCurrentSession();
			String szukaj;
			 Transaction tx = session.beginTransaction();
			List<Autor> autorzy;
			szukaj = textField.getText();
			if (szukaj.equals("")) {
				autorzy = session.createQuery("from Autor ").getResultList();
			} else {
				autorzy = session.createQuery("from Autor where nazwisko like upper('%" + szukaj + "%')").getResultList();
			}

			list.setModel(new DefaultComboBoxModel(autorzy.toArray()));

			tx.commit();
			session.close();

		}
		else if (e.getActionCommand().equals(">")) {
			model.addElement((Autor)list.getModel().getElementAt(selected));
		}
		else if (e.getActionCommand().equals("<")) {
			model.removeElementAt(selected2);
		}
		else if (e.getActionCommand().equals("Nowy autor")) {
			NewAuthor frame = new NewAuthor(sessionFactory);
			frame.setVisible(true);
		}
		else if (e.getActionCommand().equals("Zatwierd\u017A")) {
			authors = model.toArray();
			//this.dispose();
			this.setVisible(false);
		}		
	}
	
	public Object[] getAuthors() {
		return authors;
	}
	
	
	public void loadState() {
		model.removeAllElements();
		//model = new DefaultListModel<>();
		for(Object o: authors) {
			model.addElement((Autor)o);
		}
		//model = authors;
	}

}
