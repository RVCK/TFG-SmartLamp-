package contenidos.servicio;

public interface ContentProvider {
		
	public String getContent();
	
	//FAVORITOS
	public int favsTwitter();
	public int favsTwitterDos(); // FUNCIONA
	public int favsTwitterTresAuto();
	public int favsTwitterCuatro();
	public int favsTwitterCinco();
	
	
	//MENSAJES
	public int mensajesTwitter();
	//AMIGOS
	public int amigosTwitter();
	public boolean checkStatus(); // Función principal, que llema a demás funciones para dar un veredicto
	
	public String getPinOauth();
	public void setPinOauth(String pini);
	
	public String getAccTok();
	public void setAccTok(String token);
		
	public String getAccTokSecret();
	public void setAccTokSecret(String tokensecret);
	
	public int getFavs();
	public void setFavs(int nuevo);
	
	public int getMensajes();
	public void setMensajes(int nuevo);
	
	public int getAmigos();
	public void setAmigos(int nuevo);
	
	public boolean tokensLeidos();
	public void setTokensLeidos(boolean valor);
	
	public void completarTokens(String a, String b, String c, String d);
	
	//https://github.com/yusuke/twitter4j/tree/master/twitter4j-examples/src/main/java/twitter4j/examples/directmessage
	//https://github.com/yusuke/twitter4j/blob/master/twitter4j-examples/src/main/java/twitter4j/examples/friendsandfollowers/GetFollowersIDs.java
}