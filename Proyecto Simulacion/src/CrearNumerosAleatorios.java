import java.util.ArrayList;

public class CrearNumerosAleatorios {
		double[] insemillas;
		int generar, mod, separacion;
		void semillas(int generar, int mod, int... semi) {
			this.generar=generar;
			this.mod=mod;
			separacion=semi.length-1;
			insemillas=new double[separacion+1+generar];
			for(int o=0;o<semi.length;o++) {
				insemillas[o]=semi[o];
			}
		}
		double[] generar() {
			double[] generados=new double[generar];
			double p=.25;
			ArrayList<Double> a=new ArrayList<Double>(), b=new ArrayList<Double>(), c=new ArrayList<Double>(), d=new ArrayList<Double>(), e=new ArrayList<Double>();
			ArrayList[] re=new ArrayList[] { a,b,c,d,e};
			
			for(int u=0;u<generar;u++) {
				generados[u]=insemillas[separacion+u+1]=(insemillas[u]+insemillas[separacion+u])%mod;
				//System.out.print(generados[u]+" "+re+" ");
				generados[u]=generados[u]/(mod-1);
				//System.out.println(generados[u]);
				if(generados[u]<=.2) {
					a.add(generados[u]);
				}
				else if(generados[u]<=.4) {
					b.add(generados[u]);
				}
				else if(generados[u]<=.6) {
					c.add(generados[u]);
				}
				else if(generados[u]<=.8) {
					d.add(generados[u]);
				}
				else if(generados[u]<=1) {
					e.add(generados[u]);
				}
				
			}
			
		    for(ArrayList<Double> al:re) {
		        System.out.println("\n"+al.size());
		    	for(double ge:al) {
		    		System.out.println(String.format("%.2f ", ge));
		    	}
		    }
		    System.out.println("\n");
		/*	System.out.println(String.format("%d %d %d %d %d", a,b,c,d, e));
			System.out.println("\n\nFInl\n\n");*/
			return generados;
		}	
}
