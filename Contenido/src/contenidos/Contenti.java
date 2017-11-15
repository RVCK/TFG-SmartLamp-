package contenidos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import contenidos.servicio.ContentProvider;
import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class Contenti implements ContentProvider {
	
	//TOKEN DE TWITTER 4 
	static String token1;
	static String token2;
    static String token3;
	static String token4;
	static boolean tokenscheck = false;

	
	public int favs;
	public static int mensajes;
	public static int amigos;
	public int numerolineas = 6;//ID+NOMBRE + 4 TOKENS
	public String pin_OAuth;
	public String token_acceso;
	public String token_acceso_secreto;
	
	public String getContent(){
		return "666 prueba";
	}
	
	public Contenti(){
		
	}
	
/**
	public int favsTwitterCinco(){
	
		Twitter twitter =
			     new TwitterFactory().getOAuthAuthorizedInstance("QZZDz6cARCTsC6dWWO5t8kEAg", "V3UzaVZMvcMznFuIebihHr6wxD8dMX1IINninwp9mBguIGfZmM");
TwitterFactory a = new TwitterFactory();

			  try {
			    RequestToken requestToken = twitter.getOAuthRequestToken(
			           "http://localhost:9000/application/callback");

			    session.put("requestToken_token", requestToken.getToken());
			    session.put("requestToken_secret", requestToken.getTokenSecret());
			    redirect(requestToken.getAuthorizationURL());
			   } catch (Exception e) {
			    e.printStackTrace();
			   }
			}
**/
	
	public void completarTokens(String a, String b, String c, String d){
		this.token1 = a;
		this.token2 = b;
		this.token3 = c;
		this.token4 = d;
	}	
	
	
public int favsTwitterCuatro(){
	
		    // The factory instance is re-useable and thread safe.
		    Twitter twitter = TwitterFactory.getSingleton();
		    twitter.setOAuthConsumer("QZZDz6cARCTsC6dWWO5t8kEAg","V3UzaVZMvcMznFuIebihHr6wxD8dMX1IINninwp9mBguIGfZmM");
		    RequestToken requestToken = null;
			try {
				requestToken = twitter.getOAuthRequestToken();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    AccessToken accessToken = null;
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    while (null == accessToken) {
		      System.out.println("Open the following URL and grant access to your account:");
		      System.out.println(requestToken.getAuthorizationURL());
		      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
		      String pin = "";
			try {
				//pin = br.readLine();
				pin = "8724556";
				this.setPinOauth(pin);
				System.out.println("PIN ES ["+pin+"]");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      try{
		         if(pin.length() > 0){
		        	System.out.println("PIN.LENG > 0");
		           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		         }else{
		           accessToken = twitter.getOAuthAccessToken();
		         }
		         
		         this.setAccTok(accessToken.getToken());
		         this.setAccTokSecret(accessToken.getTokenSecret());
		         System.out.println("ACCESS TOKEN = " + this.getAccTok());
		         
		         
		      } catch (TwitterException te) {
		        if(401 == te.getStatusCode()){
		          System.out.println("Unable to get the access token.");
		        }else{
		          te.printStackTrace();
		        }
		      }
		    }
		    
		    System.out.println("HE SALIDO DE WHILE PIN");

		       ResponseList<Status> statuses;
		        
				try{
					statuses = twitter.getFavorites();
					return statuses.size();
				}catch (TwitterException e) {
					// TODO Auto-generated catch block
					System.out.println("ESTOY EN TWITTER-EXCEPTION");
					e.printStackTrace();
					return -1;
				}

		    

//return 0;
		    
}
	
	
	public int favsTwitterTresAuto(){
		
		ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("QZZDz6cARCTsC6dWWO5t8kEAg")
            .setOAuthConsumerSecret("V3UzaVZMvcMznFuIebihHr6wxD8dMX1IINninwp9mBguIGfZmM");
    		TwitterFactory tf = new TwitterFactory(cb.build());
    		Twitter twitter = tf.getInstance();
        // Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        
        RequestToken requestToken = null;
		try {
			requestToken = twitter.getOAuthRequestToken();
	        System.out.println("Obtenido request token (OAUTH TOKEN).");
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			System.out.println("EXCEPCION OBTENIENDO OAUTH TOKEN");
			e1.printStackTrace();
		}
		String token = requestToken.getToken();
        System.out.println("Request token: " + token);
        String secrettoken = requestToken.getTokenSecret();
        System.out.println("Request token secret: " + secrettoken);
        AccessToken accessToken = null;        
        
		ConfigurationBuilder completo = new ConfigurationBuilder();
        completo.setDebugEnabled(true)
        .setOAuthConsumerKey("QZZDz6cARCTsC6dWWO5t8kEAg")
        .setOAuthConsumerSecret("V3UzaVZMvcMznFuIebihHr6wxD8dMX1IINninwp9mBguIGfZmM")
		 .setOAuthAccessToken(token)
		 .setOAuthAccessTokenSecret(secrettoken);
		TwitterFactory tfc = new TwitterFactory(completo.build());
		Twitter twitterc = tfc.getInstance();

        
	//	TwitterFactory tf = new TwitterFactory(cb.build());
	//	Twitter twitter = tf.getInstance();


	       ResponseList<Status> statuses;
	        
			try{
				statuses = twitterc.getFavorites();
				return statuses.size();
			}catch (TwitterException e) {
				// TODO Auto-generated catch block
				System.out.println("ESTOY EN TWITTER-EXCEPTION");
				e.printStackTrace();
				return -1;
			}

		
	}
	
	public int favsTwitterDos(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("QZZDz6cARCTsC6dWWO5t8kEAg")
		  .setOAuthConsumerSecret("V3UzaVZMvcMznFuIebihHr6wxD8dMX1IINninwp9mBguIGfZmM")
		  .setOAuthAccessToken("756173471625846785-FbNrdHSjEmcFuPE292Pu48JO2fyfq7i")
		  .setOAuthAccessTokenSecret("3xRtAgfHCyYHkmbPRPVQmf4v4HTowZ36QYIULn8zS5ijM");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();

	       ResponseList<Status> statuses;
	        
			try{
				statuses = twitter.getFavorites();
				return statuses.size();
			}catch (TwitterException e) {
				// TODO Auto-generated catch block
				System.out.println("ESTOY EN TWITTER-EXCEPTION");
				e.printStackTrace();
				return -1;
			}

		
	}
	
	public int favsTwitter(){
		
		
		Twitter twitter = new TwitterFactory().getInstance();
        //List<Status> statuses;
        ResponseList<Status> statuses;
        
		try {
		
			statuses = twitter.getFavorites();
			return statuses.size();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			System.out.println("ESTOY EN TWITTER-EXCEPTION");
			e.printStackTrace();
			return -1;
		}

	}
	
	
public boolean checkStatus(){
		boolean cambios = false;
		boolean cambiosamigos = false;
		boolean cambiosmensajes = false;
		int old_favs;
		int old_mensajes;
		int mensajes_aux;
		int old_amigos;
		int amigos_aux;
 //synchronized(this){

		//old_favs = this.getFavs();
		old_mensajes = this.getMensajes();
		old_amigos = this.getAmigos();
		System.out.println("MENSAJES ACTUALES = " + old_mensajes);
		System.out.println("AMIGOS ACTUALES = " + old_amigos);
		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mensajes_aux = mensajesTwitter() ;
		amigos_aux= amigosTwitter();
		System.out.println("MENSAJES ACTUALIZADOS = " + mensajes_aux);
		System.out.println("AMIGOS ACTUALIZADOS = " + amigos_aux);
		
	  if(amigos_aux != -1 && mensajes_aux != -1){ // fav_aux != -1  && 
		  
		  if(old_amigos != amigos_aux){
			  cambiosamigos = true;
			  this.setAmigos(amigos_aux);
		  }else{
			  cambiosamigos = false;
		  }
		  
		  if(old_mensajes != mensajes_aux){
			  cambiosmensajes = true;
			  this.setMensajes(mensajes_aux);
		  }else{
			  cambiosmensajes = false;
		  }
		  
		  if(cambiosmensajes || cambiosamigos){
			  cambios = true;
		  }else{
			  cambios = false;
		  }
		  
	 
	  }else{
		  System.out.println("ERROR EN FUNCIONES QUE LLAMAN A TWITTER4J ( return-1 ) ");
		  cambios = false;
	  } 
 //}
		return cambios;
	}


	public int getFavs(){
		return this.favs;
	}
	
	
	
	public void setFavs(int nuevo) {
		// TODO Auto-generated method stub
		this.favs = nuevo;
	}
	
	public int getMensajes(){
		return this.mensajes;
	}
	
	
	
	public void setMensajes(int nuevo) {
		// TODO Auto-generated method stub
		this.mensajes = nuevo;
	}
	
	public int getAmigos(){
		return this.amigos;	
	}
	
	
	
	public void setAmigos(int nuevo) {
		// TODO Auto-generated method stub
		this.amigos= nuevo;

	}

	@Override
	public int favsTwitterCinco() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getPinOauth(){
		return this.pin_OAuth;
	}
	
	public void setPinOauth(String pini){
		this.pin_OAuth = pini;
		
	}

	@Override
	public String getAccTok() {
		// TODO Auto-generated method stub
		return this.token_acceso;
	}

	@Override
	public void setAccTok(String token) {
		// TODO Auto-generated method stub
		this.token_acceso = token;
	}

	@Override
	public String getAccTokSecret() {
		// TODO Auto-generated method stub
		return this.token_acceso_secreto;
	}

	@Override
	public void setAccTokSecret(String tokensecret) {
		// TODO Auto-generated method stub
		this.token_acceso_secreto = tokensecret;
		
	}

	@Override
	public int amigosTwitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("aObj61Q4riGtH6G1q1tqv9CKj")
		  .setOAuthConsumerSecret("Obygzv87cECsPmYV6wwvJpRJJz6pjjiXjOxkG31u7jxjhcyzEM")
		  .setOAuthAccessToken("756173471625846785-pjqjwmD2A5D2mvusaMb6l5vSc8Qt27k")
		  .setOAuthAccessTokenSecret("jNMQHbVnnUviVJG4xYwl5wRAwWLYTxqE73mNcrG8WbGhf");
		
		System.out.println("set los keys" );

			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();   

	        int numero = 0;
            long cursor = -1;
            IDs ids;

			try{
				do {

	                ids = twitter.getFollowersIDs(cursor);
	            
	                for (long id : ids.getIDs()) {
	                    System.out.println(id);
	                    numero++;
	                }
				} while ((cursor = ids.getNextCursor()) != 0);  

				 return numero;
				  
			}catch (TwitterException e) {
				// TODO Auto-generated catch block
				System.out.println("ESTOY EN TWITTER-EXCEPTION -- amigos");
				e.printStackTrace();
				return -1;
			}
		
	}

	@Override
	public int mensajesTwitter() {

		int mensajes = 0;
		int aux = 0;
		
		ConfigurationBuilder cb = new ConfigurationBuilder();

	  /**	
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("lPHJsMPx64FXbfgO49KGL2NAl")
		  .setOAuthConsumerSecret("DnhAyB6mPJYCLlSS1lKu3RhcME5Yo2LvaEP9ekjmDmSHHrPqux")
		  .setOAuthAccessToken("756173471625846785-w6qLbe7FgOG0k8E9JApWbL491qf4Zb6")
		  .setOAuthAccessTokenSecret("PHlnyRs4u6Fl34EYhqjSp2NUQQGlyqr4fn63tEmoGAjB8");
		**/
/**
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(this.token1)
		  .setOAuthConsumerSecret(this.token2)
		  .setOAuthAccessToken(this.token3)
		  .setOAuthAccessTokenSecret(this.token4);
	**/	

		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("aObj61Q4riGtH6G1q1tqv9CKj")
		  .setOAuthConsumerSecret("Obygzv87cECsPmYV6wwvJpRJJz6pjjiXjOxkG31u7jxjhcyzEM")
		  .setOAuthAccessToken("756173471625846785-pjqjwmD2A5D2mvusaMb6l5vSc8Qt27k")
		  .setOAuthAccessTokenSecret("jNMQHbVnnUviVJG4xYwl5wRAwWLYTxqE73mNcrG8WbGhf");
		
		System.out.println("set los keys" );

			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();  

			//twitter.setOAuthConsumer("aObj61Q4riGtH6G1q1tqv9CKj","Obygzv87cECsPmYV6wwvJpRJJz6pjjiXjOxkG31u7jxjhcyzEM");
			
			try {
	            Paging paging = new Paging(1);
	            List<DirectMessage> messages;
	            do {
	                messages = twitter.getDirectMessages(paging);
	                aux = messages.size();
	                mensajes = mensajes + aux;
	                aux = 0;
	                paging.setPage(paging.getPage() + 1);
	            } while (messages.size() > 0 && paging.getPage() < 10);
	           return mensajes;
	        } catch (TwitterException te) {
				System.out.println("ESTOY EN TWITTER-EXCEPTION -- mensajes");
	            te.printStackTrace();
	            return -1;
	}
	}
	
	public void setTokensLeidos(boolean valor){
		this.tokenscheck = valor;
	}
	
	public boolean tokensLeidos(){
		return this.tokenscheck;
	}
	
	

}//end class
