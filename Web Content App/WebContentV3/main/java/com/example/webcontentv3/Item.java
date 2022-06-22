package com.example.webcontentv3;

/**
 * Encapsulates an item in the XML document that contains a title and link only
 */
public class Item
{
    private String title;
    private String link;
    public Item(String newTitle, String newLink ) {
        setTitle( newTitle );
        setLink( newLink );
    }
    public void setTitle( String newTitle ) { title = newTitle; }
    public void setLink( String newLink ) { link = newLink; }
    public String getTitle( ) {
        return title;
    }
    public String getLink( ) {
        return link;
    }
    public String toString( ) {
        return title + "; " + link;
    }
}
