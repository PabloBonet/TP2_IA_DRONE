package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.state.EnvironmentState;
import java.util.Vector;

public class StateMap extends EnvironmentState {

	
	//TODO: Setup Variables
    private int energiaDrone;
    private int[] posicionDrone;
    //private Other mapa;
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
     public int[] getposicionDrone(){
        return posicionDrone;
     }
     public void setposicionDrone(int[] arg){
        posicionDrone = arg;
     }
//     public Other getmapa(){
//        return mapa;
//     }
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
