import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.io.IOError;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Hashtable;

class Persona{
	Container contain;
	
	int x, y, cax, limite,cay, cuerpo, cabeza, espaciox, espacioy, paso,pasoy, pasox=0,pasoadar, pasoreal, retardo, centrox, centroy,
		limitex, limitex2, retardotemblor=0, 
		disx, disy, op;
	double dpasox, dpasoy;
	String nombre;
	Color ropa, ropaoriginal;
	Thread iniciarm, checkcolision, hilReloj;
	boolean detener=false, detenercolision=false;
	JPanel informacion, jphInformacion, jphhInfo, jpTiempo;
	JLabel jlNombre, jlTiempo;
	Persona[] personas, todas_menosyo;
	Personas perso;
	Persona(String nombre){
		this.nombre=nombre;
	}
	void meDetener() {
		detener=true;
		detenercolision=true;
	}
	void meIniciar(Persona[] personas, JPanel pizarra) {
		meColisicion(personas);
		movimiento(pizarra);
		iniciarm.start();
		checkcolision.start();
	}
	void posicionc(int x, int y) {
		this.x=x; this.y=y;
	}
	void posicioncabeza(int x, int y) {
		this.cax=x; this.cay=y;
	}
	void cuerpo(int cuerpo) {
		this.cuerpo=cuerpo;
		cax=x; cay=y;
	}
	void miembros(int cuerpo, int cabeza) {
		this.cuerpo=cuerpo;
		this.cabeza=cabeza;
		cax=x+(cuerpo/2)-(cabeza/2); cay=y+(cuerpo/2)-(cabeza/2);
		espaciox=x+cuerpo;
		espacioy=y+cuerpo;
		centroy=y+(cuerpo/2);
    	centrox=x+(cuerpo/2);
	}
	void acomodo() {
		cax=x+(cuerpo/2)-(cabeza/2); cay=y+(cuerpo/2)-(cabeza/2);
	}
	void colorRopa(Color color) {
		ropa=color;
		ropaoriginal=color;
	}
	void meAtocado(Point pos, JPanel lanzar, JLabel nombre, JLabel paso, JLabel retardo) {
		informacion=lanzar;
		int xn=pos.x, yn=pos.y;
		if(xn>=x && xn<=espaciox && yn>=y && yn<=espacioy) {
			lanzar.setVisible(true);
			lanzar.setBounds(xn+20,yn-108,150,125);
			lanzar.setBackground(ropa);
			nombre.setText("Nombre: "+this.nombre);
			paso.setText("Tama. paso: "+this.paso);
			retardo.setText("Retraso: "+this.retardo+" ms");
		}
	}
	void meIrAPuerta(Puerta puerta) {
		int numpas;
		disx=(int) Math.sqrt(Math.pow(((centrox)-(puerta.centrox)), 2)+Math.pow((centroy)-(centroy), 2));
		disy=(int) Math.sqrt(Math.pow(((centrox)-(centrox)), 2)+Math.pow((centroy)-(puerta.centroy), 2));
		//System.out.print(puerta.numpuerta);
		if(centrox>puerta.centrox && centroy<puerta.centroy) {
			op=1;
			numpas=disy/paso;
			pasoy=paso;
			dpasox=(double)disx/(double)numpas;
			pasox=(int)dpasox*-1;
			dpasox=(dpasox+pasox)*-1;
			pasoy=paso;
			//System.out.println("La distancia es "+disx+" "+paso+" "+disy+" "+numpas+" "+dpasox);
		}
		if(centrox<puerta.centrox && centroy<puerta.centroy) {
			op=-1;
			numpas=disy/paso;
			pasoy=paso;
			dpasox=(double)disx/(double)numpas;
			pasox=(int)dpasox;
			dpasox=(dpasox-pasox);
			pasoy=paso;
			//System.out.println("La distancia es "+disx+" "+paso+" "+disy+" "+numpas+" "+dpasox);
		}
	}
	void meAjustarpas() {
		//int dis=Math.sqrt(Math.pow(((centrox)-(p2.centrox)), 2)+Math.pow((centroy)-(p2.centroy), 2));
	}
    void meColisicion(Persona[] personas) {
    	checkcolision=new Thread(new Runnable() {
    		@Override
    		public void run() {
    			int co;
    	    	double dis;
    	    	try {
    	    		informacion.setVisible(false);
    	    	}catch(java.lang.NullPointerException e) {
    	    		
    	    	}
    	    	
    	    	while(detenercolision==false) {
    	    		    try{
						Thread.sleep(retardo);
					    }catch(InterruptedException e) {
					    }
    					co=0;
    					for(Persona p2:personas) {
    						/*centrox=x+(cuerpo/2);
    						centroy=y+(cuerpo/2);*/
    						dis=Math.sqrt(Math.pow(((centrox)-(p2.centrox)), 2)+Math.pow((centroy)-(p2.centroy), 2));
    						if(dis<(double)cuerpo) {
    							if(centroy<p2.centroy) {
    								co++;
    							}
    			    			Esquivar();
    			    			//co++;
    			    		}
    					}
    					if(co>0) {
    						ropa=Color.RED;
    					}else {
    						ropa=ropaoriginal;
    					}
    					
    				}
    			}    		
    	});
    }
    void Esquivar() {
    	if(true) {
    		
    	}
    }
	void pasoRetardo(int paso, int retardo) {
		this.paso=paso;this.retardo=retardo;
		pasoreal=paso;
	}
	void mePasarInicio() {
		int t, c=0, nom=Integer.valueOf(nombre);
		Enumeration<Integer> enu=perso.hashInfos.keys();
		int[] pos=perso.pos;
		for(t=0;t<perso.numper; t++) {
			if(nom==pos[t]) {
				break;
			}
		}
		int guardar=pos[t];
		for(int u=t; u>0;u--) {
			pos[u]=pos[u-1];
		}
		pos[0]=guardar;
		 perso.jpInformacion.remove(jphInformacion);
		 perso.jpInformacion.add(jphInformacion, 0);
		/*for(int p=0;p<=t;p++) {
			//System.out.print(pos[p]+" ");
		   
		}*/
		//System.out.println("");
		//perso.jpInformacion.removeAll();
		/*for(int o=0;o<perso.numper;o++) {
			perso.jpInformacion.add(perso.hashInfos.get(perso.pos[0]));
		}*/
	}
    void movimiento(JPanel pizarra) {
	    iniciarm=new Thread(new Runnable() {
	    		@Override 
	    		public void run() {
	    			int cuer=cuerpo/2, avance=0;
	    			//System.out.println("La distancia en x es: "+disx+"\nDistancia en y: "+ disy);
	    			double llenar=0;
	    			while(detener==false) {
	    				
	    				/*double dis=Math.sqrt(Math.pow((centrox-p2.centrox), 2)+Math.pow(centroy-p2.centroy, 2));
                        if(dis<(double)20) {
                        	ropa=Color.RED;
                        }else {
                        	ropa=Color.BLUE;
                        }
                        System.out.println(nombre+" "+p2.nombre+" "+dis);*/
	    			  	y+=pasoy;
	    			  	x+=pasox;
	    			  	llenar+=dpasox;
	    			  	if(((int)llenar*(-op))>0) {
	    			  		x+=(int)llenar;
	    			  		llenar=llenar+op;
	    			  	}
	    			  	centrox=x+cuer;
	    			  	centroy=y+cuer;
	    			  	if(y>limite || x<limitex || x>limitex2) {
	    			  		hilReloj.stop();
	    			  		jpTiempo.setBackground(Color.RED);
	    			  		mePasarInicio();
	    			  		perso.dentro++;
	    			  		break;
	    			  	}
	    			  	try{	
	    			  		Thread.sleep(retardo+retardotemblor);
	    			  	}catch(InterruptedException e) {	    			  		
	    			  	}	    		    			  	
	    			  	//pizarra.repaint();
	    			}
	    		}
	    	});
	    }
    
