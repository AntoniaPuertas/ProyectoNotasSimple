package clases;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import io.EscribirFicheros;
import io.LeerFichero;

public class GestionNotas {
	
	//arraylist dónde se van a almacenar los datos en tiempo de ejecución
	static ArrayList<Nota> listaNotas;


	public static void main(String[] args) {
		
		//ruta y nombre del archivo dónde se van a guardar los datos
		String rutaDatos = "datos.dt";
		
		//arraylist dónde se van a almacenar los datos en tiempo de ejecución
		listaNotas = new ArrayList<>(); 
		
		//instancia del tipo nota
		Nota nota;
		
		//instancia para leer los datos del fichero
		LeerFichero entradaDatos = new LeerFichero();
		
		//instancia para escribir los datos en fichero
		EscribirFicheros salidaDatos = new EscribirFicheros();
		
		//lee los datos del fichero y los guarda en el arraylist
		try {
			entradaDatos.abrir(rutaDatos);
			do {
				nota = (Nota) entradaDatos.leer();
				if(nota != null) {
					listaNotas.add(nota);
				}
			}while(nota != null);
			
			entradaDatos.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		//crea una instancia de la ventana y le pasa el arraylist con los datos
		VentanaPrincipal ventanaP = new VentanaPrincipal(listaNotas);
		ventanaP.setVisible(true);
		
		
		//listener para la ventana
		ventanaP.addWindowListener(new WindowListener() {

			//cuando se cierre la ventana guardo los datos del arraylist en el fichero
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					salidaDatos.abrir(rutaDatos);
					for (Nota nota : listaNotas) {
						salidaDatos.escribir(nota);
					}
					salidaDatos.cerrar();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
	}
	
	

}
