package hu.domparse.n5if3v;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import hu.domparse.n5if3v.utils.MyAppException;

public class DOMReadN5IF3V {

	private Document document;
	
	/*Document Object Model gets read from file in the constructor.*/
	public DOMReadN5IF3V() throws IOException, ParserConfigurationException, SAXException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(new File("XMLN5IF3V.xml"));
	}
	
	/*Run the class. The class instantiates itself in its main method.*/
	public static void main(String[] args) {
		try {
			DOMReadN5IF3V ownInstance = new DOMReadN5IF3V();
			/*Print all planets.*/
			ownInstance.printAllPlanets();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	/*Function to read all planets.*/
	public void printAllPlanets() throws MyAppException{
		NodeList planets = document.getElementsByTagName("planet");
		
		/*Go through all planets*/
		for(int i = 0; i < planets.getLength(); i++) {
			/*Get id of planet*/
			NamedNodeMap attributes = planets.item(i).getAttributes();
			String planetId = attributes.getNamedItem("id").getTextContent();
			System.out.println("id: "+planetId);
			Element planetElement = (Element) planets.item(i);
			/*Get local data of planets*/
			String planetName = planetElement.getElementsByTagName("name").item(0).getTextContent();
			String planetRadius = planetElement.getElementsByTagName("radius").item(0).getTextContent();
			String planetMass = planetElement.getElementsByTagName("mass").item(0).getTextContent();
			String planetType = planetElement.getElementsByTagName("type").item(0).getTextContent();
			double radius = Double.parseDouble(planetRadius);
			double mass = Double.parseDouble(planetMass);
			double planetDensity = mass/(radius*radius*radius*Math.PI*4/3)/1000000000;
			System.out.println("name: "+planetName);
			System.out.println("radius: "+planetRadius+" km");
			System.out.println("mass: "+planetMass+ " kg");
			System.out.println("type: "+planetType);
			System.out.println("density: "+planetDensity+" kg per m3");
			/*Print the name of the planet's sun(s)*/
			NodeList planetOfList = document.getElementsByTagName("planet_of");
			for(int j = 0; j < planetOfList.getLength(); j++) {
				Node planetOf = planetOfList.item(j);
				if(planetOf.getNodeType() == 1) {
					Element planetOfElement = (Element)planetOf;
					if(planetOfElement.getElementsByTagName("orbiting_planet").item(0).getTextContent().equals(planetId)) {
						String starId = planetOfElement.getElementsByTagName("owner_star").item(0).getTextContent();
						System.out.println("star: "+this.getStarNameById(starId));
					}
				}
			}
			/*Print the name of the planet's moon(s)*/
			NodeList moons = document.getElementsByTagName("moon");
			for(int j = 0; j < moons.getLength(); j++) {
				Node moon = moons.item(j);
				if(moon.getAttributes().getNamedItem("id").getTextContent().equals(planetId)) {
					if(moon.getNodeType() == 1) {
						Element moonElement = (Element) moon;
						String moonName = moonElement.getElementsByTagName("name").item(0).getTextContent();
						System.out.println("moon: "+moonName);
					}
				}
			}
			/*Print the name and the amount of the material(s) the planet is composed of*/
			NodeList planetMaterials = document.getElementsByTagName("planet_material");
			for(int j = 0; j < planetMaterials.getLength(); j++) {
				Element planetMaterialElement = (Element) planetMaterials.item(j);
				if(planetMaterialElement.getElementsByTagName("in_planet").item(0).getTextContent().equals(planetId)) {
					String materialId = planetMaterialElement.getElementsByTagName("of_material").item(0).getTextContent();
					String amount = planetMaterialElement.getElementsByTagName("amount").item(0).getTextContent();
					System.out.println("material: "+amount+" kg of "+this.getMaterialNameById(materialId));
				}
			}
			System.out.println();
		}
	}
	
	/*Get the name for the star with the given id*/
	public String getStarNameById(String starId) throws MyAppException{
		NodeList stars = document.getElementsByTagName("star");
		for(int i = 0; i < stars.getLength(); i++) {
			Element starElement = (Element) stars.item(i);
			if(starElement.getAttributes().getNamedItem("id").getTextContent().equals(starId)) {
				String name = starElement.getElementsByTagName("name").item(0).getTextContent();
				if(name != null && name.length() > 0) {
					return name;
				}
				else {
					return null;
				}
			}
		}
		throw new MyAppException("FATAL: Star not found! Foreign key violated (or coding error)!");
	}
	
	/*Get the name for the material with the given id*/
	public String getMaterialNameById(String materialId) throws MyAppException{
		NodeList materials = document.getElementsByTagName("material");
		for(int i = 0; i < materials.getLength(); i++) {
			Element materialElement = (Element) materials.item(i);
			if(materialElement.getAttributes().getNamedItem("id").getTextContent().equals(materialId)) {
				String name = materialElement.getElementsByTagName("name").item(0).getTextContent();
				if(name != null && name.length() > 0) {
					return name;
				}
				else {
					return null;
				}
			}
		}
		throw new MyAppException("FATAL: Material not found! Foreign key violated (or coding error)!");
	}

}
