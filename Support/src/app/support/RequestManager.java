package app.support;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import android.util.Log;

/*
 * 		Clase: RequestManager
 * 
 * 		Descripcion: Esta clase les provee la estructura necesaria para establecer peticiones
 * 		a un script de ejecucion (en este caso, PHP) enviandole valores por el metodo POST y
 * 		almacenando la respuesta de la peticion enviada.
 * 
 * 		Por lo tanto, esta clase puede ser utilizada para todas las peticiones que realicen
 * 		al servidor.
 * 		
 * 		------ IMPORTANTE ------
 * 		Deben incluir en el archivo Manifest, los permisos de uso de Internet.
 * 
 * 		Antes de la etiqueta "<application>" incluir la siguiente linea:
 * 
 * 		<uses-permission android:name="android.permission.INTERNET"/>
 * 
 * 		Revisar el archivo manifest.
 * 		------------------------
 */

public class RequestManager {
	
	/*
	 * Propiedades (atributos)
	 */
	
	InputStream stream;
	JSONArray responseJSON;
	String responseString;
	
	/*
	 * Constructor:
	 *   Se encarga de realizar la conexion con la URL y las variables POST recibidas como argumento.
	 * Codigo:
	 *   Si la conexion es correcta y ademas se obtuvo una respuesta
	 * 		-> La respuesta es almacenada en la propiedad "responseJSON"
	 * 		para posteriormente obtenerla desde un Activity
	 *   de lo contrario
	 * 		-> La propiedad "responseJSON" es igualada a null
	 */
	
	public RequestManager(String url, ArrayList<NameValuePair> vars) {
		if (connectPostMethod(url, vars) && this.stream != null) {
			this.responseJSON = getJSONResponse();
		} else {
			this.responseJSON = null;
		}
	}
	
	/*
	 * Metodo:
	 *   Devuelve la respuesta del servidor en formato JSON para
	 *   que posteriormente sea tratada y mostrada por un Activity.
	 */
	
	public JSONArray getServerResponse() {
		return this.responseJSON;
	}
	
	/*
	 * Metodo:
	 *   Se encarga de establecer la conexion con el servidor (definido por la URL)
	 *   y pasar los valores (en este caso POST) correspondientes. 
	 */

	private boolean connectPostMethod(String url, ArrayList<NameValuePair> vars) {
		
		try {
			HttpClient client = new DefaultHttpClient();	// Se crea una instancia HttpClient
			
			HttpPost post = new HttpPost(url);				// Se crea una instancia HttpPost
			post.setEntity(new UrlEncodedFormEntity(vars));	// ... por esta instancia, se codifican las variables
			
			HttpResponse response = client.execute(post);	// Se crea una instancia HttpResponse
															// ... en la cual se almacena la respuesta del servidor al
															// enviar por el cliente las variables (POST) codificadas.
			
			HttpEntity entity = response.getEntity();		// Se crea una instancia de HttpEntity
															// ... en la cual se almacena el cuerpo de la respuesta del servidor.
			
			this.stream = entity.getContent();				// El contenido del cuerpo de la respuesta es almacenado
															// en la propiedad "stream" (tipo InputStream)
			
			return true;									// Si no surgen Excepciones, el metodo devuelve el valor TRUE
			
		} catch (Exception e) {
			// Si ocurre una Excepcion, es mostrado en LogCat la descripcion correspondiente a la excepcion
			Log.e("#EXCEPTION: connectPostMethod()", e.getMessage());
			// El metodo devuelve el valor FALSE
			return false;
		}
		
	}
	
	/*
	 * Metodo:
	 *   Se encarga de tratar la respuesta (responseString) para devolverla en formato JSONArray
	 */
	private JSONArray getJSONResponse() {
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(this.stream,"iso-8859-1"),8);
			
			StringBuilder sbuilder = new StringBuilder();
			
			String line = null;
			
			while ((line = buffer.readLine()) != null) {
				sbuilder.append(line + "\n");
			}
			
			this.stream.close();
			
			this.responseString = sbuilder.toString();
			
			JSONArray jsonResponse = new JSONArray(this.responseString);
			
			return jsonResponse;
			
		} catch (Exception e) {
			Log.e("#EXCEPTION: getJSONResponse()", e.getMessage());
			return null;
		}
	}

}
