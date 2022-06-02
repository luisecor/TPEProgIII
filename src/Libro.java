

import java.util.ArrayList;
import java.util.Objects;



public class Libro implements Comparable<Libro> {
	private String titulo;
	private String autor;
	private int paginas;
	private ArrayList<String> generos;
	
	
	public Libro(String titulo, String autor, int paginas) {
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.generos = new ArrayList<>();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public ArrayList<String> getGeneros() {
		return generos;
	}
	public void setGeneros(ArrayList<String> generos) {
		this.generos = generos;
	}
	
	private void buscarYagregar(String genero) {
		int medio, izq = 0 , der = generos.size()-1;
		boolean encontrado = false;
		while (!encontrado && izq <= der) {
			medio = (izq + der)/2;
			if (genero.compareTo(generos.get(medio)) < 0) der = medio -1;
			else if (genero.compareTo(generos.get(medio)) > 0) izq = medio+1;
			else encontrado = true;
		}
		if (!encontrado) {
			boolean agregado = false;
			try {
				if (genero.compareTo(generos.get(generos.size()-1)) > 0) {
					generos.add(izq, genero);
					agregado = true;
				}
					
			} catch (Exception e) {}
			try {
				if (genero.compareTo(generos.get(0)) < 0 && !agregado) {
					generos.add(0,genero);
					agregado = true;
				}
					
			} catch (Exception e) {}
			try {
				if (!agregado)
					if (izq<generos.size())
						generos.add(izq, genero);
					else
						generos.add(der, genero);
			} catch (Exception e) {}
		}
	}
	
	public void addGenero(String genero) { //Modificacion 01/06/2022 lucho
		if (generos.size()>0) 
			buscarYagregar(genero);		
		else
			generos.add(genero);
	}
	
	private String concatenarGeneros() {
		String cadena = "";
		for (String genero : generos)
			cadena+=genero + " ";
		return cadena;
	}

	@Override
	public String toString() {
		return  titulo + "," + autor + ", " + paginas + "," + concatenarGeneros();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(titulo, other.getTitulo());
	}

	@Override
	public int compareTo(Libro o) {
		return titulo.compareTo(o.getTitulo());
	}
	
	
	

}
