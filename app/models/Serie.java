package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.seletor.MaisAntigoNaoAssistido;
import models.seletor.MaisAntigoNecessario;
import models.seletor.ProximoNaoAssistido;
import models.seletor.SeletorProximoEpisodio;

@Entity
@Table(name="serie")
public class Serie implements Comparable<Serie> {
	
	@Id
    @GeneratedValue
    private Long idSerie;

	private String nome;
	private boolean status;
	
	@OneToMany(mappedBy = "serie")
	private List<Episodio> episodios;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private SeletorProximoEpisodio seletor;
	
	public static final int MAIS_ANTIGO = 1;
	public static final int PROXIMO = 2;
	public static final int MAIS_ANTIGO_NECESSARIO = 3;
	
	public Serie() {}
	
	public Serie(String nome) {
		this.nome = nome;
		this.status = false;
		this.seletor = new MaisAntigoNaoAssistido();
		this.episodios = new ArrayList<Episodio>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAssistindo() {
		return status;
	}

	public void mudaStatus() {
		if(this.status){
			this.status = false;
		}
		else {
			this.status = true;
		}
	}
	
	public boolean hasTemporadaAssistidaCompleta() {
		for (int i = 1; i <= getTotalDeTemporadas(); i++) {
			if(isTemporadaAssistidaCompleta(i)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTemporadaAssistidaIncompleta() {
		for (int i = 1; i <= getTotalDeTemporadas(); i++) {
			if(isTemporadaAssistidaIncompleta(i)){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTemporadaNaoAssistida() {
		for (int i = 1; i <= getTotalDeTemporadas(); i++) {
			if(isTemporadaNaoAssistida(i)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isTemporadaAssistidaCompleta(int temporada) {
		List<Episodio> temp = getEpisodios(temporada);
		for (int i = 0; i < temp.size(); i++) {
			if(!temp.get(i).isAssistido()){
				return false;
			}
		}
		return true;
	}
	
	public boolean isTemporadaAssistidaIncompleta(int temporada) {
		if(isTemporadaAssistidaCompleta(temporada)){
			return false;
		}
		if(isTemporadaNaoAssistida(temporada)){
			return false;
		}
		return true;
	}
	
	public boolean isTemporadaNaoAssistida(int temporada) {
		List<Episodio> temp = getEpisodios(temporada);
		for (int i = 0; i < temp.size(); i++) {
			if(temp.get(i).isAssistido()){
				return false;
			}
		}
		return true;
	}
	
	public Episodio getProximoEpisodio(int temporada) {
		return seletor.selecionar(this, temporada);
	}
	
	public List<Integer> getTemporadas() {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < this.episodios.size(); i++) {
			if(!result.contains(episodios.get(i).getTemporada())){
				result.add(episodios.get(i).getTemporada());
			}
		}
		return result;
	}
	
	public int getTotalDeTemporadas() {
		return getTemporadas().size();
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	public List<Episodio> getEpisodios() {
		return this.episodios;
	}
	
	public List<Episodio> getEpisodios(int temporada) {
		List<Episodio> temp = new ArrayList<Episodio>();
		for (int i = 0; i < this.episodios.size(); i++) {
			if(episodios.get(i).getTemporada() == temporada){
				temp.add(episodios.get(i));
			}
		}
		return temp;
	}

	public int getTotalDeEpisodios() {
		return this.episodios.size();
	}
	
	public int getTotalDeEpisodiosDaTemporada(int temporada) {
		return getEpisodios(temporada).size();
	}
		
	public int getTotalDeEpisodiosAssistidos() {
		int cont = 0;
		for (int i = 0; i < this.episodios.size(); i++) {
			if(episodios.get(i).isAssistido()){
				cont++;
			}
		}
		return cont;
	}
	
	public int getTotalDeEpisodiosAssistir() {
		return this.episodios.size() - getTotalDeEpisodiosAssistidos();
	}
	
	public Episodio addEpisodio(String nome, int numero, int temporada) {
		Episodio temp = new Episodio(nome, numero, temporada, this);
		this.episodios.add(temp);
		return temp;
	}
	
	public void removeEpisodio(Episodio episodio) {
		this.episodios.remove(episodio);
	}
	
	public Long getId() {
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
	
	@Override
	public int compareTo(Serie serie) {
		return this.nome.compareTo(serie.getNome());
	}
	
	public void setSeletorProximoEpisodio(SeletorProximoEpisodio seletor) {
		this.seletor = seletor;	
	}
	
	public SeletorProximoEpisodio getSeletorProximoEpisodio() {
		return seletor;
	}
	
	public int getRecomendacao() {
		if (seletor instanceof MaisAntigoNaoAssistido) {
			return MAIS_ANTIGO;
		} else if (seletor instanceof ProximoNaoAssistido) {
			return PROXIMO;
		} else if (seletor instanceof MaisAntigoNecessario) {
			return MAIS_ANTIGO_NECESSARIO;
		}
		return -1;
	}
}
