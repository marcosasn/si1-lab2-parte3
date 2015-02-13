package models.seletor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.Episodio;
import models.Serie;

@Entity
public abstract class SeletorProximoEpisodio {
	
	@Id
    @GeneratedValue
    private Long id;
	
	public SeletorProximoEpisodio() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public abstract Episodio selecionar(Serie serie, int temporada);
}
