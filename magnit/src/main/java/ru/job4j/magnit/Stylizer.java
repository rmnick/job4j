package ru.job4j.magnit;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;

public class Stylizer {
    public void changeStyle(File source, File result) throws TransformerException {
        String xsl = "<?xml version=\"1.0\"?>\n"
                + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "<xsl:template match=\"/\">\n"
                + "<entries>"
                + "   <xsl:for-each select=\"entries/entry\">\n"
                + "       <entry>"
                + "           <xsl:attribute name=\"field\">"
                + "               <xsl:value-of select=\"field\"/>"
                + "           </xsl:attribute>"
                + "       </entry>\n"
                + "   </xsl:for-each>\n"
                + " </entries>\n"
                + "</xsl:template>\n"
                + "</xsl:stylesheet>\n";
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(
                        new ByteArrayInputStream(xsl.getBytes()))
        );
        System.out.println("transform entries");
        transformer.transform(new StreamSource(source),
                new StreamResult(result)
        );
    }
}