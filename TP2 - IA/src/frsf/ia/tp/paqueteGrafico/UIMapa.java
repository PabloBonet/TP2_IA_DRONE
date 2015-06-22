package frsf.ia.tp.paqueteGrafico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import frsf.cidisi.exercise.libreriaclases.*;
import frsf.cidisi.exercise.situationCalculus.StateDrone;

import javax.swing.ImageIcon;


public class UIMapa extends Canvas {

	
	private Image fondo;
	private Grafo grafo;
	private Image drone;
	//private Point posicionDrone;
	private StateDrone estadoDrone = null;
	

	/**
	 * El constructor recibiria como parametro el grafo
	 * @param grafo
	 */
	public UIMapa(Grafo grafo) {
		this.grafo = grafo;
		inicializarImagenes();
		
		
	}

	private void inicializarImagenes() {
		fondo = new ImageIcon("imagenes/entornoMapa.png").getImage();
		
		drone = new ImageIcon("imagenes/drone.png").getImage();
	}
	
	
	/**
	 * Se reescribe el metodo paint que se hereda de Canvas
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		
		//dibujarCuadrantes(g);
		dibujarNodos(g,grafo);
		//dibujarArcosEntreNodos(g, grafo);
		//g.drawImage(drone,200,0, this);
		
		if(estadoDrone != null)
		{
			
			int x = estadoDrone.getPosicionActual().x;
			int y = estadoDrone.getPosicionActual().y;
			drone = new ImageIcon("imagenes/drone.png").getImage();
			g.drawImage(drone,100,100, this);
		}
	}
	
	public void setEstadoAgente(StateDrone estadoDrone)
	{
		this.estadoDrone = estadoDrone;
		this.repaint();
	}
	

	/**
	 * Metodo para graficar los nodos sobre el mapa
	 * @param g
	 * @param grafo
	 */
	private void dibujarNodos(Graphics g,Grafo grafo) {
		for(int i=0; i<grafo.getListaNodos().size(); i++){
			
			//cuenta las personas en el nodo
			
			int cantPersonas = grafo.getListaNodos().get(i).getPersonas().size();
			/*if(estadoDrone != null)
			System.out.println("Estado Drone: posicion: " + estadoDrone.getPosicionActual().x + " " + estadoDrone.getPosicionActual().y);*/
			//coloca el color al nodo dependiendo si tiene victimarios
			if(grafo.getListaNodos().size() > 0)
			{
				if(hayVictimarios(grafo.getListaNodos().get(i).getPersonas()))
				{
					g.setColor(Color.red);
				}
				else
				{
					if(grafo.getListaNodos().get(i).getPersonas().size()>0)
					{
						g.setColor(Color.green);
					}
					else
					{
						g.setColor(Color.black);
					}
					
				}
			}
			int desplazarX = 0;
			int desplazarY = 0;
			
			switch(i)
			{
			case 0:
				desplazarX = 25;
				desplazarY = 25;
				
				break;
			case 1:
				desplazarX = 210;
				desplazarY = 25;
				
				break;
			case 2:
				
				desplazarX = 400;
				desplazarY = 25;
				
				break;
			case 3:
				desplazarX = 25;
				desplazarY = 200;
				
				break;
			case 4:
				desplazarX = 210;
				desplazarY = 200;
				
				break;
			case 5:
				desplazarX = 400;
				desplazarY = 200;
				
				break;
			case 6:
				desplazarX = 25;
				desplazarY = 370;
				
				break;
			case 7:
				desplazarX = 210;
				desplazarY = 370;
				
				break;
			case 8:
				desplazarX = 400;
				desplazarY = 370;
				
				break;
				
			}
			
			g.fillOval(grafo.getListaNodos().get(i).getPosX()+desplazarX,grafo.getListaNodos().get(i).getPosY() + desplazarY ,25,25);
			//String esquina = Integer.toString(grafo.getListaNodos().get(i).getId());
			
				if(grafo.getListaNodos().get(i).getVisitado())
				{
					g.setColor(Color.yellow);
					g.fillOval(grafo.getListaNodos().get(i).getPosX()+desplazarX,grafo.getListaNodos().get(i).getPosY() + desplazarY ,25,25);
					//g.drawImage(drone,grafo.getListaNodos().get(i).getPosX()+desplazarX,grafo.getListaNodos().get(i).getPosY() + desplazarY, this);
				}
				
				g.setColor(Color.black);
				//g.drawString(esquina, grafo.getListaNodos().get(i).getPosX(),grafo.getListaNodos().get(i).getPosY());
				String personas = Integer.toString(cantPersonas);
				g.drawString(personas, grafo.getListaNodos().get(i).getPosX()+desplazarX+10,grafo.getListaNodos().get(i).getPosY()+desplazarY+15);
				g.setColor(Color.blue);
				String ubicacion = "["+grafo.getListaNodos().get(i).getPosX()+ ", " + grafo.getListaNodos().get(i).getPosY()+"]";
				g.drawString(ubicacion, grafo.getListaNodos().get(i).getPosX()+desplazarX-15,grafo.getListaNodos().get(i).getPosY()+desplazarY);
				
		}
	}
	
	public boolean hayVictimarios(List<Persona> personas)
	{
		for(Persona p: personas)
		{
			if(p.esVictimario())
				return true;
		}
	return false;
	}
	

	


}
