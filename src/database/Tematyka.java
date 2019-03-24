package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Tematyka")
public class Tematyka {
	
	@Id
	@GeneratedValue(generator = "tematyka_generator")
	@SequenceGenerator(name="tematyka_generator", sequenceName = "s_tematyka",allocationSize=1)
	@Column(name = "Id_tematyki")
	private int idTematyki;
	
	@Column(name = "nazwa")
	private String nazwa;
	
	public Tematyka() {
		
	}

	public Tematyka(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getIdTematyki() {
		return idTematyki;
	}

	public void setIdTematyki(int idTematyki) {
		this.idTematyki = idTematyki;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	@Override
	public String toString() {
		return nazwa;
	}


	
}
