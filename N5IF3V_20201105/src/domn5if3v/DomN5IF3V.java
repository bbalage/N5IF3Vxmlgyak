package domn5if3v;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;

import java.io.*;

import javax.xml.parsers.*;

public class DomN5IF3V {
	
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("BB_munkafajlok/szemelyek.xml"));
			
			Element root = document.getDocumentElement();
			
			System.out.println("Root element: "+root.getTagName());
			
			NodeList nodelist = root.getChildNodes();
			
			
			for(int i = 1; i < nodelist.getLength(); i+=2) {
				Element el = (Element)nodelist.item(i);
				String attrVal = el.getAttribute("id");
				System.out.println("id: "+attrVal);
				
				NodeList szemelyValues = el.getChildNodes();
				
				
				for(int j = 1; j < szemelyValues.getLength(); j+=2) {
					Element szemelyValue = (Element)szemelyValues.item(j);
					String value = szemelyValue.getTextContent();
					String tagname = szemelyValue.getTagName();
					System.out.println("  "+tagname+" : "+value);
				}
			}
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		catch(SAXException exc) {
			exc.printStackTrace();
		}
		catch(ParserConfigurationException exc) {
			exc.printStackTrace();
		}
		catch(ClassCastException exc) {
			exc.printStackTrace();
			
		}
	}
	
}
