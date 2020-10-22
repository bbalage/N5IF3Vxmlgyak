package saxpack;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import java.io.IOException;

public class SaxN5IF3V implements ContentHandler{

    public static void main(String[] args) {
    	try{
    		XMLReader parser = new SAXParser();
    		SaxN5IF3V sax = new SaxN5IF3V();
    		parser.setContentHandler(sax);
    		if (args.length > 0) {
    			parser.parse(args[0]);
    		}
    		else {
    			parser.parse("cats.xml");
    		}
    	}
    	catch(Exception exc) {
    		exc.printStackTrace();
    	}
    }

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		for(int i = arg1; i < arg1+arg2; i++) {
			System.out.print(arg0[i]);
		}
		System.out.println();
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2) throws SAXException {
		System.out.println("end");
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2, Attributes arg3) throws SAXException {
		System.out.println("start");
		//System.out.println(arg0);
		System.out.println(arg1);
		//System.out.println(arg2);
		if(arg3.getLength() > 0) {
			System.out.println(arg3.getLocalName(0)+": "+arg3.getValue(0));
		}
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub
		
	}

}