
package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.NoAction;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusBasedAgent;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.solver.situationcalculus.SituationCalculus;

public class AgentDrone extends SituationCalculusBasedAgent {

    private Action lastAction = NoAction.getInstance();

    public AgentDrone() throws PrologConnectorException {
        this.setAgentState(new StateDrone());
    }

    @Override
    public void tell(Action action) {
        StateDrone kb = this.getAgentState();
        kb.tell(action);
    }

    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }

    @Override
    public Action selectAction() {
        this.setSolver(new SituationCalculus());

        Action selectedAction = null;
        try {
            selectedAction = this.getSolver().solve(new Object[]{this.getAgentState()});
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.lastAction = selectedAction;

        return selectedAction;
    }

    @Override
    public StateDrone getAgentState() {
        StateDrone agentState = (StateDrone) super.getAgentState();

        return agentState;
    }

    public Action getLastAction() {
        return this.lastAction;
    }
}
