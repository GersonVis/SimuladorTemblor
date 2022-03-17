import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class evReiniciar implements ActionListener{
	Personas personas;
	Dibujar pizarra;
	Ventana ventana;
	evReiniciar(Personas personas, Ventana ventana){
		this.personas=personas;
		this.ventana=ventana;
		this.pizarra=ventana.pizarra;
	}
	@Override
	public void actionPerformed(ActionEvent eve) {
		personas.mePararTodo();
		pizarra.personasc=pizarra.originales;
		personas.personas=pizarra.originales;
		ventana.evIniciar.personas.personas=pizarra.originales;
		ventana.evIniciar.personas.meRegresar();
		pizarra.repaint();
		ventana.temblor.parar();
		
	}
}
