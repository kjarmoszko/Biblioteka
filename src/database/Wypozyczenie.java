package database;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

@Entity
@Table(name = "Wypozyczenie")
public class Wypozyczenie {
	
	@Id
	@GeneratedValue(generator = "wypozyczenie_generator")
	@SequenceGenerator(name="wypozyczenie_generator", sequenceName = "s_adres",allocationSize=1)
    @Column(name = "Id_wypozyczenia")
	private int idWypozyczenia;
	
	@ManyToOne
	@JoinColumn(name = "Id_egzemplarza", nullable=false)
	private Egzemplarz idEgzemplarza;
	
	@ManyToOne
	@JoinColumn(name = "Id_pracownika", nullable=false)
	private Pracownik idPracownika;
	
	@ManyToOne
	@JoinColumn(name = "Id_czytelnika", nullable=false)
	private Czytelnik idCzytelnika;
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "data_wypozyczenia")
	private Date dataWypozyczenia;
	
	//@Temporal(TemporalType.DATE)
	@Column(name = "data_zwrotu")
	private Date dataZwrotu;

	public Wypozyczenie() {
		
	}

	public Wypozyczenie(Egzemplarz idEgzemplarza, Pracownik idPracownika, Czytelnik idCzytelnika, Date dataWypozyczenia) {
		this.idEgzemplarza = idEgzemplarza;
		this.idPracownika = idPracownika;
		this.idCzytelnika = idCzytelnika;
		this.dataWypozyczenia = dataWypozyczenia;
		this.dataZwrotu = dataZwrotu;
	}

	public int getIdWypozyczenia() {
		return idWypozyczenia;
	}

	public void setIdWypozyczenia(int idWypozyczenia) {
		this.idWypozyczenia = idWypozyczenia;
	}

	public Egzemplarz getIdEgzemplarza() {
		return idEgzemplarza;
	}

	public void setIdEgzemplarza(Egzemplarz idEgzemplarza) {
		this.idEgzemplarza = idEgzemplarza;
	}

	public Pracownik getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(Pracownik idPracownika) {
		this.idPracownika = idPracownika;
	}

	public Czytelnik getIdCzytelnika() {
		return idCzytelnika;
	}

	public void setIdCzytelnika(Czytelnik idCzytelnika) {
		this.idCzytelnika = idCzytelnika;
	}

	public Date getDataWypozyczenia() {
		return dataWypozyczenia;
	}

	public void setDataWypozyczenia(Date dataWypozyczenia) {
		this.dataWypozyczenia = dataWypozyczenia;
	}

	public Date getDataZwrotu() {
		return dataZwrotu;
	}

	public void setDataZwrotu(Date dataZwrotu) {
		this.dataZwrotu = dataZwrotu;
	}

	@Override
	public String toString() {
		if(dataWypozyczenia == null)
			return "";
		else
			return "Wypo¿yczono "+ dataWypozyczenia;
	}


	
	
}
