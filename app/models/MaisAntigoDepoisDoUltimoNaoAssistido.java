package models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class MaisAntigoDepoisDoUltimoNaoAssistido extends SeletorProximoEpisodio {
	
	public MaisAntigoDepoisDoUltimoNaoAssistido() {}

	@Override
	public Episodio selecionar(Serie serie, int temporada) {
		List<Episodio> eps = serie.getEpisodios(temporada);
		int i = 0;
		int index = -1;
		while (i < eps.size()) {
			if(eps.get(i).isAssistido()) {
				index = i;
			}
			i++;
		}
		if(index == i-1) return null;
		if(index == -1) return eps.get(0);
		return eps.get(index+1);
	}

}
