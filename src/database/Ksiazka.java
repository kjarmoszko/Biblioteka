package database;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ksiazka")
public class Ksiazka {
	
	@Id
	@Column(name = "ISBN")
	private int ISBN;
	
	@Column(name = "tytul")
	private String tytul;
	
	@Column(name = "opis")
	private String opis;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="ISBN")
	private Set<Egzemplarz> egzemplarze;
	
	
	@ManyToOne
	@JoinColumn(name="Id_wydawnictwa", nullable=false)
	private Wydawnictwo idWydawnictwa;
	
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Autor.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "Autorstwo", 
				joinColumns = { @JoinColumn(name = "ISBN") }, 
				inverseJoinColumns = { @JoinColumn(name = "Id_autora") })
	
	private Set<Autor> autorzy;
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Tematyka.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "Tematyka_ksiazki", 
				joinColumns = { @JoinColumn(name = "ISBN") }, 
				inverseJoinColumns = { @JoinColumn(name = "Id_tematyki") })
	
	private Set<Tematyka> tematyki;
	
	
	
	
	public Ksiazka() {
		
	}

	public Ksiazka(int iSBN, String tytul, String opis, Wydawnictwo idWydawnictwa) {
		ISBN = iSBN;
		this.tytul = tytul;
		this.opis = opis;
		this.idWydawnictwa = idWydawnictwa;
	}
	
	public Ksiazka(int iSBN, String tytul, Wydawnictwo idWydawnictwa) {
		ISBN = iSBN;
		this.tytul = tytul;
		this.idWydawnictwa = idWydawnictwa;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Egzemplarz> getEgzemplarze() {
		return egzemplarze;
	}

	public void setEgzemplarze(Set<Egzemplarz> egzemplarze) {
		this.egzemplarze = egzemplarze;
	}

	public Wydawnictwo getIdWydawnictwa() {
		return idWydawnictwa;
	}

	public void setIdWydawnictwa(Wydawnictwo idWydawnictwa) {
		this.idWydawnictwa = idWydawnictwa;
	}

	public Set<Autor> getAutorzy() {
		return autorzy;
	}

	public void setAutorzy(Set<Autor> autorzy) {
		this.autorzy = autorzy;
	}

	public Set<Tematyka> getTematyki() {
		return tematyki;
	}

	public void setTematyki(Set<Tematyka> tematyki) {
		this.tematyki = tematyki;
	}

	@Override
	public String toString() {
		return "\"" + tytul + "\", "+ autorzy + ", wyd. " + idWydawnictwa;
	}

	

}
