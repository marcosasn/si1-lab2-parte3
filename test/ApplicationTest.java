import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import models.Serie;
import models.GenericDAO;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
	
	private static GenericDAO dao = new GenericDAO();

    @Test
    public void renderTemplate() {
    	List<Serie> series = dao.findAllByClassName(Serie.class.getName());
		List<Serie> seriesAssistir = new ArrayList<Serie>(); 
		List<Serie> seriesAssistindo = new ArrayList<Serie>();
		for (int i = 0; i < series.size(); i++) {
			if(series.get(i).isAssistindo()) seriesAssistindo.add(series.get(i));
			else seriesAssistir.add(series.get(i));
		}
		Collections.sort(seriesAssistir);
		Collections.sort(seriesAssistindo);
        Content html = views.html.index.render("Minhas Séries", seriesAssistir, seriesAssistindo);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Nenhuma série acompanhada ainda");
        assertThat(contentAsString(html)).contains("2 Broke Girls");
    }


}
