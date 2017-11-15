package gpio.servicio;

public interface GpioLed {
	public void creoLeds();
	public void EncenderLedrgb(int plug, double r, double g, double b, double intensidad);
	public void ApagarLedrgb(int plug, double r, double g, double b, double intensidad);
	
	public void EncenderRGB2(int plug, double r, double g, double b, double intensidad);
	public void ApagarRGB2(double intensidad);


	
	public void EncenderLed(int plug, double r, double g, double b, double intensidad);
	public void ApagarLed(int plug);
	//Con PWM : http://www.lediouris.net/RaspberryPI/PWM/readme.html
	public void EncenderLedPWM(int plug, double r, double g, double b, double intensidad);
	public void ApagarLedPWM(int plug);

	public double getColorPLug(char letra);
	public void setColorPlug(double nuevo, char letra);
	public int getTipo();
	public void setTipo(int nuevotipo);
	public double getIntensidad();
	public void setIntensidad(double nuevaintensidad);
}
