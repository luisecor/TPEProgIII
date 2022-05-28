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
	
	public void writteFile(List<Libro> libros) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Users\\peter\\eclipse-workspace\\Prog3\\src\\TPEp1\\TPEProgIII\\assets");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			Iterator itLibros = libros.iterator();
			while (itLibros.hasNext()) {
				Libro libro = (Libro) itLibros.next();
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
