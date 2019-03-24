package database;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tematyka_ksiazki")
public class TematykaKsiazki implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "Id_tematyki")
	private Tematyka idTematyki;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ISBN")
	private Ksiazka ISBN;
	
	public TematykaKsiazki() {
		
	}

	public TematykaKsiazki(Tematyka idTematyki, Ksiazka iSBN) {
		this.idTematyki = idTematyki;
		ISBN = iSBN;
	}

	public Tematyka getIdTematyki() {
		return idTematyki;
	}

	public void setIdTematyki(Tematyka idTematyki) {
		this.idTematyki = idTematyki;
	}

	public Ksiazka getISBN() {
		return ISBN;
	}

	public void setISBN(Ksiazka iSBN) {
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "TematykaKsiazki [idTematyki=" + idTematyki + ", ISBN=" + ISBN + "]";
	}
	
}
