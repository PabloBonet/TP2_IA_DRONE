package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.ActionFactory;

import frsf.cidisi.exercise.situationCalculus.actions.*;



public class AgentDroneActionFactory extends ActionFactory {

    private static AgentDroneActionFactory instance;

    private AgentDroneActionFactory() {
    }

    public static AgentDroneActionFactory getInstance() {
        if (instance == null) {
            instance = new AgentDroneActionFactory();
        }
        return instance;
    }

    @Override
    protected String endActionString() {
        return "noAction";
    }

   
     @Override
   	 protected Action stringToAction(String stringAction) {
        Action actionObject = null;
        
	if (stringAction.equals("IrNorte")) {
            actionObject = new IrNorte();
        } else if (stringAction.equals("IrEste")) {
            actionObject = new IrEste();
        } else if (stringAction.equals("IrSur")) {
            actionObject = new IrSur();
        } else if (stringAction.equals("IrOeste")) {
            actionObject = new IrOeste();

        }
        
        return actionObject;
    }
}
