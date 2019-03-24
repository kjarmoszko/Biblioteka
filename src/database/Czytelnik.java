package database;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Czytelnik")
public class Czytelnik {

    @Id
    @GeneratedValue(generator = "czytelnik_generator")
	@SequenceGenerator(name="czytelnik_generator", sequenceName = "s_czytelnik",allocationSize=1)
    @Column(name = "Id_czytelnika")
	private int idCzytelnika;
    
    @ManyToOne
	@JoinColumn(name="Id_adresu_zameldowania", nullable=false)
	private Adres idAdresuZameldowania;
	
	@ManyToOne
	@JoinColumn(name = "Id_adresu_korespondencyjnego", nullable=false)
	private Adres idAdresuKorespondencyjnego;
	
	@OneToMany(mappedBy="idCzytelnika")
	private Set<Wypozyczenie> wypozyczenia;
	
    @Column(name = "pesel")
	private String pesel;
    
    @Column(name = "imie")
	private String imie;
    
    @Column(name = "nazwisko")
	private String nazwisko;
    
    @Column(name = "telefon")
	private int telefon;
    
    @Column(name = "email")
	private String email;
    
    public Czytelnik() {
    	
    }

	public Czytelnik(Adres idAdresuZameldowania, Adres idAdresuKorespondencyjnego, String pesel, String imie,
			String nazwisko, int telefon, String email) {
		this.idAdresuZameldowania = idAdresuZameldowania;
		this.idAdresuKorespondencyjnego = idAdresuKorespondencyjnego;
		this.pesel = pesel;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.telefon = telefon;
		this.email = email;
	}

	public Czytelnik(Adres idAdresuZameldowania, Adres idAdresuKorespondencyjnego, String pesel,
			String imie, String nazwisko) {
		this.idAdresuZameldowania = idAdresuZameldowania;
		this.idAdresuKorespondencyjnego = idAdresuKorespondencyjnego;
		this.pesel = pesel;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public int getIdCzytelnika() {
		return idCzytelnika;
	}

	public void setIdCzytelnika(int idCzytelnika) {
		this.idCzytelnika = idCzytelnika;
	}

	public Adres getIdAdresuZameldowania() {
		return idAdresuZameldowania;
	}

	public void setIdAdresuZameldowania(Adres idAdresuZameldowania) {
		this.idAdresuZameldowania = idAdresuZameldowania;
	}

	public Adres getIdAdresuKorespondencyjnego() {
		return idAdresuKorespondencyjnego;
	}

	public void setIdAdresuKorespondencyjnego(Adres idAdresuKorespondencyjnego) {
		this.idAdresuKorespondencyjnego = idAdresuKorespondencyjnego;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}

	public void setWypozyczenia(Set<Wypozyczenie> wypozyczenia) {
		this.wypozyczenia = wypozyczenia;
	}

	@Override
	public String toString() {
		return "["+imie + " " + nazwisko + ", pesel:" + pesel  + ", telefon:" + telefon
				+ ", email:" + email + "]";
	}

	
    
}
