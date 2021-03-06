package models.seletor;

import java.util.List;

import javax.persistence.Entity;

import models.Episodio;
import models.Serie;

@Entity
public class MaisAntigoNaoAssistido extends SeletorProximoEpisodio {
	
	public MaisAntigoNaoAssistido() {}

	@Override
	public Episodio selecionar(Serie serie, int temporada) {
		List<Episodio> eps = serie.getEpisodios(temporada);
		for(Episodio ep: eps) {
			if (!ep.isAssistido()) {
				return ep;
			}	
		}
		return null;		
	}

}
