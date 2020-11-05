package domn5if3v;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomParserN5IF3V {
	
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("BB_munkafajlok/szemelyek.xml"));
		
			Element root = document.getDocumentElement();
			
			System.out.println("Root element: "+root.getTagName());
			
			NodeList nodelist = root.getChildNodes();
			
			for(int i = 0; i < nodelist.getLength(); i++) {
				
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
}
