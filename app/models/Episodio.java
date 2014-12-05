package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Episodio {
	
	@Id
    @GeneratedValue
    private Long idEpisodio;
	
	@ManyToOne
	@JoinColumn(name="idSerie")
	private Serie serie;
	
	private boolean status;
	private String nome;
	private int numero;
	private int temporada;
	
	public Episodio(String nome, int numero, int temporada, Serie serie) {
		this(nome, numero, temporada);
		this.serie = serie;
	}
	
	public Episodio(String nome, int numero, int temporada) {
		this.nome = nome;
		this.status = false;
		this.numero = numero;
		this.temporada = temporada;
		this.serie = new Serie();
	}
	
	public Episodio() {
	}
	
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getTemporada() {
		return temporada;
	}

	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}

	public boolean isAssistido() {
		return this.status;
	}

	public void mudaStatus() {
		if(this.status) this.status = false;
		else this.status = true;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return idEpisodio;
	}

}
