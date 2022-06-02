

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Biblioteca {
	private ArrayList<Libro> libros;
	private ArrayList<Genero> generos;
	
	public Biblioteca() {
		libros = new ArrayList<>();
		this.generos = new ArrayList<>();
	}
	
	public void cargarLibros() {
		String csvFile = ".\\assets\\dataset4.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	int fila = 0;
            while ((line = br.readLine()) != null) {
            	
            	if (fila>0) {
            		System.out.println(fila);
		                String[] items = line.split(",");
            			
		            
		                Libro libro = new Libro (items[0], items[1], Integer.parseInt(items[2]));
		                String[] generos = items[3].split(" ");
		              
		                //Que el libro solo tenga STRINGS - Modificado OK
		      
		                
		                for (String genero : generos) {
		                	Genero tmp = new Genero(genero);	// Creo el genero
		                	libro.addGenero(genero); 			// Se lo agrego al libro
							this.addGeneroOrdenado(tmp); 	// Agrego el Genero Nuevo a la bilioteca
							this.agregarLibroAGenero(tmp, libro); // Agrego el Libro al Genero de la Biblioteca
						}
		                libros.add(libro); //Libro cargado ok
		                   
            	}
            	fila++ ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean escribirArchivo(ArrayList<Libro> libros, String buscado) {
		LeeYEscribe admin = new LeeYEscribe();
		return admin.writteFile(libros,buscado);
	}
	
	public ArrayList<Libro> buscarLibrosPorGenero(String g){ //Modificado 31-05-2022 Lucho
		Genero tmp = new Genero(g);		
		int izquierda = 0;
		int derecha = generos.size()-1;
		ArrayList<Libro> resultado = new ArrayList<Libro> ();
		if (busquedaBinariaXGenero(tmp, izquierda, derecha)!=null)
			resultado.addAll(busquedaBinariaXGenero(tmp,izquierda,derecha).getLibros());
		 if (resultado!=null)
			 escribirArchivo(resultado,g);
		return resultado ;
	}
	
	private void agregarLibroAGenero(Genero g, Libro l) {
		Genero tmp = busquedaBinariaXGenero(g, 0, generos.size()-1);
		if (tmp!=null)
			tmp.addLibro(l);
	}
	
	private Genero busquedaBinariaXGenero(Genero genero, int izquierda, int derecha){
		if (izquierda > derecha) {
			return null;
		} else {
			int mitad = (izquierda + derecha)/2;
			if (genero.compareTo(this.generos.get(mitad)) > 0) {
				return busquedaBinariaXGenero(genero,mitad +1 , derecha);
			} else if (genero.compareTo(this.generos.get(mitad)) < 0) {
				return busquedaBinariaXGenero(genero, izquierda, mitad-1);
			} else 
				return this.generos.get(mitad);
		}
		
	}
	
	private void  buscarYagregar(Genero generoBuscado) {
		int medio, izq = 0 , der = generos.size()-1;
		boolean encontrado = false;
		while (!encontrado && izq <= der) {
			medio = (izq + der)/2;
			if (generoBuscado.compareTo(generos.get(medio)) < 0) der = medio -1;
			else if (generoBuscado.compareTo(generos.get(medio)) > 0) izq = medio+1;
			else encontrado = true;
		}
		if (!encontrado) {
			boolean agregado = false;
			try {
				if (generoBuscado.compareTo(generos.get(generos.size()-1)) > 0) {
					generos.add(izq, generoBuscado);
					agregado = true;
				}
					
			} catch (Exception e) {}
			try {
				if (generoBuscado.compareTo(generos.get(0)) < 0 && !agregado) {
					generos.add(0,generoBuscado);
					agregado = true;
				}
					
			} catch (Exception e) {}
			try {
				if (!agregado)
					if (izq<generos.size())
						generos.add(izq, generoBuscado);
					else
						generos.add(der, generoBuscado);
			} catch (Exception e) {}
		}
		
				
	}
	
	
	//MODIFICAR CONTAINS --> hacer un solo recorrido
	private void addGeneroOrdenado(Genero g) {
		if (generos.size()>0) {
				buscarYagregar(g);
		} else 
			generos.add(g);
	}
	
	
	@Override
	public String toString() {
		return "Cant Libros: " + libros.size() + " Cant G: " + generos.size();
	}

	public static void main (String[] args) {
		System.out.println("arte".compareTo("ciencia"));
		Biblioteca B = new Biblioteca();
		B.cargarLibros();
		System.out.println(B);
		System.out.println(B.generos);
//////		System.out.println(B.buscarLibrosPorGenero("noexiste"));
//		System.out.println((B.buscarLibrosPorGenero("noexiste") != null) ? B.buscarLibrosPorGenero("noexiste").size() : 0);
		System.out.println(B.buscarLibrosPorGenero("ensayo").size());
	}
}
