package frsf.cidisi.exercise.situationCalculus;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.environment.Environment;

public class AgentDronePerception extends SituationCalculusPerception {

  	
	//TODO: Setup Sensors
	//private int percepcionmapa;
	private int [][] señales = new int[9][3];
	private int [][] camara = new int[4][2];
	private int camaraEnPos;
	private int posX;
	private int posY;
	private int energia;
	

    public AgentDronePerception() {
        super();

        camaraEnPos = 0;
        posX = 0;
        posY = 0;
        energia = 0;
    }

    @Override
    public void initPerception(Agent agent, Environment environment) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        StringBuffer perceptionString = new StringBuffer("percepcion([");

        //agrego las señales a la percepcion
        for(int i = 0; i<9; i++)
        {
        	for(int j = 0; j<3; j++)
        	{
        		if(señales[i][0] > 0)
        		{
        			perceptionString.append("["+señales[i][0]+", "+ señales[i][1]+", "+señales[i][2]+"]");
        		}
        		else
        		{
        			perceptionString.append("[_, "+ señales[i][1]+", "+señales[i][2]+"]");
        		}
        		if( i!= 8)
        		perceptionString.append(",");
        	}
        	
        	
        }
        
      //Agrego elementos a la lista de cámaras
        for(int i = 0; i<4; i++)
        {
        	perceptionString.append("[" + camara[i][0]+ ", "+camara[i][1]+"]");
        	
        	if( i!= 3)
        		perceptionString.append(",");
        }
        
        perceptionString.append("], ");
        
        //Agrego la camaraEnPosicion
        perceptionString.append("" + camaraEnPos);
        
        //Agrego posicion
        
        perceptionString.append(", " + posX + ", " + posY);
        
        //Agrego energia
        
        perceptionString.append(", " + energia + ")");

        
        
        return perceptionString.toString();
    }

	public int[][] getSeñales() {
		return señales;
	}

	public void setSeñales(int[][] señales) {
		this.señales = señales;
	}

	public int[][] getCamara() {
		return camara;
	}

	public void setCamara(int[][] camara) {
		this.camara = camara;
	}

	public int getCamaraEnPos() {
		return camaraEnPos;
	}

	public void setCamaraEnPos(int camaraEnPos) {
		this.camaraEnPos = camaraEnPos;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}
    
    
    
  /*   public int getpercepcionmapa(){
        return percepcionmapa;
     }
     public void setpercepcionmapa(int arg){
        this.percepcionmapa = arg;
     }
*/

}
