package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.environment.Environment;

public class AgentDronePerception extends SituationCalculusPerception {

  	
	//TODO: Setup Sensors
	private int percepcionmapa;
	

    public AgentDronePerception() {
        super();

		//TODO: Complete Method
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        StringBuffer perceptionString = new StringBuffer("perception([");

        //TODO: Complete Method

        return perceptionString.toString();
    }
    
    
     public int getpercepcionmapa(){
        return percepcionmapa;
     }
     public void setpercepcionmapa(int arg){
        this.percepcionmapa = arg;
     }


}
