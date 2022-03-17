import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;

class Personas{
	int[][] pospersonas;
	Persona[] personas, personasorigi;
	JPanel pizarra=null;
	int[] pos;
	Container n;
	boolean detener=false;
	int numper, dentro=0;
	CrearNumerosAleatorios generar=new CrearNumerosAleatorios();
	JPanel contenedor, jpInformacion;
    JPanel[] jpInfos;
    Hashtable<Integer, JPanel> hashInfos=new Hashtable<Integer, JPanel>();
	Personas perso;
	Personas(){
		perso=this;
	}
	void meRegresar() {
		for(Persona per:personas) {
			per.detener=false;
			per.detenercolision=false;
		}
	}
	void meRegresarPintura() {
		for(Persona per:personas) {
			per.ropa=per.ropaoriginal;
		}
	}
	void mePararTodo() {
		for(Persona per:personas) {
			per.hilReloj.stop();
			per.jlTiempo.setText("00:00:00");
			per.jpTiempo.setBackground(new Color(-1118482));
			per.checkcolision.stop();
			per.iniciarm.stop();
		}
	}
	void IniciarTodo() {
		for(Persona per:personas) {
			per.meIniciar(personas, pizarra);
		}
	}
	void meChoques() {
		for(Persona per:personas) {
			per.meColisicion(personas);
			per.checkcolision.start();
		}
	}
	void addComponentes(JPanel contenedor, int limite, Container n, int limitex, int limitex2) {
	  this.contenedor=contenedor;
	  this.n=n;
	  for(Persona p: personas) {
			p.informacion=contenedor;
			p.contain=n;
			p.limite=limite;	
			p.limitex=limitex;
			p.limitex2=limitex2;
			p.meAjustarpas();
		}
	}
	void addComponentes(int limite, Container n, int limitex, int limitex2) {
		  this.n=n;
		  for(Persona p: personas) {
				p.informacion=contenedor;
				p.contain=n;
				p.limite=limite;	
				p.limitex=limitex;
				p.limitex2=limitex2;
				p.meAjustarpas();
			}
		}	
	void iniciarMovimientos() {
		for(Persona p: personas) {
			p.movimiento(pizarra);
			p.iniciarm.start();
		}
		/*new Thread(new Runnable() {
			@Override
			public void run() {
	          while(true) {
	        	  try {
	        		  Thread.sleep(100);
	        	  }catch(InterruptedException e) {
	        		  
	        	  }
	        	
	        	  n.repaint();
	          } 			
			} 
		}).start();*/
	}
	void meAsignarPuerta(Puerta[] puertas) {
		generar.semillas(numper, 42, new int[] {23,12,46,73,52,64,67,21,32,25});
		double[] elegirpuerta=generar.generar();
		double em=elegirpuerta[0], emo;
		for(int pos=1;pos<numper; pos++) {
			if(elegirpuerta[pos]>em) {
				em=elegirpuerta[pos];
				//System.out.println(em);
			}
		}
		emo=0;
		em=(double)em/puertas.length;
		int c=0, c2=0;
		int[] con=new int[] {0,0};
		while(c<puertas.length) {
			c2=0;
			for(double n:elegirpuerta) {
				//con[1]++;
			//	System.out.println(con[1]);
				if(n<=em && n>=emo) {
					personas[c2].meIrAPuerta(puertas[c]);;
					//con[c]++;
				}
				c2++;
			}
			//System.out.print("tamaño "+con[c]+" \n");
			emo=emo+em;
			em=em+em;
			c++;
		}
	}
	void mePasosyretardos() {
	
		//aleatoriamente se asigna a que puerta escogeran irse
		
		//aleatoriamente se asignan cuanto avanza y cada cuanto lo hace
		generar.semillas(numper, 33, new int[]{21,35,21,94,68,73,28,98});
	    int mul,pos=0;
	    double[] pasos=(generar.generar());
	    generar.semillas(numper, 42, new int[]{1,19,68,7,8,9});
	    double[] retardos=generar.generar();
	    int paso, retardo;
	    while(pos<numper) {
	    	paso=(int)((pasos[pos]*10)); retardo=(int)(retardos[pos]*300);
	    	if(paso==0) {
	    		paso=1;
	    	}
	    	if(retardo==0) {
	    		retardo=100;
	    	}
	    	personas[pos].pasoRetardo(paso, retardo);
	    	//System.out.println(String.valueOf((int)((pasos[pos]*10))+" "+(int)(retardos[pos]*100)));
	    	//System.out.println("aqsssssssssssssssssss");
	        pos++;
	    }	
	}
	void posicionPersonasOrdenadas(int numper, int x, int y, int sepa_aba, int sepa_alad, int columnas){
		int xi=x, colui=columnas;
		personas=new Persona[numper];
		pospersonas=new int[numper][2];
		for(int u=0; u<numper; u++) {
			if(u>=columnas) {
				columnas=columnas+colui;
				y+=sepa_aba;
				x=xi;
			}
			pospersonas[u][0]=x;
			pospersonas[u][1]=y;
     		x+=sepa_alad;
     		
     		//personas[u]=new Persona();
     		//personas[u].posicionc(x, y);
     		//personas[u].cuerpo(20);
     		//System.out.println(": "+u+"x: "+x+" y: "+y);
		}
	}
	void posicionPersonasAleatorias(int numper, int[] semillas, int[] semillas2) {
		this.numper=numper;
		pospersonas=new int[numper][2];
		personas=new Persona[numper];
		personasorigi=new Persona[numper];
    	generar.semillas(numper,10, semillas);
    	//System.out.println("Posicion de las personas aleatorias X");
    	double[] geneX=generar.generar();
    	//System.out.println("Posicion de las personas aleatorias X");
    	generar.semillas(numper,10, semillas2);
    	double[] geneY=generar.generar();
    	int po=0;
    	int sec=numper/4;
    	int au=sec;
    	int xv=-1, yv=-1, co=1;
    	while(po<numper) {
    		if(po==sec) {
    			if(co==1 || co==3) {
    				xv=xv*-1;
    			}else {
    				yv=yv*-1;
    			}
    			sec+=au;
    			co++;
    		}
    		int x=200+((int)(geneX[po]*100)*xv), y=200+((int)(geneY[po]*100)*yv);
    		int vec=0;
    		while(vec==0) {
    			vec=1;
    			for(int z=0;z<po; z++) {
        			Persona p=personas[z];
        			if(  x>=p.x && x<=p.x+p.cuerpo	
        				&&
        				 y>=p.y && y<=p.y+p.cuerpo	
        				 ||
        				 x>=p.x && x<=p.x+p.cuerpo	
         				&&
         				 y+p.cuerpo>=p.y && y+p.cuerpo<=p.y+p.cuerpo
         				 ||
         				 x+p.cuerpo>=p.x && x+p.cuerpo<=p.x+p.cuerpo	
         				&&
         				 y>=p.y && y<=p.y+p.cuerpo	
        				 ||
        				 x+p.cuerpo>=p.x && x+p.cuerpo<=p.x+p.cuerpo	
         				&&
         				 y+p.cuerpo>=p.y && y+p.cuerpo<=p.y+p.cuerpo
        			  ) {
                        vec=0;
        				x=x+(p.x+p.cuerpo-x)+1;   
        				//y=y+(p.y+p.cuerpo-y);    	
        			    }
        			
        		}
    		}
    		
    		personas[po]=new Persona(String.valueOf(po));
    		personasorigi[po]=new Persona(String.valueOf(po));
     		personas[po].posicionc(x, y);
     		personas[po].miembros(20,10);
     		
     		if(x<225 && y<225) {
     			personas[po].colorRopa(new Color(x,y,x));
     		}else {
     			personas[po].colorRopa(new Color(y%225, x%225, y%225));
     		}
     		personasorigi[po].posicionc(x, y);
     		po++;
     		
    	}
       
    	
	}
	void AsignarLentitud(int retardo) {
		
		for(Persona p:personas) {
			p.retardotemblor=retardo;
		}
	}
	void TodasMenosyo() {
		//asigna todas las personas menos ella misma a la persona
		Persona[] perso=new Persona[numper-1];
		for(int p=0; p<numper;p++) {
			int con=0;
			//System.out.println("\n "+personas[p].nombre);
			for(int f=0; f<numper; f++) {
			  if(p!=f) {
				  perso[con]=personas[f];
				  con++;
			  }
			}
			personas[p].todas_menosyo=perso;
		}
	}
	void meRegresarPos() {
		int c=0;
		for(Persona per:personas) {
			per.x=personasorigi[c].x;
			per.y=personasorigi[c].y;
			c++;
		}
	}
	void reacomodo() {
		pizarra.repaint();		
	}
	
