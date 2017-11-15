package receptoreventos;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import contenidos.Contenti;
import gpio.servicio.Leds;

public class ReceptorEventos implements EventHandler{ //CREAR VARIOS BUDNLES COMO ESTE PARA CADA RED SOCIAL; EMISOR CREA TODOS
	//

	@Override
	public void handleEvent(Event event) {
		
		System.out.println("NOMBRE EVENTO RECIBIDO : " + event.getProperty("TITLE"));
		System.out.println("RECIBIDO EVENTO "+event.getTopic() + "en tiempo = " + event.getProperty("TIME")+"");
		System.out.println("PROPERTIES LUZ = "+ event.getProperty("LUZ") +"");
		String luz = (String) event.getProperty("LUZ");
		System.out.println("MI STRING DE LUZ VALOR ="+ luz);
		
		boolean favs_recibidos_emisor_evento = false;
		
		if(luz.equals("true")){
			favs_recibidos_emisor_evento = true;
		}else if(luz.equals("false")){
		    favs_recibidos_emisor_evento = false;
		}else{
			System.out.println("VALOR RARO EN LUZ PROPERTIES ¿?¿???¿??¿?¿?¿?");
		}
		
	//favs_recibidos_emisor_evento = (boolean) event.getProperty("LUZ");

		if(favs_recibidos_emisor_evento == true){
			System.out.println("Se recibio el evento con LUZ = TRUE");
			
			//LED ,, LED.ENCENDER
			Leds tuiter_on = new Leds();
			//azul 59 131 189
			tuiter_on.setColorPlug(59,'r');
			tuiter_on.setColorPlug(131,'g');
			tuiter_on.setColorPlug(189.0,'b');
			double rold,gold,bold;
			rold = tuiter_on.getColorPLug('r');
			gold = tuiter_on.getColorPLug('g');
			bold = tuiter_on.getColorPLug('b');
			System.out.println("ENCIENDO LED TUITER ------> AZUL ");
			tuiter_on.EncenderRGB2(0, 59, 131, 189, tuiter_on.getIntensidad());
			try {
				Thread.currentThread().sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tuiter_on.EncenderRGB2(0, rold, gold, bold, tuiter_on.getIntensidad());



		}else if(favs_recibidos_emisor_evento == false){
			System.out.println(" SE HA RECIBIDO EL EVENTO CON LUZ = FALSE , NO CAMBIOS");
		}else{
			System.out.println(" SE HA RECIBIDO EL EVENTO CON LUZ = ¿?¿?¿? --> EXCEPCION ");
		}
		
	}

}
