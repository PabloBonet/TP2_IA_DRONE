package frsf.ia.tp.paqueteGrafico;
import java.beans.PropertyVetoException;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import frsf.cidisi.exercise.libreriaclases.*;
import frsf.cidisi.exercise.situationCalculus.StateDrone;
import frsf.cidisi.exercise.situationCalculus.StateMap;
import frsf.cidisi.faia.simulator.events.EventHandler;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.ia.tp.exceciones.*;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



public class UIVentanaPrincipal extends JFrame implements EventHandler{
	
	
	//Atributos publicos para manejar la simulación
	private FuncionesAuxiliares.EstadoSimulacion estado;
	
	private static final long serialVersionUID = 1L;
	private Grafo grafo;
	private Converter datos;
	private ConverterEscenario nodosEscenario;
	private boolean datosCargados;
	private StateDrone estadoDrone;
	private StateMap estadoMapa;
	public Point posicionInicial;
	
	JPanel panelGrafico;
	JPanel panelInformativo;
	UIVentanaGrafica ventanaGrafica;

	private JMenuBar menuBar;
	private JMenu menuArchivo;
	private JMenuItem mnItemCargarEscenario;
	private JMenu menuVer;
	private JMenu menuHelp;
	private JSeparator separator;
	private JMenuItem mnItemSalir;
	private JMenuItem mnItemVerCuadrantes;
	private JPanel panel;
	private JLabel lblEstadoDelDrone;
	private JPanel panelEstado;
	private JLabel lblEnergia;
	private JLabel lblPosicion;
	//private JLabel lblSeniales;
	private JTextArea lblSeniales;
	private JLabel lblVictimarioIdentificado;
	private JLabel lblPosicionVictimario;

