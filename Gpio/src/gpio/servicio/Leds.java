package gpio.servicio;

import java.awt.Color;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.wiringpi.SoftPwm;

public class Leds implements GpioLed{

	int plug;
	static double ractual, gactual, bactual;
	static double intensidad_actual;
	
	private Color coloract;
	
	//final GpioController gpio = null ;
	
    //private final Pin redPin;
    //private final Pin greenPin;
    //private final Pin bluePin;
	//public static Pin a = RaspiPin.GPIO_00;
	// public static Pin b = RaspiPin.GPIO_02;
	 //public static Pin c = RaspiPin.GPIO_03;
//	final Pin a= RaspiPin.GPIO_00;
//	final Pin b= RaspiPin.GPIO_02;
//	final Pin c= RaspiPin.GPIO_03;
	 
	//	public static Pin a;
		// public static Pin b;
		 //public static Pin c;
	//public PinLayout pines;
	//RGBLed rgbled;
	//Libreria que trabaja con pi4j
	//https://github.com/entrusc/Pi-RGB-LED/tree/master/src/main/java/de/pi3g/pi/rgbled !!!!!!!
	//https://github.com/entrusc/Pi-RGB-LED/blob/master/src/main/java/de/pi3g/pi/rgbled/RGBLed.java
	//static Pin a=null,b=null,c=null;
	
	public void creoLeds(){
		System.out.println("Entro en creo leds");
	/**
       this.a = CommandArgumentParser.getPin(
                RaspiPin.class,    // pin provider class to obtain pin instance from
                RaspiPin.GPIO_00,  // default pin if no pin argument found
                null); 
        
        this.b = CommandArgumentParser.getPin(
                RaspiPin.class,    // pin provider class to obtain pin instance from
                RaspiPin.GPIO_02,  // default pin if no pin argument found
                null);  
        
        this.c = CommandArgumentParser.getPin(
                RaspiPin.class,    // pin provider class to obtain pin instance from
                RaspiPin.GPIO_03,  // default pin if no pin argument found
                null); 
                
                **/
     
		//this.a = RaspiPin.getPinByName("GPIO_00");
		//this.b = RaspiPin.getPinByName("GPIO_02");
		//this.c = RaspiPin.getPinByName("GPIO_03");
		
		//Pin a = null,b = null,c = null;
		System.out.println("He creado pines");
		
		//PinLayout aux = new PinLayout(a,b,c);
				//this.pines = aux.rpi;
		//System.out.println("he creado pinlayout");
		///PinLayout aux = new PinLayout(a,b,c);
		//this.pines = aux;
		//RGBLed auxdos = new RGBLed(this.pines);
		//this.rgbled = new RGBLed(this.pines);
		//RGBLed aux2 = new RGBLed(aux);

		//this.rgbled = auxdos;
	}
	
	
	
