package configuracion;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import servicios_configuracion.Config;


public class Activator implements BundleActivator {

	static boolean conf_inicial_hecha = false;
	ServiceRegistration reg;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		// TODO Auto-generated method stub
		Configuracion conf = null;
		try{
		 conf = new Configuracion();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		reg = bundleContext.registerService(Config.class.getName(), conf, null);		
		boolean primera_conexion = conf.getConfInicialStatus();
		if(primera_conexion == false){ // configuracion inicial necesaria
			conf.configuracionInicial();// ya entra en modo inicial
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {

	}

}
