

import java.util.ArrayList;
import java.util.Objects;

public class Genero implements Comparable<Genero>{
	private String genero;
	private ArrayList<Libro> libros;
	
	public String getGenero() {
		String nombre = new String (genero);
		return nombre;
	}

	public Genero(String genero) {
		this.genero = genero;
		libros = new ArrayList<>();
	}

	public void addLibro(Libro libro) {
		if(!libros.contains(libro))
		libros.add(libro);
	}
	
	public ArrayList<Libro> getLibros(){
		return new ArrayList<Libro>(libros);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		return Objects.equals(genero, other.genero);
	}
	
	public int compareTo(Genero g2) {
		return this.genero.compareTo(g2.genero);
	}

	@Override
	public String toString() {
		return  genero ;
	}


	
	
	

}
