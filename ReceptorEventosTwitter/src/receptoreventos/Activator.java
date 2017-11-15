package receptoreventos;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		String[] topics = new String[] {
	            "TWITTER"
	        };
//EVENTO LUZ, MAS ESCALABLE, EN EMISOR EVENTOS T,F,I , el evento es luz, yo en mis bundle emisores utilizo la funciones de tw, y le meto en el properties
		
	        Dictionary props = new Hashtable();
	        props.put(EventConstants.EVENT_TOPIC, topics);
	        bundleContext.registerService(EventHandler.class.getName(), new ReceptorEventos() , props);
	       		System.out.println("HA FINALIZADO START DEL ACTIVATOR DE RECEPTOR EVENTOS TUITER ");
	       		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
