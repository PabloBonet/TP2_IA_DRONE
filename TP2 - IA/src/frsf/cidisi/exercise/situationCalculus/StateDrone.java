package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.exercise.libreriaclases.Enlace;
import frsf.cidisi.exercise.libreriaclases.Grafo;
import frsf.cidisi.exercise.libreriaclases.Nodo;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

import java.awt.Point;
import java.util.Hashtable;

public class StateDrone extends KnowledgeBase {

	private Grafo mapa;
    public StateDrone() throws PrologConnectorException {

    	
		//TODO: Replace file name  
        super("drone_logic.pl");

        //Mapa
      //  mapa = new Grafo();
    	
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
    	//this.addKnowledge("agenteEnPosicion(0,0,0)");
    	
    	//Carga el mapa (nodos y enlaces) sin personas
    	//cargaMapa();
		
    }

    @Override
    public String toString() {
        String str = "";
Point posicionActual = this.getPosicionActual();
       str = str + "\nPosici�n: "+ posicionActual.x + ", " + posicionActual.y;
       str = str + "\nEnerg�a: "+ this.getEnergiaActual();
       
       int [][] se�ales = this.getSe�ales();
       
       str = str + "\nSe�ales: ";
       for(int i = 0; i < se�ales.length; i++)
       {
    	   if(se�ales[i][0] != 0) //si no tiene se�al en esa posici�n
    	   {
    		   str = str + "\nNodo " + (i+1) + ": Posici�n: "+ se�ales[i][1] + ", " + se�ales[i][2]+ ". Cantidad de personas: " + se�ales[i][0];   
    	   }
    	   
       }
       
       //Muestra la posici�n del victimario, 
       Point posicionVictimario = this.getPosicionVictimario();
       if(posicionVictimario.x != -1 && posicionVictimario.y != -1)
       {
    	   str= str + "\nPosici�n del victimario: " + posicionVictimario.x + ", "+ posicionVictimario.y;   
       }
       else
       {
    	   str= str + "\nA�n no se conoce la posici�n del victimario";
       }
    	   
      int idVictimario = this.getIdVictimario();
      if(idVictimario == 0)
      {
    	  str = str + "\nA�n no se identific� al victimario";
      }
      else
      {
    	  str = str + "\n\n\n****************************************";
    	  str = str + "\n*** Se ha encontrado al victimario!! ***";
    	  str = str + "\n****************************************\n";
    	  str= str + "\nPosici�n del victimario: " + posicionVictimario.x + ", "+ posicionVictimario.y;
    	  str= str + "\nID del victimario: " + idVictimario + "";
    	  str = str + "\n\n****************************************\n\n\n";
      }
       
        return str;
    }
    
    /**
     * Funci�n que realiza la consulta a la base de conocimiento y devuelve la posici�n actual del agente
     * */
    public Point getPosicionActual()
    {
    	String consultaPosicion = "agenteEnPosicion(X,Y," + this.getSituation() + ")";
    	
    	Hashtable[] result = this.query(consultaPosicion);
    	
    	int x = Integer.parseInt(result[0].get("X").toString());
        int y = Integer.parseInt(result[0].get("Y").toString());

        return new Point(x,y);
    }
    
    /**
     * Funci�n que realiza la consulta a la base de conocimiento y devuelve la energ�a actual del agente
     * */
    
    public int getEnergiaActual()
    {
    	String consultaEnergia = "energia(E," + this.getSituation() + ")";
    	
    	Hashtable[] result = this.query(consultaEnergia);
    	
    	int e = Integer.parseInt(result[0].get("E").toString());
        
    	return e;
    }
    
    /**
     * Funci�n que realiza la consulta a la base de conocimiento y devuelve la lista de se�ales del agente
     * */
    public int[][] getSe�ales()
    {
    	int [][] se�ales = new int[9][3];
    	
    	String consultarSe�ales = "radar(C,I,J)";
    	
    	Hashtable[] result = this.query(consultarSe�ales);
    	
    	for(int i = 0; i < result.length; i++)
    	{
    		int c = Integer.parseInt(result[i].get("C").toString());
    		int x = Integer.parseInt(result[i].get("I").toString());
    		int y = Integer.parseInt(result[i].get("J").toString());
    		
    		se�ales[i][0] = c;
    		se�ales[i][1] = x;
    		se�ales[i][2] = y;
    	}
    	
    	return se�ales;
    }
    
    /**
     * Funci�n que consulta la base de conocimiento para saber la posici�n el victimario
     * si todav�a no la conoce, retorna como posici�n (-1,-1).
     * */
    public Point getPosicionVictimario()
    {	
    	Point posicion;
    	
    	String consultarVictimario = "victimario(I,J,"+ this.getSituation() + ")";
    	
    	if(this.queryHasSolution(consultarVictimario)) {
			Hashtable[] result = this.query(consultarVictimario);
			
			int x = Integer.parseInt(result[0].get("I").toString());
        	int y = Integer.parseInt(result[0].get("J").toString());
        	
        	posicion = new Point(x,y);
        	return posicion;
		}
    	
    	return new Point (-1,-1);
    }
    
