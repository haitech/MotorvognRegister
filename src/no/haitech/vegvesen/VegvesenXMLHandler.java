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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A XML Parser class for Staten vegvesen REST. 
 * 
 * @author Thomas Le
 * 
 */
public class VegvesenXMLHandler extends DefaultHandler {
    private Vehicle vehicle;
    // Value from tag
    private String tagValue = null;
    // Keep track of tag
    private Boolean endTag = false;
    
    
    // Childs
    private Boolean inBrukstype = false;
    private Boolean inSlagvolum = false;
    
    private Boolean isKjoretoy = false;
    private Boolean isInvalid = false;
    
    
    @Override
    public void startDocument() throws SAXException {
        vehicle = new Vehicle();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        endTag = false;
        
        // Checks if its a valid search, if yes; isValid false.
        if(localName.equals("feilmelding")) {
            isInvalid = true;
        }
        
        // Checks if its valid search.
        if(!isInvalid) {
            if(localName.equals("brukstype")) {
                inBrukstype = true;
            }
            else if(localName.equals("slagvolum")) {
                inSlagvolum = true;
                vehicle.setSlagvolum(new Slagvolum());
            }
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        endTag = true;
        
        // Checks if its valid search
        if(!isInvalid) { 
            if(localName.equals("kjoretoy")) {
                if(tagValue.equals("true")) isKjoretoy = true;
                else isKjoretoy = false;
            }

            // Check if its a car.
            if(isKjoretoy) {
                if(localName.equals("merke")) 
                    vehicle.setMerke(tagValue);
                else if(localName.equals("gruppe")) {
                    if(inBrukstype) {
                        vehicle.setGruppe(tagValue);
                    }
                }
                else if(localName.equals("brukstype"))
                    inBrukstype = false;
                
                
                // Slagvolum
                else if(localName.equals("oppgitt")) {
                    if(inSlagvolum) {
                        vehicle.getSlagvolum().setOppgitt(stringToInt(tagValue));
                    }
                }
                else if(localName.equals("liter")) {
                    if(inSlagvolum) {
                        vehicle.getSlagvolum().setLiter(stringToDouble(tagValue));
                    }
                }
                else if(localName.equals("slagvolum"))
                    inSlagvolum = false;
                
                else if(localName.equals("modell")) 
                    vehicle.setModell(tagValue);
                else if(localName.equals("type"))
                    vehicle.setType(tagValue);
                else if(localName.equals("farge")) 
                    vehicle.setFarge(tagValue);
                else if(localName.equals("forstegangsreg")) 
                    vehicle.setForstegangsreg(stringToDateLong(tagValue));
                else if(localName.equals("registrertEierDato")) 
                    vehicle.setRegistrertEierDato(stringToDateLong(tagValue));
                else if(localName.equals("avregistrertDato")) 
                    vehicle.setAvregistrertDato(stringToDateLong(tagValue));
                else if(localName.equals("eukontrollfrist")) 
                    vehicle.setEukontrollfrist(stringToDateLong(tagValue));
                else if(localName.equals("eukontrollSist")) 
                    vehicle.setEukontrollSist(stringToDateLong(tagValue));
            }
        }
    }
    
    private long stringToDateLong(String s) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", 
                                                                Locale.UK);
            return format.parse(s).getTime();
        }
        catch(ParseException pe) {
            return 0;
        }
    }
    
    private int stringToInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    private double stringToDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(!endTag) {
            tagValue = new String(ch, start, length);
            endTag = true;
        }
    }
    

    
    /**
     * Get the Vehicle Object.
     * 
     * @return if Vehicle exist {@code Vehicle} object, else null.
     */
    public Vehicle getVehicle() {
        return(isInvalid ? null : vehicle);
    }
}
