package configuracion;

import gpio.servicio.Leds;

import java.util.List;


import servicios_configuracion.Config;

public class Configuracion implements Config{

	static boolean configuracion_inicial_completa = false;
	
	@Override
	public void configuracionInicial() {
		// TODO Auto-generated method stub
	
		//se indicará la luz en blanco, esto quiere decir que la SmartlAMP no tiene ninguna red social activada, pero da luz
		Leds primer_encendido = new Leds();
		//blanca 255 255 255
		primer_encendido.setColorPlug(255,'r');
		primer_encendido.setColorPlug(255,'g');
		primer_encendido.setColorPlug(255.0,'b');
		primer_encendido.setIntensidad(100.0);
		primer_encendido.EncenderRGB2(0, 255, 255,255, 100);
		System.out.println("ENCIENDO LED modo inicial ");
		this.setConfInicialStatus(true);// MODO INICIAL ACTIVO
		
	}

	
	public boolean getConfInicialStatus(){
		return this.configuracion_inicial_completa;
	}
	
	public void setConfInicialStatus(boolean valor){
		this.configuracion_inicial_completa = valor;
	}

}
