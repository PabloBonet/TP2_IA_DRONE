package frsf.cidisi.exercise.situationCalculus;

import java.awt.Point;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;
import frsf.ia.tp.paqueteGrafico.UIVentanaPrincipal;

public class AgentDroneMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
	
	static UIVentanaPrincipal ventanaPrincipal;
    public static void main(String[] args) throws PrologConnectorException {
        
    	
    	
    	ventanaPrincipal = new UIVentanaPrincipal();
    	
    	//Posición inicial
    	Point posicionInicial = new Point(0,0);
    	
        AgentDrone agent = new AgentDrone();
        EnvironmentMap environment = new EnvironmentMap(ventanaPrincipal.getGrafo(), posicionInicial);

        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        simulator.start();
    }
}
