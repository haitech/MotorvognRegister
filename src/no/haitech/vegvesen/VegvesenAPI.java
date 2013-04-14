/*
 * Copyright (C) 2013 Thomas Le
 * 
 * This file is part of MotorvognRegister.
 *
 * MotorvognRegister is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MotorvognRegister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public license
 * along with MotorvognRegister. If not, see <http://www.gnu.org/licenses/>.
 */
package no.haitech.vegvesen;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Staten vegvesen API
 * Retrieves the vehicle identification number from Staten vegvesen REST.
 * 
 * @author Thomas Le
 * 
 */
public class VegvesenAPI {
    private final String APIURL = "http://www.vegvesen.no/system/mobilapi?"
    		                        + "registreringsnummer=";
    private String vehicleNumber;
    private SAXParserFactory spf;
    private SAXParser sp;
    private XMLReader xr;
    private VegvesenXMLHandler vegvesenHandler;
    private InputStream is;
    
    /**
     * Constructor
     * 
     * @param vehicleNumber the vehicle identification number, XX99999
     */
    public VegvesenAPI(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;     
    }
    
    
    
    /**
     * Open a connection to Staten vegvesen API, and parse the XML, and returns
     * a vehicle object.
     * 
     * @return {@code Vehicle} object with vehicle information.
     * @throws ParserConfigurationException Failed to initialise SAX. 
     * @throws SAXException Failed to parse.
     * @throws IOException Failed to initialise {@code InputStream}.
     */
    public Vehicle getVehicle() throws ParserConfigurationException, 
                SAXException, IOException {        
        is = downloadInfo();

        spf = SAXParserFactory.newInstance();
        sp = spf.newSAXParser();
        xr = sp.getXMLReader();

        vegvesenHandler = new VegvesenXMLHandler();
        xr.setContentHandler(vegvesenHandler);
        xr.parse(new InputSource(is));
        is.close();

        return vegvesenHandler.getVehicle();
    }



    /*
     * Opens a InputStream towards APIURL and retrieves the REST from Staten 
     * vegvesen.
     */
    private InputStream downloadInfo() throws IOException {
        URL url = new URL(APIURL+vehicleNumber); // Add APIURL and vehicleNumber
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // Set timeout
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        // Request method
        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        // Starts the query
        conn.connect();
        // TODO getResponse();
        return conn.getInputStream();

    }
}
