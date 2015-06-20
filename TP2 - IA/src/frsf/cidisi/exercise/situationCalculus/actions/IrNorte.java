package frsf.cidisi.exercise.situationCalculus.actions;

import java.awt.Point;

import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.cidisi.exercise.libreriaclases.FuncionesAuxiliares;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.exercise.situationCalculus.*;

public class IrNorte extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        
        StateDrone estadoAgente = (StateDrone) ast;
        StateMap estadoAmbiente = (StateMap) est;
        
        
        //obtiene la siguiente esquina al norte
        
        Nodo siguienteEsquina = FuncionesAuxiliares.irNorte(estadoAmbiente.getposicionDrone(), estadoAmbiente.getMapa());
        
        //obtiene el punto al cual debe moverse
        Point puntoSiguiente = new Point( siguienteEsquina.getPosX(),siguienteEsquina.getPosY() );
        //Actualiza la posición en el ambiente
        
        estadoAmbiente.setposicionDrone(puntoSiguiente);
        int energia = estadoAmbiente.getenergiaDrone();
        estadoAmbiente.setenergiaDrone(energia-1);
        
        for(Nodo n: estadoAmbiente.getMapa().getListaNodos())
        {
        	if(n.getPosX() == puntoSiguiente.x && n.getPosY() == puntoSiguiente.y)
        	{
        		n.visitar();
        	}
        }
        
        return estadoAmbiente;
    }

    @Override
    public String toString() {
        return "irNorte";
    }
}

