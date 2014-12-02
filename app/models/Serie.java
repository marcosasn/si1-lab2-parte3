package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="serie")
public class Serie {
	
	@Id
    @GeneratedValue
    private Long idSerie;

	private String nome;
	
	@OneToMany(mappedBy = "serie")
	private List<Temporada> temporadas;
		
	public Serie(String nome) {
		this.nome = nome;
		this.temporadas = new ArrayList<Temporada>();
	}
		
	public Serie() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public Long getIdSerie() {
		return idSerie;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Serie)) {
			return false;
		}
		Serie temp = (Serie) obj;
		return this.nome.equals(temp.getNome());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
}
