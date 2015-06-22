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
	
	@Override
	/**
	 * Se reescribe el metodo paint que se hereda de Canvas
	 */
	public void paint(Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		
		//dibujarCuadrantes(g);
		dibujarNodos(g,grafo);
		//dibujarArcosEntreNodos(g, grafo);
		//g.drawImage(drone,0,0, this);
		
		if(estadoDrone != null)
		{
			
			g.drawImage(drone,estadoDrone.getPosicionVictimario().x,estadoDrone.getPosicionVictimario().y, this);
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
			if(estadoDrone != null)
			System.out.println("Estado Drone: posicion: " + estadoDrone.getPosicionActual().x + " " + estadoDrone.getPosicionActual().y);
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
			
			if(i < 3)
			{
				desplazarY = 25;
				desplazarX = 25;
			}
			else
			{
				if(i > 2 && i < 6)
				{
					desplazarY = 200;
					desplazarX = 25;
				}
				else
				{
					desplazarY = 375;
					desplazarX = 25;
				}
			}
			
			g.fillOval(grafo.getListaNodos().get(i).getPosX()*3 +desplazarX,grafo.getListaNodos().get(i).getPosY() + desplazarY ,18,18);
			//String esquina = Integer.toString(grafo.getListaNodos().get(i).getId());
			g.setColor(Color.black);
			//g.drawString(esquina, grafo.getListaNodos().get(i).getPosX(),grafo.getListaNodos().get(i).getPosY());
			String personas = Integer.toString(cantPersonas);
			g.drawString(personas, grafo.getListaNodos().get(i).getPosX()+5,grafo.getListaNodos().get(i).getPosY()+5);
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
