package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.exercise.libreriaclases.Grafo;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.exercise.libreriaclases.Persona;
import frsf.cidisi.faia.state.EnvironmentState;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class StateMap extends EnvironmentState {

	
	//TODO: Setup Variables
    private int energiaDrone;
    private Point posicionDrone;
    private Grafo mapa;
    private ArrayList<Nodo> señales;
	
	
    public StateMap(Grafo grafo, Point posicionInicialDrone) {
        
    	
		señales = new ArrayList<Nodo>();
		mapa = new Grafo();
		posicionDrone = posicionInicialDrone;
		this.mapa = grafo;

        this.initState();
    }

    @Override
    public void initState() {
    	//Inicializa al done con 1000 undades de energía
    	  this.energiaDrone = 1000;
    	  
    	  inicializarListaSeñales();
          
    }

    
    @Override
    public String toString() {
        String str = "";
	    
	    //TODO: Complete Method
	    
        return str;
    }
    
    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
    
   	
     public int getenergiaDrone(){
        return energiaDrone;
     }
     public void setenergiaDrone(int arg){
        energiaDrone = arg;
     }
     public Point getposicionDrone(){
        return posicionDrone;
     }
     public void setposicionDrone(Point arg){
        posicionDrone = arg;
     }
     public Grafo getMapa(){
        return mapa;
     }

     public ArrayList<Nodo> getseñales(){
        return señales;
     }
     
     /***
      * Inicializa la lista de señales, copiado los nodos con personas del mapa
      */
     private void inicializarListaSeñales()
     {
     	for(Nodo n: this.mapa.getListaNodos())
     	{
     		if(n.getPersonas().size() > 0)
     		{
     			Point ubicacion = new Point();
     			ubicacion.x = n.getPosX();
     			ubicacion.y = n.getPosY();
     			Nodo nodoNuevo=new Nodo(n.getId(), n.getPosX(), n.getPosY(), n.getVisitado());
     			for(Persona p:n.getPersonas())
     				nodoNuevo.agregarPersona(new Persona(p.getId(), p.getTipo()));
     			señales.add(nodoNuevo);
     		}
     	}
     }


    
}
