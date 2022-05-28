
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LeeYEscribe {

	public BufferedReader readFile() {
		String csvFile = "D:\\\\Programacion\\\\TPE-PARTE1\\\\assets\\\\dataset1.csv";
        try {
        	return new BufferedReader(new FileReader(csvFile)); 
           
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        
		return null;
	}
	
	public void writteFile(ArrayList<Libro> libros) {
		BufferedWriter bw = null;
		try {
			File file = new File("D:\\Programacion\\TPE-PARTE1\\assets\\nuevos");
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
