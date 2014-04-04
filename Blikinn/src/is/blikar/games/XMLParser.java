package is.blikar.games;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
/**
 * 
 * @author Guðný Lára Guðmundsdóttir
 * @since 26.11.13
 * Description: This class parses through a xml file
 *
 */

public class XMLParser extends DefaultHandler {
	
	//The URL to be parsed
	private String URL_MAIN = "http://urslit.1x2.is/urslit/xml.exe/AllirLeikir";
	String TAG = "XMLHelper";
	
	Boolean currTag = false;
	String currTagVal = "";
	int meet;
	public LeikirValue leikur = null;
	public MotValue mot = null;
	public ArrayList<LeikirValue> leikir = new ArrayList<LeikirValue>();
	public ArrayList<MotValue> mots = new ArrayList<MotValue>();
	
	//Method to read XML from URL_MAIN
	public void get() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser mSaxParser = factory.newSAXParser();
			XMLReader mXmlReader = mSaxParser.getXMLReader();
			mXmlReader.setContentHandler(this);
			InputStream mInputStream = new URL(URL_MAIN).openStream();
			Reader reader = new InputStreamReader(mInputStream, "ISO-8859-1");
			InputSource is = new InputSource(reader);
			is.setEncoding("ISO-8859-1");
			mXmlReader.parse(is);
		} 
		catch(Exception e) {
			Log.e(TAG, "Exception: " + e.getMessage());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(currTag) {
			currTagVal = currTagVal + new String(ch, start, length);
			currTag = false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currTag = false;
		
		if(localName.equalsIgnoreCase("SkyringHeimalids"))
			leikur.setNyjastaMarkHeima(currTagVal);
		
		if(localName.equalsIgnoreCase("SkyringUtilids"))
			leikur.setNyjastaMarkUti(currTagVal);
		
		if(localName.equalsIgnoreCase("MorkHeimalids"))
			leikur.setMarkHeima(currTagVal);
		
		else if(localName.equalsIgnoreCase("MorkUtilids"))
			leikur.setMarkUti(currTagVal);
		
		else if(localName.equalsIgnoreCase("Leikur"))
			leikir.add(leikur);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		Log.i(TAG, "TAG: " + localName);
		currTag = true;
		currTagVal = "";
		if(localName.equals("Mot")){
			meet += 1;
			mot = new MotValue();
			String motid= attributes.getValue("HeitiMots");
			String ar= attributes.getValue("Artal");
			String motstring= motid +" "+ ar;
			String kyn=attributes.getValue("Kyn");
			mot.setKyn(kyn);
			String grein = attributes.getValue("Grein");
			mot.setGrein(grein);
			mot.setMot(motstring);
			mot.setMeet(meet);
			mots.add(mot);
		}
		if(localName.equals("Leikur")) {
			leikur = new LeikirValue();
			leikur.setDone(false);
			leikur.setMeet(meet);
			leikur.setDate(attributes.getValue("Dagsetning"));
			leikur.setStada(attributes.getValue("StadaLeiks"));
		}	
		if(localName.equals("Heimalid")){
			leikur.setHeimalid(attributes.getValue("StuttHeiti"));
		}
		if(localName.equals("Utilid")){
			leikur.setUtilid(attributes.getValue("StuttHeiti"));
		}
	}
}