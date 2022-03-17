import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class runCrono implements Runnable {
	JLabel crono;
	Personas personas;
	runCrono(JLabel crono, Personas personas){
		this.crono=crono;
	    this.personas=personas;
	}

	@Override
	public void run() {
		int mil=0,seg=0,min=0;
		while(personas.dentro<personas.numper){
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				
			}
			mil++;
			if(mil==100) {
				mil=0;
				seg++;
				if(seg==60) {
					seg=0;
					min++;
				}
			}
			crono.setText(String.format("%02d:%02d:%02d %d %d", min,seg,mil, personas.dentro, personas.numper));
	}
    }
}
public class evBotonIniciar implements ActionListener{
	Personas personas;
	JFrame ventana;
	int p=0, c=0;
	Thread ac, cronometro;
	JLabel crono;
	Dibujar pizarra;
	Temblor temblor, tem;
	evBotonIniciar(Personas personas, JFrame ventana, JLabel crono, Dibujar pizarra, Temblor tem){
		this.pizarra=pizarra;
		this.ventana=ventana;
		this.personas=personas;
		this.crono=crono;
		this.tem=tem;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
				personas.iniciarMovimientos();
				personas.meChoques();
				personas.meCrearContadores();
				/*personas.personas[36].movimiento(personas.pizarra);
				personas.personas[36].iniciarm.start();;*/
				tem.iniciar();
				tem.eleccion=c;
				c++;
				if(p==0) {
					cronometro=new Thread(new runCrono(crono, personas));
					cronometro.start();
					ac=new Thread(new Runnable() {
						@Override
						public void run() {
							int r=-5;
							while(true) {
								try {
									Thread.sleep(100);
								}catch(InterruptedException e) {
									
								}
								ventana.repaint();
							}
						}
					});
					ac.start();
					p=1;
					
					
				}else {
					cronometro.stop();
					ac.stop();
					
					cronometro=new Thread(new runCrono(crono, personas));
					cronometro.start();
					ac=new Thread(new Runnable() {
						@Override
						public void run() {
							int r=-5;
							while(true) {
								try {
									Thread.sleep(100);
								}catch(InterruptedException e) {
									
								}
								ventana.repaint();
							}
						}
					});
					ac.start();
				}
	}
				
		
	}

