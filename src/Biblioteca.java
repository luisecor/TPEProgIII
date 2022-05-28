

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;



public class Biblioteca {
	private ArrayList<Libro> libros;
	private ArrayList<Genero> generos;
	
	public Biblioteca() {
		libros = new ArrayList<>();
		this.generos = new ArrayList<>();
	}
	
	public void cargarLibros() {
		LeeYEscribe admin = new LeeYEscribe();
		BufferedReader archivo = admin.readFile();
		String csvFile = "D:\\Programacion\\TPE-PARTE1\\assets\\dataset1.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	int fila = 0;
            while ((line = br.readLine()) != null) {
            	
           
            	if (fila>0) {
            		
		                String[] items = line.split(",");
            			
		            
		                Libro libro = new Libro (items[0], items[1], Integer.parseInt(items[2]));
		                String[] generos = items[3].split(" ");
		              
		                
		                for (String genero : generos) {
		                	Genero tmp = new Genero(genero);
		                	libro.addGenero(tmp);
							if(!this.generos.contains(tmp)) {
								tmp.addLibro(libro);
								this.addGeneroOrdenado(tmp);
							}
							else {
								this.agregarLibroAGenero(genero, libro);
							}
						}
		                libros.add(libro);
		                   
            	}
            	fila++ ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ArrayList<Libro> buscarLibrosPorGenero(String g){
		
		//BUSQUEDA BINARIA
		
		return libros;
		
	}

	private void agregarLibroAGenero(String genero, Libro libro) {
		if(generos.contains(genero)) {//VERIFICAR generos son clase Genero, y el dato es String
			int i = generos.indexOf(genero);
			this.generos.get(i).addLibro(libro);
		}
		
	}

	private void addGeneroOrdenado(Genero g) {
		/*if (this.generos!=null) {
			int i=0;
			while(generos.get(i).compareTo(g)<0) {
				i++;
			}
			generos.add(i, g);
			} else {
				generos.add(g);
			}*/
			
			if (generos!=null && !generos.contains(g)) {
				generos.add(g);
			} 			
			
			Collections.sort(generos);
		}
		
		
	
	
	

	public static void main (String[] args) {
		Biblioteca B = new Biblioteca();
		B.cargarLibros();
		System.out.println(B.generos);
		
	}
}
