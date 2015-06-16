package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.exercise.libreriaclases.Grafo;
import frsf.cidisi.faia.agent.Action;

import java.awt.Point;
import java.util.Vector;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.environment.Environment;


public class EnvironmentMap extends Environment {

    public EnvironmentMap(Grafo mapa, Point posicionInicial) {
        this.environmentState = new StateMap(mapa, posicionInicial);
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

        //obtiene la posición actual del drone
        

        return p;
    }
    
    @Override
    public String toString() {
        return this.environmentState.toString();
    }
}
