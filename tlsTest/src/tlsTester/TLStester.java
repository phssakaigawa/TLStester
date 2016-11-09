/**
 *
 */
package tlsTester;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
/**
 * @author s.sakaigawa
 *
 */
public class TLStester {
	 //Set the https use TLSv1.2
	private static Registry<ConnectionSocketFactory> getRegistry() throws KeyManagementException, NoSuchAlgorithmException {
	    SSLContext sslContext = SSLContexts.custom().build();
	    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
	            new String[]{"TLSv1.2","TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	    return RegistryBuilder.<ConnectionSocketFactory>create()
	            .register("http", PlainConnectionSocketFactory.getSocketFactory())
	            .register("https", sslConnectionSocketFactory)
	            .build();
	}

	public static void main(String[] args) {
	    try {

	        //Set the https use TLSv1.2
	        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(getRegistry());
	        clientConnectionManager.setMaxTotal(100);
	        clientConnectionManager.setDefaultMaxPerRoute(20);
	        HttpClient client = HttpClients.custom().setConnectionManager(clientConnectionManager).build();
	        //Then you can do : client.execute(HttpGet or HttpPost);

	        HttpGet httpget = new HttpGet(args[0]);
	        System.out.println(httpget.getRequestLine());
	        HttpResponse response = client.execute(httpget);

	        HttpEntity entity = response.getEntity();
	        System.out.println(response);
	        EntityUtils.consume(entity);


	    } catch (KeyManagementException | NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	}
}
