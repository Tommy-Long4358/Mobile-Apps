package com.example.webcontentv0;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

/**
 * A class for XML parsing using a SAXHandler to process various elements of a XML document like start tags
 * end tags, and text between tags
*/
public class SAXHandler extends DefaultHandler {

    /**
     *  Empty constructor
     */
    public SAXHandler( ) {
    }

    /** Method for the starting tag like <item> or <link>
     *  Whenever a start tag is encountered, we execute inside the startElement method, and the
     *  value of the startElement parameter is the name of the tag.
     *
     * @param uri - namespace uri
     * @param localName - local name
     * @param startElement - name of the start tag
     * @param attributes - a list of (name, value) pairs that are found inside the startElement tag
     * @throws SAXException
     */
    public void startElement( String uri, String localName,
                              String startElement, Attributes attributes )
            throws SAXException {
        Log.w( "MainActivity", "Inside startElement, startElement = "
                + startElement );
    }

    /** Method for the starting tag like </item> or </link>
     *  Whenever an end tag is encountered, we execute inside the endElement method, and the
     *  value of the endElement parameter is the name of the tag.
     *
     * @param uri - namespace uri
     * @param localName - local name
     * @param endElement - end tag of the start
     * @throws SAXException
     */
    public void endElement( String uri, String localName,
                            String endElement ) throws SAXException {
        Log.w( "MainActivity", "Inside endElement, endElement = "
                + endElement );
    }

    /** Method for any titles or links like Hello World or www.facebook.com.
     *  Between the start and end tags of the same element, we execute inside
     *  the characters method and the array ch stores the characters between the start and end
     *  tags (also called the element contents).
     *
     *  Between the end tag of an element and the start tag of another element, we execute inside
     *  the characters method and the text only contains white space characters.
     *
     * @param ch - stores characters
     * @param start - starting index
     * @param length - number of characters to read from the "ch" variable
     * @throws SAXException
     */
    public void characters( char ch [], int start,
                            int length ) throws SAXException {
        String text = new String( ch, start, length );
        Log.w( "MainActivity", "Inside characters, text = " + text );
    }
}