    void movimiento(int persona, int cambiox, int cambioy,int pausa, int pasos) {
    	new Thread(new Runnable() {
    		@Override
    		public void run() {
    			int cp=1;
    			while(cp<=pasos) {
    				pospersonas[persona][0]=pospersonas[persona][0]+cambiox;
    				pospersonas[persona][1]=pospersonas[persona][1]+cambioy;
    				pizarra.repaint();
    				try {
						Thread.sleep(pausa);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
    				cp++;
    			}
    		}
    	}).start();
    }
    void meAddAinformacioin(JPanel jpInformacion) {
    	this.jpInformacion=jpInformacion;
    	int contador=0;
    	jpInfos=new JPanel[numper];
    	pos=new int[numper];
    	for(Persona persona:personas) {
    		persona.meAddAInformacion(jpInformacion, contador, perso);
    		jpInformacion.updateUI();
    		hashInfos.put(contador, persona.jphInformacion);
    		jpInfos[contador]=persona.jphInformacion;
    	    pos[contador]=contador;
    		contador++;
    	}
    }
    void addMeatocado(Point pos, JPanel lanzar, JLabel nombre, JLabel paso, JLabel retardo) {
    	for(Persona p: personas) {
    		p.meAtocado(pos, lanzar, nombre, paso, retardo);
    	}
    }
    void meAddPersonas() {
    	for(Persona p: personas) {
    		//p.persoanas=personas;
    	}
    }
    void meKillReloj() {
    	for(Persona p: personas) {
    	   p.hilReloj.stop();
    	   p.jlTiempo.setText("00:00:00");
    	}
    }
    void meCrearContadores() {
    	int cont=0;
    	//jpInfos=new JPanel[numper];
    	for(Persona p: personas) {
    		p.meCrearHiloContar();
    		p.hilReloj.start();
    		//jpInfos[cont]=p.jphInformacion;
    		cont++;
    	}
    }
}