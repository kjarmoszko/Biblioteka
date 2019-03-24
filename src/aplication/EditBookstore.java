package aplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.SessionFactory;

import javax.swing.JButton;

public class EditBookstore extends JFrame implements ActionListener {

	private JPanel contentPane;
	SessionFactory sessionFactory;

	/**
	 * Create the frame.
	 */
	public EditBookstore(SessionFactory sF) {
		sessionFactory = sF;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 368, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDodajAutora = new JButton("Dodaj autora");
		btnDodajAutora.addActionListener(this);
		btnDodajAutora.setBounds(10, 11, 160, 23);
		contentPane.add(btnDodajAutora);
		
		JButton btnNewButton = new JButton("Dodaj wydawnictwo");
		btnNewButton.setBounds(180, 11, 160, 23);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dodaj tematyk\u0119");
		btnNewButton_1.setBounds(10, 45, 160, 23);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		
		JButton btnDodajKsik = new JButton("Dodaj ksi\u0105\u017Ck\u0119");
		btnDodajKsik.setBounds(10, 100, 160, 23);
		btnDodajKsik.addActionListener(this);
		contentPane.add(btnDodajKsik);
		
		JButton btnNewButton_2 = new JButton("Dodaj egzemplarz");
		btnNewButton_2.setBounds(10, 153, 160, 23);
		btnNewButton_2.addActionListener(this);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Dodaj s\u0142owo kluczowe");
		btnNewButton_3.setBounds(180, 153, 160, 23);
		btnNewButton_3.addActionListener(this);
		contentPane.add(btnNewButton_3);
		
		JButton btnCofnij = new JButton("Cofnij");
		btnCofnij.addActionListener(this);
		btnCofnij.setBounds(10, 227, 89, 23);
		contentPane.add(btnCofnij);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Cofnij")) {
			this.dispose();
		}
		else if(e.getActionCommand().equals("Dodaj autora")) {
			NewAuthor frame = new NewAuthor(sessionFactory);
			frame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Dodaj wydawnictwo")) {
			NewPublisher frame = new NewPublisher(sessionFactory);
			frame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Dodaj tematyk\u0119")) {
			NewSubject frame = new NewSubject(sessionFactory);
			frame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Dodaj ksi\u0105\u017Ck\u0119")) {
			NewBook frame = new NewBook(sessionFactory);
			frame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Dodaj egzemplarz")) {
			SearchBook frame = new SearchBook(sessionFactory,true);
			frame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Dodaj s\u0142owo kluczowe")) {
			NewKeyWord frame = new NewKeyWord(sessionFactory);
			frame.setVisible(true);
		}
		
	}

}
