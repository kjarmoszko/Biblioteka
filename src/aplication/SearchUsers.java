package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.mapping.Component;

import database.Czytelnik;
import database.Pracownik;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class SearchUsers extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField textField;
	private String choose;
	private JList list;
	//Transaction tx;
	private int selected;

	//private Session session;
	private SessionFactory sessionFactory;

	/**
	 * Create the frame.
	 */
	public SearchUsers(SessionFactory sF) {
		
		sessionFactory = sF;
		
		
		choose = "ID";
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
		
		JButton btnSzukaj = new JButton("Szukaj");
		btnSzukaj.addActionListener(this);
		btnSzukaj.setBounds(435, 227, 89, 23);
		contentPane.add(btnSzukaj);
		
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "nazwisko", "pesel"}));
		comboBox.addItemListener(this);
		comboBox.setBounds(196, 8, 89, 20);
		contentPane.add(comboBox);
		
		JButton btnEdytuj = new JButton("Edytuj");
		btnEdytuj.addActionListener(this);
		btnEdytuj.setBounds(336, 227, 89, 23);
		contentPane.add(btnEdytuj);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Transaction tx = session.beginTransaction();
		if (e.getActionCommand().equals("Szukaj")) {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = null;
			if(tx == null)
				tx = session.beginTransaction();
			String szukaj;
			if(choose.equals("nazwisko")) {
				//Transaction tx = session.beginTransaction();
				List <Czytelnik> czytelnicy;
				List <Pracownik> pracownicy;
				szukaj = textField.getText();
				if(szukaj.equals("")) {
					czytelnicy = session.createQuery("from Czytelnik ").getResultList();
					pracownicy = session.createQuery("from Pracownik ").getResultList();
				}
				else {
				czytelnicy = session.createQuery("from Czytelnik where nazwisko like upper('%"+szukaj+"%')").getResultList();
				pracownicy = session.createQuery("from Pracownik where nazwisko like upper('%"+szukaj+"%')").getResultList();
				}
				List <Object> margeList = new ArrayList();
				margeList.addAll(czytelnicy);
				margeList.addAll(pracownicy);
				
				list.setModel(new DefaultComboBoxModel(margeList.toArray()));
				

				//tx.commit();
			}
			else if(choose.equals("pesel")) {
				//Transaction tx = session.beginTransaction();
				szukaj = textField.getText();
				List <Czytelnik> czytelnicy = session.createQuery("from Czytelnik where pesel like "+szukaj).getResultList();
				List <Pracownik> pracownicy = session.createQuery("from Pracownik where pesel like "+szukaj).getResultList();
				List <Object> margeList = new ArrayList();
				margeList.addAll(czytelnicy);
				margeList.addAll(pracownicy);
				
				
				list.setModel(new DefaultComboBoxModel(margeList.toArray()));
				

				//tx.commit();
			}
			else {
				if(!textField.getText().equals("")) {
				szukaj = textField.getText();
				List <Czytelnik> czytelnicy = session.createQuery("from Czytelnik where idCzytelnika like upper("+szukaj+")").getResultList();
				List <Pracownik> pracownicy = session.createQuery("from Pracownik where idPracownika like upper("+szukaj+")").getResultList();
				List <Object> margeList = new ArrayList();
				margeList.addAll(czytelnicy);
				margeList.addAll(pracownicy);
				
				
				list.setModel(new DefaultComboBoxModel(margeList.toArray()));

				//tx.commit();
				}
			}
			tx.commit();
			session.close();
			
		}
		else if (e.getActionCommand().equals("Cofnij")) {
			//tx.rollback();
			//session.close();
			this.dispose();
		}
		
		else if (e.getActionCommand().equals("Edytuj")) {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			if(list.getModel().getElementAt(selected).getClass().equals(database.Czytelnik.class)){
				try {
					this.dispose();
					tx.commit();
					session.close();
					NewUser frame = new NewUser(sessionFactory,(Czytelnik)list.getModel().getElementAt(selected));
					frame.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			else {
				try {
					this.dispose();
					tx.commit();
					session.close();
					NewEmployee frame = new NewEmployee(sessionFactory,(Pracownik)list.getModel().getElementAt(selected));
					frame.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
				
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		choose = arg0.getItem().toString();		
	}
}
