package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Autor;
import database.Tematyka;

public class SetSubjectFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	private JList<Tematyka> list_1;
	private int selected;
	private int selected2;
	private DefaultListModel<Tematyka> model;
	private Object[] subjects;
	//private DefaultListModel<Autor> savedModel;

	private SessionFactory sessionFactory;
	//private Transaction tx;
	//private Session session;

	/**
	 * Create the frame.
	 */
	public SetSubjectFrame(SessionFactory sF) {
		sessionFactory = sF;
		//session = sessionFactory.getCurrentSession();
		//tx = session.beginTransaction();
		setTitle("Dodaj tematyki ksi\u0105\u017Cki");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNazwisko = new JLabel("Nazwa");
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

		JLabel lblAutorzyKsiki = new JLabel("Tematyki ksi\u0105\u017Cki:");
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

		JButton btnNowyAutor = new JButton("Nowa tematyka");
		btnNowyAutor.setBounds(176, 179, 156, 23);
		btnNowyAutor.addActionListener(this);
		contentPane.add(btnNowyAutor);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			//tx.rollback();
			//session.close();
			if(subjects != null)
				loadState();
			this.dispose();
		} 
		else if (e.getActionCommand().equals("Szukaj")) {
			Session session = sessionFactory.getCurrentSession();
			String szukaj;
			 Transaction tx = session.beginTransaction();
			List<Tematyka> tematyki;
			szukaj = textField.getText();
			if (szukaj.equals("")) {
				tematyki = session.createQuery("from Tematyka ").getResultList();
			} else {
				tematyki = session.createQuery("from Tematyka where nazwa like upper('%" + szukaj + "%')").getResultList();
			}

			list.setModel(new DefaultComboBoxModel(tematyki.toArray()));

			tx.commit();
			session.close();

		}
		else if (e.getActionCommand().equals(">")) {
			model.addElement((Tematyka)list.getModel().getElementAt(selected));
		}
		else if (e.getActionCommand().equals("<")) {
			model.removeElementAt(selected2);
		}
		else if (e.getActionCommand().equals("Nowa tematyka")) {
			NewSubject frame = new NewSubject(sessionFactory);
			frame.setVisible(true);
		}
		else if (e.getActionCommand().equals("Zatwierd\u017A")) {
			subjects = model.toArray();
			//this.dispose();
			this.setVisible(false);
		}		
	}
	
	public Object[] getSubjects() {
		return subjects;
	}
	
	
	public void loadState() {
		model.removeAllElements();
		//model = new DefaultListModel<>();
		for(Object o: subjects) {
			model.addElement((Tematyka)o);
		}
		//model = authors;
	}

}
