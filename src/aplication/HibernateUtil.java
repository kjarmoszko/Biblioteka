package aplication;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import database.Adres;
import database.Autor;
import database.Autorstwo;
import database.Czytelnik;
import database.Egzemplarz;
import database.Ksiazka;
import database.Pracownik;
import database.SlowoKluczowe;
import database.SlowoKluczoweEgzemplarza;
import database.Tematyka;
import database.TematykaKsiazki;
import database.Wydawnictwo;
import database.Wypozyczenie;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate-annotation.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Adres.class)
			.addAnnotatedClass(Autor.class)
			//.addAnnotatedClass(Autorstwo.class)
			.addAnnotatedClass(Czytelnik.class)
			.addAnnotatedClass(Egzemplarz.class)
			.addAnnotatedClass(Ksiazka.class)
			.addAnnotatedClass(Pracownik.class)
			.addAnnotatedClass(SlowoKluczowe.class)
			//.addAnnotatedClass(SlowoKluczoweEgzemplarza.class)
			.addAnnotatedClass(Tematyka.class)
			//.addAnnotatedClass(TematykaKsiazki.class)
			.addAnnotatedClass(Wydawnictwo.class)
			.addAnnotatedClass(Wypozyczenie.class);
			System.out.println("Hibernate Annotation Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			System.out.println("Hibernate Annotation serviceRegistry created");

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}
}