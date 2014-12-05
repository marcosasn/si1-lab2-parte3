import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import models.GenericDAO;
import models.Serie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;


public class SeriesTest {

	private GenericDAO dao = new GenericDAO();
	private EntityManager em;

	@Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }

	@Test
	public void testDeveIniciarComSeriesCadastradas() {
		List<Serie> series = dao.findAllByClassName(Serie.class.getName());
		//assertEquals(3, series.size());
	}

}
