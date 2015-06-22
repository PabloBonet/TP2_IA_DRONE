package frsf.cidisi.exercise.situationCalculus;

import java.awt.Point;

import javax.swing.JOptionPane;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import frsf.ia.tp.paqueteGrafico.UIDialogPosicion;
import frsf.ia.tp.paqueteGrafico.UIVentanaPrincipal;

public class AgentDroneMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
	

    public static void main(String[] args) throws PrologConnectorException {
        
  
    	UIVentanaPrincipal ventanaPrincipal = new UIVentanaPrincipal();
    
    
    	while(! ventanaPrincipal.datosCargados())
    	  {
    		  
    	  }
    	
    	
    	/*
    	 *** COLOCA LA POSICIÓN INICIAL****
    	 * Coloque el número de nodo inicial en la funcion nodoInicial(nodo)
    	 * */
    	
    	Point posicionInicial = nodoInicial(7);
    	
    	
    	
        AgentDrone agent = new AgentDrone();
        EnvironmentMap environment = new EnvironmentMap(ventanaPrincipal.getGrafo(), posicionInicial);
        //EnvironmentMap environment = new EnvironmentMap();
        ventanaPrincipal.setEstadoDrone(agent.getAgentState());
        
        SimulatorEventNotifier.SubscribeEventHandler(EventType.IterationFinished, ventanaPrincipal);
        
        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        
        
        simulator.start();
    }

	private static Point nodoInicial(int nodo) {

		switch(nodo)
		{
		case 1:
			return new Point(0,0);
		case 2:
			return new Point(70,0);
			
		case 3:
			return new Point(130,0);
			
		case 4:
			
			return new Point(0,65);
			
		case 5:
			return new Point(70,65);
			
		case 6:
			
			return new Point(130,65);
		case 7: 
			return new Point(0,135);
		case 8:
			return new Point(70,135);
		case 9:
			return new Point(130,135);
		}
		return new Point(0,0);
	}
}
