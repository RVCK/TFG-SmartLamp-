package servicioflujos;

import java.util.List;

public interface Flujos {
	
	public void leerLineaArchivo(String name); // no se usa
	
	//REDES SOCIALES
	public List<String> leePlugins(String name);
	public List<String> leePluginsDOS(String name);
	
	//TOKENS ASOCIADOS A CADA RED SOCIAL ( DEPENDE DE LA EMPRESA DE LA RED SOCIAL )	
	public List<String> leeTokensTwitter(String name);

	public String leeIntensidad(String name);
	public List<String> leeColor(String name);
	
	public int getNumeroPluginsCreados();
	public void setNumeroPluginsDisponibles(int num);


}
