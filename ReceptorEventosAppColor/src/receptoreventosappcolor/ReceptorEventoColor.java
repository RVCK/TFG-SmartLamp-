package receptoreventosappcolor;

import gpio.servicio.Leds;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;




public class ReceptorEventoColor implements EventHandler{

	@Override
	public void handleEvent(Event event) {
		System.out.println("NOMBRE EVENTO RECIBIDO : " + event.getProperty("TITLE"));
		System.out.println("RECIBIDO EVENTO "+event.getTopic() + "en tiempo = " + event.getProperty("TIME"));
		String rojo = (String) event.getProperty("R");
		String verde = (String) event.getProperty("G");
		String azul = (String) event.getProperty("B");
		double r = Double.valueOf(rojo);
		double g = Double.valueOf(verde);
		double b = Double.valueOf(azul);
		
		if(r>255){
			r=255;
		}
		
		if(g>255){
			g=255;
		}
		
		if(b>255){
			b=255;
		}
		
		System.out.println("RECIBI R = " + r);
		System.out.println("RECIBI G = " + g);
		System.out.println("RECIBI B = " + b);

		
		
		//NEW RGB : 
		Leds colorhtml = new Leds();
		colorhtml.setColorPlug(r,'r');
		colorhtml.setColorPlug(g,'g');
		colorhtml.setColorPlug(b,'b');
		System.out.println("Voy a encender");
		colorhtml.EncenderRGB2(0, r, g, b, colorhtml.getIntensidad());
		System.out.println("Ya he encendido. fUNCION ENCENDER RGB COMPLETA");
		//try {
		//	Thread.currentThread().sleep(2000);
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	System.out.println("He petado durmiendo tras led on");

		//	e.printStackTrace();
		//}
		//colorhtml.ApagarRGB2(colorhtml.getIntensidad());
		//System.out.println("Apagado, fin handler event color-app");
	
		
/**		
			Leds colorapp = new Leds();
			double rojoold = colorapp.getColorPLug('r');
			double verdeold = colorapp.getColorPLug('g');
			double azulold = colorapp.getColorPLug('b');
			System.out.println("RGB ACTUAL (r-g-b)" + rojoold + "-" + verdeold + "-" + azulold);
			System.out.println("RGB ACTUALIZADO (r-g-b)" + r + "-" +g + "-" + b);
			boolean encender = false;
			
			if(rojoold != r){
				colorapp.setColorPlug(r, 'r');
				encender = true;
			}
			
			if(verdeold != g){
				colorapp.setColorPlug(g, 'g');
				encender = true;
			}			
			
			if(azulold != b){
				colorapp.setColorPlug(b, 'b');
				encender = true;
			}
			
			if(encender){
				System.out.println("MODIFICAR COLOR SMARTLAMP");
				//colorapp.EncenderLed(4, r, g, b, colorapp.getIntensidad());
			}else{
				System.out.println("NO    MODIFICAR COLOR SMARTLAMP");

			}
**/
		
			
	}

}
