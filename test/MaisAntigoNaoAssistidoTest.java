import static org.junit.Assert.*;

import models.Episodio;
import models.Serie;

import org.junit.Before;
import org.junit.Test;

public class MaisAntigoNaoAssistidoTest {

	Serie serie;
	
	@Before
	public void setUp() throws Exception {
		serie = new Serie("serie A");
			
		serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);
		
		serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);
		serie.addEpisodio("Episodio 5", 5, 2);
		
		serie.addEpisodio("Episodio 1", 1, 3);
		serie.addEpisodio("Episodio 2", 2, 3);
		serie.addEpisodio("Episodio 3", 2, 3);
	}

	@Test
	public void testMaisAntigoNaoAssistido() {
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 1", 1, 1));
		assertEquals(serie.getEpisodios(1).get(0),new Episodio("Episodio 1", 1, 1));
		serie.getEpisodios(1).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(1).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 2", 2, 1));
		
		
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 1", 1, 2));
		assertEquals(serie.getEpisodios(2).get(0),new Episodio("Episodio 1", 1, 2));
		serie.getEpisodios(2).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(2).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 2", 2, 2));
		
		assertEquals(serie.getProximoEpisodio(3),new Episodio("Episodio 1", 1, 3));
		assertEquals(serie.getEpisodios(3).get(0),new Episodio("Episodio 1", 1, 3));
		serie.getEpisodios(3).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(3).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(3),new Episodio("Episodio 2", 2, 3));
	}
}
