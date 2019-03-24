package database;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Slowo_kluczowe_egzemplarza")
public class SlowoKluczoweEgzemplarza implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "Id_egzemplarza")
	private Egzemplarz idEgzemplarza;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "Id_slowa")
	private SlowoKluczowe idSlowa;
	
	public SlowoKluczoweEgzemplarza() {
		
	}

	public SlowoKluczoweEgzemplarza(Egzemplarz idEgzemplarza, SlowoKluczowe idSlowa) {
		this.idEgzemplarza = idEgzemplarza;
		this.idSlowa = idSlowa;
	}

	public Egzemplarz getIdEgzemplarza() {
		return idEgzemplarza;
	}

	public void setIdEgzemplarza(Egzemplarz idEgzemplarza) {
		this.idEgzemplarza = idEgzemplarza;
	}

	public SlowoKluczowe getIdSlowa() {
		return idSlowa;
	}

	public void setIdSlowa(SlowoKluczowe idSlowa) {
		this.idSlowa = idSlowa;
	}

	@Override
	public String toString() {
		return "SlowoKluczoweEgzemplarza [idEgzemplarza=" + idEgzemplarza + ", idSlowa=" + idSlowa + "]";
	}
	
}
