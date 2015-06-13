package frsf.cidisi.exercise.situationCalculus.actions;

import java.awt.Point;

import frsf.cidisi.exercise.libreriaclases.FuncionesAuxiliares;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.exercise.situationCalculus.StateDrone;
import frsf.cidisi.exercise.situationCalculus.StateMap;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrSur extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        
    	StateDrone estadoAgente = (StateDrone) ast;
        StateMap estadoAmbiente = (StateMap) est;
        
        
        //obtiene la siguiente esquina al sur
        
        Nodo siguienteEsquina = FuncionesAuxiliares.irSur(estadoAmbiente.getposicionDrone(), estadoAmbiente.getMapa());
        
        //obtiene el punto al cual debe moverse
        Point puntoSiguiente = new Point( siguienteEsquina.getPosX(),siguienteEsquina.getPosY() );
        //Actualiza la posición en el ambiente
        
        estadoAmbiente.setposicionDrone(puntoSiguiente);
        
        return est;
    }

    @Override
    public String toString() {
        return "irSur";
    }
}

