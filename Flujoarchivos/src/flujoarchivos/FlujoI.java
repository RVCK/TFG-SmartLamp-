package flujoarchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import servicioflujos.Flujos;

public class FlujoI implements Flujos{
	
	static int numero_plugins; // Numero de redes sociales instaladas, inicialmente : TWITTER SI, FB A MEDIAS

	@Override
	public void leerLineaArchivo(String name) {
		String linea = "666";
		int i=0;
		try {
			BufferedReader reader =	new BufferedReader(new FileReader(name));
			linea = reader.readLine();
			//while(linea != null) {
			// procesar el texto de la línea
			
			while(i<4){
			 linea = reader.readLine();
			 System.out.println("LO LEIDO = " + linea);
			 i++;
			}
			reader.close();
			}
			catch(FileNotFoundException e) {
			// no se encontró el fichero
				e.printStackTrace();
			}
			catch(IOException e) {
			// algo fue mal al leer o cerrar el fichero
				e.printStackTrace();
			}

		
	}

	@Override
	public List<String> leePlugins(String name){
		List<String> activos = new ArrayList<String>();
		String linea = "666";
		int i=0;
		try {
			BufferedReader reader =	new BufferedReader(new FileReader(name));
			// procesar el texto de la línea
			while(i<this.numero_plugins){//i<3
				
			 linea = reader.readLine();
			 System.out.println("LO LEIDO leeplugins = " + linea);
			// activos.add(i+linea+""); USAR PARA NO CARGARSE OSGI !!!!!
			 activos.add(linea);

			 i++;
			}
			 
			reader.close();
			}catch(FileNotFoundException e) {
			// no se encontró el fichero
				e.printStackTrace();
			}catch(IOException e) {
			// algo fue mal al leer o cerrar el fichero
				e.printStackTrace();
			}
		
		return activos;
	}
	
	public List<String> leeTokensTwitter(String name){
		List<String> tokens = new ArrayList<String>();
		String linea = "666";
		int i=0;
		try {
			BufferedReader reader =	new BufferedReader(new FileReader(name));
			
			while(i<4){//i<3
				
			 linea = reader.readLine();
			 System.out.println("LO LEIDO leetokenstuiter= " + linea);
			// activos.add(i+linea+""); USAR PARA NO CARGARSE OSGI !!!!!
			 tokens.add(linea);

			 i++;
			}
			 
			reader.close();
			}catch(FileNotFoundException e) {
			// no se encontró el fichero
				e.printStackTrace();
			}catch(IOException e) {
			// algo fue mal al leer o cerrar el fichero
				e.printStackTrace();
			}
		
		return tokens;
	}

	
	
	
	@Override
	public List<String> leePluginsDOS(String name){// no se usa
		List<String> activos = new ArrayList<String>();
		String linea = "666";
		int i=0;
		int j = this.numero_plugins;
		try {
			BufferedReader reader =	new BufferedReader(new FileReader(name));
			// procesar el texto de la línea
			 System.out.println("LO LEIDO ANTES DE WHILE = " + linea);

			while(i<j){//i<this.numero_plugins
				
			 linea = reader.readLine();
			 System.out.println("LO LEIDO = " + linea);
			 activos.add(i+linea+""); //USAR PARA NO CARGARSE OSGI !!!!!
			 i++;
			}
			 
			reader.close();
			}catch(FileNotFoundException e) {
			// no se encontró el fichero
				e.printStackTrace();
			}catch(IOException e) {
			// algo fue mal al leer o cerrar el fichero
				e.printStackTrace();
			}
		
		return activos;
	}

	
	@Override
	public String leeIntensidad(String name) {
		//String linea = "666";
		String linea=null;
		int i=0;
		try {
			BufferedReader reader =	new BufferedReader(new FileReader(name));

			 linea = reader.readLine();

			 System.out.println("LO LEIDO leeintensidad = " + linea);
			 i++;
			 
			reader.close();
			}
			catch(FileNotFoundException e) {
			// no se encontró el fichero
				e.printStackTrace();
			}
			catch(IOException e) {
			// algo fue mal al leer o cerrar el fichero
				e.printStackTrace();
			}
		
		return linea;
	}

	@Override
	public List<String> leeColor(String name) {
		List<String> activos = new ArrayList<String>();
		String linea = "666";
		int i=0;
		try {
			BufferedReader reader =	new BufferedReader(new FileReader(name));


			while(i<3){//linea != null)
			 linea = reader.readLine();

			 System.out.println("LO LEIDO leecolor = " + linea);
			 activos.add(linea+"");
			 i++;
			}
			reader.close();
			}
			catch(FileNotFoundException e) {
			// no se encontró el fichero
				e.printStackTrace();
			}
			catch(IOException e) {
			// algo fue mal al leer o cerrar el fichero
				e.printStackTrace();
			}
		
		return activos;
	}
 
	
	public int getNumeroPluginsCreados(){
		return this.numero_plugins;
	}
	public void setNumeroPluginsDisponibles(int num){
		this.numero_plugins = num;
	}
		
	


}
