package emisoreventos;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import configuracion.Configuracion;
import contenidos.Contenti;
import flujoarchivos.FlujoI;

import javax.crypto.*;

public class Activator implements BundleActivator, Runnable {

	private static BundleContext context;
	EventAdmin eventAdmin = null;
	int favs_actuales;
	Thread t = null;
	
	Contenti tuiterobj; 
	//DECLARAR OBJETOS DEL BUNDLE QUE SE ASOCIE CON LA RED SOCIAL ( ESCALABILIDAD )
	
	Configuracion confi;
	
	//Declarar boolean red social, ver ejemplo en la implementación
	boolean twitter = false;
	boolean facebook = false;
	
	
	
	boolean orden_app_intensidad = false;
	boolean orden_app_color = false;
	String lastintensidad;
	String lastr, lastg, lastb;
	
	String rutaplugins = "/var/www/html/plugins.txt";
	String rutacolor = "/var/www/html/color.txt";
	String rutaintensidad = "/var/www/html/intensidad.txt";
	
	String rutatokenstwitter = "/var/www/html/twitter.txt";
	
	boolean terminar = false;
	
	boolean confinicial;
	
	ServiceReference ref = null;
	
	//para saber si estas en modo inicial y debes mandar o no el evento ( HTML mod )
	boolean html_intensidad_modificado = false;//el usuario no ha cambiado la intensidad desde HTML, sigue en MODO INICIAL
	boolean enviar_evento_intensidad = false;
	boolean html_color_modificado=false;
	boolean enviar_evento_color = false;
	boolean html_social_modificado=false;
	boolean enviar_evento_social = false;
	

