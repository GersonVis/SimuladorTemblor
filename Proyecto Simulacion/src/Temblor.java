import javax.swing.JLabel;
import javax.swing.JPanel;

public class Temblor {
    Dibujar contenedor;
	Thread temblor, tem;
	int eleccion=0, se=0;
	Personas personas;
	JLabel jlTemblor;
    Temblor(Dibujar contenedor, Personas personas, JLabel jlTemblor){
	   this.contenedor=contenedor;
       this.personas=personas;
       this.jlTemblor=jlTemblor;
    }
   void parar() {
	   //System.out.println("FinalizandO");
	   System.out.println("FinalizandOssss");
	   temblor.stop();
	   tem.stop();
   }
   
   void iniciar(){
	   String[] temb=new String[] {""};
	   CrearNumerosAleatorios crear=new CrearNumerosAleatorios();
	   crear.semillas(50, 11, new int[]{23,12,45,37,28,34,41});
	   double[] semi=crear.generar();
	   crear.semillas(50, 11,  new int[]{12,78,25,73,9,65,43,27,36});
	   double[] semi2=crear.generar();
	   //System.out.println("Generado: "+semi[eleccion]);
	   personas.AsignarLentitud((int)(semi2[eleccion]*1000));
	   temb[0]="Tie: "+String.valueOf(semi2[eleccion]*20);
	   temblor=new Thread(new Runnable() {
		   @Override
		   public void run() {
				   int retar=(int)(semi[eleccion]*10000);
				   temb[0]=temb[0]+String.valueOf(" Mg: "+semi[eleccion]*10);
				   jlTemblor.setText(temb[0]);
				   tem=new Thread(new Runnable() {
					   @Override 
					   public void run() {
						   int val=-5, x=contenedor.getX(), y=contenedor.getY(), w=contenedor.getWidth(), h=contenedor.getHeight();
						   while(se==0) {
							  val=val*-1;
							  contenedor.setBounds(x+val,y+val,w,h);
							//  System.out.println(se);
						      try {
						    	  Thread.sleep(200);
						      }catch(InterruptedException e) {
						    	  
						      }
						   }
					   }
				   });
				   tem.start();
				   try {
					   Thread.sleep(retar);
				   }catch(InterruptedException e) {}
				  // System.out.println("Finalizando");
				   personas.AsignarLentitud(0);
				   tem.stop();
			   
		   }
	   });
	   temblor.start();
   }	
}
