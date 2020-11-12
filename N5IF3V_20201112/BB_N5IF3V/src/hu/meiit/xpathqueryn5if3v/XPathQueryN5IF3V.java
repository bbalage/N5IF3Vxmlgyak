package hu.meiit.xpathqueryn5if3v;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XPathQueryN5IF3V {

	public static void main(String[] args) {
		try{
			File file = new File("studentN5IF3V.xml");
			FileInputStream fileis = new FileInputStream(file);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fileis);
			XPath xPath = XPathFactory.newInstance().newXPath();
			System.out.println("Current element: student");
			System.out.println("Student roll no: 593");
			String firstname =  xPath.compile("//student[@rollno=593]/firstname").evaluate(doc);
			System.out.println("First name: "+firstname);
			String lastname =  xPath.compile("//student[@rollno=593]/lastname").evaluate(doc);
			System.out.println("Last name: "+lastname);
			String nickname =  xPath.compile("//student[@rollno=593]/nickname").evaluate(doc);
			System.out.println("Nick name: "+nickname);
			String marks =  xPath.compile("//student[@rollno=593]/marks").evaluate(doc);
			System.out.println("Marks: "+marks);
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
