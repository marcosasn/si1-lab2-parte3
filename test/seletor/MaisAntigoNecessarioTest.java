package seletor;

import static org.junit.Assert.*;

import models.Episodio;
import models.Serie;
import models.seletor.MaisAntigoNecessario;
import org.junit.Before;
import org.junit.Test;

public class MaisAntigoNecessarioTest {

	Serie serie;
	
	@Before
	public void setUp() throws Exception {
		serie = new Serie("serie A");
		serie.setSeletorProximoEpisodio(new MaisAntigoNecessario());
			
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
	public void testMaisAntigoNecessarioPrimeiraTemporada() {		
		/*serie.addEpisodio("Episodio 1", 1, 1);[visto]
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);*/
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 1", 1, 1));
		assertEquals(serie.getEpisodios(1).get(0),new Episodio("Episodio 1", 1, 1));
		serie.getEpisodios(1).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(1).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 2", 2, 1));

		/*serie.addEpisodio("Episodio 1", 1, 1);[visto]
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);[visto]*/
		serie.getEpisodios(1).get(2).mudaStatus();
		assertTrue(serie.getEpisodios(1).get(2).isAssistido());
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 2", 2, 1));
		
		/*serie.addEpisodio("Episodio 1", 1, 1);
		serie.addEpisodio("Episodio 2", 2, 1);
		serie.addEpisodio("Episodio 3", 3, 1);[visto]*/
		serie.getEpisodios(1).get(0).mudaStatus();
		assertFalse(serie.getEpisodios(1).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 1", 1, 1));
		
		/*serie.addEpisodio("Episodio 1", 1, 1);[visto]
		serie.addEpisodio("Episodio 2", 2, 1);[visto]
		serie.addEpisodio("Episodio 3", 3, 1);*/
		serie.getEpisodios(1).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(1).get(0).isAssistido());
		serie.getEpisodios(1).get(1).mudaStatus();
		assertTrue(serie.getEpisodios(1).get(1).isAssistido());
		serie.getEpisodios(1).get(2).mudaStatus();
		assertFalse(serie.getEpisodios(1).get(2).isAssistido());	
		assertEquals(serie.getProximoEpisodio(1),new Episodio("Episodio 3", 3, 1));
	}

	@Test
	public void testMaisAntigoNecessarioSegundaTemporada() {
		/*serie.addEpisodio("Episodio 1", 1, 2);[visto]
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);
		serie.addEpisodio("Episodio 5", 5, 2);*/
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 1", 1, 2));
		assertEquals(serie.getEpisodios(2).get(0),new Episodio("Episodio 1", 1, 2));
		serie.getEpisodios(2).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(2).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 2", 2, 2));
		
		/*serie.addEpisodio("Episodio 1", 1, 2);[visto]
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);[visto]
		serie.addEpisodio("Episodio 5", 5, 2);*/
		serie.getEpisodios(2).get(3).mudaStatus();
		assertTrue(serie.getEpisodios(2).get(3).isAssistido());
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 2", 2, 2));
		
		/*serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);
		serie.addEpisodio("Episodio 3", 3, 2);
		serie.addEpisodio("Episodio 4", 4, 2);[visto]
		serie.addEpisodio("Episodio 5", 5, 2);*/
		serie.getEpisodios(2).get(0).mudaStatus();
		assertFalse(serie.getEpisodios(2).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 1", 1, 2));

		/*serie.addEpisodio("Episodio 1", 1, 2);
		serie.addEpisodio("Episodio 2", 2, 2);[visto]
		serie.addEpisodio("Episodio 3", 3, 2);[visto]
		serie.addEpisodio("Episodio 4", 4, 2);[visto]
		serie.addEpisodio("Episodio 5", 5, 2);*/
		serie.getEpisodios(2).get(1).mudaStatus();
		serie.getEpisodios(2).get(2).mudaStatus();
		assertEquals(serie.getProximoEpisodio(2),new Episodio("Episodio 5", 5, 2));
	}
	
	@Test
	public void testMaisAntigoNessesarioTerceiraTemporada() {
		/*serie.addEpisodio("Episodio 1", 1, 3);[visto]
		serie.addEpisodio("Episodio 2", 2, 3);
		serie.addEpisodio("Episodio 3", 2, 3);*/
		assertEquals(serie.getProximoEpisodio(3),new Episodio("Episodio 1", 1, 3));
		assertEquals(serie.getEpisodios(3).get(0),new Episodio("Episodio 1", 1, 3));
		serie.getEpisodios(3).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(3).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(3),new Episodio("Episodio 2", 2, 3));
		
		/*serie.addEpisodio("Episodio 1", 1, 3);[visto]
		serie.addEpisodio("Episodio 2", 2, 3);
		serie.addEpisodio("Episodio 3", 2, 3);[visto]*/
		serie.getEpisodios(3).get(2).mudaStatus();
		assertTrue(serie.getEpisodios(3).get(2).isAssistido());
		assertEquals(serie.getProximoEpisodio(3),new Episodio("Episodio 2", 2, 3));
		
		/*serie.addEpisodio("Episodio 1", 1, 3);
		serie.addEpisodio("Episodio 2", 2, 3);
		serie.addEpisodio("Episodio 3", 2, 3);[visto]*/
		serie.getEpisodios(3).get(0).mudaStatus();
		assertFalse(serie.getEpisodios(3).get(0).isAssistido());
		assertEquals(serie.getProximoEpisodio(3),new Episodio("Episodio 1", 1, 3));	
		
		/*serie.addEpisodio("Episodio 1", 1, 3);[visto]
		serie.addEpisodio("Episodio 2", 2, 3);[visto]
		serie.addEpisodio("Episodio 3", 2, 3);[visto]*/
		serie.getEpisodios(3).get(0).mudaStatus();
		assertTrue(serie.getEpisodios(3).get(0).isAssistido());
		serie.getEpisodios(3).get(1).mudaStatus();
		assertTrue(serie.getEpisodios(3).get(1).isAssistido());
		assertEquals(serie.getProximoEpisodio(3),null);	
	}
}