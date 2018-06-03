package proz;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class NBPConnector {

	private Client client;
	private URI uri;

	public String connect(String tab, String code, String topCount) {
		client = ClientBuilder.newClient();
		uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates").build();

		WebTarget webTarget = client.target(uri).path(tab).path(code).path("last").path(topCount);
		return webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
	}
}