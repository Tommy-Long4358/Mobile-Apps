package com.example.webcontentv2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * A class for XML parsing using a SAXHandler to process various elements of a XML document like start tags
 * end tags, and text between tags
*/
public class SAXHandler extends DefaultHandler {
    // current state of parsing to make sure valid data is being processed
    // Helps filter out white spaces
    private boolean validText;

    // current element
    private String element = "";

    // current Item object being built
    private Item currentItem;

    // ArrayList of Item objects
    private ArrayList<Item> items;

    /**
     *  Empty constructor
     */
    public SAXHandler( ) {
        validText = false;
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() { return items; }

    /** Method for the starting tag like <item> or <link>
     *  Whenever a start tag is encountered, we execute inside the startElement method, and the
     *  value of the startElement parameter is the name of the tag.
     *
     *  We make a new Item object to store this data and call the characters method automatically afterwards.
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

        // Set to True because it gets the starting tag, which is a valid element
        validText = true;

        // Assign element encountered to element variable like a title or a link
        element = startElement;

        if (startElement.equals("item"))
        {
            currentItem = new Item("", "");
        }

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

        // Set to false to signify the end of an element
        validText = false;

        // If the element is an item, the program is done
        // processing the current Item and adds the currentItem to the arrayList
        if (endElement.equals("item"))
        {
            items.add(currentItem);
        }

    }

    /** Method to set the titles or links of the XML document to an Item object.
     *  Between the start and end tags of the same element, we execute inside
     *  the characters method and the array ch stores the characters between the start and end
     *  tags (also called the element contents).
     *
     *  Set the title or link of an object to the data that was retrieved
     *  from startElement method
     *
     * @param ch - stores characters
     * @param start - starting index
     * @param length - number of characters to read from the "ch" variable
     * @throws SAXException
     */
    public void characters( char ch [], int start,
                            int length ) throws SAXException {

        // Process data only when validText is true and the element equals anything but a white space
        if( currentItem != null && element.equals( "title" ) && validText )
        {
            currentItem.setTitle( new String( ch, start, length ) );
        }
        else if( currentItem != null && element.equals( "link" ) && validText )
        {
            currentItem.setLink( new String( ch, start, length ) );
        }

    }
}
