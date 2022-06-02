package TPEProgIII.src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Biblioteca {
	private LinkedList<Libro> libros;
	private ArrayList<Genero> generos;
	
	public Biblioteca() {
		libros = new LinkedList<>();
		this.generos = new ArrayList<>();
	}
	
	public void cargarLibros() {
		LeeYEscribe admin = new LeeYEscribe();
		BufferedReader archivo = admin.readFile();
		String csvFile = "C:\\Users\\peter\\eclipse-workspace\\Prog3\\src\\TPEProgIII\\assets\\dataset4.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	int fila = 0;
            while ((line = br.readLine()) != null) {
            	
            	if (fila>0) {

		                String[] items = line.split(",");
            			
		            
		                Libro libro = new Libro (items[0], items[1], Integer.parseInt(items[2]));
		                String[] generos = items[3].split(" ");
		              
		                //Que el libro solo tenga STRINGS
		                //Cotains usa uno a uno
		                
		                for (String genero : generos) {
		                		 //Creo el genero
		                	libro.addGenero(genero); 				// Se lo agrego al libro
							if(!this.generos.contains(genero)) {
								Genero tmp = new Genero(genero); //Si la biblioteca no tiene el genero (hacer Busqueda Binaria?)
								tmp.addLibro(libro); 			//Agrego el libro al Genero de la Biblioteca
								int inicio = 0;
								int longGeneros = this.generos.size()-1;
								this.addGeneroOrdenado(tmp, inicio, longGeneros); 	//Agrego el Genero Nuevo a la bilioteca
							}
							else {
								this.agregarLibroAGenero(genero, libro); // Agrego al Genero de La Biblioteca el Nuebo Libro;
							}
						}
		                libros.add(libro); //Libro cargado ok
		                   
            	}
            	fila++ ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public boolean escribirArchivo(ArrayList<Libro> libros) {
		LeeYEscribe admin = new LeeYEscribe();
		return admin.writteFile(libros);
	}
	
	public ArrayList<Libro> buscarLibrosPorGenero(String g){ //Modificado 31-05-2022 Lucho
		Genero tmp = new Genero(g);		
		int izquierda = 0;
		int derecha = generos.size()-1;
		ArrayList<Libro> resultado = new ArrayList<Libro> ();
		resultado.addAll(busquedaBinaria(tmp,izquierda,derecha));
//		 if (resultado!=null)
//			 escribirArchivo(resultado);
		return resultado ;
	}
	
	private ArrayList<Libro> busquedaBinaria(Genero genero, int izquierda, int derecha){
		if (izquierda > derecha) {
			return null;
		} else {
			int mitad = (izquierda + derecha)/2;
			if (genero.compareTo(this.generos.get(mitad).getGenero()) > 0) {
				return busquedaBinaria(genero,mitad +1 , derecha);
			} else if (genero.compareTo(this.generos.get(mitad).getGenero()) < 0) {
				return busquedaBinaria(genero, izquierda, mitad-1);
			} else 
				return this.generos.get(mitad).getLibros();
		}
		
	}
	

	private void agregarLibroAGenero(String genero, Libro libro) { //Modificado 31-05-2022 Lucho
		int index = this.generos.indexOf(genero);
		this.generos.get(index).addLibro(libro);
	}

	private void addGeneroOrdenado(Genero g, int izquierda, int derecha) {
		if (!generos.isEmpty()) {
			if (izquierda < derecha) {
				int mitad = (izquierda+derecha)/2;
				if(g.compareTo(this.generos.get(mitad).getGenero()) < 0) {
					addGeneroOrdenado(g, izquierda, mitad-1);
				} else if(g.compareTo(this.generos.get(mitad).getGenero()) > 0) {
					addGeneroOrdenado(g, mitad+1, derecha); 
				}
			}else if(izquierda == derecha && generos.get(izquierda).compareTo(g.getGenero())!=0) {
				generos.add(izquierda, g);
			}
			
		} 
		else {
			generos.add(g);
		}
	}
	
	
	@Override
	public String toString() {
		return "Cant Libros: " + libros.size() + " Cant G: " + generos.size();
	}

	public static void main (String[] args) {
		Biblioteca B = new Biblioteca();
		B.cargarLibros();
//		System.out.println(B);
		System.out.println(B.generos);
//////		System.out.println(B.buscarLibrosPorGenero("noexiste"));
////		System.out.println((B.buscarLibrosPorGenero("noexiste") != null) ? B.buscarLibrosPorGenero("noexiste").size() : 0);
		System.out.println(B.buscarLibrosPorGenero("arte"));
//		
	}
}