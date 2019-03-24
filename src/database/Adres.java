package database;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Adres")
public class Adres {
	
	@Id
	@GeneratedValue(generator = "adres_generator")
	@SequenceGenerator(name="adres_generator", sequenceName = "s_adres",allocationSize=1)
    @Column(name = "Id_adresu", updatable = false, nullable = false)
	private int idAdresu;
	
    @Column(name = "ulica")
	private String ulica;
	
    @Column(name = "miasto")
	private String miasto;
	
    @Column(name = "kod")
	private String kod;
	
    @Column(name = "panstwo")
	private String panstwo;
    
    @OneToMany(mappedBy="idAdresu")
	private Set<Wydawnictwo> wydawnictwa;
    
    @OneToMany(mappedBy="idAdresuZameldowania")
	private Set<Czytelnik> czytelnicyZameldowania;
    
    @OneToMany(mappedBy="idAdresuZameldowania")
	private Set<Pracownik> pracownicyZameldowania;
    
    @OneToMany(mappedBy="idAdresuKorespondencyjnego")
   	private Set<Czytelnik> czytelnicyKorespondencja;
       
       @OneToMany(mappedBy="idAdresuKorespondencyjnego")    
   	private Set<Pracownik> pracownicyKorespondencja;
	
	public Adres() {
		
	}

	public Adres(String ulica, String miasto, String kod, String panstwo) {
		this.ulica = ulica;
		this.miasto = miasto;
		this.kod = kod;
		this.panstwo = panstwo;
	}

	public int getIdAdresu() {
		return idAdresu;
	}

	public void setIdAdresu(int idAdresu) {
		this.idAdresu = idAdresu;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(String panstwo) {
		this.panstwo = panstwo;
	}

	public Set<Wydawnictwo> getWydawnictwa() {
		return wydawnictwa;
	}

	public void setWydawnictwa(Set<Wydawnictwo> wydawnictwa) {
		this.wydawnictwa = wydawnictwa;
	}

	public Set<Czytelnik> getCzytelnicyZameldowania() {
		return czytelnicyZameldowania;
	}

	public void setCzytelnicyZameldowania(Set<Czytelnik> czytelnicyZameldowania) {
		this.czytelnicyZameldowania = czytelnicyZameldowania;
	}

	public Set<Pracownik> getPracownicyZameldowania() {
		return pracownicyZameldowania;
	}

	public void setPracownicyZameldowania(Set<Pracownik> pracownicyZameldowania) {
		this.pracownicyZameldowania = pracownicyZameldowania;
	}

	public Set<Czytelnik> getCzytelnicyKorespondencja() {
		return czytelnicyKorespondencja;
	}

	public void setCzytelnicyKorespondencja(Set<Czytelnik> czytelnicyKorespondencja) {
		this.czytelnicyKorespondencja = czytelnicyKorespondencja;
	}

	public Set<Pracownik> getPracownicyKorespondencja() {
		return pracownicyKorespondencja;
	}

	public void setPracownicyKorespondencja(Set<Pracownik> pracownicyKorespondencja) {
		this.pracownicyKorespondencja = pracownicyKorespondencja;
	}

	@Override
	public String toString() {
		return "[" + ulica + " " +kod +" "+ miasto +" "+ panstwo + "]";
	}


	
	
}
