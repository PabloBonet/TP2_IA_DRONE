package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;

public class AgentDroneMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
    public static void main(String[] args) throws PrologConnectorException {
        
        AgentDrone agent = new AgentDrone();
        EnvironmentMap environment = new EnvironmentMap();

        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        simulator.start();
    }
}
