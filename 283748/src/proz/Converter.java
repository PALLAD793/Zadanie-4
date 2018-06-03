package proz;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Path("exchangerates/rates")
public class Converter {

	private NBPConnector connector;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{table}/{code}/{topCount}")
	public String getTextAnswer(@PathParam("table") String tab, @PathParam("code") String cod,
			@PathParam("topCount") String topCnt) {
		
		if(tab != null && tab != null && cod != null && tab.length() != 0  && tab.length() != 0 && cod.length() != 0 && tab.toLowerCase().equals("c")) {
			connector = new NBPConnector();
			String xml = connector.connect(tab, cod, topCnt);
	
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(NBPAnswers.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader sr = new StringReader(xml);
				NBPAnswers answer = (NBPAnswers) unmarshaller.unmarshal(sr);
				return String.valueOf(answer.average(answer));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return "0";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("{table}/{code}/{topCount}")
	public String getHtmlAnswer(@PathParam("table") String tab, @PathParam("code") String cod,
			@PathParam("topCount") String topCnt) {
		
		if(tab != null && tab != null && cod != null && tab.length() != 0  && tab.length() != 0 && cod.length() != 0 && tab.toLowerCase().equals("c")) {
			connector = new NBPConnector();
			String xml = connector.connect(tab, cod, topCnt);
	
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(NBPAnswers.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader sr = new StringReader(xml);
				NBPAnswers answer = (NBPAnswers) unmarshaller.unmarshal(sr);
				return "<html><body><h1>" + answer.average(answer) + "</h1></body></html>";
	
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return "<html><body><h1>0</h1></body></html>";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{table}/{code}/{topCount}")
	public String getXmlAnswer(@PathParam("table") String tab, @PathParam("code") String cod,
			@PathParam("topCount") String topCnt) {
		
		if(tab != null && tab != null && cod != null && tab.length() != 0  && tab.length() != 0 && cod.length() != 0 && tab.toLowerCase().equals("c")) {
			connector = new NBPConnector();
			String xml = connector.connect(tab, cod, topCnt);
	
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(NBPAnswers.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader sr = new StringReader(xml);
				NBPAnswers answer = (NBPAnswers) unmarshaller.unmarshal(sr);
				return "<?xml version=\"1.0\"?>" + "<average>" + answer.average(answer) + "</average>";
	
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return "<?xml version=\"1.0\"?>" + "<average>0</average>";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{table}/{code}/{topCount}")
	public String getJsonAnswer(@PathParam("table") String tab, @PathParam("code") String cod,
			@PathParam("topCount") String topCnt) {
		
		if(tab != null && tab != null && cod != null && tab.length() != 0  && tab.length() != 0 && cod.length() != 0 && tab.toLowerCase().equals("c")) {
			connector = new NBPConnector();
			String xml = connector.connect(tab, cod, topCnt);
	
			JAXBContext context;
			try {
				context = JAXBContext.newInstance(NBPAnswers.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				StringReader sr = new StringReader(xml);
				NBPAnswers answer = (NBPAnswers) unmarshaller.unmarshal(sr);
	
				JsonObjectBuilder jsonbuilder = Json.createObjectBuilder();
				jsonbuilder.add("average", answer.average(answer));
				JsonObject jsonobject = jsonbuilder.build();
				return jsonobject.toString();
	
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return "0";
	}

}
