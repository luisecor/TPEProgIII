package TPEp1.TPEProgIII.src;



import java.util.ArrayList;



public class Libro {
	private String titulo;
	private String autor;
	private int paginas;
	private ArrayList<Genero> generos;
	
	
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
	public ArrayList<Genero> getGeneros() {
		return generos;
	}
	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}

	public void addGenero(Genero genero) {
		this.generos.add(genero);
		
	}

	@Override
	public String toString() {
		return "titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + ", generos=" + generos;
	}
	
	
	

}