	static BundleContext getContext() {
		return context;
	}



	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {// 
		this.context = bundleContext;		
		this.ref = bundleContext.getServiceReference(EventAdmin.class.getName());
		
        if (ref != null){
        	
 // Con getServiceReference encontramos una implementacion de un servicio (interfaz) creado previamente
        	EventAdmin eventAdmin = (EventAdmin) bundleContext.getService(ref);
        	
        	t = new Thread(this,"HebraCreadoraEventos");
        	confi = new Configuracion();
        	confinicial = confi.getConfInicialStatus();
        	if(confinicial == true){
        	  System.out.println("Configuracion inicial = TRUE");
        	  //MODO INICIAL ; NO HAY REDES SOCIALES DE LOS QUE LEER
        	}else{
        	  System.out.println("Configuracion inicial = FALSE");	
        	}	
        	
        	t.start();        	
            }
       
   }

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		terminar = true;
	}
	
	boolean inicial_intensidad = true;
	boolean inicial_color = true;
	boolean inicial_social = true;
	
	boolean cambioi_after = false ;
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		BundleContext envio = this.getContext();
		EventAdmin envios = (EventAdmin) envio.getService(ref);
		
		Thread actual = Thread.currentThread();
		
		while(t == actual && t != null){
		
        while(terminar == false){
        	
        	try {   
        		Thread.currentThread().sleep(5500);			
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        		System.out.println("Error en sleep de hebra emisor eventos");
        	}   
        	
	
        	//INTENSIDAD
        	String cienuno = "100";
        	String ciendos = "100.0";
        	FlujoI intensidad = new FlujoI();
        	String intensidad_app_aux = intensidad.leeIntensidad(rutaintensidad);
        	System.out.println("resultado leeIntensidad =" + intensidad_app_aux);
        	if((intensidad_app_aux.equals(cienuno) || intensidad_app_aux.equals(ciendos)) && inicial_intensidad == true){
        		//Sigue en MODO INICIAL
        		html_intensidad_modificado = false;
        	}else{
        		html_intensidad_modificado = true;
        		inicial_intensidad = false;
        	}
        	
        	if(confinicial==true && html_intensidad_modificado==true ){//El usuario ha tocado el HTML de intensidad --> Se envía
        		enviar_evento_intensidad = true;
        		confi.setConfInicialStatus(false);//salir MODO INICIAL !
        		
        	}else if(confinicial==true && html_intensidad_modificado==false ){//MODO INICIAL --> No se envía
        		enviar_evento_intensidad = false;	
        		
        	}else if(confinicial == false ){
        		enviar_evento_intensidad = true;
        	}
        	
        	if(enviar_evento_intensidad == true){// Creación y envío del evento intensidad , para modificar LED
        		System.out.println("SI SE ENVIA EVENTO INTENSIDAD .");

            	String intensidad_app = intensidad.leeIntensidad(rutaintensidad);
        		System.out.println("Intensidad leida de HTML = " + intensidad_app);

            	this.lastintensidad = intensidad_app;
                Dictionary evento_intensidad_properties = new Hashtable();
                evento_intensidad_properties.put("TITLE", "APP CAMBIA INTENSIDAD");
                evento_intensidad_properties.put("INTENSIDAD" , intensidad_app+"");
                evento_intensidad_properties.put("TIME", System.currentTimeMillis()+"");
                Event intenapp = new Event("APPINTENSIDAD", evento_intensidad_properties);
                envios.sendEvent(intenapp);
                this.orden_app_intensidad= true;
        	}else{
        		System.out.println("NO SE ENVIA EVENTO INTENSIDAD");
        	}


            
        	//COLOR
        	String colordefecto = "255";
        	String colordefecto2 = "255.0";
        	FlujoI color = new FlujoI();
        	List<String> color_app_aux;
        	color_app_aux = color.leeColor(rutacolor);
        	String red, green, blue;
        	Iterator itc = color_app_aux.iterator();
        	red = (String) itc.next();
        	green = (String) itc.next();
        	blue = (String) itc.next();


        	if( ((red.equals(colordefecto) || red.equals(colordefecto2)) && (green.equals(colordefecto) || green.equals(colordefecto2)) && (blue.equals(colordefecto) || blue.equals(colordefecto2))) && inicial_color == true){
        		//Sigue en MODO INICIAL
        		html_color_modificado = false;
        	}else{
        		html_color_modificado = true;
        		inicial_color = false;
        	}
        	
        	if(confinicial==true && html_color_modificado==true ){//El usuario ha tocado el HTML de intensidad --> Se envía
        		enviar_evento_color = true;
        		confi.setConfInicialStatus(false);//salir MODO INICIAL !
        		
        	}else if(confinicial==true && html_color_modificado==false ){//MODO INICIAL --> No se envía
        		enviar_evento_color = false;	
        		
        	}else if(confinicial == false){
        		enviar_evento_color = true;
        	}

          if(enviar_evento_color == true){	//&& valores leidos distintos a lastr,lastg,lastb
        	System.out.println("SI SE ENVIA EVENTO COLOR");

        	List<String> rgb;
        	rgb = color.leeColor(rutacolor);
        	String r, g, b;
        	Iterator itc2 = rgb.iterator();
        	r = (String) itc2.next();
        	g = (String) itc2.next();
        	b = (String) itc2.next();
    		System.out.println("Color leido de HTML = " + r + "-" + g + "-" + b);

        	this.lastr = r;
        	this.lastg = g;
        	this.lastb = b;        	
            Dictionary evento_color_properties = new Hashtable();
            evento_color_properties.put("TITLE", "APP CAMBIA COLOR");
            evento_color_properties.put("R" , r);
            evento_color_properties.put("G" , g);
            evento_color_properties.put("B" , b);
            evento_color_properties.put("TIME", System.currentTimeMillis()+"");
            Event colorapp = new Event("APPCOLOR", evento_color_properties);
            envios.sendEvent(colorapp);
        	orden_app_color = true;
          }else{
      		System.out.println("NO SE ENVIA EVENTO COLOR");
          }	
        
    
        	//REDES SOCIALES
      	FlujoI pluginsaux = new FlujoI();
    	List<String> resultadoaux;
    	resultadoaux = pluginsaux.leePlugins(rutaplugins);   
      	String tuiter, feisbuk;
    	Iterator itcc = resultadoaux.iterator();
    	int tipoplugaux = 0;
    	boolean tuiter_activado = false;
    	boolean facebook_activado = false;

//!!  EL 1 LO HAGO POR EL FACEBOOK, CUANDO LO ACTIVO EN EL HTML SE ME PONE FALSE EN EL PLUGINS.TXT
    	
    	while(itcc.hasNext()){ // NO haria falta flujo.getNumeroPLugins Y PONER WHILE I<VARIABLE-GET
    		String aux = (String) itcc.next();
    		char caracter;
    		if(aux.equals("")){
    		 caracter = ' ';	
    		}else{
    		 caracter = aux.charAt(0);//1
    		}
        		
    		if(caracter == 't' && tipoplugaux == 0){//TWITTER ACTIVADO
    			tuiter_activado = true;  
    		}
    			
    		if(caracter == 'f' && tipoplugaux == 1){ //FACEBOOK ACTIVADO
    			facebook_activado = true;
    		}
    		
    		if(caracter == ' ' && tipoplugaux == 0){//TWITTER DESACTIVADO
    			tuiter_activado = false;    			
    		}
    		
    		if(caracter == ' ' && tipoplugaux == 1){ // FACEBOOK DESACTIVADO
    			facebook_activado = false;
    		}	        			

    		tipoplugaux++;
    		
    		if(tipoplugaux == 2){// Se itera en 0:Twitter 1:Facebook 2:NO EXISTE PLUGIN ASOCIADO
    			tipoplugaux = 0;
    		}
    		   		
    		
    		}//while comprobacion plugins
    	
/// PLUGINS COMPROBADOS SI ESTAN ACTIVADOS O NO, AHORA PROCEDEMOS A ENVIAR LOS EVENTOS DE LOS QUE SI ESTAN PROGRAMADOS    	
 	
    if(confinicial == true){// MODO INICIAL
    	if(tuiter_activado == true || facebook_activado == true){
    		enviar_evento_social = true;
			confi.setConfInicialStatus(false);
    	}
    }else{
    		enviar_evento_social = true;
    }	
    	
    if(enviar_evento_social == true){	 
          
        	FlujoI plugins = new FlujoI();
        	List<String> resultado;
        	resultado = plugins.leePlugins(rutaplugins);    	
        	Iterator it = resultado.iterator();
        	int tipoplug = 0;

	//!!  EL 1 LO HAGO POR EL FACEBOOK, CUANDO LOACTIVO EN EL HTML SE ME PONE FALSE EN EL PLUGINS.TXT
        	
        	while(it.hasNext()){ // NO haria falta flujo.getNumeroPLugins Y PONER WHILE I<VARIABLE-GET
        		
        		//System.out.println("iterador princpal = " + tipoplug);
        		String aux = (String) it.next();
        		char caracteraux;
        		if(aux.equals("")){
        		 caracteraux = ' ';	
        		}else{
        		 caracteraux = aux.charAt(0);//1
        		}
        		        		
        		if(caracteraux == 't' && tipoplug == 0){//TWITTER ACTIVADO
        			
        			System.out.println("SE ACTIVO PLUGIN TWITTER ");       
        			
    				tuiterobj = new Contenti();
    				
    			  if(tuiterobj.tokensLeidos() == true){
    			    	System.out.println("TOKENS ACTIVADOS, PROCEDO A CREAR Y ENVIAR EVENTO");
    				boolean cambiotuiter = tuiterobj.checkStatus(); // PROCESAMIENTO REAL DE TWITTER
    				
    				
    				System.out.println("mi boolean de checkStatus tuiter " + cambiotuiter);
    				Dictionary tproperties = new Hashtable();
    	            tproperties.put("TITLE", "FAVORITOS DE TUITER ASOCIADOS AL USUARIO SMARTLAMP");
    	            tproperties.put("LUZ" , cambiotuiter+"");
    	            tproperties.put("TIME", System.currentTimeMillis()+"");
    	        	Event reportGeneratedEvent = new Event("TWITTER", tproperties);
    	                        	
    	            envios.sendEvent(reportGeneratedEvent);
    	            
    	            this.twitter= true;
    	            
    	            if(cambiotuiter){
    	            	orden_app_color = false;
    	            	orden_app_intensidad = false;
    	            	
    	            }
        		 }else{//Los tokens no se han leido
        		    System.out.println("TOKENS DESACTIVADOS, NO SE ENVIA EVENTO, VOY A LEER DEL HTML TOKENS TWITTER");

                	FlujoI mistokens = new FlujoI();
                	List<String> tokenstuiter;
                	tokenstuiter = plugins.leeTokensTwitter(rutatokenstwitter); 
                	Iterator ittoken = tokenstuiter.iterator();
                	String uno, dos, tres, cuatro;
                	uno = (String) ittoken.next();
                	dos = (String) ittoken.next();
                	tres = (String) ittoken.next();
                	cuatro = (String) ittoken.next();
                	
                	if(uno.equals("") || dos.equals("") || tres.equals("") || cuatro.equals("")){ // FALLO EN TOKEN
           			 //System.out.println("Red social TWITTER activa PERO usuario no ha insertado las claves asociadas a su cuenta");
                     System.out.println("ERROR EN ALGUN TOKENS");
                	}else{//tokens bien
                		tuiterobj.completarTokens(uno,dos,tres,cuatro);
                    	System.out.println("set tokens ok");

                		tuiterobj.setTokensLeidos(true);
                    	System.out.println("TOKENS LEIDOS CORRECTAMENTE");

                	}
                	
        			 
        		 } 
        		
        		}
        		
        		
        		if(caracteraux == 't' && tipoplug == 1){ //FACEBOOK ACTIVADO
        			System.out.println("SE ACTIVO PLUGIN FACEBOOK ");
        		}
        		
  
        		tipoplug++;    		
        		if(tipoplug == 2){
        			tipoplug = 0;
        		}
        		   		
        		
        		}//while comprobacion plugins
         }else{//if enviar evento
         	System.out.println("HTML PLUGIN NO MODIFICADO");
         }	 
        	 
        }//while que lo para el stop() de activator 

	}//while hebra actual
		System.out.println("LA HEBRA VALE NULL O ES UNA HEBRA PERDIDA DEL SISTEMA");
   }//method

	

}//class
