package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.agent.Action;
import java.util.Vector;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.environment.Environment;


public class EnvironmentMap extends Environment {

    public EnvironmentMap() {
        this.environmentState = new StateMap();
    }

    @Override
    public StateMap getEnvironmentState() {
        return (StateMap) super.getEnvironmentState();
    }

    @Override
    public boolean agentFailed(Action action) {

        //TODO: Complete Method

        return false;
    }

    @Override
    public AgentDronePerception getPercept() {
        AgentDronePerception p = new AgentDronePerception();

        //TODO: Complete Method

        return p;
    }
    
    @Override
    public String toString() {
        return this.environmentState.toString();
    }
}
