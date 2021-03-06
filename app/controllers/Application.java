package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Episodio;
import models.Serie;
import models.bd.GenericDAO;
import models.seletor.MaisAntigoNaoAssistido;
import models.seletor.MaisAntigoNecessario;
import models.seletor.ProximoNaoAssistido;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	private static GenericDAO dao = new GenericDAO();
	
	@Transactional
    public static Result index() {
		List<Serie> series = dao.findAllByClassName(Serie.class.getName());
		List<Serie> seriesAssistir = new ArrayList<Serie>(); 
		List<Serie> seriesAssistindo = new ArrayList<Serie>();
		
		for (int i = 0; i < series.size(); i++) {
			if(series.get(i).isAssistindo()){
				seriesAssistindo.add(series.get(i));
			}
			else{
				seriesAssistir.add(series.get(i));
			}
		}
		
		Collections.sort(seriesAssistir);
		Collections.sort(seriesAssistindo);
        return ok(index.render("Minhas Séries de TV", seriesAssistir, seriesAssistindo));
    }
	
	@Transactional
    public static Result mudarStatusDaSerie() {
		DynamicForm requestData = Form.form().bindFromRequest();
		Long id = Long.parseLong(requestData.get("id"));
	
		Serie serie = dao.findByEntityId(Serie.class, id);
		serie.mudaStatus();
        return redirect("/#serie-" + id);
    }
	
	@Transactional
    public static Result mudarStatusDoEpisodio() {
		DynamicForm requestData = Form.form().bindFromRequest();
		Long id = Long.parseLong(requestData.get("id"));
		
		Episodio episodio = dao.findByEntityId(Episodio.class, id);
		episodio.mudaStatus();
		Long idSerie = episodio.getSerie().getId();
        return redirect("/#serie-" + idSerie);
    }
	
	@Transactional
	public static Result mudarUltimoEpisodio() {
		DynamicForm requestData = Form.form().bindFromRequest();
		Long idSerie = Long.parseLong(requestData.get("id"));
		Serie serie = dao.findByEntityId(Serie.class, idSerie);
		Integer valor = Integer.parseInt(requestData.get("recomendar"));
		
		switch (valor){
		case Serie.MAIS_ANTIGO:
			if (serie.getRecomendacao() != Serie.MAIS_ANTIGO) {
				serie.setSeletorProximoEpisodio(new MaisAntigoNaoAssistido());
			}
			break;
		case Serie.PROXIMO:
			if (serie.getRecomendacao() != Serie.PROXIMO) {
				serie.setSeletorProximoEpisodio(new ProximoNaoAssistido());
			}
			break;
		case Serie.MAIS_ANTIGO_NECESSARIO:
			if (serie.getRecomendacao() != Serie.MAIS_ANTIGO_NECESSARIO) {
				serie.setSeletorProximoEpisodio(new MaisAntigoNecessario());
			}
			break;
		default:
			serie.setSeletorProximoEpisodio(new MaisAntigoNaoAssistido());
		}
		return redirect("/#serie-" + idSerie);
    }

}
