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
@Table(name = "Pracownik")
public class Pracownik {

	@Id
	@GeneratedValue(generator = "pracownik_generator")
	@SequenceGenerator(name="pracownik_generator", sequenceName = "s_pracownik",allocationSize=1)
    @Column(name = "Id_pracownika")
	private int idPracownika;
    
	@ManyToOne
	@JoinColumn(name = "Id_adresu_zameldowania", nullable=false)
	private Adres idAdresuZameldowania;
	
	@ManyToOne
	@JoinColumn(name = "Id_adresu_korespondencyjnego", nullable=false)  
	private Adres idAdresuKorespondencyjnego;
	
    @Column(name = "pesel")
	private String pesel;
    
    @Column(name = "imie")
	private String imie;
    
    @Column(name = "nazwisko")
	private String nazwisko;
    
    @Column(name = "stanowisko")
	private String stanowisko;
    
    
    @OneToMany(mappedBy="idPracownika")
	private Set<Wypozyczenie> wypozyczenia;
    
    
    
    public Pracownik() {
    	
    }

	public Pracownik(Adres idAdresuZameldowania, Adres idAdresuKorespondencyjnego, String pesel,
			String imie, String nazwisko, String stanowisko) {
		this.idAdresuZameldowania = idAdresuZameldowania;
		this.idAdresuKorespondencyjnego = idAdresuKorespondencyjnego;
		this.pesel = pesel;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.stanowisko = stanowisko;
	}

	public int getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
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

	public String getStanowisko() {
		return stanowisko;
	}

	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}

	public Set<Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}

	public void setWypozyczenia(Set<Wypozyczenie> wypozyczenia) {
		this.wypozyczenia = wypozyczenia;
	}


	@Override
	public String toString() {
		/*return "["+imie + " " + nazwisko + ", pesel=" + pesel  + ", telefon=" + telefon
				+ ", email=" + email + "]";*/
		return "["+imie + " " + nazwisko + ", pesel:" + pesel  + ", stanowisko:" + stanowisko+ "]";
	}
    
 
	
	
	
	
}