	public void EncenderRGB2(int plug, double r, double g, double b, double intensidad){

		//float rojo = (int) r;
		//float verde = (int) g;
		//float azul = (int) b;
		int rojo = (int) r;
		int verde = (int) g;
		int azul = (int) b;
		System.out.println("RECIBI R INT= " + rojo);
		System.out.println("RECIBI G INT= " + verde);
		System.out.println("RECIBI B INT= " + azul);
		Color a = new Color(rojo,verde,azul);
		this.coloract = a;
		//display
		int intensidadd = (int) intensidad;
		
		final GpioController gpio  = GpioFactory.getInstance();
 
        final GpioPinDigitalOutput ledRed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"uno",PinState.HIGH);
        final GpioPinDigitalOutput ledGreen = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23,"dos",PinState.HIGH);
        final GpioPinDigitalOutput ledBlue = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24,"tres",PinState.HIGH);
        //final GpioPinPwmOutput pwmr = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01);
        //final GpioPinPwmOutput pwmg = gpio.provisionPwmOutputPin(RaspiPin.GPIO_23);
        //final GpioPinPwmOutput pwmb = gpio.provisionPwmOutputPin(RaspiPin.GPIO_24);
        
        com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
        com.pi4j.wiringpi.Gpio.pwmSetRange(1000);
        com.pi4j.wiringpi.Gpio.pwmSetClock(500);
        
        //pwmr.setPwm(500);
        //pwmg.setPwm(500);
        //pwmb.setPwm(500);
        ledRed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        ledGreen.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        ledBlue.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        
        SoftPwm.softPwmCreate(RaspiPin.GPIO_01.getAddress(), 0, 100);//100
        SoftPwm.softPwmCreate(RaspiPin.GPIO_23.getAddress(), 0, 100);
        SoftPwm.softPwmCreate(RaspiPin.GPIO_24.getAddress(), 0, 100);
        
        final float[] colors = a.getRGBColorComponents(null);
		System.out.println("c0 "+colors[0] );
		System.out.println("c1 "+colors[1] );
		System.out.println("c2 "+colors[2] );

		int x= (int) colors[0] * intensidadd;
		int y= (int) colors[1] * intensidadd;
		int z= (int) colors[2] * intensidadd;
		System.out.println("c0  2   "+x );
		System.out.println("c1  2	"+y );
		System.out.println("c2  2	"+z);
		
        SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(), x); //*50f
        SoftPwm.softPwmWrite(RaspiPin.GPIO_23.getAddress(), z);
        SoftPwm.softPwmWrite(RaspiPin.GPIO_24.getAddress(), y);
        
        //SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(), (int) (colors[0] * intensidadd)); //*50f
        //SoftPwm.softPwmWrite(RaspiPin.GPIO_23.getAddress(), (int) (colors[1] * intensidadd));
        //SoftPwm.softPwmWrite(RaspiPin.GPIO_24.getAddress(), (int) (colors[2] * intensidadd));
        
        
       // SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(),  (rojo * intensidadd)); //*50f
        //SoftPwm.softPwmWrite(RaspiPin.GPIO_23.getAddress(), (verde * intensidadd));
        //SoftPwm.softPwmWrite(RaspiPin.GPIO_24.getAddress(), (azul * intensidadd));

        /**
                ledRed.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        ledGreen.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        ledBlue.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);

        SoftPwm.softPwmCreate(pinLayout.getRedPin().getAddress(), 0, 50);
        SoftPwm.softPwmCreate(pinLayout.getGreenPin().getAddress(), 0, 50);
        SoftPwm.softPwmCreate(pinLayout.getBluePin().getAddress(), 0, 50);
        
        off();
        
        **/
		
		gpio.shutdown();
		gpio.unprovisionPin(ledRed);
		gpio.unprovisionPin(ledGreen);
		gpio.unprovisionPin(ledBlue);
		//gpio.unprovisionPin(pwmr);
		//gpio.unprovisionPin(pwmg);
		//gpio.unprovisionPin(pwmb);
	}
	

	public void ApagarRGB2(double intensidad){
		this.coloract = Color.BLACK;
		
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput ledRed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        final GpioPinDigitalOutput ledGreen = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23);
        final GpioPinDigitalOutput ledBlue = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24);
        
        final float[] colors = this.coloract.getRGBColorComponents(null);
        SoftPwm.softPwmWrite(RaspiPin.GPIO_01.getAddress(), (int) (colors[0] * intensidad)); //*50f
        SoftPwm.softPwmWrite(RaspiPin.GPIO_23.getAddress(), (int) (colors[1] * intensidad));
        SoftPwm.softPwmWrite(RaspiPin.GPIO_24.getAddress(), (int) (colors[2] * intensidad));
		gpio.shutdown();
		gpio.unprovisionPin(ledRed);
		gpio.unprovisionPin(ledGreen);
		gpio.unprovisionPin(ledBlue);
	
	}

	
	

	
	
	public void EncenderLedrgb(int plug, double r, double g, double b, double intensidad) {
		
		float rojo = (int) r;
		float verde = (int) g;
		float azul = (int) b;
		Color a = new Color(rojo,verde,azul);
		double inten = this.getIntensidad();
		//this.rgbled.displayColor(a,inten); // SUSTITUIR POR LA MIA
		
/*
 * while(true){
 *  this.rgbled.displayColor(a,inten); // SUSTITUIR POR LA MIA
	delay(1000)
 *  this.rgbled.off(); // ApagarLedrgb(0,rojo,verde,azul,0);
 * }
 * 
 * 
 */
		
	}
	
	public void ApagarLedrgb(int plug, double r, double g, double b, double intensidad){
		//this.rgbled.off();
	}
	

	
	
	/**
	 * int plug permite saber de qué evento viene la llamada a EncenderLed()
	 * 0 twitter --> azul
	 * 1 fb --> verde
	 * 2 insta --> rojo 204-006-005
	 * 3 Configuración personal del usuario INTENSIDAD(desde app/html)
	 * 4 Configuracion personal del usuario COLOR 
	 * 
	 * **/
	@Override // https://github.com/Pi4J/pi4j/tree/master/pi4j-example/src/main/java	
	public void EncenderLed(int plug, double r, double g, double b, double intensidad) {
		// TODO Auto-generated method stub
		  final GpioController gpio = GpioFactory.getInstance();

	        // by default we will use gpio pin #01; however, if an argument
	        // has been provided, then lookup the pin by address
	        Pin pin = CommandArgumentParser.getPin(
	                RaspiPin.class,    // pin provider class to obtain pin instance from
	                RaspiPin.GPIO_01,  // default pin if no pin argument found
	                null);             // ARGS argument array to search in

	        // provision gpio pin as an output pin and turn on
	final GpioPinDigitalOutput output = gpio.provisionDigitalOutputPin(pin, "My Output", PinState.HIGH);
		
		
	}
	
	public void EncenderLedPWM(int plug, double r, double g, double b, double intensidad){
		
		final GpioController gpio = GpioFactory.getInstance();

		// by default we will use gpio pin #01; however, if an argument
		// has been provided, then lookup the pin by address
		Pin pin = CommandArgumentParser.getPin(
						RaspiPin.class,    // pin provider class to obtain pin instance from
						RaspiPin.GPIO_01,  // default pin if no pin argument found
						null);             // argument array to search in

		// we will provision the pin as a software emulated PWM output
		// pins that support hardware PWM should be provisioned as normal PWM outputs
		// each software emulated PWM pin does consume additional overhead in
		// terms of CPU usage.
		//
		// Software emulated PWM pins support a range between 0 (off) and 100 (max) by default.
		//
		// Please see: http://wiringpi.com/reference/software-pwm-library/
		// for more details on software emulated PWM
		GpioPinPwmOutput pwm = gpio.provisionSoftPwmOutputPin(pin);

		// optionally set the PWM range (100 is default range)
		pwm.setPwmRange(100);

		// prompt user that we are ready
		System.out.println(" ... Successfully provisioned PWM pin: " + pwm.toString());
		// set the PWM rate to 100 (FULLY ON)
		//pwm.setPwm(100);
		pwm.setPwm((int) intensidad);


			//No , porque esto solo enciende, habra uno que apague gpio.shutdown();
	}


	@Override
	public void ApagarLed(int plug) {
		// TODO Auto-generated method stub
		  final GpioController gpio = GpioFactory.getInstance();

	        // by default we will use gpio pin #01; however, if an argument
	        // has been provided, then lookup the pin by address
	        Pin pin = CommandArgumentParser.getPin(
	                RaspiPin.class,    // pin provider class to obtain pin instance from
	                RaspiPin.GPIO_01,  // default pin if no pin argument found
	                null);             // ARGS argument array to search in

	        // provision gpio pin as an output pin and turn on
	final GpioPinDigitalOutput output = gpio.provisionDigitalOutputPin(pin, "My Output", PinState.LOW);
		
		
		
	}
	
	public void ApagarLedPWM(int plug){
		
	  final GpioController gpio = GpioFactory.getInstance();
		Pin pin = CommandArgumentParser.getPin(
				RaspiPin.class,    // pin provider class to obtain pin instance from
				RaspiPin.GPIO_01,  // default pin if no pin argument found
				null);
		GpioPinPwmOutput pwm = gpio.provisionSoftPwmOutputPin(pin);
		
		pwm.setPwm(0); // apagar ?
		
		gpio.shutdown();

	  

		
	}


	@Override
	public double getColorPLug(char letra) {
		double result = 0;
		switch(letra){
		case 'r':
			result = this.ractual;
		case 'g':
			result = this.gactual;
		case 'b':
			result = this.bactual;
		}
	 return result;	
	}

	@Override
	public void setColorPlug(double nuevo, char letra) {
		// TODO Auto-generated method stub
		if(letra == 'r'){
			this.ractual = nuevo;
		}else if(letra == 'g'){
			this.gactual = nuevo;
		}else if(letra == 'b'){
			this.bactual = nuevo;
		}else{
			System.out.println("COMPONENTE DE COLOR RGB INCORRECTA ");
		}
		
	}

	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return this.plug;
	}

	@Override
	public void setTipo(int nuevotipo) {
		// TODO Auto-generated method stub
		this.plug = nuevotipo;
		
	}

	@Override
	public double getIntensidad() {
		// TODO Auto-generated method stub
		return this.intensidad_actual;
	}

	@Override
	public void setIntensidad(double nuevaintensidad) {
		// TODO Auto-generated method stub
		this.intensidad_actual = nuevaintensidad;
	}
}
