package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="temporada")
public class Temporada {
	
	@Id
    @GeneratedValue
    private Long idTemporada;
	
	private int numero;
	
	@OneToMany(mappedBy = "temporada")
	private List<Episodio> episodios;
	
	@ManyToOne
	@JoinColumn(name="idSerie")
	private Serie serie;
	
	public Temporada(int numero, Serie serie) {
		this.numero = numero;
		this.serie = serie;
		this.episodios = new ArrayList<Episodio>();
	}
	
	public Temporada() {
	}

	public Long getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(Long idTemporada) {
		this.idTemporada = idTemporada;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}
	
	
}
