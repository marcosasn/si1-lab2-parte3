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
	@JoinColumn(name="idTemporada")
	private Temporada temporada;
	
	private boolean status;
	private String nome;
	private int numero;
	
	public Episodio(String nome, int numero) {
		this.nome = nome;
		this.status = false;
		this.numero = numero;
		this.temporada = new Temporada();
	}
	
	public Episodio() {
	}

	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdEpisodio() {
		return idEpisodio;
	}

}
