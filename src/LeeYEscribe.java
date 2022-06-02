
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LeeYEscribe {

	public BufferedReader readFile(String csvFile) {
        try {
        	return new BufferedReader(new FileReader(csvFile)); 
           
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        
		return null;
	}
	
	@SuppressWarnings("finally")
	public boolean writteFile(ArrayList<Libro> libros,String buscado) {
		BufferedWriter bw = null;
		
		LocalDateTime hoy = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-YYYY_HH-mm-ss");
		String nombre = buscado+"-"+ hoy.format(formato);
		
		try {
			File file = new File(".\\assets\\nuevos\\"+nombre.toString()+".csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (Libro libro : libros) {
				String contenidoLinea1 = libro.toString();
				bw.write(contenidoLinea1);
				bw.newLine();	
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				return true;
			} catch (final Exception ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}

}
