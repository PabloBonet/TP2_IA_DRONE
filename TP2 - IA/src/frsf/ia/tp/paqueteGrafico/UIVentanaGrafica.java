package frsf.ia.tp.paqueteGrafico;



import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import frsf.cidisi.exercise.libreriaclases.*;
import javax.swing.UIManager;



public class UIVentanaGrafica extends JInternalFrame {

	UIMapa mapa;
	
	public UIVentanaGrafica(Grafo grafo)  {
		//setRootPaneCheckingEnabled(false);
		inicializar(grafo);
	}
	
	private void inicializar(Grafo grafo){
		setSize(600, 600);
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
