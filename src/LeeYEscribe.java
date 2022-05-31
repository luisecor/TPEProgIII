package TPEp1.TPEProgIII.src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeeYEscribe {

	public BufferedReader readFile() {
		String csvFile = "C:\\\\Users\\\\peter\\\\eclipse-workspace\\\\Prog3\\\\src\\\\TPEp1\\\\TPEProgIII\\\\assets\\\\dataset1.csv";
        try {
        	return new BufferedReader(new FileReader(csvFile)); 
           
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        
		return null;
	}
	
	public boolean writteFile(ArrayList<Libro> libros) {
		BufferedWriter bw = null;
		
		LocalDateTime hoy = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-YYYY_HH-mm-ss");
		String nombre = hoy.format(formato);
		
		try {
			File file = new File("D:\\Programacion\\TPE-PARTE1\\assets\\nuevos\\"+nombre.toString()+".csv");
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