	public UIVentanaPrincipal() {
		
		inicializarVentanaPrincipal();
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws PropertyVetoException
	 */
	private void inicializarVentanaPrincipal() {

		datosCargados = false;
		
		setTitle("TP IA 2015 - Vehiculo Aéreo No Tripulado (VANT)");

		// tamaño de la ventana principal
		setSize(1200, 660);
		setResizable(false);
		setAlwaysOnTop(false);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuArchivo = new JMenu("Archivo");
		menuArchivo.setActionCommand("Archivo");
		menuBar.add(menuArchivo);

		mnItemCargarEscenario = new JMenuItem("Cargar Escenario");
		menuArchivo.add(mnItemCargarEscenario);

		
		mnItemCargarEscenario.addActionListener(new ControlerCargarEscenario());

		separator = new JSeparator();
		menuArchivo.add(separator);

		mnItemSalir = new JMenuItem("Salir");
		mnItemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				System.exit(0);
			}
		});
		menuArchivo.add(mnItemSalir);

		menuVer = new JMenu("Ver");
		menuBar.add(menuVer);

		mnItemVerCuadrantes = new JMenuItem("VisualizarCuadrantes");
		mnItemVerCuadrantes.setEnabled(false);
		menuVer.add(mnItemVerCuadrantes);

		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		// Se crea un Managment Layout del tipo grilla para gestionar la
		// ubicacion de los paneles
		GridLayout gestorDeFondo = new GridLayout(0, 2);
		getContentPane().setLayout(gestorDeFondo);

		// JPanel para alojar el InternalJFrame para el grafico del mapa
		panelGrafico = new JPanel();
		panelGrafico.setBorder(new TitledBorder(new LineBorder(new Color(64,
				64, 64), 2, true), "Area Grafica", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.DARK_GRAY));
		panelGrafico.setSize(600, 600);
		panelGrafico.setVisible(true);
		panelGrafico.setLayout(new BorderLayout(0, 0));

		// JPanel para alojar el JFrame para el informe de busqueda del agente
		panelInformativo = new JPanel();
		panelInformativo.setSize(600, 600);
		panelInformativo.setBorder(new TitledBorder(new LineBorder(new Color(
				64, 64, 64), 2, true), "Area Informativa", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.DARK_GRAY));
		panelInformativo.setVisible(true);

		// Se agregan los JPanels a la ventana principal
		getContentPane().add(panelGrafico);
		getContentPane().add(panelInformativo);
		panelInformativo.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panelInformativo.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblEstadoDelDrone = new JLabel("Estado del Drone");
		lblEstadoDelDrone.setBackground(Color.WHITE);
		lblEstadoDelDrone.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoDelDrone.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(lblEstadoDelDrone, BorderLayout.NORTH);
		
		panelEstado = new JPanel();
		panel.add(panelEstado, BorderLayout.CENTER);
		GridBagLayout gbl_panelEstado = new GridBagLayout();
		gbl_panelEstado.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelEstado.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelEstado.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEstado.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelEstado.setLayout(gbl_panelEstado);
		
		lblEnergia = new JLabel("Energ\u00EDa:");
		GridBagConstraints gbc_lblEnergia = new GridBagConstraints();
		gbc_lblEnergia.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnergia.gridx = 2;
		gbc_lblEnergia.gridy = 2;
		panelEstado.add(lblEnergia, gbc_lblEnergia);
		
		lblPosicion = new JLabel("Posici\u00F3n:");
		GridBagConstraints gbc_lblPosicion = new GridBagConstraints();
		gbc_lblPosicion.insets = new Insets(0, 0, 5, 0);
		gbc_lblPosicion.gridx = 2;
		gbc_lblPosicion.gridy = 3;
		panelEstado.add(lblPosicion, gbc_lblPosicion);
		
		
		//lblSeniales = new JLabel("Señales:");
		lblSeniales =  new JTextArea("Señales:");
		lblSeniales.setEditable(false);
		
		 GridBagConstraints gbc_lblSeniales = new GridBagConstraints();
		gbc_lblSeniales.insets = new Insets(0, 0, 5, 0);
		gbc_lblSeniales.gridx = 2;
		gbc_lblSeniales.gridy = 4;
		panelEstado.add(lblSeniales, gbc_lblSeniales);
		
		lblVictimarioIdentificado = new JLabel("Se identific\u00F3 al victimario:");
		GridBagConstraints gbc_lblVictimarioIdentificado = new GridBagConstraints();
		gbc_lblVictimarioIdentificado.insets = new Insets(0, 0, 5, 0);
		gbc_lblVictimarioIdentificado.gridx = 2;
		gbc_lblVictimarioIdentificado.gridy = 6;
		panelEstado.add(lblVictimarioIdentificado, gbc_lblVictimarioIdentificado);
		
		lblPosicionVictimario = new JLabel("Posici\u00F3n del victimario:");
		GridBagConstraints gbc_lblPosicionVictimario = new GridBagConstraints();
		gbc_lblPosicionVictimario.gridx = 2;
		gbc_lblPosicionVictimario.gridy = 7;
		panelEstado.add(lblPosicionVictimario, gbc_lblPosicionVictimario);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Carga el grafo ubicado en el archivo: "archivosCSV/nodosYenlaces.csv"
		cargarGrafo();
	}
	
	/**
	 * Fucnción que carga el archivo "archivosCSV/nodosYenlaces.csv"
	 * Este archivo contiene toda la información de los nodos y enlaces
	 * */
	private void cargarGrafo()
	{
		try {
			
			//File archivoElegido = fc.getSelectedFile();
			
			File archivoElegido = new File("archivosCSV" + "\\"+"nodosYenlaces.csv");
				
			/**Se crea una instancia de la Clase Converter*/
			datos = new Converter(archivoElegido);
					
			
			
			/**Se carga el escenario con los elementos devueltos por Converter*/
			grafo = new Grafo(datos.getListaNodos(),datos.getListaEnlaces());

				//creacion de la ventana grafica tomando los datos del grafo
				ventanaGrafica = new UIVentanaGrafica(grafo);
			//ventanaGrafica = new UIVentanaGrafica(this.estadoMapa, this.estadoDrone);	
			//ventanaGrafica = new UIVentanaGrafica(grafo, this.estadoDrone);
			ventanaGrafica.setAutoscrolls(true);
				panelGrafico.add(ventanaGrafica);
				ventanaGrafica.setVisible(true);
				ventanaGrafica.repaint();
				
				
				/*TODO luego tomar los datos del grafo y setearlos en la tabla que se muestra en pantalla**/

				//Completar


		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	
	//Clase que se utilizar para controlar los eventos del boton "Cargar Escenario"
	class ControlerCargarEscenario implements ActionListener {

		public void actionPerformed(ActionEvent e) {
				itmCargarArchivoActionEvent(e);
		}

		private void itmCargarArchivoActionEvent(ActionEvent e) {
			
			// Crear un objeto FileChooser
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter fiCsv = new FileNameExtensionFilter(".CSV","csv");
			

			fc.setFileFilter(fiCsv);
			fc.setVisible(true);
			fc.getAcceptAllFileFilter();
			//fc.setSelectedFile(new File("archivosCSV/nodosYenlaces.csv"));
			

			// Mostrar la ventana para abrir archivo y recoger la respuesta
			// En el parámetro del showOpenDialog se indica la ventana
			// al que estará asociado. Con el valor this se asocia a la
			// ventana que la abre.

			int respuesta = fc.showOpenDialog(null);
			

			// Comprobar si se ha pulsado Aceptar
			if (respuesta == JFileChooser.APPROVE_OPTION) {

				// Crear un objeto File con el archivo elegido
				try {
					
					File archivoElegido = fc.getSelectedFile();
					
				
					if (new EvaluaExtension().accept(archivoElegido, ".csv")) {
						
						//Se crea una instancia de la Clase ConverterEscenario
						nodosEscenario = new ConverterEscenario(archivoElegido);
						
						
						//Se cargan las personas al grafo con los elementos devueltos por ConverterEscenario
						
						for(Nodo nodo: nodosEscenario.getListaNodos())
						{
							for(Persona p: nodo.getPersonas())
							{
								(grafo.buscarNodo(nodo.getId())).agregarPersona(p);	
								
							}
							
						}

						//creacion de la ventana grafica tomando los datos del grafo
						panelGrafico.remove(ventanaGrafica);
						
						ventanaGrafica = new UIVentanaGrafica(grafo);
						//ventanaGrafica = new UIVentanaGrafica(grafo, estadoDrone);
						//System.out.println("CREA VENTANA GRAFICA");
						ventanaGrafica.setAutoscrolls(true);
						panelGrafico.add(ventanaGrafica);
						ventanaGrafica.setVisible(true);
						ventanaGrafica.repaint();
						
						
						
						//Carga la posicion inicial del drone.
						/*posicionInicial = new Point();
						
						//String posicion = JOptionPane.showInputDialog(this, "Indique la posición del drone. \nDebe ser con formato: x,y", "Posición del drone", JOptionPane.QUESTION_MESSAGE);
						
						UIDialogPosicion ventanaPosicion = new UIDialogPosicion();
					  	  
					  	  ventanaPosicion.show();
					  	  
					  	  posicionInicial = new Point(ventanaPosicion.posX, ventanaPosicion.posY);
					  	
					  	//ventanaPosicion.wait();
					  	  System.out.println("POSx: " + ventanaPosicion.posX  + " posY: " + ventanaPosicion.posY);
					  	
						if(ventanaPosicion.b_ok)
						{
							System.out.println("b_ok");
							datosCargados = true;
							ventanaPosicion = null;
						}
							*/
						/*luego tomar los datos del grafo y setearlos en la tabla que se muestra en pantalla**/

						//Completar
						datosCargados = true;
					} else {
						throw new FormatoDeArchivoNoValidoException();
					}

				} catch (Exception exception) {
					exception.getMessage();
				}
			}
		}
		
		private class EvaluaExtension implements java.io.FilenameFilter {
			public boolean accept(File dir, String extension) {
				return dir.getName().endsWith(extension);
			}
		}
}
	public void setEstadoDrone(StateDrone estadoDrone)
	{
		this.estadoDrone = estadoDrone;
	}
	public Grafo getGrafo() {
		return grafo;
	}
	
	public FuncionesAuxiliares.EstadoSimulacion getEstado()
	{
		return estado;
	}
	
	public boolean datosCargados()
	{
		return datosCargados;
	}

	@Override
	public void runEventHandler(Object[] params) {
		if(estadoDrone != null) {

			int energia = estadoDrone.getEnergiaActual();
			
			this.lblEnergia.setText("Energía: " + String.valueOf(energia));
			
			this.lblPosicion.setText("Posición: " + String.valueOf(estadoDrone.getPosicionActual().x) + ", " + String.valueOf(estadoDrone.getPosicionActual().y));
			
			String str =  "Señales: ";
		       for(int i = 0; i < estadoDrone.getSeñales().length; i++)
		       {
		    	   if(estadoDrone.getSeñales()[i][0] != 0) //si no tiene señal en esa posición
		    	   {
		    		   str = str + "Nodo " + (i+1) + ": Posición: "+ estadoDrone.getSeñales()[i][1] + ", " + estadoDrone.getSeñales()[i][2]+ ". Cantidad de personas: " + estadoDrone.getSeñales()[i][0];
		    		   
		    		   str = str + "\n";
		    	   }
		    	   
		       }
		       this.lblSeniales.setText(str);
		       
		      
		       str = "";
		       
		       if(estadoDrone.getIdVictimario() == 0)
		       {
		     	  str = str + " Aún no se identificó al victimario";
		       }
		       else
		       {
		     	  
		     	  str = str + " Se ha encontrado al victimario!!. ID del Victimario: "+ estadoDrone.getIdVictimario() + "";
		
		     	  
		       }
		       
		       this.lblVictimarioIdentificado.setText(str);
		       if( estadoDrone.getPosicionVictimario().x != -1 && estadoDrone.getPosicionVictimario().y != -1)
		       {
		    	   str = "Cámara: ";
			       str= str + "Posición del victimario: " + estadoDrone.getPosicionVictimario().x + ", "+ estadoDrone.getPosicionVictimario().y;
			     	   
		       }
		       else
		       {
		    	   str = "";
		       }
		      
		       this.lblPosicionVictimario.setText(str);
		       
		       str = "";
		       
		       panel.remove(ventanaGrafica);
		       ventanaGrafica = new UIVentanaGrafica(grafo);
				//ventanaGrafica = new UIVentanaGrafica(grafo, estadoDrone);
				//System.out.println("CREA VENTANA GRAFICA");
				ventanaGrafica.setAutoscrolls(true);
				ventanaGrafica.setEstadoAgente(estadoDrone);
				panelGrafico.add(ventanaGrafica);
				ventanaGrafica.setVisible(true);
				//ventanaGrafica.repaint();
			
				//ventanaGrafica.remove(mapa);
				
			//pl.inicializarPercepcion(estadoAmbiente,estadoRonly);
			//ventanaGrafica.mapa = new UIMapa(grafo);
			//ventanaGrafica.mapa.setEstadoAgente(estadoDrone);
	    	//ventanaGrafica.mapa.repaint();
    	}
	}

	
}






