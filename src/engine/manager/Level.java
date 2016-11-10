/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package engine.manager;

import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Desktop
 */
public class Level {
    
    SAXParserFactory factory;
    SAXParser saxParser;
    
    public Level(){
        
        factory = SAXParserFactory.newInstance();
        try {
            saxParser = factory.newSAXParser();
        } catch (Exception ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        DefaultHandler handler = new DefaultHandler();
        
    }
}
