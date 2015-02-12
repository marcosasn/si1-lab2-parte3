package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class SeletorProximoEpisodio {
	
	@Id
    @GeneratedValue
    private Long idSeletorProximoEpisodio;
	
	public abstract Episodio selecionar(Serie serie, int temporada);
}