    /**
     * Funci�n que retorna el ID del victimario si lo identifica
     * Si todav�a no conoce el ID retorna un Id = 0
     * */
    public int getIdVictimario()
    {
    	int id = 0;
    	
    	String consultarIdVictimario = "victimarioEncontrado(ID,"+ this.getSituation() + ")";
    	
    	if(this.queryHasSolution(consultarIdVictimario)) {
			Hashtable[] result = this.query(consultarIdVictimario);
			
		 id = Integer.parseInt(result[0].get("ID").toString());
        	
        	return id;
		}
    	
    	return id;
    }
    
    
    
    
    /**
     * Funci�n que carga el escenario. Carga el mapa sin personas, solo los nodos y enlaces
     * */
    private void cargaMapa()
    {
    	/*Carga Nodos*/
    	
    	 Nodo nodo1 = new Nodo(1,0,0,false);
      	  Nodo nodo2 = new Nodo(2,70,0,false);
      	  Nodo nodo3 = new Nodo(3,130,0,false);
      	  Nodo nodo4 = new Nodo(4,0,65,false);
      	  Nodo nodo5 = new Nodo(5,70,65,false);
      	  Nodo nodo6 = new Nodo(6,130,65,false);
      	  Nodo nodo7 = new Nodo(7,0,135,false);
      	  Nodo nodo8 = new Nodo(8,70,135,false);
      	  Nodo nodo9 = new Nodo(9,130,135,false);
      	  
      	  
      	  /*Agrega los nodos al mapa*/
       	  mapa.getListaNodos().add(nodo1);
       	  mapa.getListaNodos().add(nodo2);  
       	  mapa.getListaNodos().add(nodo3);  
       	  mapa.getListaNodos().add(nodo4);  
       	  mapa.getListaNodos().add(nodo5);  
       	  mapa.getListaNodos().add(nodo6);  
       	  mapa.getListaNodos().add(nodo7); 
       	  mapa.getListaNodos().add(nodo8);  
       	  mapa.getListaNodos().add(nodo9);  
       	  
       	  
       	  /* ---- CARGA ENLACES ----*/
       	  Enlace e1 = new Enlace(1,2);
       	  Enlace e2 = new Enlace(1,4);
       	  Enlace e3 = new Enlace(2,1);
       	  Enlace e4 = new Enlace(2,5);
       	  Enlace e5 = new Enlace(2,3);
       	  Enlace e6 = new Enlace(3,2);
       	  Enlace e7 = new Enlace(3,6);
       	  Enlace e8 = new Enlace(4,1);
       	  Enlace e9 = new Enlace(4,5);
       	  Enlace e10 = new Enlace(4,7);
       	  Enlace e11 = new Enlace(5,2);
       	  Enlace e12 = new Enlace(5,4);
       	  Enlace e13 = new Enlace(5,8);
       	  Enlace e14 = new Enlace(5,6);
       	  Enlace e15 = new Enlace(6,3);
       	  Enlace e16 = new Enlace(6,5);
       	  Enlace e17 = new Enlace(6,9);
       	  Enlace e18 = new Enlace(7,4);
       	  Enlace e19 = new Enlace(7,8);
       	  Enlace e20 = new Enlace(8,7);
       	  Enlace e21 = new Enlace(8,5);
       	  Enlace e22 = new Enlace(8,6);
       	  Enlace e23 = new Enlace(9,6);
       	  Enlace e24 = new Enlace(9,8);
       	  
       	  /*Agrega los enlaces al mapa*/
       	  mapa.getListaEnlaces().add(e1);
       	  mapa.getListaEnlaces().add(e2);
       	  mapa.getListaEnlaces().add(e3);
       	  mapa.getListaEnlaces().add(e4);
       	  mapa.getListaEnlaces().add(e5);
       	  mapa.getListaEnlaces().add(e6);
       	  mapa.getListaEnlaces().add(e7);
       	  mapa.getListaEnlaces().add(e8);
       	  mapa.getListaEnlaces().add(e9);
       	  mapa.getListaEnlaces().add(e10);
       	  mapa.getListaEnlaces().add(e11);
       	  mapa.getListaEnlaces().add(e12);
       	  mapa.getListaEnlaces().add(e13);
       	  mapa.getListaEnlaces().add(e14);
       	  mapa.getListaEnlaces().add(e15);
       	  mapa.getListaEnlaces().add(e16);
       	  mapa.getListaEnlaces().add(e17);
       	  mapa.getListaEnlaces().add(e18);
       	  mapa.getListaEnlaces().add(e19);
       	  mapa.getListaEnlaces().add(e20);
       	  mapa.getListaEnlaces().add(e21);
       	  mapa.getListaEnlaces().add(e22);
       	  mapa.getListaEnlaces().add(e23);
       	  mapa.getListaEnlaces().add(e24);
       	  
    }
    
    
}
