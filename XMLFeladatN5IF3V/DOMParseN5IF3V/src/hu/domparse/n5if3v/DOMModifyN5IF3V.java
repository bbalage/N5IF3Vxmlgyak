package hu.domparse.n5if3v;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import hu.domparse.n5if3v.utils.MyAppException;

import java.util.Scanner;

public class DOMModifyN5IF3V {

	private Document document;
	
	/*Document Object Model gets initialized in the constructor.*/
	public DOMModifyN5IF3V() throws IOException, ParserConfigurationException, SAXException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		/*Has to be set so the namespace declaration part of the xml 
		 * document won't cause an error at validation.*/
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(new File("XMLN5IF3V.xml"));
	}
	
	/*When the class is run, it instantiates itself in the main method.*/
	public static void main(String[] args) {
		try {
			DOMModifyN5IF3V ownInstance = new DOMModifyN5IF3V();
			/*Modify the planet.*/
			ownInstance.consoleModifySession();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	/*A modification loop, which uses a console to modify XML objects.*/
	public void consoleModifySession() throws TransformerConfigurationException, TransformerException, IOException, SAXException{
		Scanner input = new Scanner(System.in);
		String choice;
		do {
			System.out.println("Please, type a letter to continue:\nq to quit, m to modify a planet");
			choice = input.nextLine();
			switch(choice) {
			case "m":
				try {
					this.modifyPlanet();
				}
				catch(SAXParseException exc) {
					System.out.println("Modification failed: "+exc.getMessage());
				}
				catch(MyAppException exc) {
					System.out.println(exc.getMessage());
				}
				break;
			}
		}
		while(!choice.equals("q"));
		System.out.println("Thank you for being the part of this universe! Goodbye!");
	}
	
	/*Function to modify the local data of the planets.*/
	public void modifyPlanet() throws MyAppException, TransformerConfigurationException, TransformerException, IOException, SAXException{
		System.out.println("Please, type the id of the planet you want to modify. Available:\n");
		NodeList planets = document.getElementsByTagName("planet");
		for(int i = 0; i < planets.getLength(); i++) {
			System.out.println(planets.item(i).getAttributes().getNamedItem("id").getTextContent());
		}
		Scanner input = new Scanner(System.in);
		
		/*Choose the planet*/
		String planetId = input.nextLine();
		Element planet = null;
		for(int i = 0; i < planets.getLength(); i++) {
			if(planets.item(i).getAttributes().getNamedItem("id").getTextContent().equals(planetId)) {
				planet = (Element) planets.item(i);
				break;
			}
		}
		if(planet == null) {
			throw new MyAppException("No planet by such id.");
		}
		
		/*Get the new data of the planet.*/
		System.out.println("Please, write the new NAME of the planet, then press Enter.");
		String name = input.nextLine();
		System.out.println("Please, write the new RADIUS of the planet, then press Enter.");
		String radius = input.nextLine();
		System.out.println("Please, write the new MASS of the planet, then press Enter.");
		String mass = input.nextLine();
		System.out.println("Please, write the new TYPE of the planet (solid, gas, dwarf), then press Enter.");
		String type = input.nextLine();
		Node planetName = planet.getElementsByTagName("name").item(0);
		planetName.setTextContent(name);
		Node planetRadius = planet.getElementsByTagName("radius").item(0);
		planetRadius.setTextContent(radius);
		Node planetMass = planet.getElementsByTagName("mass").item(0);
		planetMass.setTextContent(mass);
		Node planetType = planet.getElementsByTagName("type").item(0);
		planetType.setTextContent(type);
		
		/*Validate the new data of the planet*/
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		Schema schema = factory.newSchema(new File("XMLSchemaN5IF3V.xsd"));
		Validator validator = schema.newValidator();
		DOMSource domsource = new DOMSource(document);
		validator.validate(domsource);
		
		/*Save the new data of the planet into the file.
		 * First calibrate the transformer.*/
		Transformer tr = TransformerFactory.newInstance().newTransformer();
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty(OutputKeys.METHOD, "xml");
		tr.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-2");
		tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		/*Write the result into the file.
		 * XMLN5IF3V_mod.xml is a support file, which exist so the original data don't get 
		 * messed up.*/
		tr.transform(domsource, new StreamResult(new FileOutputStream("XMLN5IF3V_mod.xml")));
		System.out.println("Modification succesful!\n");
	}

}
