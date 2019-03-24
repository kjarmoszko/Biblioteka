package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Slowo_kluczowe")
public class SlowoKluczowe {
		
		@Id
		@GeneratedValue(generator = "slowo_generator")
		@SequenceGenerator(name="slowo_generator", sequenceName = "s_slowo_kluczowe",allocationSize=1)
		@Column(name = "Id_slowa")
		private int idSlowa;
		
		@Column(name = "slowo")
		private String slowo;
		
		public SlowoKluczowe() {
			
		}

		public SlowoKluczowe(String slowo) {
			this.slowo = slowo;
		}

		public int getIdSlowa() {
			return idSlowa;
		}

		public void setIdSlowa(int idSlowa) {
			this.idSlowa = idSlowa;
		}

		public String getSlowo() {
			return slowo;
		}

		public void setSlowo(String slowo) {
			this.slowo = slowo;
		}

		@Override
		public String toString() {
			return slowo;
		}


		
		
	}
