package flujoarchivos;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import servicioflujos.Flujos;

public class Activator implements BundleActivator {
	
	ServiceRegistration reg;
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		// TODO Auto-generated method stub
		FlujoI flujo = null;
		try{
		 flujo = new FlujoI();
		 //IMPORTANTE : AQUI INDICAMOS CUANTAS REDES SOCIALES EXISTEN EN NUESTRO SISTEM ACTUALEMNTE
		 flujo.setNumeroPluginsDisponibles(2);//TWITTER SI + FB A MEDIAS ( SE HA MEDIO IMPLEMENTADO PARA QUE EL FUTURO PROGRAMADOR VEA LOS PASOS A SEGUIR )
		 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		reg = bundleContext.registerService(Flujos.class.getName(), flujo, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {

	}

}
