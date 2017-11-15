package receptoreventosappintensidad;

import gpio.servicio.Leds;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;





public class ReceptorEventoIntensidad implements EventHandler{

	@Override
	public void handleEvent(Event event) {
		System.out.println("NOMBRE EVENTO RECIBIDO : " + event.getProperty("TITLE"));
		System.out.println("RECIBIDO EVENTO "+event.getTopic() + "en tiempo = " + event.getProperty("TIME"));
		String nuevaintensidad = (String) event.getProperty("INTENSIDAD");
		double inten = Double.valueOf(nuevaintensidad);
		if(inten > 100){
			inten = 100;
		}

			Leds intensidadapp = new Leds();
			double intensidad_actual = intensidadapp.getIntensidad();
			System.out.println("INTENSIDAD ACTUAL = " + intensidad_actual);
			System.out.println("INTENSIDAD ACTUALIZADA = "+ inten);
			
			if(intensidad_actual == inten ){
				System.out.println("INTENSIDAD IGUAL, NO CAMBIOS EN SMARTLAMP");
			}else{
				intensidadapp.setIntensidad(inten);
				//intensidadapp.EncenderLedPWM(3, intensidadapp.getColorPLug('r'),  intensidadapp.getColorPLug('g'),  intensidadapp.getColorPLug('b'), inten);
				intensidadapp.EncenderRGB2(0, intensidadapp.getColorPLug('r'),  intensidadapp.getColorPLug('g'),  intensidadapp.getColorPLug('b'), inten);
				System.out.println("INTENSIDAD CAMBIADA, CAMBIOS EN SMARTLAMP --> DIMMING ");
			}
			

	}

}
