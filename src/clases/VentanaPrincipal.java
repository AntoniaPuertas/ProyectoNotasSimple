package clases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;


public class VentanaPrincipal extends JFrame{
	
	private JList<Nota> listNota;
	private DefaultListModel<Nota> model;
	private JPanel contentPane;
	private ArrayList<Nota> listaNotas;

	/**
	 * Constructor que se encarga de crear la ventana que contiene todos los componentes
	 * de la ventana principal de la aplicación
	 * @param listaNotas
	 */
	public VentanaPrincipal(ArrayList<Nota> listaNotas) {
		//titulo de la ventana
		super("Proyecto Notas Simple");
		
		//tamaño
		setBounds(100,100,750,400);
		
		//acción que se realiza al cerrar la ventana: cerrar la aplicación
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//guardo la referencia a los datos
		this.listaNotas = listaNotas;
		
		//crea un contenedor	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
			
		//añade los paneles al contenedor
		JPanel panelDatos = getPanelDatos();
		contentPane.add(panelDatos, BorderLayout.NORTH);
		contentPane.add(getPanelNotas(), BorderLayout.CENTER);
		contentPane.add(getPanelBotones(), BorderLayout.SOUTH);
		
		//accedo a los componentes del panel de datos
    	JTextField cajaTexto =(JTextField) panelDatos.getComponent(0);
    	JComboBox combo = (JComboBox) panelDatos.getComponent(1);
    	JButton botonNueva = (JButton) panelDatos.getComponent(2);
		
    	//listener para crear una nota nueva
    	botonNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//lee el texto introducido y le borra los espacios del principio y final
				String texto = cajaTexto.getText().trim();
				//lee la categoría seleccionada
				String categoriaSeleccionada = (String) combo.getSelectedItem();

				
				if(texto.length() > 0) {//si hay texto
					//crea una nota nueva con los datos leidos
					Nota notaAux = new Nota(texto, categoriaSeleccionada);
					//le agrega la nota al modelo que alimenta al jlist
					model.addElement(notaAux);
					//le agrega la nota al arraylist de datos
					listaNotas.add(notaAux);
					//limpia el texto de la caja de texto
					cajaTexto.setText(null);
				}else {//si no hay texto 
					//lanza un mensaje indicando que debe haber texto para la nota
					JOptionPane.showMessageDialog(null, "Debes escribir alguna nota", "UPSS!!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	
	/**
	 * Método que se encarga de crear un JPanel que contiene:
	 * Un JTextField para introducir el texto de la nota
	 * Un JComboBox para seleccionar la categoría de la nota
	 * Un JButton para crear una nueva nota
	 * @return JPanel
	 */
	private JPanel getPanelDatos() {
		//crea el panel para el primer bloque
				JPanel panelDatos = new JPanel();
				panelDatos.setLayout(new FlowLayout());
				
				//caja de texto para el texto de la nota
				JTextField jTxtTextoNota = new JTextField(30);
				panelDatos.add(jTxtTextoNota);
				
				//combo desplegable para el tipo de nota
				JComboBox combo = new JComboBox();
				String[] listaCategorias = Nota.getListaCategorias();
				
				//rellena el combo con las categorías
				for (String categoria : listaCategorias) {
					combo.addItem(categoria);
				}
				panelDatos.add(combo);
				
				//boton para crear nota
				JButton btnCrear = new JButton("Nueva");
				panelDatos.add(btnCrear);
				
				return panelDatos;
	}
	

	/**
	 * Método que se encarga de crear un JPanel que contiene:
	 * Un JCrollPane para mostrar el listado de notas
	 * @return JPanel
	 */
	private JPanel getPanelNotas() {
		//crea el panel para el segundo bloque que contiene el listado de notas
				JPanel panelNotas = new JPanel(new BorderLayout());
				panelNotas.setBorder(new EmptyBorder(5,5,5,5));
				//Crea el modelo de datos que va se van a listar
				listNota = createListNotas();
				panelNotas.add(new JScrollPane(listNota));
				return panelNotas;
	}
	
	
	/**
	 * Método que se encarga de crear un JPanel que contiene:
	 * Un JButton para eliminar nota
	 * Un JButton para modificar nota
	 * @return
	 */
	private JPanel getPanelBotones() {
		//crea el panel para el tercer bloque que contiene dos botones
				JPanel panelBotones = new JPanel();
				panelBotones.setLayout(new FlowLayout());
				
				//botones para eliminar y modificar nota
				JButton btnEliminar = new JButton("Eliminar");
				JButton btnModificar = new JButton("Modificar");
				
				//listener para el evento de pulsar btnEliminar
				btnEliminar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						//guarda en una nota el elemento seleccionado
						Nota nota = listNota.getSelectedValue();
						//guardo en un int el indice seleccionado
						int selectedIndex = listNota.getSelectedIndex();
						
						
						if (selectedIndex != -1) {//comprueba que se ha seleccionado una nota
							//muestra una ventana de diálogo para confirmar que se quiere borrar la nota
							int dialogResult = JOptionPane.showConfirmDialog (null, nota.toString(), "¿Seguro que quieres eliminar esta nota?", JOptionPane.YES_NO_OPTION);
							if(dialogResult == 0) {//seleccionada la opción si
								//borra la nota del modelo
								model.remove(selectedIndex);
								//elimina la nota del arraylist
								listaNotas.remove(nota);
							} 
						}else {//no hay ninguna nota seleccionada
							//muestra una ventana informando del error
							JOptionPane.showMessageDialog(null, "Debes seleccionar alguna nota", "UPSS!!", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				
				//listener para el evento de pulsar btnModificar
				btnModificar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int selectedIndex = listNota.getSelectedIndex();
						if (selectedIndex != -1) {//hay una nota seleccionada. Llama al método modificaNota
							modificaNota();
						}else {//no hay ninguna nota seleccionada
							//muestra una ventana informando del error
							JOptionPane.showMessageDialog(null, "Debes seleccionar alguna nota", "UPSS!!", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				//añade los botones al panel
				panelBotones.add(btnModificar);
				panelBotones.add(btnEliminar);
				
				return panelBotones;
	}
	
	/**
	 * Método que se encarga de crear el listado de datos que va a mostrar el JSCrollPane
	 * @return JList<Nota> con los datos
	 */
    private JList<Nota> createListNotas() {
        // crea el modelo
        model = new DefaultListModel<>();
        // agrega los datos al modelo
        for (Nota nota : this.listaNotas) {
			model.addElement(nota);
		}

        // crea el JList con el modelo
        listNota = new JList<Nota>(model);
        //establece que el modo de selección de los datos es simple
        //solo puede haber un dato seleccionado
        listNota.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listNota;
    }
    
    /**
     * Método que se encarga de modificar la nota seleccionada en el JSCrollPane
     */
    private void modificaNota() {
    	//crea una nueva ventana utilizando el panelDatos
    	JFrame frame = new JFrame();
    	JPanel panel = getPanelDatos();
    	
    	//le cambia el título
    	frame.setTitle("Modifica nota");
    	
    	//le cambia el tamaño
    	frame.setBounds(100,400,550,200);
    			
    	//acción que se realiza al cerrar la ventana
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	//accedo a los componentes del panel
    	JTextField cajaTexto =(JTextField) panel.getComponent(0);
    	JComboBox combo = (JComboBox) panel.getComponent(1);
    	JButton botonModificar = (JButton) panel.getComponent(2);
    	
    	//accedo a la nota seleccionada
    	Nota notaSEleccionada = listNota.getSelectedValue();
		
    	//relleno los componentes con los valores de la nota
    	cajaTexto.setText(notaSEleccionada.getTexto());
    	
    	combo.setSelectedItem(notaSEleccionada.getCategoria());
    	
    	//cambia el texto del JButton
    	botonModificar.setText("Modificar");

    	//listener para cuando se pulse el botonModificar
    	botonModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//recoje los valores introducidos y se los establece a la nota seleccionada
				notaSEleccionada.setTexto(cajaTexto.getText());
				notaSEleccionada.setCategoria((String)combo.getSelectedItem());
			
				listNota.updateUI();
				frame.dispose();
			}
		});
		
		frame.add(panel);
		frame.setVisible(true);
    }

}
