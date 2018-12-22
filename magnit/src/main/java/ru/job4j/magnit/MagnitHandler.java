package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MagnitHandler extends DefaultHandler {
    private int summ = 0;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        for (int i = 0; i < attributes.getLength(); i++) {
            if ("field".equals(attributes.getQName(i))) {
                summ += Integer.valueOf(attributes.getValue(i));
                //System.out.println(attributes.getValue(i));
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(String.format("end; result equals %d\r\n", summ));
    }
}
