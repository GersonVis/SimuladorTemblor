import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



class Ventana extends JFrame{
	Personas personas;
	Dibujar pizarra;
	evBotonIniciar evIniciar;
	Temblor temblor;
	void iniciarVentana() {
		this.setTitle("Simulador");
		this.setSize(760,700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	Ventana(){
		iniciarVentana();	
		JLabel jlCronoUni=new JLabel("00:00:00");
		  jlCronoUni.setBounds(625,450,100,100);
		Container contenedor=this.getContentPane();
		contenedor.setLayout(null);
	    JPanel jpInformacion=new JPanel(null);
	   
		  jpInformacion.setBackground(Color.GREEN);
		 JPanel interior=new JPanel(new GridLayout(3,0));
		  interior.setBounds(3,3,145,120);
		  interior.setBackground(Color.WHITE);
		  jpInformacion.add(interior);
		 JLabel jlNombre=new JLabel("Nombre: ");
		 JLabel jlPaso=new JLabel("Paso: ");
		 JLabel jlRetardo=new JLabel("Retardo: ");
		 interior.add(jlNombre);
		 interior.add(jlPaso);
		 interior.add(jlRetardo);
		 jpInformacion.add(interior);
		JPanel jpInformacionLista=new JPanel();
		 jpInformacionLista.setBackground(Color.CYAN);
		 GridLayout glInformacion=new GridLayout(0,1);
		 glInformacion.setVgap(2);
		 jpInformacionLista.setLayout(glInformacion);
		 
		 //jpInformacionLista.setBounds(540, 20, 200,300);
		JScrollPane spPersonas=new JScrollPane();
		  
		  spPersonas.setBounds(540, 20, 200,300);
	      spPersonas.setViewportView(jpInformacionLista);
	      spPersonas.getViewport().setView(jpInformacionLista);
		  
		/*JPanel pPersonas=new JPanel();
		  pPersonas.setBackground(Color.RED);
		  pPersonas.setBounds(40, 0, 700, 500);
		 GridBagLayout glPersonas=new GridBagLayout();
		  glPersonas.columnWeights=new double[] {10, 20};
		  pPersonas.setLayout(glPersonas);*/
		 
		//puertas
		 Puerta puerta1=new Puerta(Color.RED, 225, 610, 50,10,1, 1);
		 Puerta puerta2=new Puerta(Color.BLACK, 0, 330, 50,10,2, 2);
		 Puerta[] puertas=new Puerta[] {puerta1, puerta2};
		//fin puertas 
		// contenedor.add(jpInformacionLista);
		contenedor.add(jpInformacion);
		contenedor.add(spPersonas);
		contenedor.add(jlCronoUni);
		personas=new Personas();
        personas.posicionPersonasAleatorias(50,new int[] {233,333,22,355,24,53,344}, new int[] {5465,321,1551,685,1565,8465});
        personas.mePasosyretardos();
        personas.meAsignarPuerta(puertas);
        personas.meAddAinformacioin(jpInformacionLista);
        personas.meAddPersonas();
        personas.TodasMenosyo();
        pizarra=new Dibujar(personas.personas, puertas);
		 pizarra.setBounds(20, 20, 500, 620);
		 pizarra.setBackground(Color.WHITE);
		contenedor.add(pizarra);
		 personas.pizarra=pizarra; 
		repaint();
		personas.addComponentes(640, contenedor, -30, 540);
		
		JLabel jlTemblor=new JLabel("a\n\naasd");
		contenedor.add(jlTemblor);
		
		
		JButton jbIniciar=new JButton("INICIAR");
		temblor=new Temblor(pizarra, personas, jlTemblor);
		evIniciar=new evBotonIniciar(personas, this, jlCronoUni, pizarra, temblor);
		  jbIniciar.addActionListener(evIniciar);
		JButton reiniciar=new JButton("Reiniciar");
	      reiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("Reiniciando");
				personas.meRegresarPos();
				personas.mePararTodo();
				personas.meRegresarPintura();
				temblor.parar();
				evIniciar.cronometro.stop();
				jlCronoUni.setText("00:00:00");
			} 
	      });
		JTextField jtMod=new JTextField();
		contenedor.add(jbIniciar);
		
		contenedor.add(reiniciar);
		contenedor.add(jtMod);
		//contenedor.add(jpInformacion);
		jtMod.setBounds(600,350,100,25);
		jbIniciar.setBounds(600,400,100,24);
		reiniciar.setBounds(600, 424, 100, 24);
		jlTemblor.setBounds(580, 450, 100, 24);
		pizarra.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				//jpInformacion.setVisible(false);
				
			}
			
		});
		pizarra.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent pos) {
				jpInformacion.setVisible(true);
				personas.addMeatocado(pos.getPoint(), jpInformacion, jlNombre, jlPaso, jlRetardo);
				/*jpInformacion.setBackground(Color.YELLOW);
				jpInformacion.setBounds(pos.getX()+20, pos.getY()-80, 100,100);
				//jpInformacion.updateUI();
		        //prueba.setOpaque(false);
		        
				System.out.println(pos.getX()+" "+pos.getY());*/
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
							
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				int t=1;
				while(true) {
					try {Thread.sleep(100);}catch(InterruptedException e) {
						
					}
					t=t*-1;
					repaint();
				}
			}			while(true) {
	
		}).start();*/
		//personas.movimiento(1, 5, 2, 200, 40);
		//personas.movimiento(43, 5, 2, 10, 40);
	   /* double p=1.1;
	    int u=(int) p;
	    System.out.println(u);
		*/
		JPanel s=new JPanel(null);
		
	}
	
}
public class Inicio {
    public static void main(String[] agrs) {
        Ventana ventana=new Ventana();
    	/*generarNumeros generar=new generarNumeros(7,100);
    	generar.semillas(new int[]{65,89,98,3,69});
    	generar.generar();*/
        
    }
}
