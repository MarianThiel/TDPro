package de.hda.tdpro.core.persistence;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import de.hda.tdpro.GameUpgrade;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.enemy.EnemyType;
import de.hda.tdpro.core.enemy.MetaEnemy;
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
        //reloadDefaultConfig();
    }

    public static ConfigWriter getInstance() {
        if(instance == null) instance = new ConfigWriter();
        return instance;
    }

    public void writeDiamonds(int diamonds){

        try {

            Document doc = readConfig();
            Element element = doc.getDocumentElement();
            element.normalize();

            element.setAttribute("diamonds",Integer.toString(diamonds));

            FileWriter output = new FileWriter(DST_PATH);
            writeXml(doc, output);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public void writeMaxTowers(int i){
        try {

            Document doc = readConfig();
            Element element = doc.getDocumentElement();
            element.normalize();

            element.setAttribute("maxtower",Integer.toString(i));

            FileWriter output = new FileWriter(DST_PATH);
            writeXml(doc, output);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public int readMaxTowers(){
        try {
            Document d = readConfig();
            Element element = d.getDocumentElement();
            element.normalize();

            NodeList nodes = element.getElementsByTagName("config");
            Log.println(Log.ASSERT,"DIM", element.getAttribute("maxtower"));
            return Integer.parseInt(element.getAttribute("maxtower"));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void writeInitHealth(int health){

        try {

            Document doc = readConfig();
            Element element = doc.getDocumentElement();
            element.normalize();

            element.setAttribute("health",Integer.toString(health));

            FileWriter output = new FileWriter(DST_PATH);
            writeXml(doc, output);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void writeTowerStats(MetaTower meta, TowerType type){
        try {
            Document d = readConfig();

            Element e1 = d.getDocumentElement();
            e1.normalize();

            NodeList lst = e1.getElementsByTagName("tower");

            for(int i = 0; i < lst.getLength(); i++){
                Node node = lst.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) node;
                    if(e2.getAttribute("type").equals(type.toString())){
                        int dmg = meta.getDmg();
                        int rng = meta.getRange();
                        float vel = meta.getVelocity();
                        int price = meta.getPrice();
                        int mlvl = meta.getMaxLevel();
                        e2.setAttribute("dmg",dmg + "");
                        e2.setAttribute("rng", rng + "");
                        e2.setAttribute("vel", vel + "");
                        e2.setAttribute("price",price + "");
                        e2.setAttribute("maxlevel", mlvl + "");
                        //String name = e2.getAttribute("name");
                        break;
                        //return new MetaTower(name,dmg,rng,vel,price);
                    }
                }
            }
            FileWriter output = new FileWriter(DST_PATH);
            writeXml(d, output);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void writeTowerUpgrade(String key, TowerType type, GlobalTowerUpgrade upgrade){
        try {
            Document d = readConfig();

            Element e1 = d.getDocumentElement();
            e1.normalize();

            NodeList lst = e1.getElementsByTagName("tower");

            for(int i = 0; i < lst.getLength(); i++){
                Node node = lst.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) node;
                    if(e2.getAttribute("type").equals(type.toString())){

                        NodeList upgradeNodes = e2.getElementsByTagName("globalupgrade");

                        for(int j = 0; j < upgradeNodes.getLength(); j++){
                            Node up = upgradeNodes.item(j);
                            if(up.getNodeType() == Node.ELEMENT_NODE){
                                Element e3 = (Element) up;
                                if(e3.getAttribute("key").equals(key)){
                                    e3.setAttribute("current", upgrade.getCurrentLevel() + "");
                                    e3.setAttribute("base", upgrade.getBase() + "");

                                }
                            }
                        }

                        break;

                    }
                }
            }
            FileWriter output = new FileWriter(DST_PATH);
            writeXml(d, output);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
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

    public MetaTower readMetaTowerData(TowerType type){
        try {
            Document d = readConfig();

            Element e1 = d.getDocumentElement();
            e1.normalize();

            NodeList lst = e1.getElementsByTagName("tower");

            for(int i = 0; i < lst.getLength(); i++){
                Node node = lst.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) node;
                    if(e2.getAttribute("type").equals(type.toString())){
                        String name = e2.getAttribute("name");
                        int dmg = Integer.parseInt(e2.getAttribute("dmg"));
                        int rng = Integer.parseInt(e2.getAttribute("rng"));
                        float vel = Float.parseFloat(e2.getAttribute("vel"));
                        int price = Integer.parseInt(e2.getAttribute("price"));
                        int mlvl = Integer.parseInt(e2.getAttribute("maxlevel"));
                        return new MetaTower(name,dmg,rng,vel,price,mlvl);
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MetaEnemy readMetaEnemyData(EnemyType type){
        try {
            Document d = readConfig();

            Element e1 = d.getDocumentElement();
            e1.normalize();

            NodeList lst = e1.getElementsByTagName("enemy");

            for(int i = 0; i < lst.getLength(); i++){
                Node node = lst.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) node;
                    if(e2.getAttribute("type").equals(type.toString())){
                        String name = e2.getAttribute("name");
                        int dmg = Integer.parseInt(e2.getAttribute("hp"));
                        float vel = Float.parseFloat(e2.getAttribute("vel"));
                        int price = Integer.parseInt(e2.getAttribute("value"));
                        return new MetaEnemy(name,dmg,vel,price);
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,GlobalTowerUpgrade> readGlobalTowerUpgrades(TowerType type){
        try {
            Document d = readConfig();

            Element e1 = d.getDocumentElement();
            e1.normalize();
            Map<String,GlobalTowerUpgrade> ulst = new HashMap<>();
            NodeList lst = e1.getElementsByTagName("tower");

            for(int i = 0; i < lst.getLength(); i++){
                Node node = lst.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) node;
                    if(e2.getAttribute("type").equals(type.toString())){
                       lst = e2.getElementsByTagName("globalupgrade");
                       for(int j = 0; j < lst.getLength(); j++){
                           Node n = lst.item(j);
                           if(n.getNodeType() == Node.ELEMENT_NODE){
                               Element e3 = (Element) n;
                               String name = e3.getAttribute("name");
                               String description = e3.getAttribute("name");
                               int current = Integer.parseInt(e3.getAttribute("current"));
                               int max = Integer.parseInt(e3.getAttribute("max"));
                               float value = Float.parseFloat(e3.getAttribute("value"));
                               float multi = Float.parseFloat(e3.getAttribute("multi"));
                               float base = Float.parseFloat(e3.getAttribute("base"));
                               GlobalTowerUpgrade upgrade = new GlobalTowerUpgrade(name,description,current, max, base, value, multi);
                               ulst.put(e3.getAttribute("key"),upgrade);
                           }
                       }
                        return ulst;

                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameUpgrade readGameUpgrade(String key){

        try {
            Document d = readConfig();

            Element e1 = d.getDocumentElement();
            e1.normalize();

            NodeList lst = e1.getElementsByTagName("gameupgrade");

            for(int i = 0; i < lst.getLength(); i++){
                Node n = lst.item(i);
                if(n.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) n;
                    if(e2.getAttribute("key").equals(key)){

                        String k = e2.getAttribute("key");
                        int cost = Integer.parseInt(e2.getAttribute("costs"));
                        int val = Integer.parseInt(e2.getAttribute("value"));
                        float m = Float.parseFloat(e2.getAttribute("multi"));
                        int c = Integer.parseInt(e2.getAttribute("clevel"));

                        return new GameUpgrade(k,cost,val,m,c);

                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeGameUpgrade(GameUpgrade g){
        try {

            Document doc = readConfig();
            Element element = doc.getDocumentElement();
            element.normalize();

            NodeList lst = element.getElementsByTagName("gameupgrade");

            for(int i = 0; i < lst.getLength(); i++){
                Node n = lst.item(i);

                if(n.getNodeType() == Node.ELEMENT_NODE){
                    Element e2 = (Element) n;
                    if(e2.getAttribute("key").equals(g.getKey())){
                        e2.setAttribute("costs",Integer.toString(g.getCosts()));
                        e2.setAttribute("value",Integer.toString(g.getValue()));
                        e2.setAttribute("clevel",Integer.toString(g.getCurrentLevel()));
                        e2.setAttribute("multi",Float.toString(g.getMulti()));
                    }
                }
            }


            FileWriter output = new FileWriter(DST_PATH);
            writeXml(doc, output);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