    void salio() {
    		
    }
    void meCrearHiloContar() {
    	hilReloj=new Thread(new Runnable() {
    		@Override
    		public void run() {
    			int cont=0, mil, seg=0, mi=0;
    			while(true) {	
    				if(cont==100) {
    					cont=0;
    					seg++;
    				}
    				if(seg==60) {
    					seg=0;
    					mi++;
    				}
    				jlTiempo.setText(String.format("%02d:%02d:%02d", mi,seg,cont));
    				try {
    					Thread.sleep(10);
    				}catch(InterruptedException e) {
    					
    				}
    				cont++;
    			}
    		}
    	});
    }
    void meAddAInformacion(JPanel jpInformacion, int contador, Personas perso) {
    	//jphInformacion.setBackground(Color.BLUE);
    	this.perso=perso;
    	jlNombre=new JLabel("Nombre: "+nombre);
    	jlTiempo=new JLabel("00:00:00");
    	jpTiempo =new JPanel(new FlowLayout());
    	   jpTiempo.add(jlTiempo); 
    	jphhInfo=new JPanel(new GridLayout(3,0));	
    	jphhInfo.add(jlNombre); jphhInfo.add(jpTiempo);
    	jphInformacion=new JPanel(null);
    	 jphInformacion.setPreferredSize(new Dimension(100,80));
    	  jphhInfo.setBounds(4,4, 174,72);
    	  jphhInfo.setBackground(new Color(250,250,250));
    	 // jphhInfo.setPreferredSize(new Dimension(300, 300));
    	 jphInformacion.add(jphhInfo);
    	 jphInformacion.setBackground(ropa);
    	
    	jpInformacion.add(jphInformacion);
    	
    }



}



