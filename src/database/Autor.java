package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(generator = "autor_generator")
	@SequenceGenerator(name="autor_generator", sequenceName = "s_autor",allocationSize=1)
    @Column(name = "Id_autora")
    private int idAutora;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    public Autor() {

    }

	public Autor(String imie, String nazwisko) {
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public int getIdAutora() {
		return idAutora;
	}

	public void setIdAutora(int idAutora) {
		this.idAutora = idAutora;
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

	@Override
	public String toString() {
		return imie.charAt(0) +"."+ nazwisko;
	}


	
    
}
