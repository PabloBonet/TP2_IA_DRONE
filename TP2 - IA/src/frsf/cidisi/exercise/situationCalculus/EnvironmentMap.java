package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.exercise.libreriaclases.FuncionesAuxiliares;
import frsf.cidisi.exercise.libreriaclases.Grafo;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.exercise.libreriaclases.Persona;
import frsf.cidisi.faia.agent.Action;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.environment.Environment;


public class EnvironmentMap extends Environment {

    /*public EnvironmentMap(Grafo mapa, Point posicionInicial) {
        this.environmentState = new StateMap(mapa, posicionInicial);
    }*/
	public EnvironmentMap() {
        this.environmentState = new StateMap();
    }

    @Override
    public StateMap getEnvironmentState() {
        return (StateMap) super.getEnvironmentState();
    }

    @Override
    public boolean agentFailed(Action action) {

        //TODO: Complete Method

        return false;
    }

    @Override
    public AgentDronePerception getPercept() {
        AgentDronePerception p = new AgentDronePerception();

        //obtiene la posición actual del drone
        p.setPosX(this.getEnvironmentState().getposicionDrone().x);
        p.setPosY(this.getEnvironmentState().getposicionDrone().y);
        
        //obtiene la energia del agente
        p.setEnergia(this.getEnvironmentState().getenergiaDrone());
        
        
        /*
         * 
         * FALTA COMPLETAR!!
         * 
         * */
        //obtiene la lista de señales
        
        int[][] señales = new int[9][3];
        
        int ind =0;
        
        for(Nodo n: this.getEnvironmentState().getseñales())
        {
        	int cantidad = n.getPersonas().size();
        	Point punto = new Point(n.getPosX(), n.getPosY());
        	if(n.getPersonas().size() > 0)
        	{
        
        		señales[ind][0] =cantidad;
        		señales[ind][1] = punto.x;
        		señales[ind][2] = punto.y;
        	}
        	ind++;
        }
        p.setSeñales(señales);
        
       
        
        
        //obtiene la cámara en posición
        Nodo nodoActual = this.getEnvironmentState().getMapa().nodoEnPosicion(this.getEnvironmentState().getposicionDrone());
        
        System.out.println("Nodo: " + nodoActual.getId());
        for(Persona persona: nodoActual.getPersonas())
        {
        	if(persona.esVictimario())
        	{
        		p.setCamaraEnPos(persona.getId()); // la persona es un victimario, por lo que se obtiene su ID
        		break;
        	}
        	else
        	{
        		p.setCamaraEnPos(0); //la persona no es un victimario
                		
        	}
        }
        
      //obtiene la lista de camaras (Norte, Este, Sur, Oeste)
        
    	 int [][] camara = new int[4][2];
        
    	 for(int i = 0; i<4; i++)
    	 {
    		 for(int j=0; j<2; j++)
    		 {
    			 camara[i][j] = -1;
    		 }
    	 }
    	 
    	 boolean existeVictimario = false;
    	 int i = 1;
    	 while(!existeVictimario && i <5)
    	 {
    		 //Obtiene el en la dirección especificada como parámetro
    		 // nodoAl(1, nodoActual) obtiene el nodo al norte(1). Al Este(2), Sur(3), Oeste(4)
    		 Nodo n = this.getEnvironmentState().getMapa().nodoAl(i,nodoActual);
    		 
    		 if(n!= null)
    		 {
    			 
    		 
    			 for(Persona per: n.getPersonas())
    			 {
    				 if(per.esVictimario())
    				 {
    					 //copio en la matriz las posiciones del nodo que tiene victimario
    					 camara[i-1][0] = n.getPosX();
    					 camara[i-1][1] = n.getPosY();
    					 existeVictimario = true;
    					 break;
    				 }
         		
    			 }	 
    		 }
    		 
    		 i++;
    	 }	
    	 
        p.setCamara(camara);	
    	      
        
        return p;
    }
    
    @Override
    public String toString() {
        return this.environmentState.toString();
    }
}
