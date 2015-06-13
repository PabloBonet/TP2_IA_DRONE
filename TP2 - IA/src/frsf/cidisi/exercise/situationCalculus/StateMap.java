package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.exercise.libreriaclases.Grafo;
import frsf.cidisi.faia.state.EnvironmentState;

import java.awt.Point;
import java.util.Vector;

public class StateMap extends EnvironmentState {

	
	//TODO: Setup Variables
    private int energiaDrone;
    private Point posicionDrone;
    private Grafo mapa;
    //private Other señales;
	
	
    public StateMap() {
        
		//TODO: Complete Method

        this.initState();
    }

    @Override
    public void initState() {
        //TODO: Complete Method
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
//     public void setmapa(Other arg){
//        mapa = arg;
//     }
//     public Other getseñales(){
//        return señales;
//     }
//     public void setseñales(Other arg){
//        señales = arg;
//     }

    
}
