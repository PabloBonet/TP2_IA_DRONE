package frsf.ia.tp.paqueteGrafico;



import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import frsf.cidisi.exercise.libreriaclases.*;
import frsf.cidisi.exercise.situationCalculus.StateDrone;
import frsf.cidisi.exercise.situationCalculus.StateMap;

import javax.swing.UIManager;



public class UIVentanaGrafica extends JInternalFrame {

	UIMapa mapa;
	
	public UIVentanaGrafica(Grafo grafo)  {
		//setRootPaneCheckingEnabled(false);
		inicializar(grafo);
	}
	public UIVentanaGrafica(Grafo grafo, StateDrone estDrone)  {
		//setRootPaneCheckingEnabled(false);
		inicializar(grafo);
		mapa.setEstadoAgente(estDrone);
	}
	
	public UIVentanaGrafica(StateMap estMapa, StateDrone estDrone)
	{
		inicializar(estMapa.getMapa());
		mapa.setEstadoAgente(estDrone);
	}
	
	public void setEstadoAgente(StateDrone estDrone)
	{
		this.mapa.setEstadoAgente(estDrone);
	}
	
	private void inicializar(Grafo grafo){
		setSize(150, 150);
		setTitle("mapa");
		setAlignmentX(0); setAlignmentY(0);
		setResizable(false);
		setClosable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		mapa = new UIMapa(grafo);
		
		getContentPane().add(mapa);
	}
}
