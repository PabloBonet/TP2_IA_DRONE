package frsf.cidisi.exercise.situationCalculus.actions;

import java.awt.Point;

import frsf.cidisi.exercise.libreriaclases.FuncionesAuxiliares;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.exercise.situationCalculus.StateDrone;
import frsf.cidisi.exercise.situationCalculus.StateMap;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrEste extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        
    	StateDrone estadoAgente = (StateDrone) ast;
        StateMap estadoAmbiente = (StateMap) est;
        
        
        //obtiene la siguiente esquina al este
        
        Nodo siguienteEsquina = FuncionesAuxiliares.irEste(estadoAmbiente.getposicionDrone(), estadoAmbiente.getMapa());
        
        //obtiene el punto al cual debe moverse
        Point puntoSiguiente = new Point( siguienteEsquina.getPosX(),siguienteEsquina.getPosY() );
        //Actualiza la posici�n en el ambiente
        
        estadoAmbiente.setposicionDrone(puntoSiguiente);
        int energia = estadoAmbiente.getenergiaDrone();
        estadoAmbiente.setenergiaDrone(energia-1);
        
        return estadoAmbiente;
    }

    @Override
    public String toString() {
        return "irEste";
    }
}

