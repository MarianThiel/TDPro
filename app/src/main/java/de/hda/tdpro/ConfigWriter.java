package de.hda.tdpro;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaTower;

public class ConfigWriter {

    private final String FILE_PATH = "config/config.xml";
    private final File DST_PATH = new File(StaticContext.getContext().getFilesDir(),"config.xml");
    private final String FORMAT_XSLT = "config";

    private final DocumentBuilderFactory documentBuilderFactory;

    private static ConfigWriter instance;

    private ConfigWriter(){
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
    }

    public static ConfigWriter getInstance() {
        if(instance == null) instance = new ConfigWriter();
        return instance;
    }

    public void writeDiamonds(int diamonds){
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream is = StaticContext.getContext().getAssets().open(FILE_PATH);
            Document doc = readConfig();
                    // documentBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();

            element.setAttribute("diamonds",Integer.toString(diamonds));





            FileWriter output = new FileWriter(DST_PATH);
            writeXml(doc, output);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }


    }

    public void writeInitHealth(int health){

    }

    public void writeTowerStats(MetaTower meta){

    }

    private void writeXml(Document doc,
                                 FileWriter output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // The default add many empty new line, not sure why?
        // https://mkyong.com/java/pretty-print-xml-with-java-dom-and-xslt/
        // Transformer transformer = transformerFactory.newTransformer();


        // add a xslt to remove the extra newlines
        Transformer transformer = transformerFactory.newTransformer();


        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    private Document readConfig() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = null;
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        FileInputStream is = new FileInputStream(DST_PATH);
        return documentBuilder.parse(is);
    }

    public int readDiamonds(){
        try {
            Document d = readConfig();
            Element element = d.getDocumentElement();
            element.normalize();

            NodeList nodes = element.getElementsByTagName("config");
            Log.println(Log.ASSERT,"DIM", element.getAttribute("diamonds"));
            return Integer.parseInt(element.getAttribute("diamonds"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int readHealth(){
        try {
            Document d = readConfig();
            Element element = d.getDocumentElement();
            element.normalize();


            return Integer.parseInt(element.getAttribute("health"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int readGold(){
        try {
            Document d = readConfig();
            Element element = d.getDocumentElement();
            element.normalize();


            return Integer.parseInt(element.getAttribute("gold"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void reloadDefaultConfig(){
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream is = StaticContext.getContext().getAssets().open(FILE_PATH);
            Document doc = documentBuilder.parse(is);

            FileWriter output = new FileWriter(DST_PATH);
            writeXml(doc, output);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
