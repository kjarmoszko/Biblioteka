package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import database.Egzemplarz;
import database.Ksiazka;

import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ValidCopyFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JList list;
	
	private SessionFactory sessionFactory;
	private List<Egzemplarz> egzemplarze;
	private int selected;

	/**
	 * Create the frame.
	 */
	public ValidCopyFrame(SessionFactory sF, Ksiazka ksiazka) {
		sessionFactory = sF;
		setTitle("Dost\u0119pne egzemplarze");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList();
		list.setBounds(10, 11, 414, 209);
		{
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			egzemplarze = session.createQuery("from Egzemplarz where isbn like "+ksiazka.getISBN()).getResultList();
			
			list.setModel(new DefaultComboBoxModel(egzemplarze.toArray()));
			
			tx.commit();
			session.close();
		}
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = list.getSelectedIndex();
			}
		});	
		contentPane.add(list);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.setBounds(10, 231, 105, 23);
		btnCofnij.addActionListener(this);
		contentPane.add(btnCofnij);
		
		JButton btnWypoycz = new JButton("Wypo\u017Cycz");
		btnWypoycz.setBounds(319, 231, 105, 23);
		btnWypoycz.addActionListener(this);
		contentPane.add(btnWypoycz);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			this.dispose();
		}
		
	}

}
