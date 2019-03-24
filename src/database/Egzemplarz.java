package database;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Egzemplarz")
public class Egzemplarz {

	@Id
	@GeneratedValue(generator = "egzemplarz_generator")
	@SequenceGenerator(name="egzemplarz_generator", sequenceName = "s_egzemplarz",allocationSize=1)
    @Column(name = "Id_egzemplarza")
	private int idEgzemplarza;
	
	@ManyToOne
	@JoinColumn(name = "ISBN", nullable=false)
	private Ksiazka ISBN;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="idEgzemplarza")
	private Set<Wypozyczenie> wypozyczenia;
	
	
	@Column(name = "rok_wydania")
	private int rokWydania;
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = SlowoKluczowe.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "Slowo_kluczowe_egzemplarza", 
				joinColumns = { @JoinColumn(name = "Id_egzemplarza") }, 
				inverseJoinColumns = { @JoinColumn(name = "Id_slowa") })
	
	private Set<SlowoKluczowe> slowaKluczowe;
		
	
	public Egzemplarz() {
		
	}

	public Egzemplarz(int rokWydania) {
		this.rokWydania = rokWydania;
	}
	
	

	public Egzemplarz(Ksiazka iSBN, int rokWydania) {
		ISBN = iSBN;
		this.rokWydania = rokWydania;
	}

	public int getIdEgzemplarza() {
		return idEgzemplarza;
	}

	public void setIdEgzemplarza(int idEgzemplarza) {
		this.idEgzemplarza = idEgzemplarza;
	}


	public int getRokWydania() {
		return rokWydania;
	}

	public void setRokWydania(int rokWydania) {
		this.rokWydania = rokWydania;
	}

	public Ksiazka getISBN() {
		return ISBN;
	}

	public void setISBN(Ksiazka iSBN) {
		ISBN = iSBN;
	}

	public Set<Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}

	public void setWypozyczenia(Set<Wypozyczenie> wypozyczenia) {
		this.wypozyczenia = wypozyczenia;
	}

	public Set<SlowoKluczowe> getSlowaKluczowe() {
		return slowaKluczowe;
	}

	public void setSlowaKluczowe(Set<SlowoKluczowe> slowaKluczowe) {
		this.slowaKluczowe = slowaKluczowe;
	}

	@Override
	public String toString() {
		if(getLast() == null)
			return "id:" + idEgzemplarza + ", wydana w:"+ rokWydania;
		else
			return "id:" + idEgzemplarza + ", wydana w:"+ rokWydania +" "+ getLast();
	}

	public Wypozyczenie getLast() {
		if(!wypozyczenia.isEmpty()) {
			int id = 0;
			Wypozyczenie wyp = null;
			for(Wypozyczenie w: wypozyczenia){
				if(w.getIdWypozyczenia() > id) {
					id = w.getIdWypozyczenia();
					wyp = w;
				}
			}
			return wyp;
		}
			
		else
			return null;
	}
	
}
