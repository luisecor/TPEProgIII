

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
	
	private void buscarYagregar(Libro libro) {
		int medio, izq = 0 , der = libros.size()-1;
		boolean encontrado = false;
		while (!encontrado && izq <= der) {
			medio = (izq + der)/2;
			if (libro.compareTo(libros.get(medio)) < 0) der = medio -1;
			else if (libro.compareTo(libros.get(medio)) > 0) izq = medio+1;
			else encontrado = true;
		}
		if (!encontrado) {
			boolean agregado = false;
			try {
				if (libro.compareTo(libros.get(libros.size()-1)) > 0) {
					libros.add(izq, libro);
					agregado = true;
				}
					
			} catch (Exception e) {}
			try {
				if (libro.compareTo(libros.get(0)) < 0 && !agregado) {
					libros.add(0,libro);
					agregado = true;
				}
					
			} catch (Exception e) {}
			try {
				if (!agregado)
					if (izq<libros.size())
						libros.add(izq, libro);
					else
						libros.add(der, libro);
			} catch (Exception e) {}
		}
		
	}
	

	public void addLibro(Libro libro) {
		if (libros.size()>0) {
			buscarYagregar(libro);
		} else
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
		return  genero;
	}


	
	
	

}
