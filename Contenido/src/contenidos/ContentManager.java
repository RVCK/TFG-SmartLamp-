package contenidos;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import contenidos.servicio.ContentProvider;

public class ContentManager implements BundleActivator{
	
	ServiceRegistration reg;

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		Contenti contenido = null;
		try{
		 contenido = new Contenti();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		reg = context.registerService(ContentProvider.class.getName(), contenido, null);		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}