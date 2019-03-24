package database;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Wydawnictwo")
public class Wydawnictwo {
	
	@Id
	@GeneratedValue(generator = "wydawnictwo_generator")
	@SequenceGenerator(name="wydawnictwo_generator", sequenceName = "s_wydawnictwo",allocationSize=1)
	@Column(name = "Id_wydawnictwa", updatable = false, nullable = false)
	private int idWydawnictwa;
	
	@Column(name = "nazwa")
	private String nazwa;
	
	@ManyToOne
	@JoinColumn(name="Id_adresu", nullable=false)
	private Adres idAdresu;
	
	
	@OneToMany(mappedBy="ISBN")
	private Set<Ksiazka> ksiazki;
	
	public Wydawnictwo() {
		ksiazki = new HashSet();
	}

	public Wydawnictwo(String nazwa, Adres idAdresu) {
		this.nazwa = nazwa;
		this.idAdresu = idAdresu;
		ksiazki = new HashSet();
	}

	public int getIdWydawnictwa() {
		return idWydawnictwa;
	}

	public void setIdWydawnictwa(int idWydawnictwa) {
		this.idWydawnictwa = idWydawnictwa;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Adres getIdAdresu() {
		return idAdresu;
	}

	public void setIdAdresu(Adres idAdresu) {
		this.idAdresu = idAdresu;
	}

	public Set<Ksiazka> getKsiazki() {
		return ksiazki;
	}

	public void setKsiazki(Set<Ksiazka> ksiazki) {
		this.ksiazki = ksiazki;
	}

	@Override
	public String toString() {
		return nazwa;
	}

	public void addKsiazka(Ksiazka k) {
		ksiazki.add(k);
	}
	
	public void removeKsiazka(Ksiazka k) {
		ksiazki.remove(k);
	}
	
	
	
}
