package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import java.util.Hashtable;

public class StateDrone extends KnowledgeBase {

    public StateDrone() throws PrologConnectorException {

		//TODO: Replace file name  
        super("drone_logic.pl");

        this.initState();
    }

    @Override
    public ActionFactory getActionFactory() {
        return AgentDroneActionFactory.getInstance();
    }

    @Override
    public String getSituationPredicate() {
        return "actualSituation";
    }

    @Override
    public String getBestActionPredicate() {
        return "bestAction";
    }

    @Override
    public String getExecutedActionPredicate() {
        return "action";
    }

    @Override
    public void updateState(Perception p) {
        this.tell((SituationCalculusPerception) p);
    }

    @Override
    public void initState() {
       
		//Inicializo la KB 
    	this.addKnowledge("agenteEnPosicion(0,0,0)");
    	
		
    }

    @Override
    public String toString() {
        String str = "";

       //TODO: Complete Method
       
        return str;
    }
}
