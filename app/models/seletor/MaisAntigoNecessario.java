package models.seletor;

import java.util.List;

import javax.persistence.Entity;

import models.Episodio;
import models.Serie;

@Entity
public class MaisAntigoNecessario extends SeletorProximoEpisodio {

	private final int MAXIMO_EPISODIOS_ASSISTIDOS = 3;
	
	public MaisAntigoNecessario() { }

	@Override
	public Episodio selecionar(Serie serie, int temporada) {
		List<Episodio> eps = serie.getEpisodios(temporada);
		int size = eps.size();

		for (int i = 0; i < size; i++){			
			if (!eps.get(i).isAssistido()){	
				boolean quantAssistido = contaEpisodiosAssistidos(eps.subList(i+1, size));
				if (i == size - 1 || (i != size-1 && !quantAssistido)){	
					return eps.get(i);					
				} 				
			}
		}		
		return null;
	}

	private boolean contaEpisodiosAssistidos(List<Episodio> episodiosAssistidos) {
		int assistido = 0;
		for (Episodio ep : episodiosAssistidos) {
			if (ep.isAssistido()){
				assistido++;				
			}
			if (assistido >= MAXIMO_EPISODIOS_ASSISTIDOS){
				return true;
			}
		}		
		return false;
	}
}
