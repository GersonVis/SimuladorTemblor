import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class Dibujar extends JPanel{
	int[][] personas;
	Persona[] personasc;
	Persona[] originales;
	Puerta[] puertas;
	Dibujar(int[][] personas){
		this.personas=personas;
	}
	Dibujar(){}
	Dibujar(Persona[] personasc, Puerta[] puertas){
	    this.puertas=puertas;
		originales=new Persona[personasc.length];
		this.personasc=personasc;
		int c=0;
		for(Persona per:personasc) {
			originales[c]=new Persona(String.valueOf(c));
			originales[c].x=per.x;
			originales[c].y=per.y;
			originales[c].cuerpo=per.cuerpo;
			originales[c].ropa=per.ropa;
			originales[c].ropaoriginal=per.ropaoriginal;
			c++;
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		int cam=0;
		for(int d=0; d<personasc.length; d++) {
			Persona persona=personasc[d];
			g.setColor(persona.ropa);			
			g.drawOval(persona.x, persona.y, persona.cuerpo, persona.cuerpo);
			g.fillOval(persona.x, persona.y, persona.cuerpo, persona.cuerpo);
			g.setColor(Color.BLACK);
			g.drawString(persona.nombre, persona.x, persona.y);
		}
		for(Puerta puerta:puertas) {
			g.setColor(puerta.color);
			g.drawRect(puerta.x, puerta.y, puerta.width, puerta.heigth);
			g.fillRect(puerta.x, puerta.y, puerta.width, puerta.heigth);
		}
	}
}