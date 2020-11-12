package hu.meiit.xpathparsen5if3v;

import java.io.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XpathParseN5IF3V {

	public static void main(String[] args) {
		
		try{
			File file = new File("studentN5IF3V.xml");
			FileInputStream fileis = new FileInputStream(file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileis);
			XPath xPath = XPathFactory.newInstance().newXPath();
			System.out.println("Current element: ");
			String name =  xPath.compile("//student[name()=student]").evaluate(doc);
			System.out.println(name);
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		catch(ParserConfigurationException exc) {
			exc.printStackTrace();
		}
		catch (SAXException exc) {
			exc.printStackTrace();
		}
		catch(XPathException exc) {
			exc.printStackTrace();
		}
	}
}
