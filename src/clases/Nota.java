package clases;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;

public class Nota implements Serializable{

	private Calendar fecha;
	private String texto;
	private String categoria;
	private static String[] categorias = {"Urgente", "Importante", "Normal"};
	
	public Nota(String texto, String categoria) {
		this.fecha = Calendar.getInstance();
		this.texto = texto;
		this.categoria = comprobarCategoria(categoria);
	};
	
	private String comprobarCategoria(String categoria) {
		String aux = categorias[0];
		if(categoria.equals(categorias[1])) {
			aux = categorias[1];
		}else if(categoria.equals(categorias[2])) {
			aux = categorias[2];
		}
		return aux;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = comprobarCategoria(categoria);
	}

	public static String[] getListaCategorias() {
		return categorias;
	}

	@Override
	public String toString() {
		
		String cat = categoria.substring(0,3);
		return cat + " :  " + fecha.get(Calendar.DAY_OF_MONTH) + "-" + fecha.get(Calendar.MONTH) + "-" + fecha.get(Calendar.YEAR) + "  -->  " + texto;
	}

}
