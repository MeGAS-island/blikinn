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
 * @author Aðalsteinn Stefánsson
 * @since 22.11.13
 * Description: Parses through a XML file
 *
 */
public class XMLParseMenLeague extends DefaultHandler {
	//The URL to be parsed
	private String URL_MAIN = "http://urslit.1x2.is/urslit/xml.exe/stadamots?mot=ISL001&Deild=1&Timabil=2013";
	String TAG = "XMLParseMenLeague";
	
	Boolean currTag = false;
	String currTagVal = "";
	public LeaguePosValue leaguePos = null;
	public ArrayList<LeaguePosValue> leaguePositions = new ArrayList<LeaguePosValue>();
	
	//Method to read XML from {@link XMLParseMenLeague#URL_MAIN}
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
			Log.e(TAG, "ExceptionLALA: " + e.getMessage());
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

	// Receives notification of end of element
	public void endElement(String uri, String localName, String qName)
		throws SAXException {
		currTag = false;
				
		if(localName.equalsIgnoreCase("LidIMoti"))
			leaguePositions.add(leaguePos);
	}

	// Receives notification of start of an element
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//Log.i(TAG, "TAG: " + localName);
		
		currTag = true;
		currTagVal = "";
		
		if(localName.equals("LidIMoti")){
			leaguePos = new LeaguePosValue();
		}
		if(localName.equals("HeitiLids")){
			leaguePos.setTeam(attributes.getValue("StuttHeiti"));
		}
		if(localName.equals("LidIMoti")){
			leaguePos.setPosition(attributes.getValue("Rod"));
			leaguePos.setWins(attributes.getValue("Unnir"));
			leaguePos.setDraws(attributes.getValue("Jafntefli"));
			leaguePos.setLosses(attributes.getValue("Tapadir"));
			leaguePos.setGames(null);
			leaguePos.setGoalsFor(attributes.getValue("Skorud"));
			leaguePos.setGoalsAgainst(attributes.getValue("Fengin"));
			leaguePos.setPoints(attributes.getValue("Stig"));
		}
	}
}