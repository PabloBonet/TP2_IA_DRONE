package frsf.cidisi.exercise.libreriaclases;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;


public class Nodo {
	//El id del nodo.
	private int id;
	
	//El posicion del nodo: posX, posY.
	private int posX;
	private int posY;

	private boolean visitado;
	
	//Lista de personas ubicadas en el nodo.
	private List<Persona> personas;

//	/**
//	 * Constructor que inicializa las propiedades del nodo.
//	 * 
//	 * @param idNodo
//	 *            El id del v�rtice.
//	 * @param pX
//	 * 			  Posici�n X del nodo
//	 * 
//	 * @param pY
//	 * 			  Posici�n Y del nodo
//	 */
//	public Nodo(int idNodo, int pX, int pY, List<Persona> persona, boolean visitado) {
//		super();
//		this.id = idNodo;
//		this.posX = pX;
//		this.posY = pY;
//		this.personas = persona;
//		this.visitado = visitado;
//	}
	
	/**
	 * 
	 * @param idNodo
	 * @param pX
	 * @param pY
	 * @param visitado
	 */
	public Nodo(int idNodo, int pX, int pY, boolean visitado) {
		super();
		this.id = idNodo;
		this.posX = pX;
		this.posY = pY;
		this.personas = new ArrayList<Persona>();
		this.visitado = visitado;
	}

	public int getId()
	{
		return this.id;
	}
	
	public int getPosX()
	{
		return this.posX;
	}
	
	public int getPosY()
	{
		return this.posY;
	}
	
	public List<Persona> getPersonas()
	{
		return this.personas;
	}
	
	public void agregarPersona(Persona p)
	{
		
		this.personas.add(p);
	}
	
	public void visitar()
	{
		this.visitado = true;
	}
	
	public boolean getVisitado()
	{
		return visitado;
	}
}
