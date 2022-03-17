import java.awt.Color;

public class Puerta {
  Color color;
  
  int x,numpuerta, y, width, heigth, centrox, centroy;
	Puerta(Color color,int x, int y,int width, int heigth, int numeroDePuerta,int pos){
	  if(pos==1) {	
	      numpuerta=numeroDePuerta;
    	  this.color=color;
	      this.x=x; this.y=y; this.width=width;
          this.heigth=heigth;
          centrox=x+(width/2); centroy=y+(heigth/2);
	  }else {
		  numpuerta=numeroDePuerta;
		  this.color=color;
		  this.x=x; this.y=y; this.width=heigth;
	      this.heigth=width;
	      centrox=x+(width/2); centroy=y+(heigth/2); 
	  }
	  }
}
