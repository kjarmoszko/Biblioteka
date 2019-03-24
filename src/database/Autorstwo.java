package database;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Autorstwo")
public class Autorstwo implements Serializable {
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_autora")
	private Autor idAutora;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ISBN")
	private Ksiazka ISBN;
	
	public Autorstwo() {
		
	}

	public Autorstwo(Autor idAutora, Ksiazka iSBN) {
		this.idAutora = idAutora;
		ISBN = iSBN;
	}

	public Autor getIdAutora() {
		return idAutora;
	}

	public void setIdAutora(Autor idAutora) {
		this.idAutora = idAutora;
	}

	public Ksiazka getISBN() {
		return ISBN;
	}

	public void setISBN(Ksiazka iSBN) {
		ISBN = iSBN;
	}

	@Override
	public String toString() {
		return "Autorstwo [idAutora=" + idAutora + ", ISBN=" + ISBN + "]";
	}
	
	
}
