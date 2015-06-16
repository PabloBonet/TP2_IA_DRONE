package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.exercise.libreriaclases.Enlace;
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
	
	
    /*public StateMap(Grafo grafo, Point posicionInicialDrone) {
        
    	
		señales = new ArrayList<Nodo>();
		mapa = new Grafo();
		posicionDrone = posicionInicialDrone;
		this.mapa = grafo;

        this.initState();
    }*/
    
    public StateMap()
    {
    	señales = new ArrayList<Nodo>();
    	mapa = new Grafo();
    	posicionDrone = new Point();
    	
    }

    @Override
    public void initState() {
    	//Inicializa al done con 1000 undades de energía
    	  this.energiaDrone = 1000;
    	  
    	  /* ---- CARGA NODOS ---- */ 
    	  Nodo nodo1 = new Nodo(1,0,0,false);
    	  Nodo nodo2 = new Nodo(1,0,0,false);
    	  Nodo nodo3 = new Nodo(1,0,0,false);
    	  Nodo nodo4 = new Nodo(1,0,0,false);
    	  Nodo nodo5 = new Nodo(1,0,0,false);
    	  Nodo nodo6 = new Nodo(1,0,0,false);
    	  Nodo nodo7 = new Nodo(1,0,0,false);
    	  Nodo nodo8 = new Nodo(1,0,0,false);
    	  Nodo nodo9 = new Nodo(1,0,0,false);
    	  
    	  Persona persona1 = new Persona(1, 0);
    	  
    	  Persona persona2 = new Persona(2, 0);
    	  Persona persona3 = new Persona(3, 0);
    	  Persona persona4 = new Persona(4, 0);
    	  
    	  Persona persona5 = new Persona(5, 0);
    	  
    	  Persona persona6 = new Persona(6, 0);
    	  Persona persona7 = new Persona(7, 0);
    	  Persona persona8 = new Persona(8, 0);
    	  Persona persona9 = new Persona(9, 0);
    	  
    	  Persona persona10 = new Persona(10, 1);
    	  
    	  Persona persona11 = new Persona(11, 0);
    	  Persona persona12 = new Persona(12, 0);
    	  
    	  Persona persona13 = new Persona(13, 0);
    	  Persona persona14 = new Persona(14, 0);
    	  
    	  
    	  // Asignación por nodo
    	  nodo1.agregarPersona(persona1);
    	  
    	  nodo2.agregarPersona(persona2);
    	  nodo2.agregarPersona(persona3);
    	  nodo2.agregarPersona(persona4);
    	  
    	  nodo4.agregarPersona(persona5);
    	  
    	  nodo5.agregarPersona(persona6);
    	  nodo5.agregarPersona(persona7);
    	  nodo5.agregarPersona(persona8);
    	  nodo5.agregarPersona(persona9);
    	  
    	  nodo6.agregarPersona(persona10);
    	  
    	  nodo7.agregarPersona(persona11);
    	  nodo7.agregarPersona(persona12);
    	  
    	  nodo8.agregarPersona(persona13);
    	  nodo8.agregarPersona(persona14);
    	  
    	  
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
