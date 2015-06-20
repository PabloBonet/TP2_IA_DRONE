package frsf.cidisi.exercise.situationCalculus.actions;

import java.awt.Point;

import frsf.cidisi.exercise.libreriaclases.FuncionesAuxiliares;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.exercise.situationCalculus.StateDrone;
import frsf.cidisi.exercise.situationCalculus.StateMap;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class IrOeste extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        
    	StateDrone estadoAgente = (StateDrone) ast;
        StateMap estadoAmbiente = (StateMap) est;
        
      //obtiene la posición actual
        Point posicionActual = new Point(estadoAmbiente.getposicionDrone());
        
        //obtiene la siguiente esquina al oeste
        
        Nodo siguienteEsquina = FuncionesAuxiliares.irOeste(estadoAmbiente.getposicionDrone(), estadoAmbiente.getMapa());
        
        //obtiene el punto al cual debe moverse
        Point puntoSiguiente = new Point( siguienteEsquina.getPosX(),siguienteEsquina.getPosY() );
        //Actualiza la posición en el ambiente
        
        estadoAmbiente.setposicionDrone(puntoSiguiente);
      //Decrementa energía. -1 si hay personas, -2 si no hay
        boolean hayPersonas = false;
        
        for(Nodo s: estadoAmbiente.getMapa().getListaNodos())
        {
        	if(s.getPosX() == posicionActual.x && s.getPosY() == posicionActual.y )
        	{
        		if(s.getPersonas().size() > 0)
        			hayPersonas = true;
        		break;
        	}
        	
        }
    	int energia = estadoAmbiente.getenergiaDrone();
    	if(hayPersonas)
        {
        	System.out.println("Hay Personas");
            estadoAmbiente.setenergiaDrone(energia-1);
            	
        }
        else
        {
        	System.out.println("NO Hay Personas");
            estadoAmbiente.setenergiaDrone(energia-2);
        } 
        //Marca el nodo actual como visitado
        
        for(Nodo n: estadoAmbiente.getMapa().getListaNodos())
        {
        	if(n.getPosX() == posicionActual.x && n.getPosY() == posicionActual.y)
        	{
        		n.visitar();
        	}
        }
        
        return estadoAmbiente;
    }

    @Override
    public String toString() {
        return "irOeste";
    }
}

