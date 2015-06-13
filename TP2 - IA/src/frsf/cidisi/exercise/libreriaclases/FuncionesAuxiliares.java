package frsf.cidisi.exercise.libreriaclases;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import jpl.fli.functor_t;

public class FuncionesAuxiliares {

	public static int ANCHO_MAPA = 600; // ancho del MAPA
	public static int ALTO_MAPA = 600; // alto del MAPA
	public static int ANCHO_CUADRANTE = ANCHO_MAPA / 2;
	public static int ALTO_CUADRANTE = ALTO_MAPA / 2;
	public static int ANCHO_SUB_CUADRANTE = ANCHO_CUADRANTE / 2;
	public static int ALTO_SUB_CUADRANTE = ALTO_CUADRANTE / 2;

	public enum EstadoSimulacion {
		EJECUTANDO, PAUSADO, DETENIDO
	};




	/**
	 * 
	 * @param ubicacionActual
	 * @param subGrafo
	 * @return
	 */
	public static Nodo irNorte(Point ubicacionActual, Grafo grafo) {
		Nodo nodoActual = grafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : grafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al norte de la posicion actual 
			if (n.getPosX() == ubicacionActual.x && n.getPosY() < ubicacionActual.y)
			 {
				return n;
			}
		}
		return null;
	}

	public static Nodo irOeste(Point ubicacionActual, Grafo grafo) {
		Nodo nodoActual = grafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : grafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al norte de la posicion actual 
			if (n.getPosX() < ubicacionActual.x && n.getPosY() == ubicacionActual.y)
			 {
				return n;
			}
		}
		return null;
	}

	public static Nodo irEste(Point ubicacionActual, Grafo grafo) {
		Nodo nodoActual = grafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : grafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al norte de la posicion actual 
			if (n.getPosX() > ubicacionActual.x && n.getPosY() == ubicacionActual.y)
			 {
				return n;
			}
		}
		return null;
	}

	public static Nodo irSur(Point ubicacionActual, Grafo grafo) {
		Nodo nodoActual = grafo.nodoEnPosicion(ubicacionActual);

		for (Nodo n : grafo.buscarAdyacentes(nodoActual)) {
			// verifica que haya un nodo mas al norte de la posicion actual 
			if (n.getPosX() > ubicacionActual.x && n.getPosY() < ubicacionActual.y)
			 {
				return n;
			}
		}
		return null;
	}

	

	/**
	 * Retorna true si la lista contiene un nodo con el id pasado como parametro
	 * @param intensidadSe�al
	 * @param id
	 * @return
	 */
	public static boolean contieneNodoConID(ArrayList<Nodo> intensidadSe�al, int id) {
		
		for(Nodo n: intensidadSe�al)
		{
			if(n.getId() == id)
			{
				return true;	
			}
		}
		return false;
	}

	/**
	 * Retorna true en caso de que todos los nodos de la lista se hayan visitados
	 * 
	 * @param intensidadSe�ales
	 * @return
	 */
	public static boolean se�alesVisitadasB( ArrayList<Nodo> intensidadSe�ales) {

		for(Nodo n: intensidadSe�ales){
			if(!n.getVisitado())
			{
				return false;
			}
		}
		return true;
	}


}
