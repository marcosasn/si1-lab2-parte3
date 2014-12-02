import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import models.GenericDAO;
import models.Serie;
import play.*;
import play.db.jpa.JPA;


public class Global extends GlobalSettings {

	private static GenericDAO dao = new GenericDAO();

	@Override
	public void onStart(Application app) {
		Logger.info("inicializada...");

		JPA.withTransaction(new play.libs.F.Callback0() {
			
			public void invoke() throws Throwable {
				
				//
				CSVReader reader = new CSVReader(new FileReader("seriesFinalFile.csv"));
		        String [] nextLine;
		        List<Serie> series = new ArrayList<Serie>();
		        while ((nextLine = reader.readNext()) != null) {
		        	
		            // nextLine[] is an array of values from the line
		        	Serie newSerie  = new Serie(nextLine[0]);
		        	if(!series.contains(newSerie)) {
		        		series.add(newSerie);
		        	}

		        }
		        
		        for (int i = 0; i < series.size(); i++) {
					dao.persist(series.get(i));
				}
		         
		        //close reader
		        reader.close();
			}
		});
	}
	
	public void onStop(Application app) {
		Logger.info("desligada...");
	}

}
