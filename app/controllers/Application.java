package controllers;

import java.util.List;

import models.GenericDAO;
import models.Serie;
import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	
	private static GenericDAO dao = new GenericDAO();

	@Transactional
    public static Result index() {
		List<Serie> series = dao.findAllByClassName(Serie.class.getName());
        return ok(index.render("Minhas Séries", series));
    }

}
