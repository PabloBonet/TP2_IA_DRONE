package frsf.cidisi.exercise.situationCalculus;

import java.awt.Point;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import frsf.ia.tp.paqueteGrafico.UIVentanaPrincipal;

public class AgentDroneMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
	

    public static void main(String[] args) throws PrologConnectorException {
        
    	System.out.println("antes de Carga ventana!");
    	UIVentanaPrincipal ventanaPrincipal = new UIVentanaPrincipal();
    	System.out.println("Carga ventana!");
    	  while(! ventanaPrincipal.datosCargados())
    	  {
    		  
    	  }
    	  System.out.println("Carga datos!");
    	//Posición inicial
    	Point posicionInicial = new Point(0,0);
    	
        AgentDrone agent = new AgentDrone();
        EnvironmentMap environment = new EnvironmentMap(ventanaPrincipal.getGrafo(), posicionInicial);
        //EnvironmentMap environment = new EnvironmentMap();
        ventanaPrincipal.setEstadoDrone(agent.getAgentState());
        
        SimulatorEventNotifier.SubscribeEventHandler(EventType.IterationFinished, ventanaPrincipal);
        
        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        
        
        simulator.start();
    }
}
